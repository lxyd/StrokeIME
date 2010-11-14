
package org.me.strokeime;

import java.util.Hashtable;
import org.me.strokeime.layouts.*;

public class LayoutBank {
    public static final int DURATION_FOREVER = 0;
    public static final int DURATION_CHAR = 1;
    public static final int DURATION_WORD = 2;

    public static class LayoutItem {
        public final Layout layout;
        public final int duration;
        private LayoutItem(Layout layout, int duration) {
            this.layout = layout;
            this.duration = duration;
        }
    }

    private final Hashtable<String, LayoutItem> bank = new Hashtable<String, LayoutItem>();
    public final Layout defaultLayout;

    public LayoutItem getLayout(String name) {
        return bank.get(name);
    }

    public LayoutBank() {
        defaultLayout = new LayoutEn();
        bank.put("en", new LayoutItem(defaultLayout, DURATION_FOREVER));
        bank.put("num", new LayoutItem(new LayoutNum(), DURATION_WORD));
        // TODO: load all layouts
    }
}
