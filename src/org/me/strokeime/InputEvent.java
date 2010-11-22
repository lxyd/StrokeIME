
package org.me.strokeime;

import java.util.EventObject;

public final class InputEvent extends EventObject {
    public static interface InputEventListener {
        public void onInput(InputEvent event);
    }

    public final Action action;
    public InputEvent(Object source, Action action) {
        super(source);
        this.action = action;
    }
}
