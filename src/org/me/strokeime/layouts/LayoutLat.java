
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutLat extends Layout {
    @Override
    protected void initialize() {
        //c(RT, MB, KEYCODE_ENTER, "⏎", "⏎");
        //c(RT, MT, KEYCODE_DEL, "⇐", "⇐");
        c(RT, MB, KEYCODE_ENTER, new GliphEnter(), new GliphEnter());
        c(RT, RT, KEYCODE_DEL, new GliphBackspace(), new GliphBackspace()); 
        s(MT, RT, " ", " ", "", "");
        c(LT, RT, KEYCODE_TAB, "⇒", "⇒"); 

        s(MC, MC, " ", " ", new GliphSpace(), new GliphSpace());
        s(MC, LT, "a", "A");
        s(MC, MT, "b", "B");
        s(MC, RT, "c", "C");
        s(MC, LB, "d", "D");
        s(MC, MB, "e", "E");
        s(MC, RB, "f", "F");
        s(LT, MT, "a", "A");
        s(LT, MC, "b", "B");
        s(LT, LB, "c", "C");
        s(LT, MB, "d", "D");
        s(LT, RB, "e", "E");
        l(LT, OT, "num", "123?");

        c(MC, LT, KEYCODE_A, "a", "A");
    }
}
