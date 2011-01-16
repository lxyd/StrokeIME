/*
    Copyright (C) 2011 Alexey Dubinin 

    This file is part of StrokeIME, an alternative input method for Android OS

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.Action;
import static org.me.strokeime.GliphBank.*;
import static android.view.KeyEvent.*;

public class LayoutNum extends Layout {
    @Override protected String getTitle()          { return "Numbers and symbols"; }
    @Override protected String getName()           { return "num"; }
    @Override protected String getLabelPrimary()   { return "?123"; }
    //@Override protected int    getType()           { return TYPE_SECONDARY_WORD; }
    @Override protected int    getType()           { return TYPE_SECONDARY; }

    @Override
    protected void initializeLayout() {
        l(LT, OT, Action.LAYOUT_BACK_TO_PRIMARY);
        l(MT, OT, "diacritic");
        l(RT, OT, Action.LAYOUT_NEXT_PRIMARY);
        l(RB, OT, Action.LAYOUT_PREV_PRIMARY);

        t(MC, LT, "5", "5");
        t(MC, MT, "0", "0");
        t(MC, RT, "1", "1");
        t(MC, MC, " ", " ", GLIPH_SPACE, GLIPH_SPACE);
        t(MC, LB, "4", "4");
        t(MC, MB, "3", "3");
        t(MC, RB, "2", "2");

        c(LT, LT, KEYCODE_SHIFT_LEFT, GLIPH_SHIFT, GLIPH_SHIFT, GLIPH_SHIFT_LOCK); 
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
        t(MT, MB, "!", "!");
        t(MT, RB, "\\", "\\");

        t(RT, LT, "]", "™");
        t(RT, MT, "№", "№");
        c(RT, RT, KEYCODE_DEL, KEYCODE_DEL_WORD, KEYCODE_DEL, GLIPH_DEL, GLIPH_DEL_WORD, GLIPH_DEL); 
        //t(RT, MC, "", "");
        t(RT, LB, ")", "®");
        c(RT, MB, KEYCODE_ENTER, GLIPH_ENTER, GLIPH_ENTER);
        t(RT, RB, "}", "©");

        t(LB, LT, "+", "+");
        t(LB, MT, "-", "-");
        t(LB, RT, "_", "…");
        t(LB, MC, "#", "#");
        t(LB, LB, "9", "9");
        t(LB, MB, "~", "~");
        t(LB, RB, "@", "@");

        t(MB, LT, "'", "`");
        t(MB, MT, "?", "?");
        t(MB, RT, "\"", "„");
        t(MB, MC, "&", "&");
        t(MB, LB, "^", "^");
        t(MB, MB, "8", "8");
        t(MB, RB, "$", "$");

        t(RB, LT, ";", ";");
        t(RB, MT, ":", ":");
        t(RB, RT, ",", ",");
        t(RB, MC, "=", "=");
        t(RB, LB, "|", "√");
        t(RB, MB, ".", ".");
        t(RB, RB, "7", "7");
    }
}
