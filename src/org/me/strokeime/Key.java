
package org.me.strokeime;

public class Key {
    public final Action action;
    public final String label;
    public final Gliph gliph;

    // Some special keys with
    //public static final Key KEY_NEXT_PRIMARY_LAYOUT = new Key(Action.createSpecialAction(Action.SPECIAL_NEXT_PRIMARY_LAYOUT));
    //public static final Key KEY_PREV_PRIMARY_LAYOUT = new Key(Action.createSpecialAction(Action.SPECIAL_PREV_PRIMARY_LAYOUT));
    //public static final Key KEY_BACK_TO_PRIMARY_LAYOUT = new Key(Action.createSpecialAction(Action.SPECIAL_BACK_TO_PRIMARY_LAYOUT));

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
