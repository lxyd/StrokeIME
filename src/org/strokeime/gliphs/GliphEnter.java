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

package org.strokeime.gliphs;

import org.strokeime.Gliph;
import android.graphics.Path;
import android.graphics.RectF;

public class GliphEnter extends Gliph {
    private static final float WEIGHT_NORMAL = 5f;
    private static final float WEIGHT_BOLD   = 10f;

    @Override
    protected final void initialize(Path path, boolean bold) {
        float h=20f;
        float b = bold ? WEIGHT_BOLD : WEIGHT_NORMAL;
        path.moveTo(   0f, -b/2);
        path.lineTo(   0f,   -h);
        path.lineTo( -60f,   0f);
        path.lineTo(   0f,    h);
        path.lineTo(   0f,  b/2);
        path.lineTo(  70f,  b/2);
        path.lineTo(  70f,   -h);
        path.lineTo(70f-b,   -h);
        path.lineTo(70f-b, -b/2);

        path.close();
    }

    @Override
    protected final void modifyBounds(RectF bounds, boolean bold) {
        bounds.top -= 30f;
        bounds.bottom += 20f;
    }
}
