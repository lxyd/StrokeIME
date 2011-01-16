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

public class LayoutRu extends Layout {
    @Override protected String getTitle()          { return "Русский язык"; }
    @Override protected String getName()           { return "ru"; }
    @Override protected String getLabelPrimary()   { return "RU"; }
    @Override protected String getLabelSecondary() { return "АБВ"; }
    @Override protected String getLocalesString()  { return "ru_RU";   }

    @Override
    protected void initializeLayout() {
        l(LT, OT, "num");
        l(MT, OT, "diacritic");
        l(RT, OT, Action.LAYOUT_NEXT_PRIMARY);
        l(RB, OT, Action.LAYOUT_PREV_PRIMARY);

        t(MC, LT, "а", "А");
        t(MC, MT, "о", "О");
        t(MC, RT, "е", "Е");
        t(MC, MC, " ", " ", GLIPH_SPACE, GLIPH_SPACE);
        t(MC, LB, "у", "У");
        t(MC, MB, "и", "И");
        t(MC, RB, "я", "Я");

        c(LT, LT, KEYCODE_SHIFT_LEFT, GLIPH_SHIFT, GLIPH_SHIFT, GLIPH_SHIFT_LOCK); 
        t(LT, MT, "ю", "Ю");
        t(LT, RT, "ы", "Ы");
        t(LT, MC, "б", "Б");
        t(LT, LB, "й", "Й");
        t(LT, MB, "ч", "Ч");
        t(LT, RB, "(", "(");

        t(MT, LT, "ж", "Ж");
        t(MT, MT, "п", "П");
        t(MT, RT, "ш", "Ш");
        t(MT, MC, "д", "Д");
        t(MT, LB, "/", "\\", "/");
        t(MT, MB, "!", "!");
        t(MT, RB, "э", "Э");

        t(RT, LT, "ь", "Ь");
        t(RT, MT, "в", "В");
        c(RT, RT, KEYCODE_DEL, KEYCODE_DEL_WORD, KEYCODE_DEL, GLIPH_DEL, GLIPH_DEL_WORD, GLIPH_DEL); 
        t(RT, MC, "к", "К");
        t(RT, LB, ")", ")");
        c(RT, MB, KEYCODE_ENTER, GLIPH_ENTER, GLIPH_ENTER);
        t(RT, RB, "з", "З");

        t(LB, LT, "ц", "Ц");
        t(LB, MT, "-", "—", "-");
        t(LB, RT, "ё", "Ё");
        t(LB, MC, "г", "Г");
        t(LB, LB, "с", "С");
        t(LB, MB, "р", "Р");
        t(LB, RB, "щ", "Щ");

        t(MB, LT, "«", "'", "«");
        t(MB, MT, "?", "?");
        t(MB, RT, "»", "\"", "»");
        t(MB, MC, "м", "М");
        t(MB, LB, "л", "Л");
        t(MB, MB, "т", "Т");
        t(MB, RB, "ф", "Ф");

        t(RB, LT, ";", ";");
        t(RB, MT, ":", ":");
        t(RB, RT, ",", ",");
        t(RB, MC, "х", "Х");
        t(RB, LB, "ъ", "Ъ");
        t(RB, MB, ".", ".");
        t(RB, RB, "н", "Н");
    }
}
