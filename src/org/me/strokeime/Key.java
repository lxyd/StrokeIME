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

/**
 * Action, combined with its label (gliph).
 */
public class Key {
    public final Action action;
    public final String label;
    public final Gliph gliph;

    // constructor for special keys
    public Key(Action action) {
        this.action = action;
        this.label = null;
        this.gliph = null;
    }

    public Key(Action action, String label) {
        this.action = action;
        this.label = label;
        this.gliph = null;
    }
    public Key(Action action, Gliph gliph) {
        this.action = action;
        this.label = null;
        this.gliph = gliph;
    }
}
