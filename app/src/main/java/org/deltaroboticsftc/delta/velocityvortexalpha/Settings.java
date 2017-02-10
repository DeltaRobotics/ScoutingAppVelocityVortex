package org.deltaroboticsftc.delta.velocityvortexalpha;

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
        private final String fileStartText = "Delta Robotics Scouting App Settings";

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

        saveInfo.append(fileStartText + "\n");

        saveInfo.append(fileCSVText + csvFileSaveDefault + "\n");
        csvFileSave = csvFileSaveDefault;

    }

    private void createSaveInfo()
    {

        saveInfo.delete(0, saveInfo.length());

        saveInfo.append(fileStartText  + "\n");

        saveInfo.append(fileCSVText + csvFileSave + "\n");

        this.saveSettings();

    }

    private void saveSettings()
    {

        try
        {

            FileOutputStream contents = new FileOutputStream(settingsFile);
            contents.write(saveInfo.toString().getBytes());
            contents.flush();
            contents.close();

        }catch (Exception e)
        {

            System.out.println(e);

        }

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
                System.out.println("Settings File Incorrectly Setup: " + line + " != " + fileStartText);
                return;

            }

            line = scan.nextLine();
            settingDivider = line.indexOf(":");
            System.out.println("Index of \":\") " + settingDivider + ", In) " + line);
            if(line.substring(0, settingDivider + 1).equals(fileCSVText) == true)
            {

                if(line.substring(settingDivider + 1).equals("true") == true)
                {

                    csvFileSave = true;

                }
                else if (line.substring(settingDivider + 1).equals("false") == true)
                {

                    csvFileSave = false;

                }
                else
                {

                    csvFileSave = csvFileSaveDefault;

                }

                System.out.println("csvFileSave Set to: " + csvFileSave);

            }
            else
            {

                this.createDefaultSaveInfo();
                System.out.println("Settings File Incorrectly Setup: " + line + " != " + fileCSVText);
                return;

            }

            scan.close();

        }catch (Exception e)
        {

            System.out.println(e);

        }

    }

    public void pageLoad()
    {

        csvFileSaveLocation.setChecked(csvFileSave);

    }

    public void setCsvFileSaveLocation(CheckBox location)
    {

        csvFileSaveLocation = location;

    }

    public void setCsvFileSave()
    {

        csvFileSave = csvFileSaveLocation.isChecked();
        this.createSaveInfo();

    }

    public boolean getCsvFileSave()
    {

        return csvFileSave;

    }

}
