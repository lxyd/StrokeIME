
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutNumRu extends LayoutNum {
    @Override
    protected void initialize() {
        super.initialize();

        l(LT, OT, "ru", "АБВ");
        l(MT, OT, "spec", "êґå");
    }
}
