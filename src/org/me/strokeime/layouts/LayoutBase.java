
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutBase extends Layout {
    @Override
    protected void initialize() {
        c(RT, MB, KEYCODE_ENTER, new GliphEnter(), new GliphEnter());
        // KEYCODE_DEL_WORD is defined in the Layout class, not in KeyEvent
        c(RT, RT, KEYCODE_DEL, KEYCODE_DEL_WORD, KEYCODE_DEL, new GliphBackspace(), new GliphBackword(), new GliphBackspace()); 
        c(LT, LT, KEYCODE_SHIFT_LEFT, new GliphShift(), new GliphShift(), new GliphShiftLock()); 

        s(MC, MC, " ", " ", new GliphSpace(), new GliphSpace());
        s(MT, MB, "!", "!");
        s(MB, MT, "?", "?");
        s(LB, MT, "-", "-");
        s(RB, RT, ",", ",");
        s(RB, MB, ".", ".");
    }
}
