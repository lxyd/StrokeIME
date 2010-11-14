
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphSpace extends Gliph {
    @Override
    protected final void initialize(Path path) {
        float b=5f, h=20f, w=120f;

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
    protected final void modifyBounds(RectF bounds) {
        bounds.top -= 45f;
        bounds.bottom += 25f;
    }
}
