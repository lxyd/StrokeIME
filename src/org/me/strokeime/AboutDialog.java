
package org.me.strokeime;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class AboutDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            setTitle(String.format(getString(R.string.about_dialog_title),
                        getPackageManager().getPackageInfo(getPackageName(), 0).versionName));
        } catch(Exception ex) {
            throw new RuntimeException("This is impossible: " + ex.getMessage());
        }
        setContentView(R.layout.about);

        //setIcon(R.drawable.icon);
        //ImageView image = (ImageView) findViewById(R.id.image);
        //image.setImageResource(R.drawable.icon);
    }
}

