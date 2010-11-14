
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphShift extends Gliph {
    @Override
    protected final void initialize(Path path) {
        float h=80f, w=20f, b=5f;
        float b2 = b*(float)Math.sqrt(2f),
              b3 = b+b2;

        path.moveTo(w, 0f);

        path.lineTo(   w,    w);
        path.lineTo(  -w,    w);
        path.lineTo(  -w,   0f);
        path.lineTo(   w,   0f);

        path.lineTo( w-b,    b);
        path.lineTo(-w+b,    b);
        path.lineTo(-w+b,  w-b);
        path.lineTo( w-b,  w-b);
        path.lineTo( w-b,    b);

        path.close();


        path.moveTo(   w, -w/2);
        path.lineTo(   w,   -w);
        path.lineTo( 2*w,   -w);
        path.lineTo(  0f, -3*w);
        path.lineTo(-2*w,   -w);
        path.lineTo(  -w,   -w);
        path.lineTo(  -w, -w/2);
        path.lineTo(   w, -w/2);

        path.lineTo(    w-b,  -w/2-b);
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
    protected final void modifyBounds(RectF bounds) {
        bounds.top -= 10f;
    }
}
