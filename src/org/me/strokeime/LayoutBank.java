
package org.me.strokeime;

import java.util.Hashtable;
import org.me.strokeime.layouts.*;

public class LayoutBank {
    private final Hashtable<String, Layout> bank = new Hashtable<String, Layout>();
    public final Layout defaultLayout;

    public Layout getLayout(String name) {
        return bank.get(name);
    }

    public LayoutBank() {
        defaultLayout = new LayoutEn();
        bank.put("en", defaultLayout);
        bank.put("ru", new LayoutRu());
        bank.put("num-en", new LayoutNumEn());
        bank.put("num-ru", new LayoutNumRu());
        bank.put("spec-en", new LayoutSpecEn());
        bank.put("spec-ru", new LayoutSpecRu());
    }
}
