
package org.me.strokeime;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Matrix;

public abstract class Gliph {
    public Gliph() {
        path = new Path();
        bounds = new RectF();

        initialize(path);
        path.computeBounds(bounds, false);
        modifyBounds(bounds);
    }

    protected abstract void initialize(Path path);

    protected void modifyBounds(RectF bounds) { }

    private Path path;
    private RectF bounds;

    public final float measureWidth(Paint p) {
        return bounds.width() * p.getTextSize() / bounds.height();
    }

    public final void draw(Canvas c, float x, float y, Paint p) {
        Matrix m = new Matrix();
        Path res = new Path();
        float scale = p.getTextSize() / bounds.height();

        m.setScale(scale, scale, bounds.left, bounds.bottom);
        m.postTranslate(x-bounds.left, y-bounds.bottom);
        path.transform(m, res);
        c.drawPath(res, p);
    }
}
