/*
    Copyright (C) 2011 Alexey Dubinin 

    This file is part of StrokeIME, an alternative input method for Android OS

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
     * Call this when secondary layout should be switched back to current primary layout.
     */
    public void backToPrimary() {
        mCurrentLayout = mCurrentPrimaryLayout;
    }

    /**
     * Changes current layout to another one.
     * New layout is provided with special actions substitutions.
     * E.g. for secondary layout special action LAYOUT_BACK_TO_PRIMARY
     * is substituted to the particular primary layout.
     * All layouts (primary and secondary) are provided with next and prev
     * primary layouts.
     */
    public void changeLayout(String name) {
        mCurrentLayout = bank.get(name);
        if(mCurrentLayout.type == TYPE_PRIMARY) {
            mCurrentPrimaryLayout = mCurrentLayout;
        } else {
            mCurrentLayout.registerLayoutActionKey(LAYOUT_BACK_TO_PRIMARY, backToPrimaryKeys.get(mCurrentPrimaryLayout.name));
        }

        // if there is not any "prev" or "next" layouts, set them to null and return.
        if(primaryLayouts.size() < 2) {
            mCurrentLayout.registerLayoutActionKey(LAYOUT_PREV_PRIMARY, null);
            mCurrentLayout.registerLayoutActionKey(LAYOUT_NEXT_PRIMARY, null);
            return;
        }

        // current primary layout's index
        int i = primaryLayouts.indexOf(mCurrentPrimaryLayout);

        // find and pass next primary layout name
        int next_i = (i == primaryLayouts.size() - 1) ? 0 : (i + 1);
        mCurrentLayout.registerLayoutActionKey(LAYOUT_NEXT_PRIMARY, primaryLayoutKeys.get(primaryLayouts.get(next_i).name));

        // don't display previous primary layout button if it is equal to next primary layout
        if(primaryLayouts.size() == 2) {
            mCurrentLayout.registerLayoutActionKey(LAYOUT_PREV_PRIMARY, null);
            return;
        }

        // find and pass prev primary layout name
        int prev_i = (i == 0) ? (primaryLayouts.size() - 1) : (i - 1);
        mCurrentLayout.registerLayoutActionKey(LAYOUT_PREV_PRIMARY, primaryLayoutKeys.get(primaryLayouts.get(prev_i).name));
    }

    public LayoutSwitcher() {
        // TODO: load layouts automatically using reflection
        // TODO: load primary layouts from settings.
        // TODO: if no primary layouts are selected in settings explicitely, select it by locale

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
