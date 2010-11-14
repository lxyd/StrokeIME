
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutNum extends Layout {
    @Override
    protected void initialize() {
        c(RT, MB, KEYCODE_ENTER, new GliphEnter(), new GliphEnter());
        c(RT, RT, KEYCODE_DEL, new GliphBackspace(), new GliphBackspace()); 
        s(MT, RT, " ", " ", "", "");
        c(LT, RT, KEYCODE_TAB, new GliphTab(), new GliphTab()); 

        c(LT, LT, KEYCODE_SHIFT_LEFT, new GliphShift(), new GliphShift(), new GliphShiftLock());

        s(MC, MC, " ", " ", new GliphSpace(), new GliphSpace());
        s(MC, LT, "1", "1");
        s(MC, MT, "2", "2");
        s(MC, RT, "3", "3");
        s(MC, LB, "4", "4");
        s(MC, MB, "5", "5");
        s(MC, RB, "6", "6");
        s(LT, MT, "7", "7");
        s(LT, MC, "8", "8");
        s(LT, LB, "9", "9");
        s(LT, MB, "0", "0");
        l(LT, OT, "en", "en");
    }
}
