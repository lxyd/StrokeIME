
package org.me.strokeime;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Matrix;

public abstract class Gliph {
    public Gliph() {
        path = new Path();
        pathBold = new Path();

        bounds = new RectF();
        boundsBold = new RectF();

        initialize(path, false);
        initialize(pathBold, true);

        path.computeBounds(bounds, false);
        pathBold.computeBounds(boundsBold, false);

        modifyBounds(bounds, false);
        modifyBounds(boundsBold, true);
    }

    protected abstract void initialize(Path path, boolean bold);
    protected void modifyBounds(RectF bounds, boolean bold) { }

    private Path path, pathBold;
    private RectF bounds, boundsBold;

    public final float measureWidth(Paint p) {
        RectF bounds = p.isFakeBoldText() ? this.boundsBold : this.bounds;
        return bounds.width() * p.getTextSize() / bounds.height();
    }

    public final void draw(Canvas c, float x, float y, Paint p) {
        Path path = p.isFakeBoldText() ? this.pathBold : this.path;
        RectF bounds = p.isFakeBoldText() ? this.boundsBold : this.bounds;

        Matrix m = new Matrix();
        Path res = new Path();
        float scale = p.getTextSize() / bounds.height();

        m.setScale(scale, scale, bounds.left, bounds.bottom);
        switch(p.getTextAlign()) {
            case LEFT:
                m.postTranslate(x-bounds.left, y-bounds.bottom);
                break;
            case RIGHT:
                m.postTranslate(x-bounds.right, y-bounds.bottom);
                break;
            case CENTER:
                m.postTranslate(x-(bounds.left+bounds.right)/2f, y-bounds.bottom);
                break;
            default:
                throw new RuntimeException("Unsupported Paint.Align value");
        }
        path.transform(m, res);
        c.drawPath(res, p);
    }
}
