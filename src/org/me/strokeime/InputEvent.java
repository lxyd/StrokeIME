
package org.me.strokeime;

import java.util.EventObject;

public final class InputEvent extends EventObject {
    public final Action action;
    public InputEvent(Object source, Action action) {
        super(source);
        this.action = action;
    }
}
