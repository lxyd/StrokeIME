
package org.me.strokeime;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import org.me.strokeime.layouts.*;
import static org.me.strokeime.Layout.TYPE_PRIMARY;
import static org.me.strokeime.Action.LAYOUT_BACK_TO_PRIMARY;
import static org.me.strokeime.Action.LAYOUT_NEXT_PRIMARY;
import static org.me.strokeime.Action.LAYOUT_PREV_PRIMARY;

public class LayoutSwitcher {
    private final Map<String, Layout> bank = new Hashtable<String, Layout>();
    private final Map<String, Key> backToPrimaryKeys = new Hashtable<String, Key>();
    private final Map<String, Key> primaryLayoutKeys = new Hashtable<String, Key>();
    // usually, one-three items
    private final List<Layout> primaryLayouts = new ArrayList<Layout>();
    public final Layout defaultLayout;

    private Layout mCurrentPrimaryLayout = null;
    private Layout mCurrentLayout = null;

    public Layout getCurrentLayout()        { return mCurrentLayout; }
    public Layout getCurrentPrimaryLayout() { return mCurrentPrimaryLayout; }

    /**
     * Call this when layout should be switched back to current primary layout.
     */
    public void backToPrimary() {
        mCurrentLayout = mCurrentPrimaryLayout;
    }

    public void changeLayout(String name) {
        mCurrentLayout = bank.get(name);
        if(mCurrentLayout.type == TYPE_PRIMARY) {
            mCurrentPrimaryLayout = mCurrentLayout;
        } else {
            mCurrentLayout.registerLayoutActionKey(LAYOUT_BACK_TO_PRIMARY, backToPrimaryKeys.get(mCurrentPrimaryLayout.name));
        }

        int i = primaryLayouts.indexOf(mCurrentPrimaryLayout);
        int next_i = i == primaryLayouts.size() - 1 ? 0 : i + 1;
        mCurrentLayout.registerLayoutActionKey(LAYOUT_NEXT_PRIMARY, primaryLayoutKeys.get(primaryLayouts.get(next_i).name));

        if(primaryLayouts.size() > 2) {
            int prev_i = i == 0 ? primaryLayouts.size() - 1 : i - 1;
            mCurrentLayout.registerLayoutActionKey(LAYOUT_PREV_PRIMARY, primaryLayoutKeys.get(primaryLayouts.get(prev_i).name));
        } else {
            mCurrentLayout.registerLayoutActionKey(LAYOUT_PREV_PRIMARY, null);
        }
    }

    public LayoutSwitcher() {
        // TODO: load selected primary layouts from settings.
        // TODO: if no primary layouts are selected explicetely, select it by locale

        Layout tmp;

        tmp = new LayoutEn();
        bank.put(tmp.name, tmp);
        defaultLayout = tmp;

        tmp = new LayoutRu();
        bank.put(tmp.name, tmp);

        tmp = new LayoutNum();
        bank.put(tmp.name, tmp);

        tmp = new LayoutDiacritic();
        bank.put(tmp.name, tmp);

        // Preparing layouts to be able to switch between each other
        for(Layout l : bank.values()) {
            if(l.type == TYPE_PRIMARY) {
                backToPrimaryKeys.put(l.name, new Key(Action.createLayoutAction(l.name), l.labelSecondary));
                primaryLayoutKeys.put(l.name, new Key(Action.createLayoutAction(l.name), l.labelPrimary));
                primaryLayouts.add(l);
            }

            for(Layout l2 : bank.values()) {
                if(!l2.name.equals(l.name))
                    l.registerLayoutActionKey(l2.name, new Key(Action.createLayoutAction(l2.name), l2.labelPrimary));
            }
        }

        changeLayout(defaultLayout.name);
    }
}
