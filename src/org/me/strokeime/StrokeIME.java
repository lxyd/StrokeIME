package org.me.strokeime;

import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputConnection;
import android.view.KeyEvent;
import android.view.View;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class StrokeIME extends InputMethodService implements InputEventListener
{
    private InputView mInputView;
    private int mLastDisplayWidth;
    private Action[][] mActionTableEn;
    // TODO: add action tables for other layouts

    /**
     * Main initialization of the input method component.  Be sure to call
     * to super class.
     */
    @Override public void onCreate() {
        super.onCreate();
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
        if (mActionTableEn == null) {
            try {
                BufferedReader r = new BufferedReader(
                        new InputStreamReader(getResources().openRawResource(R.raw.layout_en),"UTF8"));
                mActionTableEn = Util.loadActionTable(r);
                r.close();
            } catch (IOException ex) {
                // TODO: log error
            }
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
        mInputView.setColors(getResources().getColor(R.color.input_foreground_dark), getResources().getColor(R.color.input_background_dark));
        mInputView.setInputEventListener(this);
        mInputView.setActionTable(mActionTableEn);
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

        if(event.action.actionType == Action.ACTION_TEXT) {
            c.commitText(event.action.value, 0);
        }
    }
}
