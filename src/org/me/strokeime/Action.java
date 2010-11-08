
package org.me.strokeime;

public class Action {
    public static final int ACTION_CHAR = 0;
    public static final int ACTION_CHANGE_LAYOUT = 1;
    public static final int ACTION_ENTER = 2;
    public static final int ACTION_BACKSPACE = 3;

    public static final int DURATION_FOREVER = 1;
    public static final int DURATION_CHAR = 2;
    public static final int DURATION_WORD = 3;

    public Action(String layoutName, int layoutDuration) {
        this.actionType = ACTION_CHANGE_LAYOUT;
        this.value = '\u0000';
        this.layoutName = layoutName;
        this.layoutDuration = layoutDuration;
    }
    public Action(int actionType) {
        this.actionType = actionType;
        this.value = '\u0000';
        this.layoutName = null;
        this.layoutDuration = 0;
    }
    public Action(char value) {
        this.actionType = ACTION_CHAR;
        this.value = value;
        this.layoutName = null;
        this.layoutDuration = 0;
    }
    public final int actionType;
    public final char value;
    public final String layoutName;
    public final int layoutDuration;
}
