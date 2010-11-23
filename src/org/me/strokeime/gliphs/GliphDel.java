
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphDel extends Gliph {
    private static final float WEIGHT_NORMAL = 5f;
    private static final float WEIGHT_BOLD   = 10f;

    @Override
    protected final void initialize(Path path, boolean bold) {
        float h=60f, w=120f;
        float b = bold ? WEIGHT_BOLD : WEIGHT_NORMAL;
        float b2 = b*(float)Math.sqrt(2f),
              b3 = b2/2;

        path.moveTo(w, h);

        path.lineTo(   w,   0f);
        path.lineTo( h/2,   0f);
        path.lineTo(  0f,  h/2);
        path.lineTo( h/2,    h);
        path.lineTo(   w,    h);

        path.close();

        path.moveTo(   w-b, h-b);
        path.lineTo(h/2+b3, h-b); 
        path.lineTo(    b2, h/2);
        path.lineTo(h/2+b3,   b);
        path.lineTo(   w-b,   b);
        path.lineTo(   w-b, h-b);

        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds, boolean bold) {
        float b = bold ? WEIGHT_BOLD : WEIGHT_NORMAL;

        bounds.top -= 10f;
        bounds.bottom += 10f;
        // to match backword gliph
        bounds.left -= b;
    }
}
