
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphEnter extends Gliph {
    @Override
    protected final void initialize(Path path) {
        float b=5f, h=20f;
        path.moveTo(   0f, -b/2);
        path.lineTo(   0f, -h/2);
        path.lineTo( -50f,   0f);
        path.lineTo(   0f,  h/2);
        path.lineTo(   0f,  b/2);
        path.lineTo(  70f,  b/2);
        path.lineTo(  70f,   -h);
        path.lineTo(70f-b,   -h);
        path.lineTo(70f-b, -b/2);

        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds) {
        bounds.top -= 40f;
        bounds.bottom += 20f;
    }
}
