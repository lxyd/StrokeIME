
package org.me.strokeime;

import android.app.Activity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.Preference.OnPreferenceClickListener;
import static org.me.strokeime.StrokeIME.PREFS_TAG;
import static org.me.strokeime.StrokeIME.PREFSKEY_COLOR_THEME_LIGHT;

public class StrokeIMESettings extends PreferenceActivity {

    //private CheckBoxPreference mUseColorThemeLight;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        //mUseColorThemeLight = (CheckBoxPreference) findPreference(PREFSKEY_COLOR_THEME_LIGHT);
        //setContentView(R.layout.settings);
    }
}
