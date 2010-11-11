
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import static android.view.KeyEvent.*;

public class LayoutLat extends Layout {
    @Override
    protected void initialize() {
        c(RT, RB, KEYCODE_ENTER, "⏎", "⏎");
        c(RT, MT, KEYCODE_DEL, "⇐", "⇐");
        s(MT, RT, " ", " ", "", "");
        c(LT, RT, KEYCODE_TAB, "⇒", "⇒"); 

        s(MC, LT, "a", "A");
        s(MC, MT, "b", "B");
        s(MC, RT, "c", "C");
        s(MC, LB, "d", "D");
        s(MC, MB, "e", "E");
        s(MC, RB, "f", "F");
        s(LT, MT, "ы", "Ы");

        c(MC, LT, KEYCODE_A, "a", "A");
    }
}
