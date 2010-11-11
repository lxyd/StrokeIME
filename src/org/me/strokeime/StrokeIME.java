
package org.me.strokeime;

import org.me.strokeime.layouts.*;
import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputConnection;
import android.view.KeyEvent;
import android.view.View;
import android.content.res.Resources;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class StrokeIME extends InputMethodService implements InputEventListener
{
    private InputView mInputView;
    private int mLastDisplayWidth;
    private Layout mLayoutLat;
    private ColorTheme colorThemeDark;
    private ColorTheme colorThemeLight;
    // TODO: add action tables for other layouts

    /**
     * Main initialization of the input method component.  Be sure to call
     * to super class.
     */
    @Override public void onCreate() {
        super.onCreate();

        Resources r = getResources();
        colorThemeDark = new ColorTheme(
                r.getColor(R.color.input_background_dark),
                r.getColor(R.color.input_foreground_dark),
                r.getColor(R.color.input_bright_dark),
                r.getColor(R.color.input_dimmed_dark)
                );
        colorThemeLight = new ColorTheme(
                r.getColor(R.color.input_background_light),
                r.getColor(R.color.input_foreground_light),
                r.getColor(R.color.input_bright_light),
                r.getColor(R.color.input_dimmed_light)
                );
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
        if (mLayoutLat == null) {
            mLayoutLat = new LayoutLat();
        }
    }

    /**
     * Called by the framework when your view for creating input needs to
     * be generated.  This will be called the first time your input method
     * is displayed, and every time it needs to be re-created such as due to
     * a configuration change.
     */
    @Override public View onCreateInputView() {
        mInputView = new InputView(this);

        mInputView.setColorTheme(colorThemeDark);
        mInputView.setInputEventListener(this);
        mInputView.setLayout(mLayoutLat, Layout.SHIFT_DOWN);
        return mInputView;
    }

    /**
     * Called by the framework when your view for showing candidates needs to
     * be generated, like {@link #onCreateInputView}.
     */
    @Override public View onCreateCandidatesView() {
        return null;
    }

    public void onInput(InputEvent event) {
        //android.content.Context context = getApplicationContext();
        //android.widget.Toast toast = android.widget.Toast.makeText(context, "HELLO!", android.widget.Toast.LENGTH_SHORT);
        //toast.show();
        InputConnection c = getCurrentInputConnection();
        
        if(event.action == null)
            return; // TODO: better action

        switch(event.action.actionType) {
            case Action.TYPE_TEXT:
                c.commitText(event.action.value, 0);
                break;
            case Action.TYPE_CODE:
                if(event.action.keyCode == KeyEvent.KEYCODE_A){
                    mInputView.setColorTheme(colorThemeLight);
                }
                c.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
                c.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, event.action.keyCode));
                c.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, event.action.keyCode));
                c.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));
                break;
            case Action.TYPE_LAYOUT:
                break;
        }
    }
}
