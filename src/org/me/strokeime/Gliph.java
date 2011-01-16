/*
    Copyright (C) 2011 Alexey Dubinin 

    This file is part of StrokeIME, an alternative input method for Android OS

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.me.strokeime;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Matrix;

public abstract class Gliph {
    public Gliph() {
        mPath = new Path();
        mPathBold = new Path();

        mBounds = new RectF();
        mBoundsBold = new RectF();

        initialize(mPath, false);
        initialize(mPathBold, true);

        mPath.computeBounds(mBounds, false);
        mPathBold.computeBounds(mBoundsBold, false);

        modifyBounds(mBounds, false);
        modifyBounds(mBoundsBold, true);
    }

    protected abstract void initialize(Path path, boolean bold);
    protected void modifyBounds(RectF bounds, boolean bold) { }

    private Path mPath, mPathBold;
    private RectF mBounds, mBoundsBold;

    public final float measureWidth(Paint p) {
        RectF bounds = p.isFakeBoldText() ? mBoundsBold : mBounds;
        return bounds.width() * p.getTextSize() / bounds.height();
    }

    private Matrix mTransformMatrix = new Matrix();
    private Path mTransformedPath = new Path();

    public final void draw(Canvas c, float x, float y, Paint p) {
        Path path = p.isFakeBoldText() ? mPathBold : mPath;
        RectF bounds = p.isFakeBoldText() ? mBoundsBold : mBounds;

        float scale = p.getTextSize() / bounds.height();

        switch(p.getTextAlign()) {
            case LEFT:
                mTransformMatrix.setScale(scale, scale, bounds.left, bounds.bottom);
                mTransformMatrix.postTranslate(x-bounds.left, y-bounds.bottom);
                break;
            case RIGHT:
                mTransformMatrix.setScale(scale, scale, bounds.right, bounds.bottom);
                mTransformMatrix.postTranslate(x-bounds.right, y-bounds.bottom);
                break;
            case CENTER:
                mTransformMatrix.setScale(scale, scale, (bounds.left+bounds.right)/2f, bounds.bottom);
                mTransformMatrix.postTranslate(x-(bounds.left+bounds.right)/2f, y-bounds.bottom);
                break;
            default:
                throw new RuntimeException("Unsupported Paint.Align value");
        }
        path.transform(mTransformMatrix, mTransformedPath);
        c.drawPath(mTransformedPath, p);
    }
}
