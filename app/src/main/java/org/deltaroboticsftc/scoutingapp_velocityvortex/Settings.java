package org.deltaroboticsftc.scoutingapp_velocityvortex;

import android.os.Environment;
import android.widget.CheckBox;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * Created by Luke Poellet on 2/6/2017.
 */

public class Settings
{

    //Settings Save System
        private File settingsDir;
        private File settingsFile;
        private StringBuilder saveInfo = new StringBuilder();
        private InfoManager infoManager;
        private final String fileStartText = "Delta Robotics Scouting App Settings\n";

    //csvFileSave
        private final String fileCSVText = "Save CSV File:";
        private boolean csvFileSaveDefault = true;
        private boolean csvFileSave = csvFileSaveDefault;
        private CheckBox csvFileSaveLocation;

    public Settings(InfoManager infoManagerPass)
    {

        infoManager = infoManagerPass;

        settingsDir = new File(Environment.getExternalStorageDirectory() + File.separator + infoManager.getGameName() + File.separator);
        System.out.println(settingsDir.getPath());

        settingsFile = new File(settingsDir.getPath() + File.separator + "Settings.txt");
        System.out.println(settingsFile.getPath());
        System.out.println(settingsFile.getName());

        try
        {

            if(settingsFile.exists() == false)
            {

                settingsDir.mkdirs();

                this.createDefaultSaveInfo();

                FileOutputStream contents = new FileOutputStream(settingsFile);
                contents.write(saveInfo.toString().getBytes());
                contents.flush();
                contents.close();

            }else
            {

                this.readSettings();

            }

        }catch (Exception e)
        {

            System.out.println(e);

        }

    }

    private void createDefaultSaveInfo()
    {

        saveInfo.delete(0, saveInfo.length());

        saveInfo.append(fileStartText);

        saveInfo.append(fileCSVText + csvFileSaveDefault + "\n");
        csvFileSave = csvFileSaveDefault;

    }

    private void createSaveInfo()
    {

        saveInfo.delete(0, saveInfo.length());

        saveInfo.append(fileStartText);

        saveInfo.append(fileCSVText + csvFileSave + "\n");

        this.saveSettings();

    }

    private void saveSettings()
    {



    }

    private void readSettings()
    {

        try
        {

            Scanner scan = new Scanner(settingsFile);
            String line;
            int settingDivider;

            line = scan.nextLine();
            if(line.equals(fileStartText) != true)
            {

                this.createDefaultSaveInfo();
                System.out.println("Settings File Incorrectly Setup");
                return;

            }

            line = scan.nextLine();
            settingDivider = line.indexOf("|");
            if(line.substring(0, settingDivider).equals(fileCSVText) == true)
            {

                if(line.substring(settingDivider).equals("true") == true)
                {

                    csvFileSave = true;

                }
                else if (line.substring(settingDivider).equals("false") == true)
                {

                    csvFileSave = false;

                }
                else
                {

                    csvFileSave = csvFileSaveDefault;

                }

                csvFileSaveLocation.setChecked(csvFileSave);

            }
            else
            {

                this.createDefaultSaveInfo();
                System.out.println("Settings File Incorrectly Setup");
                return;

            }

            scan.close();
            this.createSaveInfo();

        }catch (Exception e)
        {

            System.out.println(e);

        }

    }

    public void setCsvFileSaveLocation(CheckBox location)
    {

        csvFileSaveLocation = location;

    }

}
