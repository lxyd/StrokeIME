
package org.me.strokeime;

public class Action {
    public static final int ACTION_TEXT = 0;
    public static final int ACTION_CHANGE_LAYOUT = 1;
    public static final int ACTION_SHIFT = 2;
    public static final int ACTION_ENTER = 3;

    public static final int ACTION_BACKSPACE = 20;
    public static final int ACTION_BACKWORD = 21;
    public static final int ACTION_DELETE = 22;

    public static final int ACTION_CTRL = 30;
    public static final int ACTION_ALT = 31;
    public static final int ACTION_ESC = 32;

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

    public static final int stringToActionType(String actionTypeName) {
        if(actionTypeName.equals("enter")) {
            return ACTION_ENTER;
        } else if(actionTypeName.equals("shift")) {
            return ACTION_SHIFT;
        } else if(actionTypeName.equals("backspace")){
            return ACTION_BACKSPACE;
        } else if(actionTypeName.equals("backword")){
            return ACTION_BACKWORD;
        } else if(actionTypeName.equals("delete")){
            return ACTION_DELETE;
        } else if(actionTypeName.equals("ctrl")){
            return ACTION_CTRL;
        } else if(actionTypeName.equals("alt")){
            return ACTION_ALT;
        } else if(actionTypeName.equals("esc")){
            return ACTION_ESC;
        }
        return -1;
    }
}
