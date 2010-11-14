
package org.me.strokeime;

public class Key {
    public final Action action;
    public final String label;
    public final Gliph gliph;

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
