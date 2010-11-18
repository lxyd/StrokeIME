
package org.me.strokeime.gliphs;

import org.me.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphBackword extends Gliph {
    @Override
    protected final void initialize(Path path) {
        float h=60f, w=120f, b=5f;
        float b2 = b*(float)Math.sqrt(2f),
              b3 = b2/2;

        path.moveTo(w, h);

        path.lineTo(   w,   0f);
        path.lineTo( h/2,   0f);
        path.lineTo(  0f,  h/2);
        path.lineTo( h/2,    h);
        path.lineTo(   w,    h);

        path.lineTo(   w-b, h-b);
        path.lineTo(h/2+b3, h-b); 
        path.lineTo(    b2, h/2);
        path.lineTo(h/2+b3,   b);
        path.lineTo(   w-b,   b);
        path.lineTo(   w-b, h-b);

        path.close();


        path.moveTo( 0f, 0f);
        path.lineTo( 0f,  h);
        path.lineTo( -b,  h);
        path.lineTo( -b, 0f);
        
        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds) {
        bounds.top -= 10f;
        bounds.bottom += 10f;
    }
}
