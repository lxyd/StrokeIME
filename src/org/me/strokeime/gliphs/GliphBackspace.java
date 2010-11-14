
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphBackspace extends Gliph {
    @Override
    protected final void initialize(Path path) {
        float h=60f, w=120f, b=5f;
        float b2 = b*(float)Math.sqrt(2f)/2,
              b3 = b*2/(float)Math.sqrt(2f);

        path.moveTo(w, h);

        path.lineTo(   w,   0f);
        path.lineTo( h/2,   0f);
        path.lineTo(  0f,  h/2);
        path.lineTo( h/2,    h);
        path.lineTo(   w,    h);

        path.lineTo(   w-b, h-b);
        path.lineTo(h/2+b2, h-b); 
        path.lineTo(    b3, h/2);
        path.lineTo(h/2+b2,   b);
        path.lineTo(   w-b,   b);
        path.lineTo(   w-b, h-b);

        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds) {
        bounds.top -= 10f;
        bounds.bottom += 10f;
    }
}
