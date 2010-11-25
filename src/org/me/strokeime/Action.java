
package org.me.strokeime;

public class Action {
    public static final int TYPE_TEXT   = 0;
    public static final int TYPE_CODE   = 1;
    public static final int TYPE_LAYOUT = 2;

    public static final String LAYOUT_NEXT_PRIMARY = "_next_primary";
    public static final String LAYOUT_PREV_PRIMARY = "_prev_primary";
    public static final String LAYOUT_BACK_TO_PRIMARY = "_back_to_primary";

    public final int actionType;
    public final String value;
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
