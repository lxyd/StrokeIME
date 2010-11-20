
package org.me.strokeime.layouts;

import org.me.strokeime.Layout;
import org.me.strokeime.gliphs.*;
import static android.view.KeyEvent.*;

public class LayoutSpecEn extends LayoutSpec {
    @Override
    protected void initialize() {
        super.initialize();

        l(MT, OT, "en", "ABC");
    }
}
