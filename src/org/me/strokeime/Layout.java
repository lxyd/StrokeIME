
package org.me.strokeime;

import java.util.Hashtable;
import java.util.Map;

public abstract class Layout {
    public static final int SHIFT_OFF = 0;
    public static final int SHIFT_ON = 1;
    public static final int SHIFT_LOCK = 2;

    public static final int TYPE_PRIMARY = 0;
    public static final int TYPE_SECONDARY = 1;
    public static final int TYPE_SECONDARY_CHAR = 2;
    public static final int TYPE_SECONDARY_WORD = 3;

    public static final int MC = 0x0;
    public static final int LT = 0x1;
    public static final int MT = 0x2;
    public static final int RT = 0x3;
    public static final int LB = 0x4;
    public static final int MB = 0x5;
    public static final int RB = 0x6;

    // out zones (used OT only)
    public static final int OT = 0xA;
    public static final int OR = 0xB;
    public static final int OB = 0xC;
    public static final int OL = 0xD;

    public static final int KEYCODE_DEL_WORD = -101;

    private static class Stroke {
        public final int start;
        public final int end;
        public Stroke(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Layout type: PRIMARY, SECONDARY, SECONDARY_CHAR or SECONDARY_WORD.
     * At any moment there is one current PRIMARY layout.
     * You can switch from it to another primary layout or to secondary.
     * From secondary layout you can go back to the current primary layout.
     * If secondary layout's duration is CHAR or WORD switching will be performed
     * automatically.
     */
    public final int type;

    /**
     * Name that explicitely identify the layout.
     * Used in LayoutBank to retreive the layout by name.
     */
    public final String name;

    /**
     * Human readable layout name.
     * Used in Settings to identify the layout.
     */
    public final String title;

    /**
     * Key label for this layout when explicitely changing to it.
     * E.g. "EN" is labelPrimary for english layout.
     */
    public final String labelPrimary;
    /**
     * Key label for this layout when implicitely changing to it (eg when
     * going back from supplementary layout like num).
     * E.g. "ABC" is labelSecondary for english layout.
     */
    public final String labelSecondary;

    public Layout() {
        String tmp;

        type = getType();

        tmp = getName();
        if(tmp == null) throw new RuntimeException("Mandatory layout attribute 'name' is not set");
        name = tmp;

        tmp = getTitle();
        if(tmp == null) throw new RuntimeException("Mandatory layout attribute 'title' is not set");
        title = tmp;

        tmp = getLabelPrimary();
        labelPrimary = tmp == null ? name : tmp;

        tmp = getLabelSecondary();
        labelSecondary = tmp == null ? labelPrimary : tmp;

        initializeLayout();
    }

    protected abstract String getName();
    protected abstract String getTitle();

    protected int    getType()           { return TYPE_PRIMARY; }
    protected String getLabelPrimary()   { return null; }
    protected String getLabelSecondary() { return null; }
    protected String getLocalesString()  { return "*";  }

    /**
     * Initializes key actions.
     * Must be overriden in subclasses
     */
    protected abstract void initializeLayout();

    /**
     * Main zones map.
     * Dimensions are:
     * shift state, stroke start zone, stroke end zone
     */
    private final Key[][][] map = new Key[3][16][16];

    /**
     * Keys to use instead of registered special actions.
     */
    private final Map<String, Key> layoutSpecialActionsKeys = new Hashtable<String, Key>();

    /**
     * Set key for layout name.
     * E.g. set correct PRIMARY layout to switch to.
     */
    public final void registerLayoutActionKey(String layoutName, Key key) {
        //if(layoutName.equals(Action.LAYOUT_NEXT_PRIMARY))
        //    throw new RuntimeException(layoutName + " " + key.label + " " + key.action.value);
        if(key == null)
            layoutSpecialActionsKeys.remove(layoutName);
        else
            layoutSpecialActionsKeys.put(layoutName, key);
    }

    /**
     * Get key, associated with the stroke from start to end.
     */
    public final Key getKey(int shiftState, int start, int end) {
        Key k = map[shiftState][start][end];
        // if action has type of TYPE_LAYOUT, we should use Key, 
        // provided to us by LayoutBank
        if(k != null && k.action.actionType == Action.TYPE_LAYOUT) {
            k = layoutSpecialActionsKeys.get(k.action.value);
            // DON'T throw exception. LayoutBank may tell us to ignore some layouts (e.g. PREV_PRIMARY_LAYOUT)
            //if(k == null) throw new RuntimeException("There is no key for layout " + k.action.layoutName + ".");
        }
        return k;
    }

    /**
     * Get action, associated with the stroke from start to end.
     */
    public final Action getAction(int shiftState, int start, int end) {
        Key k = getKey(shiftState, start, end);
        return k == null ? null : k.action;
    }

    /**
     * Register usual text/char stroke in the map.
     * Lower and Upper labels for this stroke will be set equal to the characters themselves
     * 
     * @param start Start zone
     * @param end   End zone
     * @param lower Character (may be, string) to type when Shift button is not pressed
     * @param upper Character (may be, string) to type when Shift button is pressed
     */
    protected final void t(int start, int end, String lower, String upper) {
        t(start, end, lower, upper, lower, upper);
    }

    /**
     * Register usual text/char stroke in the map.
     * Lower and Upper labels for this stroke will be set equal to the characters themselves
     * 
     * @param start Start zone
     * @param end   End zone
     * @param lower Character (may be, string) to type when Shift button is not pressed
     * @param upper Character (may be, string) to type when Shift button is pressed
     * @param lock  Character (may be, string) to type when Shift button is locked
     */
    protected final void t(int start, int end, String lower, String upper, String lock) {
        t(start, end, lower, upper, lock, lower, upper, lock);
    }

    /**
     * Register usual text/char stroke in the map.
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param lower      Character (may be, string) to type when Shift button is not pressed
     * @param upper      Character (may be, string) to type when Shift button is pressed
     * @param labelLower Keyboard text for this character when Shift button is not pressed
     * @param labelUpper Keyboard text for this character when Shift button is pressed
     */
    protected final void t(int start, int end, String lower, String upper, String labelLower, String labelUpper) {
        map[SHIFT_OFF][start][end] = new Key(Action.createTextAction(lower), labelLower);
        map[SHIFT_LOCK][start][end] = map[SHIFT_ON][start][end] = new Key(Action.createTextAction(upper), labelUpper);
    }

    /**
     * Register usual text/char stroke in the map.
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param lower      Character (may be, string) to type when Shift button is not pressed
     * @param upper      Character (may be, string) to type when Shift button is pressed
     * @param lock       Character (may be, string) to type when Shift button is locked
     * @param labelLower Keyboard text for this character when Shift button is not pressed
     * @param labelUpper Keyboard text for this character when Shift button is pressed
     * @param labelLock  Keyboard text for this character when Shift button is locked
     */
    protected final void t(int start, int end, String lower, String upper, String lock, String labelLower, String labelUpper, String labelLock) {
        map[SHIFT_OFF][start][end] = new Key(Action.createTextAction(lower), labelLower);
        map[SHIFT_ON][start][end] = new Key(Action.createTextAction(upper), labelUpper);
        map[SHIFT_LOCK][start][end] = new Key(Action.createTextAction(lock), labelLock);
    }

    /**
     * Register usual text/char stroke in the map.
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param lower      Character (may be, string) to type when Shift button is not pressed
     * @param upper      Character (may be, string) to type when Shift button is pressed
     * @param gliphLower Keyboard gliph for this character when Shift button is not pressed
     * @param gliphUpper Keyboard gliph for this character when Shift button is pressed
     */
    protected final void t(int start, int end, String lower, String upper, Gliph gliphLower, Gliph gliphUpper) {
        map[SHIFT_OFF][start][end] = new Key(Action.createTextAction(lower), gliphLower);
        map[SHIFT_LOCK][start][end] = map[SHIFT_ON][start][end] = new Key(Action.createTextAction(upper), gliphUpper);
    }

    /**
     * Register usual text/char stroke in the map.
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param lower      Character (may be, string) to type when Shift button is not pressed
     * @param upper      Character (may be, string) to type when Shift button is pressed
     * @param lock       Character (may be, string) to type when Shift button is locked
     * @param gliphLower Keyboard gliph for this character when Shift button is not pressed
     * @param gliphUpper Keyboard gliph for this character when Shift button is pressed
     * @param gliphLock  Keyboard gliph for this character when Shift button is locked
     */
    protected final void t(int start, int end, String lower, String upper, String lock, Gliph gliphLower, Gliph gliphUpper, Gliph gliphLock) {
        map[SHIFT_OFF][start][end] = new Key(Action.createTextAction(lower), gliphLower);
        map[SHIFT_ON][start][end] = new Key(Action.createTextAction(upper), gliphUpper);
        map[SHIFT_LOCK][start][end] = new Key(Action.createTextAction(lock), gliphLock);
    }

    /**
     * Register stroke in the map with keycode as the event param.
     * This is useful for creating Alt and Ctrl aware layouts or registering strokes
     * for ENTER, SPACE, BACKSPACE and other special characters
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param codeLower  Raw keycode when Shift is not pressed
     * @param codeUpper  Raw keycode when Shift is pressed
     * @param labelLower Keyboard text for this character when Shift button is not pressed
     * @param labelLower Keyboard text for this character when Shift button is pressed
     */
    protected final void c(int start, int end, int codeLower, int codeUpper, String labelLower, String labelUpper) {
        map[SHIFT_OFF][start][end] = new Key(Action.createKeyCodeAction(codeLower), labelLower);
        map[SHIFT_LOCK][start][end] = map[SHIFT_ON][start][end] = new Key(Action.createKeyCodeAction(codeUpper), labelUpper);
    }

    /**
     * Register stroke in the map with keycode as the event param.
     * This is useful for creating Alt and Ctrl aware layouts or registering strokes
     * for ENTER, SPACE, BACKSPACE and other special characters
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param code       Raw keycode to use with Alt and Ctrl
     * @param labelLower Keyboard text for this character when Shift button is not pressed
     * @param labelLower Keyboard text for this character when Shift button is pressed
     */
    protected final void c(int start, int end, int code, String labelLower, String labelUpper) {
        c(start, end, code, code, labelLower, labelUpper);
    }

    /**
     * Register stroke in the map with keycode as the event param.
     * This is useful for creating Alt and Ctrl aware layouts or registering strokes
     * for ENTER, SPACE, BACKSPACE and other special characters
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param code       Raw keycode to use with Alt and Ctrl
     * @param gliphLower Keyboard gliph for this character when Shift button is not pressed
     * @param gliphUpper Keyboard gliph for this character when Shift button is pressed
     */
    protected final void c(int start, int end, int code, Gliph gliphLower, Gliph gliphUpper) {
        c(start, end, code, gliphLower, gliphUpper, gliphUpper);
    }

    /**
     * Register stroke in the map with keycode as the event param.
     * This is useful for creating Alt and Ctrl aware layouts or registering strokes
     * for ENTER, SPACE, BACKSPACE and other special characters
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param code       Raw keycode to use with Alt and Ctrl
     * @param gliphLower Keyboard gliph for this character when Shift button is not pressed
     * @param gliphUpper Keyboard gliph for this character when Shift button is pressed
     * @param gliphLock  Keyboard gliph for this character when Shift button is pressed
     */
    protected final void c(int start, int end, int code, Gliph gliphLower, Gliph gliphUpper, Gliph gliphLock) {
        c(start, end, code, code, gliphLower, gliphUpper, gliphLock);
    }

    /**
     * Register stroke in the map with keycode as the event param.
     * This is useful for creating Alt and Ctrl aware layouts or registering strokes
     * for ENTER, SPACE, BACKSPACE and other special characters
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param codeLower  Raw keycode when shift is not pressed
     * @param codeUpper  Raw keycode when shift is pressed
     * @param gliphLower Keyboard gliph for this character when Shift button is not pressed
     * @param gliphUpper Keyboard gliph for this character when Shift button is pressed
     */
    protected final void c(int start, int end, int codeLower, int codeUpper, Gliph gliphLower, Gliph gliphUpper) {
        c(start, end, codeLower, codeUpper, gliphLower, gliphUpper, gliphUpper);
    }

    /**
     * Register stroke in the map with keycode as the event param.
     * This is useful for creating Alt and Ctrl aware layouts or registering strokes
     * for ENTER, SPACE, BACKSPACE and other special characters
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param codeLower  Raw keycode when shift is not pressed
     * @param codeUpper  Raw keycode when shift is pressed
     * @param gliphLower Keyboard gliph for this character when Shift button is not pressed
     * @param gliphUpper Keyboard gliph for this character when Shift button is pressed
     * @param gliphLock  Keyboard gliph for this character when Shift button is pressed
     */
    protected final void c(int start, int end, int codeLower, int codeUpper, Gliph gliphLower, Gliph gliphUpper, Gliph gliphLock) {
        c(start, end, codeLower, codeUpper, codeUpper, gliphLower, gliphUpper, gliphLock);
    }

    /**
     * Register stroke in the map with keycode as the event param.
     * This is useful for creating Alt and Ctrl aware layouts or registering strokes
     * for ENTER, SPACE, BACKSPACE and other special characters
     * 
     * @param start      Start zone
     * @param end        End zone
     * @param codeLower  Raw keycode when shift is not pressed
     * @param codeUpper  Raw keycode when shift is pressed
     * @param codeLock   Raw keycode when shift is locked
     * @param gliphLower Keyboard gliph for this character when Shift button is not pressed
     * @param gliphUpper Keyboard gliph for this character when Shift button is pressed
     * @param gliphLock  Keyboard gliph for this character when Shift button is pressed
     */
    protected final void c(int start, int end, int codeLower, int codeUpper, int codeLock, Gliph gliphLower, Gliph gliphUpper, Gliph gliphLock) {
        map[SHIFT_OFF][start][end] = new Key(Action.createKeyCodeAction(codeLower), gliphLower);
        map[SHIFT_ON][start][end] = new Key(Action.createKeyCodeAction(codeUpper), gliphUpper);
        map[SHIFT_LOCK][start][end] = new Key(Action.createKeyCodeAction(codeLock), gliphLock);
    }

    /**
     * Register ChangeLayout stroke in the map.
     *
     * @param start      Start zone
     * @param end        End zone
     * @param layoutName Layout to change to
     */
    protected final void l(int start, int end, String layoutName) {
        map[SHIFT_LOCK][start][end] = map[SHIFT_ON][start][end] = map[SHIFT_OFF][start][end] = new Key(Action.createLayoutAction(layoutName));
    }
}

