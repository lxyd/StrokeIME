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

package org.strokeime;

import org.strokeime.gliphs.*;

public class GliphBank {
    public static final Gliph GLIPH_DEL = new GliphDel();
    public static final Gliph GLIPH_DEL_WORD = new GliphDelWord();
    public static final Gliph GLIPH_ENTER = new GliphEnter();
    public static final Gliph GLIPH_SHIFT = new GliphShift();
    public static final Gliph GLIPH_SHIFT_LOCK = new GliphShiftLock();
    public static final Gliph GLIPH_SPACE = new GliphSpace();
    public static final Gliph GLIPH_TAB = new GliphTab();
}
