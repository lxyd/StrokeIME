
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutEn extends LayoutBase {
    @Override
    protected void initialize() {
        super.initialize();

        s(MC, LT, "a", "A");
        s(MC, MT, "o", "O");
        s(MC, RT, "e", "E");
        // MC-MC is SPACE
        s(MC, LB, "u", "U");
        s(MC, MB, "i", "I");
        s(MC, RB, "s", "S");

        // LT-LT is SHIFT
        //s(LT, MT, "p", "P");
        // LT-RT is TAB
        s(LT, MC, "b", "B");
        s(LT, LB, "q", "Q");
        //s(LT, MB, "d", "D");
        //s(LT, RB, "@", "@");

        s(MT, LT, "j", "J");
        s(MT, MT, "d", "D");
        s(MT, RT, "w", "W");
        s(MT, MC, "p", "P");
        //s(MT, LB, "i", "I");
        //s(MT, MB, "j", "J");
        //s(MT, RB, "k", "K");

        //s(RT, LT, "l", "L");
        //s(RT, MT, "k", "K");
        // RT-RT is BACKSPACE
        s(RT, MC, "k", "K");
        //s(RT, LB, "?", "?");
        // RT-MB is ENTER
        //s(RT, RB, "p", "P");

        //s(LB, LT, "q", "Q");
        //s(LB, MT, "r", "R");
        //s(LB, RT, "-", "-");
        s(LB, MC, "g", "G");
        s(LB, LB, "h", "H");
        //s(LB, MB, "g", "G");
        //s(LB, RB, "w", "W");

        //s(MB, LT, "x", "X");
        //s(MB, MT, "y", "Y");
        //s(MB, RT, "z", "Z");
        s(MB, MC, "m", "M");
        //s(MB, LB, "_", "_");
        s(MB, MB, "t", "T");
        //s(MB, RB, ",", ",");

        //s(RB, LT, "'", "'");
        //s(RB, MT, "\"", "\"");
        //s(RB, RT, "/", "/");
        s(RB, MC, "y", "Y");
        //s(RB, LB, "(", "(");
        //s(RB, MB, "m", "M");
        s(RB, RB, "n", "N");
    }
}
