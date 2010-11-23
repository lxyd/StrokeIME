
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphSpace extends Gliph {
    private static final float WEIGHT_NORMAL = 5f;
    private static final float WEIGHT_BOLD   = 10f;

    @Override
    protected final void initialize(Path path, boolean bold) {
        float h=20f, w=120f;
        float b = bold ? WEIGHT_BOLD : WEIGHT_NORMAL;

        path.moveTo(   0f,   0f);

        path.lineTo(    w,   0f);
        path.lineTo(    w,   -h);
        path.lineTo(  w-b,   -h);
        path.lineTo(  w-b,   -b);
        path.lineTo(    b,   -b);
        path.lineTo(    b,   -h);
        path.lineTo(   0f,   -h);

        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds, boolean bold) {
        bounds.top -= 45f;
        bounds.bottom += 25f;
    }
}
