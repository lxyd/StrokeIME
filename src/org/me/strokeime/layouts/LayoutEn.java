
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutEn extends LayoutBase {
    @Override
    protected void initialize() {
        super.initialize();

        l(LT, OT, "num-en", "?123");
        l(RT, OT, "ru", "RU");

        s(MC, LT, "a", "A");
        s(MC, MT, "o", "O");
        s(MC, RT, "e", "E");
        // MC-MC is SPACE
        s(MC, LB, "u", "U");
        s(MC, MB, "i", "I");
        s(MC, RB, "h", "H");

        // LT-LT is SHIFT
        s(LT, MT, "y", "Y");
        // LT-RT is TAB
        s(LT, MC, "b", "B");
        s(LT, LB, "q", "Q");
        //s(LT, MB, "", "");
        s(LT, RB, "(", "(");

        s(MT, LT, "j", "J");
        s(MT, MT, "d", "D");
        s(MT, RT, "w", "W");
        s(MT, MC, "p", "P");
        s(MT, LB, "/", "/");
        // MT-MB is !
        s(MT, RB, "\\", "\\");

        //s(RT, LT, "", "");
        s(RT, MT, "v", "V");
        // RT-RT is BACKSPACE
        s(RT, MC, "k", "K");
        s(RT, LB, ")", ")");
        // RT-MB is ENTER
        s(RT, RB, "z", "Z");

        s(LB, LT, "c", "C");
        // LB-MT is -
        s(LB, RT, "_", "_");
        s(LB, MC, "g", "G");
        s(LB, LB, "s", "S");
        s(LB, MB, "r", "R");
        s(LB, RB, "@", "@");

        s(MB, LT, "'", "'");
        // MB-MT is ?
        s(MB, RT, "\"", "\"");
        s(MB, MC, "m", "M");
        s(MB, LB, "l", "L");
        s(MB, MB, "t", "T");
        s(MB, RB, "f", "F");

        s(RB, LT, ";", ";");
        s(RB, MT, ":", ":");
        //RB-RT is ,
        s(RB, MC, "x", "X");
        //s(RB, LB, "", "");
        //RB-MB is .
        s(RB, RB, "n", "N");
    }
}
