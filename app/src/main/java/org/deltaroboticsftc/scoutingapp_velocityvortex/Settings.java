package org.deltaroboticsftc.scoutingapp_velocityvortex;

import android.os.Environment;
import android.widget.CheckBox;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Luke Poellet on 2/6/2017.
 */

public class Settings
{

    //Settings Save System
        private File settingsDir;
        private File settingsFile;
        private StringBuilder saveInfo = new StringBuilder();

    //csvFileSave
        private boolean csvFileSave = true;
        private CheckBox csvFileSaveLocation;

    public Settings()
    {

        settingsDir = new File(Environment.getDataDirectory() + File.separator + "org.deltaroboticsftc.scoutingapp" + File.separator);
        System.out.println(settingsDir.getPath());

        settingsFile = new File(settingsDir.getPath() + "Settings.txt");
        System.out.println(settingsFile.getPath());
        System.out.println(settingsFile.getName());

        try
        {

            if(settingsFile.exists() == false)
            {

                settingsFile.mkdirs();

                this.setSaveInfo();

                FileOutputStream contents = new FileOutputStream(settingsFile);
                contents.write(saveInfo.toString().getBytes());
                contents.flush();
                contents.close();

            }

        }catch (Exception e)
        {

            System.out.println(e);

        }

    }


    private void setSaveInfo()
    {

        saveInfo.delete(0, saveInfo.length());

        saveInfo.append("Delta Robotics Scouting App Settings\n");

        saveInfo.append("Save CSV File:" + csvFileSave + "\n");

    }


}
