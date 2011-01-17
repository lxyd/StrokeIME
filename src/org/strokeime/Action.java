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

/**
 * Represents an action, user can do.
 * Action can be one of the following types:<br/>
 * <ul>
 *  <li> Text Action - usual input action. Send text to the receiver;</li>
 *  <li> Char Code Action - action, used to send events like DEL (backspace), TAB etc;</li>
 *  <li> Layout Action - switch to particular layout or to some variable layout like "next primary".</li>
 * </ul>
 */
public class Action {
    /**
     * Action type, used for usual text input.
     */
    public static final int TYPE_TEXT   = 0;
    /**
     * Action type for events like DEL (backspace), TAB etc.
     */
    public static final int TYPE_CODE   = 1;
    /**
     * Action type for changing layouts.
     */
    public static final int TYPE_LAYOUT = 2;

    /**
     * Special layout name to switch to, representing the next primary layout.
     */
    public static final String LAYOUT_NEXT_PRIMARY = "_next_primary";
    /**
     * Special layout name to switch to, representing the previous primary layout.
     */
    public static final String LAYOUT_PREV_PRIMARY = "_prev_primary";
    /**
     * Special layout name to switch to, representing the current primary layout.
     * Useable when some secondary layout is selected.
     */
    public static final String LAYOUT_BACK_TO_PRIMARY = "_back_to_primary";

    /**
     * Action Type.
     * This value can be one of the following:<br/>
     * <ul>
     *  <li> TYPE_TEXT - usual input action. Send text to the receiver;</li>
     *  <li> TYPE_CODE - action, used to send events like DEL (backspace), TAB etc;</li>
     *  <li> TYPE_LAYOUT - switch to particular layout or to some variable layout like "next primary"</li>
     * </ul>
     */
    public final int actionType;
    /**
     * Value (string), attached to the action.
     * Use this field if the action is of type TYPE_LAYOUT or TYPE_TEXT.
     */
    public final String value;
    /**
     * Value (char code), attached to the action.
     * Use this field if the action is of type TYPE_CODE.
     */
    public final int code;

    private Action(int actionType, String value, int code) {
        this.actionType = actionType;
        this.value = value;
        this.code = code;
    }

    public static final Action createTextAction(String value) {
        return new Action(TYPE_TEXT, value, -1);
    }

    public static final Action createKeyCodeAction(int code) {
        return new Action(TYPE_CODE, null, code);
    }

    public static final Action createLayoutAction(String layoutName) {
        return new Action(TYPE_LAYOUT, layoutName, -1);
    }
}
