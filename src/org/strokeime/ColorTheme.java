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

public class ColorTheme {
    public final int bg;
    public final int fg;
    public final int txt; 
    public final int txtBack;
    public final int txtHot;
    public final boolean isBold;

    public ColorTheme(int bg, int fg, int txt, int txtHot, int txtBack, boolean isBold) {
        this.bg = bg;
        this.fg = fg;
        this.txt = txt;
        this.txtHot = txtHot;
        this.txtBack = txtBack;
        this.isBold = isBold;
    }
}
