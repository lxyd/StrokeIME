
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import static android.view.KeyEvent.*;

public class LayoutLat extends Layout {
    @Override
    protected void initialize() {
        c(RT, RB, KEYCODE_ENTER, "\u23CE", "\u23CE");

        s(MC, LT, "a", "A");
        s(MC, MT, "b", "B");
        s(MC, RT, "c", "C");
        s(MC, LB, "d", "D");
        s(MC, MB, "e", "E");
        s(MC, RB, "f", "F");
    }
}
