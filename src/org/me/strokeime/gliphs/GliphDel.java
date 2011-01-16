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
