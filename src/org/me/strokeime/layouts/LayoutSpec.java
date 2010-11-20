
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutSpec extends Layout {
    @Override
    protected void initialize() {

        mIsSingleChar = true;

        s(MC, LT, "ç", "Ç");
        s(MC, MT, "î", "Î");
        s(MC, RT, "ñ", "Ñ");
        s(MC, MC, "ß", "S", "ß", "ss");
        s(MC, LB, "ì", "Ì");
        s(MC, MB, "ï", "Ï");
        s(MC, RB, "í", "Í");

        c(LT, LT, KEYCODE_SHIFT_LEFT, new GliphShift(), new GliphShift(), new GliphShiftLock()); 
        s(LT, MT, "é", "É");
        s(LT, RT, "ê", "Ê");
        s(LT, MC, "ë", "Ë");
        s(LT, LB, "ý", "Ý");
        s(LT, MB, "ÿ", "Ÿ");
        s(LT, RB, "è", "È");

        s(MT, LT, "à", "À");
        s(MT, MT, "â", "Â");
        s(MT, RT, "á", "Á");
        s(MT, MC, "å", "Å");
        s(MT, LB, "ã", "Ã");
        s(MT, MB, "ä", "Ä");
        s(MT, RB, "æ", "Æ");

        // Belarusian, Ukrainian
        //s(RT, LT, "", "");
        s(RT, MT, "ї", "Ї");
        s(RT, RT, "і", "І");
        s(RT, MC, "є", "Є");
        //s(RT, LB, "", "");
        s(RT, MB, "ў", "Ў");
        s(RT, RB, "ґ", "Ґ");

        s(LB, LT, "ù", "Ù");
        s(LB, MT, "û", "Û");
        s(LB, RT, "§", "§");
        //s(LB, MC, "", "");
        s(LB, LB, "ü", "Ü");
        s(LB, MB, "ú", "Ú");
        //s(LB, RB, "", "");

        s(MB, LT, "õ", "Õ");
        s(MB, MT, "ö", "Ö");
        s(MB, RT, "œ", "Œ");
        s(MB, MC, "ø", "Ø");
        s(MB, LB, "ò", "Ò");
        s(MB, MB, "ô", "Ô");
        s(MB, RB, "ó", "Ó");

        // Esperanto
        //s(RB, LT, "", "");
        s(RB, MT, "ĝ", "Ĝ");
        s(RB, RT, "ĥ", "Ĥ");
        s(RB, MC, "ŝ", "Ŝ");
        s(RB, LB, "ĵ", "Ĵ"); 
        s(RB, MB, "ŭ", "Ŭ");
        s(RB, RB, "ĉ", "Ĉ");
    }
}
