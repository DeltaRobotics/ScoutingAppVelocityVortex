package org.deltaroboticsftc.scoutingapp_velocityvortex;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import java.io.File;

/**
 * Created by Luke Poellet on 2/6/2017.
 */

public class Settings extends AppCompatActivity
{

    //Settings Save System
        private String gameName;
        private File settingsDir;

    //csvFileSave
        private boolean csvFileSave = true;
        private CheckBox csvFileSaveLocation;

    public Settings()
    {

        //gameName = getString(R.string.game_name);

        settingsDir = new File(Environment.getDataDirectory() + File.separator + "org.deltaroboticsftc.scoutingapp_" + File.separator);
        System.out.println(settingsDir.getPath());

    }

    private void setupIds()
    {

        //csvFileSaveLocation = (CheckBox)findViewById(R.id.box_setting_outputFile);

    }

}
