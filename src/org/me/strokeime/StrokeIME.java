
package org.me.strokeime;

import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputConnection;
import android.view.KeyEvent;
import android.view.View;
import android.content.res.Resources;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class StrokeIME extends InputMethodService implements InputEventListener {

    private InputView mInputView;
    private int mLastDisplayWidth;

    private ColorTheme mColorThemeDark;
    private ColorTheme mColorThemeLight;

    private LayoutSwitcher mLayoutSwitcher;

    private int mShiftState;

    // _ - not a word separator
    private String mWordSeparators = " \t\n\r`~!@#$%^&*()+-=[]{}|\\;:'\"<>,./?";
    private String mWordSeparatorsForLayoutSwitch = " \t\n\r";

    /**
     * Main initialization of the input method component.  Be sure to call
     * to super class.
     */
    @Override public void onCreate() {
        super.onCreate();

        Resources r = getResources();
        mColorThemeDark = new ColorTheme(
                r.getColor(R.color.dark_bg),
                r.getColor(R.color.dark_fg),
                r.getColor(R.color.dark_txt),
                r.getColor(R.color.dark_txt_hot),
                r.getColor(R.color.dark_txt_back)
                );
        mColorThemeLight = new ColorTheme(
                r.getColor(R.color.light_bg),
                r.getColor(R.color.light_fg),
                r.getColor(R.color.light_txt),
                r.getColor(R.color.light_txt_hot),
                r.getColor(R.color.light_txt_back)
                );

        mLayoutSwitcher = new LayoutSwitcher();

        mShiftState = Layout.SHIFT_OFF;
    }
    
    /**
     * This is the point where you can do all of your UI initialization.  It
     * is called after creation and any configuration change.
     */
    @Override public void onInitializeInterface() {
        /*if (mQwertyKeyboard != null) {
            // Configuration changes can happen after the keyboard gets recreated,
            // so we need to be able to re-build the keyboards if the available
            // space has changed.
            int displayWidth = getMaxWidth();
            if (displayWidth == mLastDisplayWidth) return;
            mLastDisplayWidth = displayWidth;
        }*/
    }

    /**
     * Called by the framework when your view for creating input needs to
     * be generated.  This will be called the first time your input method
     * is displayed, and every time it needs to be re-created such as due to
     * a configuration change.
     */
    @Override public View onCreateInputView() {
        mInputView = new InputView(this);
        mInputView.setInputEventListener(this);

        // TODO: set prefered color theme
        mInputView.setColorTheme(mColorThemeDark);
        updateView();

        return mInputView;
    }

    /**
     * Called by the framework when your view for showing candidates needs to
     * be generated, like {@link #onCreateInputView}.
     */
    @Override public View onCreateCandidatesView() {
        return null;
    }

    private final void updateView() {
        mInputView.setLayout(mLayoutSwitcher.getCurrentLayout(), mShiftState);
    }

    private final void processShift() {
        switch(mShiftState) {
            case Layout.SHIFT_LOCK:
                mShiftState = Layout.SHIFT_OFF;
                break;
            case Layout.SHIFT_ON:
                mShiftState = Layout.SHIFT_LOCK;
                break;
            case Layout.SHIFT_OFF:
                mShiftState = Layout.SHIFT_ON;
                break;
        }
        updateView();
    }

    private final void deleteWord(InputConnection c) {
        int cnt = 0, i = cnt, l;

        CharSequence t;
        while(i == cnt) { // while all the characters read are separators
            cnt += 100;
            t = c.getTextBeforeCursor(cnt, 0);
            l = t.length(); // it may differ from cnt
            for(i = 0; i < l && i < cnt; ++i) {
                if(mWordSeparators.indexOf(t.charAt(l-1-i)) != -1) {
                    break;
                }
            }
        }

        // delete at least one character
        if(i == 0) i = 1;

        c.deleteSurroundingText(i, 0);
    }

    // TODO: beautify
    public final void onInput(InputEvent event) {
        boolean wasInput = false;
        InputConnection c = getCurrentInputConnection();
        
        if(event.action == null)
            return;

        switch(event.action.actionType) {
            case Action.TYPE_TEXT:
                c.commitText("", 0); // clear selection if any
                c.commitText(event.action.value, 0);
                wasInput = true;
                break;
            case Action.TYPE_CODE:
                // Otherwise, perform some action AND release shift if necessary
                switch(event.action.code) {
                    case KeyEvent.KEYCODE_SHIFT_LEFT:
                        processShift();
                        break;
                    case KeyEvent.KEYCODE_SHIFT_RIGHT:
                        processShift();
                        break;
                    case Layout.KEYCODE_DEL_WORD:
                        wasInput = true;
                        deleteWord(c);
                        break;
                    default:
                        wasInput = true;
                        c.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, event.action.code));
                        c.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,   event.action.code));
                        break;
                }
                break;
            case Action.TYPE_LAYOUT:
                mLayoutSwitcher.changeLayout(event.action.value);
                updateView();
                break;
        }

        if(wasInput) {
            if(mShiftState == Layout.SHIFT_ON) {
                mShiftState = Layout.SHIFT_OFF;
                updateView();
            }
            if(mLayoutSwitcher.getCurrentLayout().type == Layout.TYPE_SECONDARY_CHAR) {
                mLayoutSwitcher.backToPrimary();
                updateView();
            } else if(mLayoutSwitcher.getCurrentLayout().type == Layout.TYPE_SECONDARY_WORD) {
                CharSequence s = c.getTextBeforeCursor(1, 0);
                if(s.length() == 1 && mWordSeparatorsForLayoutSwitch.indexOf(s.charAt(0)) != -1) {
                    mLayoutSwitcher.backToPrimary();
                    updateView();
                }
            }
        }
    }
}
