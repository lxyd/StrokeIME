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

package org.strokeime.layouts;

import org.strokeime.Layout;
import org.strokeime.Action;
import static org.strokeime.GliphBank.*;
import static android.view.KeyEvent.*;

public class LayoutEn extends Layout {
    @Override protected String getTitle()          { return "English"; }
    @Override protected String getName()           { return "en"; }
    @Override protected String getLabelPrimary()   { return "EN"; }
    @Override protected String getLabelSecondary() { return "ABC"; }

    @Override
    protected void initializeLayout() {
        l(LT, OT, "num");
        l(MT, OT, "diacritic");
        l(RT, OT, Action.LAYOUT_NEXT_PRIMARY);
        l(RB, OT, Action.LAYOUT_PREV_PRIMARY);

        t(MC, LT, "a", "A");
        t(MC, MT, "o", "O");
        t(MC, RT, "e", "E");
        t(MC, MC, " ", " ", GLIPH_SPACE, GLIPH_SPACE);
        t(MC, LB, "u", "U");
        t(MC, MB, "i", "I");
        t(MC, RB, "y", "Y");

        c(LT, LT, KEYCODE_SHIFT_LEFT, GLIPH_SHIFT, GLIPH_SHIFT, GLIPH_SHIFT_LOCK); 
        t(LT, MT, "h", "H");
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
        t(MT, MB, "!", "!");
        t(MT, RB, "\\", "\\");

        t(RT, LT, "]", "]");
        t(RT, MT, "v", "V");
        c(RT, RT, KEYCODE_DEL, KEYCODE_DEL_WORD, KEYCODE_DEL, GLIPH_DEL, GLIPH_DEL_WORD, GLIPH_DEL); 
        t(RT, MC, "k", "K");
        t(RT, LB, ")", ")");
        c(RT, MB, KEYCODE_ENTER, GLIPH_ENTER, GLIPH_ENTER);
        t(RT, RB, "z", "Z");

        t(LB, LT, "c", "C");
        t(LB, MT, "-", "—", "-");
        t(LB, RT, "_", "_");
        t(LB, MC, "g", "G");
        t(LB, LB, "s", "S");
        t(LB, MB, "r", "R");
        t(LB, RB, "@", "@");

        t(MB, LT, "'", "“", "'");
        t(MB, MT, "?", "?");
        t(MB, RT, "\"", "”", "\"");
        t(MB, MC, "m", "M");
        t(MB, LB, "l", "L");
        t(MB, MB, "t", "T");
        t(MB, RB, "f", "F");

        t(RB, LT, ";", ";");
        t(RB, MT, ":", ":");
        t(RB, RT, ",", ",");
        t(RB, MC, "x", "X");
        //t(RB, LB, "", "");
        t(RB, MB, ".", ".");
        t(RB, RB, "n", "N");
    }
}
