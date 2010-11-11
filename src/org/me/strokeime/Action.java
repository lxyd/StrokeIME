
package org.me.strokeime;

public class Action {
    public static final int TYPE_TEXT   = 0;
    public static final int TYPE_CODE   = 1;
    public static final int TYPE_LAYOUT = 2;

    public final int actionType;
    public final String value;
    public final int keyCode;

    private Action(int actionType, String value, int keyCode) {
        this.actionType = actionType;
        this.value = value;
        this.keyCode = keyCode;
    }

    public static final Action createTextAction(String value) {
        return new Action(TYPE_TEXT, value, -1);
    }

    public static final Action createKeyCodeAction(int keyCode) {
        return new Action(TYPE_CODE, null, keyCode);
    }

    public static final Action createLayoutAction(String layoutName) {
        return new Action(TYPE_LAYOUT, layoutName, -1);
    }
}
