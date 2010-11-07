
package org.me.strokeime;

import android.graphics.RectF;

public final class Util {
    public static final boolean pointInOval(RectF oval, float x, float y) {
        float dx, dy, c, d;
        // first, check if oval as RectF contain the point
        //if(!oval.contains(x,y))
        //    return false;

        dx = x - oval.centerX();
        dy = y - oval.centerY();

        d = oval.height();
        c = d/oval.width();

        return dx*c*dx*c + dy*dy <= d*d/4;
    }
}
