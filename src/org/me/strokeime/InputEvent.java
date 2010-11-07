
package org.me.strokeime;

import java.util.EventObject;

public final class InputEvent extends EventObject {
    public final CharSequence text;
    public InputEvent(Object source, CharSequence text) {
        super(source);
        this.text = text;
    }
}
