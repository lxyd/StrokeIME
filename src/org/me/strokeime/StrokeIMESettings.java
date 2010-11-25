
package org.me.strokeime;

import android.app.Activity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceScreen;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.Preference.OnPreferenceClickListener;
import static org.me.strokeime.StrokeIME.PREFS_TAG;
import static org.me.strokeime.StrokeIME.PREFSKEY_COLOR_THEME_LIGHT;

public class StrokeIMESettings extends PreferenceActivity {

    private PreferenceScreen mAboutPreference;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        mAboutPreference = (PreferenceScreen) findPreference("about_preference");
        try {
            mAboutPreference.setSummary(
                    String.format(getString(R.string.about_preference_summary),
                        getPackageManager().getPackageInfo(getPackageName(), 0).versionName));
        } catch(Exception ex) {
            throw new RuntimeException("This is impossible: " + ex.getMessage());
        }
    }
}
