
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphEnter extends Gliph {
    private static final float WEIGHT_NORMAL = 5f;
    private static final float WEIGHT_BOLD   = 10f;

    @Override
    protected final void initialize(Path path, boolean bold) {
        float h=20f;
        float b = bold ? WEIGHT_BOLD : WEIGHT_NORMAL;
        path.moveTo(   0f, -b/2);
        path.lineTo(   0f,   -h);
        path.lineTo( -60f,   0f);
        path.lineTo(   0f,    h);
        path.lineTo(   0f,  b/2);
        path.lineTo(  70f,  b/2);
        path.lineTo(  70f,   -h);
        path.lineTo(70f-b,   -h);
        path.lineTo(70f-b, -b/2);

        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds, boolean bold) {
        bounds.top -= 30f;
        bounds.bottom += 20f;
    }
}
