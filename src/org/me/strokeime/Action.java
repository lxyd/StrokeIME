
package org.me.strokeime;

public class Action {
    /**
     * Main keyboard action - send text (usually, one character) to client
     */
    public static final int ACTION_TEXT = 0;
    /**
     * Change layout.
     */
    public static final int ACTION_CHANGE_LAYOUT = 1;
    /**
     * Changes layout to ${CurrentLayoutName}_shift.
     * Change is stored until next TEXT action occured.
     * If current layout is already shifted, shift becomes LOCK.
     * If current layout is already locked, this will change layout to default variant.
     */
    public static final int ACTION_SHIFT = 2;
    /**
     * Sends an event bound to ENTER key.
     * Just enter new line, or post form or dial or something.
     */
    public static final int ACTION_ENTER = 3;

    /**
     * Deletes a symbol before the cursor.
     */
    public static final int ACTION_BACKSPACE = 20;
    /**
     * Deletes a word before the cursor.
     */
    public static final int ACTION_BACKWORD = 21;
    /**
     * Deletes a symbol after the cursor.
     */
    public static final int ACTION_DELETE = 22;

    /**
     * Next text event will have Ctrl-modifier ON.
     */
    public static final int ACTION_CTRL = 30;
    /**
     * Next text event will have Alt-modifier ON.
     */
    public static final int ACTION_ALT = 31;
    /**
     * If Ctrl or Alt modifiers are active, clear them.
     * Otherwise, send the Escape key event.
     */
    public static final int ACTION_ESCAPE = 32;

    public final int actionType;
    public final CharSequence value;
    public final String layoutName;

    private Action(int actionType, CharSequence value, String layoutName) {
        this.actionType = actionType;
        this.value = value;
        this.layoutName = layoutName;
    }

    public static final Action createTextAction(CharSequence value) {
        return new Action(ACTION_TEXT, value, null);
    }

    public static final Action createSpecialAction(int actionType) {
        return new Action(actionType, null, null);
    }

    public static final Action createChangeLayoutAction(String layoutName) {
        return new Action(ACTION_CHANGE_LAYOUT, null, layoutName);
    }
}
