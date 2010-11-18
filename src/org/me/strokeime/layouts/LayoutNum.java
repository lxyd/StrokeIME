
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutNum extends LayoutBase {
    @Override
    protected void initialize() {
        super.initialize();

        s(MC, LT, "5", "5");
        s(MC, MT, "0", "0");
        s(MC, RT, "1", "1");
        // MC-MC is SPACE
        s(MC, LB, "4", "4");
        s(MC, MB, "3", "3");
        s(MC, RB, "2", "2");

        // LT-LT is SHIFT
        s(LT, MT, "{", "{");
        c(LT, RT, KEYCODE_TAB, new GliphTab(), new GliphTab()); 
        s(LT, MC, "%", "%");
        s(LT, LB, "[", "[");
        //s(LT, MB, "", "");
        s(LT, RB, "(", "(");

        s(MT, LT, "<", "<");
        s(MT, MT, "6", "6");
        s(MT, RT, ">", ">");
        s(MT, MC, "&", "&");
        s(MT, LB, "/", "/");
        // MT-MB is !
        s(MT, RB, "~", "\\");

        //s(RT, LT, "", "");
        s(RT, MT, "}", "}");
        // RT-RT is BACKSPACE
        //s(RT, MC, "", "");
        s(RT, LB, ")", ")");
        // RT-MB is ENTER
        s(RT, RB, "]", "]");

        s(LB, LT, "+", "+");
        // LB-MT is -
        s(LB, RT, "_", "_");
        s(LB, MC, "#", "#");
        s(LB, LB, "9", "9");
        //s(LB, MB, "", "");
        s(LB, RB, "@", "@");

        s(MB, LT, "'", "'");
        // MB-MT is ?
        s(MB, RT, "\"", "\"");
        s(MB, MC, "*", "*");
        s(MB, LB, "^", "^");
        s(MB, MB, "8", "8");
        s(MB, RB, "$", "$");

        s(RB, LT, ";", ";");
        s(RB, MT, ":", ":");
        //RB-RT is ,
        s(RB, MC, "=", "=");
        s(RB, LB, "|", "|");
        //RB-MB is .
        s(RB, RB, "7", "7");
    }
}
