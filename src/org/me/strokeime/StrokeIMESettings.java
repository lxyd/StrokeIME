
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

public class StrokeIMESettings extends PreferenceActivity
    implements OnPreferenceClickListener {
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
        mAboutPreference.setOnPreferenceClickListener(this);
        mAboutPreference.setSummary(
                String.format(getString(R.string.about_preference_summary), mStrokeIMEVersion));
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        showDialog(DIALOG_ABOUT);
        return true;
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
