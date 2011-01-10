
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.Action;
import static org.me.strokeime.GliphBank.*;
import static android.view.KeyEvent.*;

public class LayoutNum extends LayoutBase {
    @Override protected String getTitle()          { return "Numbers and symbols"; }
    @Override protected String getName()           { return "num"; }
    @Override protected String getLabelPrimary()   { return "?123"; }
    //@Override protected int    getType()           { return TYPE_SECONDARY_WORD; }
    @Override protected int    getType()           { return TYPE_SECONDARY; }

    @Override
    protected void initializeLayout() {
        super.initializeLayout();

        l(LT, OT, Action.LAYOUT_BACK_TO_PRIMARY);
        l(MT, OT, "diacritic");
        l(RT, OT, Action.LAYOUT_NEXT_PRIMARY);
        l(RB, OT, Action.LAYOUT_PREV_PRIMARY);

        t(MC, LT, "5", "5");
        t(MC, MT, "0", "0");
        t(MC, RT, "1", "1");
        // MC-MC is SPACE
        t(MC, LB, "4", "4");
        t(MC, MB, "3", "3");
        t(MC, RB, "2", "2");

        // LT-LT is SHIFT
        c(LT, MT, KEYCODE_TAB, GLIPH_TAB, GLIPH_TAB); 
        t(LT, RT, "[", "£");
        t(LT, MC, "%", "%");
        t(LT, LB, "{", "¢");
        t(LT, MB, "°", "Π");
        t(LT, RB, "(", "€");

        t(MT, LT, "<", "÷");
        t(MT, MT, "6", "6");
        t(MT, RT, ">", "×");
        t(MT, MC, "*", "•");
        t(MT, LB, "/", "¶");
        // MT-MB is !
        t(MT, RB, "\\", "\\");

        t(RT, LT, "]", "™");
        t(RT, MT, "№", "№");
        // RT-RT is BACKSPACE
        //t(RT, MC, "", "");
        t(RT, LB, ")", "®");
        // RT-MB is ENTER
        t(RT, RB, "}", "©");

        t(LB, LT, "+", "+");
        // LB-MT is -
        t(LB, RT, "_", "…");
        t(LB, MC, "#", "#");
        t(LB, LB, "9", "9");
        t(LB, MB, "~", "~");
        t(LB, RB, "@", "@");

        t(MB, LT, "'", "`");
        // MB-MT is ?
        t(MB, RT, "\"", "„");
        t(MB, MC, "&", "&");
        t(MB, LB, "^", "^");
        t(MB, MB, "8", "8");
        t(MB, RB, "$", "$");

        t(RB, LT, ";", ";");
        t(RB, MT, ":", ":");
        //RB-RT is ,
        t(RB, MC, "=", "=");
        t(RB, LB, "|", "√");
        //RB-MB is .
        t(RB, RB, "7", "7");
    }
}
