
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutRu extends LayoutBase {
    @Override
    protected void initialize() {
        super.initialize();

        l(LT, OT, "num-ru", "?123");
        l(RT, OT, "en", "EN");

        s(MC, LT, "а", "А");
        s(MC, MT, "о", "О");
        s(MC, RT, "е", "Е");
        // MC-MC is SPACE
        s(MC, LB, "у", "У");
        s(MC, MB, "и", "И");
        s(MC, RB, "ю", "Ю");

        // LT-LT is SHIFT
        s(LT, MT, "я", "Я");
        // LT-RT is TAB
        s(LT, MC, "б", "Б");
        s(LT, LB, "й", "Й");
        s(LT, MB, "ч", "Ч");
        s(LT, RB, "(", "(");

        s(MT, LT, "ж", "Ж");
        s(MT, MT, "д", "Д");
        s(MT, RT, "ш", "Ш");
        s(MT, MC, "п", "П");
        s(MT, LB, "ы", "Ы");
        // MT-MB is !
        s(MT, RB, "э", "Э");

        s(RT, LT, "ь", "Ь");
        s(RT, MT, "в", "В");
        // RT-RT is BACKSPACE
        s(RT, MC, "к", "К");
        s(RT, LB, ")", ")");
        // RT-MB is ENTER
        s(RT, RB, "з", "З");

        s(LB, LT, "ц", "Ц");
        // LB-MT is -
        s(LB, RT, "ё", "Ё");
        s(LB, MC, "г", "Г");
        s(LB, LB, "с", "С");
        s(LB, MB, "р", "Р");
        s(LB, RB, "щ", "Щ");

        s(MB, LT, "'", "'");
        // MB-MT is ?
        s(MB, RT, "\"", "\"");
        s(MB, MC, "м", "М");
        s(MB, LB, "л", "Л");
        s(MB, MB, "т", "Т");
        s(MB, RB, "ф", "Ф");

        s(RB, LT, ";", ";");
        s(RB, MT, ":", ":");
        //RB-RT is ,
        s(RB, MC, "х", "Х");
        s(RB, LB, "ъ", "Ъ");
        //RB-MB is .
        s(RB, RB, "н", "Н");
    }
}
