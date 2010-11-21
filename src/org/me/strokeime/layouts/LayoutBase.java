
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import static org.me.strokeime.GliphBank.*;
import static android.view.KeyEvent.*;

public abstract class LayoutBase extends Layout {
    @Override
    protected void initializeLayout() {
        c(RT, MB, KEYCODE_ENTER, GLIPH_ENTER, GLIPH_ENTER);
        // KEYCODE_DEL_WORD is defined in the Layout class, not in KeyEvent
        c(RT, RT, KEYCODE_DEL, KEYCODE_DEL_WORD, KEYCODE_DEL, GLIPH_DEL, GLIPH_DEL_WORD, GLIPH_DEL); 
        c(LT, LT, KEYCODE_SHIFT_LEFT, GLIPH_SHIFT, GLIPH_SHIFT, GLIPH_SHIFT_LOCK); 

        t(MC, MC, " ", " ", GLIPH_SPACE, GLIPH_SPACE);
        t(MT, MB, "!", "!");
        t(MB, MT, "?", "?");
        t(LB, MT, "-", "-");
        t(RB, RT, ",", ",");
        t(RB, MB, ".", ".");
    }
}
