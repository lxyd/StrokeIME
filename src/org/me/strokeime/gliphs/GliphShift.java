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
