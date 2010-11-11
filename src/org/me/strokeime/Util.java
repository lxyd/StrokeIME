
package org.me.strokeime;

import android.graphics.RectF;
import java.io.BufferedReader;
import java.io.IOException;

public final class Util {
    public static final boolean pointInOval(RectF oval, float x, float y) {
        float dx, dy, c, d;

        dx = x - oval.centerX();
        dy = y - oval.centerY();

        d = oval.height();
        c = d/oval.width();

        return dx*c*dx*c + dy*dy <= d*d/4;
    }
}
