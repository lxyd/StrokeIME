
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.Action;
import static org.me.strokeime.GliphBank.*;
import static android.view.KeyEvent.*;

public class LayoutEn extends LayoutBase {
    @Override protected String getTitle()          { return "English"; }
    @Override protected String getName()           { return "en"; }
    @Override protected String getLabelPrimary()   { return "EN"; }
    @Override protected String getLabelSecondary() { return "ABC"; }

    @Override
    protected void initializeLayout() {
        super.initializeLayout();

        l(LT, OT, "num");
        l(MT, OT, "diacritic");
        l(RT, OT, Action.LAYOUT_NEXT_PRIMARY);
        l(RB, OT, Action.LAYOUT_PREV_PRIMARY);

        t(MC, LT, "a", "A");
        t(MC, MT, "o", "O");
        t(MC, RT, "e", "E");
        // MC-MC is SPACE
        t(MC, LB, "u", "U");
        t(MC, MB, "i", "I");
        t(MC, RB, "h", "H");

        // LT-LT is SHIFT
        t(LT, MT, "y", "Y");
        t(LT, RT, "[", "[");
        t(LT, MC, "b", "B");
        t(LT, LB, "q", "Q");
        t(LT, MB, "–", "–");
        t(LT, RB, "(", "(");

        t(MT, LT, "j", "J");
        t(MT, MT, "p", "P");
        t(MT, RT, "w", "W");
        t(MT, MC, "d", "D");
        t(MT, LB, "/", "/");
        // MT-MB is !
        t(MT, RB, "\\", "\\");

        t(RT, LT, "]", "]");
        t(RT, MT, "v", "V");
        // RT-RT is BACKSPACE
        t(RT, MC, "k", "K");
        t(RT, LB, ")", ")");
        // RT-MB is ENTER
        t(RT, RB, "z", "Z");

        t(LB, LT, "c", "C");
        t(LB, MT, "-", "—", "-");
        t(LB, RT, "_", "_");
        t(LB, MC, "g", "G");
        t(LB, LB, "s", "S");
        t(LB, MB, "r", "R");
        t(LB, RB, "@", "@");

        t(MB, LT, "'", "“", "'");
        // MB-MT is ?
        t(MB, RT, "\"", "”", "\"");
        t(MB, MC, "m", "M");
        t(MB, LB, "l", "L");
        t(MB, MB, "t", "T");
        t(MB, RB, "f", "F");

        t(RB, LT, ";", ";");
        t(RB, MT, ":", ":");
        //RB-RT is ,
        t(RB, MC, "x", "X");
        //t(RB, LB, "", "");
        //RB-MB is .
        t(RB, RB, "n", "N");
    }
}
