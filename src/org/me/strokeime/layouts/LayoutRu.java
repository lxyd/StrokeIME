
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.Action;
import static org.me.strokeime.GliphBank.*;
import static android.view.KeyEvent.*;

public class LayoutRu extends LayoutBase {
    @Override protected String getTitle()          { return "Русский язык"; }
    @Override protected String getName()           { return "ru"; }
    @Override protected String getLabelPrimary()   { return "RU"; }
    @Override protected String getLabelSecondary() { return "АБВ"; }
    @Override protected String getLocalesString()  { return "ru_RU";   }

    @Override
    protected void initializeLayout() {
        super.initializeLayout();

        //l(LT, OT, "num-ru", "?123");
        //l(MT, OT, "spec-ru", "êґå");
        //l(RT, OT, "en", "EN");
        l(LT, OT, "num");
        l(MT, OT, "diacritic");
        l(RT, OT, Action.LAYOUT_NEXT_PRIMARY);
        l(RB, OT, Action.LAYOUT_PREV_PRIMARY);

        t(MC, LT, "а", "А");
        t(MC, MT, "о", "О");
        t(MC, RT, "е", "Е");
        // MC-MC is SPACE
        t(MC, LB, "у", "У");
        t(MC, MB, "и", "И");
        t(MC, RB, "ю", "Ю");

        // LT-LT is SHIFT
        t(LT, MT, "я", "Я");
        t(LT, RT, "ы", "Ы");
        t(LT, MC, "б", "Б");
        t(LT, LB, "й", "Й");
        t(LT, MB, "ч", "Ч");
        t(LT, RB, "(", "(");

        t(MT, LT, "ж", "Ж");
        t(MT, MT, "п", "П");
        t(MT, RT, "ш", "Ш");
        t(MT, MC, "д", "Д");
        t(MT, LB, "/", "/");
        // MT-MB is !
        t(MT, RB, "э", "Э");

        t(RT, LT, "ь", "Ь");
        t(RT, MT, "в", "В");
        // RT-RT is BACKSPACE
        t(RT, MC, "к", "К");
        t(RT, LB, ")", ")");
        // RT-MB is ENTER
        t(RT, RB, "з", "З");

        t(LB, LT, "ц", "Ц");
        t(LB, MT, "-", "—", "-");
        t(LB, RT, "ё", "Ё");
        t(LB, MC, "г", "Г");
        t(LB, LB, "с", "С");
        t(LB, MB, "р", "Р");
        t(LB, RB, "щ", "Щ");

        t(MB, LT, "«", "'", "«");
        // MB-MT is ?
        t(MB, RT, "»", "\"", "»");
        t(MB, MC, "м", "М");
        t(MB, LB, "л", "Л");
        t(MB, MB, "т", "Т");
        t(MB, RB, "ф", "Ф");

        t(RB, LT, ";", ";");
        t(RB, MT, ":", ":");
        //RB-RT is ,
        t(RB, MC, "х", "Х");
        t(RB, LB, "ъ", "Ъ");
        //RB-MB is .
        t(RB, RB, "н", "Н");
    }
}
