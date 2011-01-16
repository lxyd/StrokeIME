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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import static org.me.strokeime.StrokeIME.PREFS_TAG;
import static org.me.strokeime.StrokeIME.PREFSKEY_COLOR_THEME_LIGHT;

public class StrokeIMESettings extends PreferenceActivity {
    private static final int DIALOG_ABOUT = 1;
    private String mStrokeIMEVersion = null;
    private View mViewAbout = null;

    private Preference mAboutPreference;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mStrokeIMEVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch(Exception ex) {
            throw new RuntimeException("This is impossible: " + ex.getMessage());
        }

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
        mViewAbout = inflater.inflate(R.layout.about, null);

        addPreferencesFromResource(R.xml.prefs);

        mAboutPreference = (Preference) findPreference("about_preference");
        mAboutPreference.setSummary(
                String.format(getString(R.string.about_preference_summary), mStrokeIMEVersion));

        mAboutPreference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog(DIALOG_ABOUT);
                return true;
            }
        });
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog d = null;
        switch(id) {
            case DIALOG_ABOUT:
                String title = "";
                title = String.format(getString(R.string.about_dialog_title), mStrokeIMEVersion);
                d = new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setView(mViewAbout)
                    .setIcon(R.drawable.icon)
                    .create();
                break;
        }
        return d;
    }
}
