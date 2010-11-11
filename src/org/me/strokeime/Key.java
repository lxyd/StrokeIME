
package org.me.strokeime;

public class Key {
    public final Action action;
    public final String label;
    public final int drawableResourceId;

    public Key(Action action, String label) {
        this.action = action;
        this.label = label;
        this.drawableResourceId = -1;
    }

    public Key(Action action, int drawableResourceId) {
        this.action = action;
        this.label = null;
        this.drawableResourceId = drawableResourceId;
    }
}
