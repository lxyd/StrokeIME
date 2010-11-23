
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphShift extends Gliph {
    private static final float WEIGHT_NORMAL = 5f;
    private static final float WEIGHT_BOLD   = 10f;

    @Override
    protected final void initialize(Path path, boolean bold) {
        float h=80f, w=20f;
        float b = bold ? WEIGHT_BOLD : WEIGHT_NORMAL;
        float c = bold ? WEIGHT_BOLD-2 : WEIGHT_NORMAL;
        float b2 = b*(float)Math.sqrt(2f),
              b3 = b+b2;

        path.moveTo(w, 0f);

        path.lineTo(   w,    w);
        path.lineTo(  -w,    w);
        path.lineTo(  -w,   0f);
        path.lineTo(   w,   0f);

        path.close();

        path.moveTo( w-c,    c);
        path.lineTo(-w+c,    c);
        path.lineTo(-w+c,  w-c);
        path.lineTo( w-c,  w-c);
        path.lineTo( w-c,    c);

        path.close();


        path.moveTo(   w, -w/2);
        path.lineTo(   w,   -w);
        path.lineTo( 2*w,   -w);
        path.lineTo(  0f, -3*w);
        path.lineTo(-2*w,   -w);
        path.lineTo(  -w,   -w);
        path.lineTo(  -w, -w/2);
        path.lineTo(   w, -w/2);

        path.close();

        path.moveTo(    w-b,  -w/2-b);
        path.lineTo(   -w+b,  -w/2-b);
        path.lineTo(   -w+b,    -w-b);
        path.lineTo(-2*w+b3,    -w-b);
        path.lineTo(     0f, -3*w+b2);
        path.lineTo( 2*w-b3,    -w-b);
        path.lineTo(    w-b,    -w-b);
        path.lineTo(    w-b,  -w/2-b);

        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds, boolean bold) {
        bounds.top -= 10f;
    }
}
