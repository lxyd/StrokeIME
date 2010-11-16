
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutBase extends Layout {
    @Override
    protected void initialize() {
        c(RT, MB, KEYCODE_ENTER, new GliphEnter(), new GliphEnter());
        c(RT, RT, KEYCODE_DEL, new GliphBackspace(), new GliphBackspace()); 
        c(LT, RT, KEYCODE_TAB, new GliphTab(), new GliphTab()); 
        c(LT, LT, KEYCODE_SHIFT_LEFT, new GliphShift(), new GliphShift(), new GliphShiftLock()); 
        s(MC, MC, " ", " ", new GliphSpace(), new GliphSpace());
    }
}
