
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphTab extends Gliph {
    @Override
    protected final void initialize(Path path) {
        float b=5f, h=20f, w=120f;
        path.moveTo(   0f,   0f);
        path.lineTo( -60f,   -h);
        path.lineTo( -60f, -b/2);
        path.lineTo( -w-b, -b/2);
        path.lineTo( -w-b,  b/2);
        path.lineTo( -60f,  b/2);
        path.lineTo( -60f,    h);

        path.close();

        path.moveTo(   0f,  0f);
        path.lineTo(   0f,  -h);
        path.lineTo(    b,  -h);
        path.lineTo(    b,   h);
        path.lineTo(   0f,   h);

        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds) {
        bounds.top -= 30f;
        bounds.bottom += 20f;
    }
}
