package org.deltaroboticsftc.scoutingapp_velocityvortex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Luke Poellet on 1/29/2017.
 * Modified by Luke Poellet on 1/29/2017.
 */

public class InfoManager
{

    private EditText teamNumberLocation;
    private EditText matchNumberLocation;
    private String teamNum;
    private String matchNum;

    private int autoStartLocation;
    private int teleStartLocation;
    private int endStartLocation;

    private Counter[] arrayCounter = new Counter[20];
    private int counterNext = 0;
    private int counterPlace;
    private final String counterKey = "counter";

    private RadioSet[] arrayRadioSet = new RadioSet[20];
    private int radioSetNext = 0;
    private int radioSetPlace;
    private final String radioSetKey = "radio";

    private String[] arrayOrder = new String[20];
    private int orderNext = 0;

    private String[] arrayOutputText = new String[20];
    private int outputTextNext = 0;

    private String gameName;

    private File saveFile;
    private File saveDir;
    private File saveDataFile;
    private File saveDataDir;

    private File checkFile = null;
    private File checkDataFile = null;
    private String info = "";
    private String infoCSV = "";

    public InfoManager(String gameNamePass)
    {

        gameName = gameNamePass;

    }

    public void setTeamNumberLoction(EditText loaction)
    {

        teamNumberLocation = loaction;

    }

    public void setMatchNumberLocation(EditText loaction)
    {

        matchNumberLocation = loaction;

    }

    public void addCounter(Counter c, String s)
    {

        arrayOrder[orderNext] = counterKey;
        orderNext = orderNext + 1;

        arrayCounter[counterNext] = c;
        counterNext = counterNext + 1;

        arrayOutputText[outputTextNext] = s;
        outputTextNext = outputTextNext + 1;

    }

    public void addRadioSet(RadioSet r, String s)
    {

        arrayOrder[orderNext] = radioSetKey;
        orderNext = orderNext + 1;

        arrayRadioSet[radioSetNext] = r;
        radioSetNext = radioSetNext + 1;

        arrayOutputText[outputTextNext] = s;
        outputTextNext = outputTextNext + 1;

    }

    public void startAutoSection()
    {

        autoStartLocation = orderNext;

    }

    public void startTeleSection()
    {

        teleStartLocation = orderNext;

    }

    public void startEndSection()
    {

        endStartLocation = orderNext;

    }

    public void resetValues()
    {

        int loop;

        teamNumberLocation.setText(null);
        teamNumberLocation.clearFocus();
        matchNumberLocation.setText(null);
        matchNumberLocation.clearFocus();

        for(loop = 0; loop < counterNext; loop++)
        {

            arrayCounter[loop].resetToDefault();

        }

        for(loop = 0; loop < radioSetNext; loop++)
        {

            arrayRadioSet[loop].resetToDefault();

        }

    }

    public int createFileInfoAndPath(Context MainActivity, boolean saveCSVData)
    {

        //Return Case 2: File Exists and File Setup Finished
        //Return Case 1: File Does Not Exist and File Setup Finished
        //Return Case 0: Do Nothing
        //Return Case -1: Directory Creation Failed
        //Return Case -2: Info Creation Failed
        //Return Case -999: Unknown Fail

        String teamNumber = this.pullTeamNumber();
        String matchNumber = this.pullMatchNumber();
        int loop;
        int returnKey = -999;
        info = "";
        infoCSV = "";

        counterPlace = 0;
        radioSetPlace = 0;

        AlertDialog alertDialogMissingData = new AlertDialog.Builder(MainActivity).create();
        alertDialogMissingData.setTitle("Missing Data");
        alertDialogMissingData.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which){dialog.dismiss();}});

        if(teamNumber.equals(""))
        {

            this.teamNumberClaimFocus();
            alertDialogMissingData.setMessage("Please Enter a Team Number");
            alertDialogMissingData.show();
            return 0;

        }

        if(matchNumber.equals(""))
        {

            this.matchNumberClaimFocus();
            alertDialogMissingData.setMessage("Please Enter a Match Number");
            alertDialogMissingData.show();
            return 0;

        }

        for(loop = 0; loop < orderNext; loop++)
        {

            if(autoStartLocation == loop)
            {

                info = info + "\n" + "---------- Autonomous ----------" + "\n";

            }

            if(teleStartLocation == loop)
            {

                info = info + "\n" + "---------- Tele-Op -------------" + "\n";

            }

            if(endStartLocation == loop)
            {

                info = info + "\n" + "---------- End Game ------------" + "\n";

            }

            if(arrayOrder[loop].equals(counterKey))
            {

                info = info + arrayOutputText[loop] + " " + arrayCounter[counterPlace].getStringValue()  + "\n";
                infoCSV = infoCSV + arrayCounter[counterPlace].getIntegerValue() + ",";
                counterPlace = counterPlace + 1;

            }else if(arrayOrder[loop].equals(radioSetKey))
            {

                info = info + arrayOutputText[loop] + " " + arrayRadioSet[radioSetPlace].getStringValue()  + "\n";
                infoCSV = infoCSV + arrayRadioSet[radioSetPlace].getIntegerValue() + ",";
                radioSetPlace = radioSetPlace + 1;

            }else
            {

                info = "ERROR";
                infoCSV = "ERROR";
                return -2;

            }

        }

        //System.out.println(teamNumber);
        //System.out.println(matchNumber);
        //System.out.println(info);

        System.out.println("Running Directory Creation");

        saveDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator +
                gameName + File.separator + teamNumber);
        saveFile = new File(saveDir.getPath() + File.separator + teamNumber + "-" + matchNumber + ".txt");

        switch(this.checkFile(saveFile, saveDir, false))
        {

            //Switch Case 1: File Does Not Exists
            //Switch Case 2: File Exists
            //Switch Case -1: Directory Creation Failed
            //Switch Case -999: Unknown Fail

            case 1:

                System.out.println("File Does Not Exist");
                returnKey = 1;
                break;

            case 2:

                System.out.println("File Exists");
                returnKey = 2;
                break;

            case -1:

                returnKey = -1;
                break;

            case -999:

                returnKey = -999;
                break;

            default:

                returnKey = -999;
                break;

        }


        if(saveCSVData == true && returnKey != -999 && returnKey != -1)
        {

            System.out.println("Running Directory Creation for CSV File");

            saveDataDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator +
                    gameName + File.separator + "CSV Data");
            saveDataFile = new File(saveDataDir.getPath() + File.separator + teamNumber + "-" + matchNumber + ".txt");

            switch(this.checkFile(saveDataFile, saveDataDir, true))
            {

                //Switch Case 1: File Does Not Exists
                //Switch Case 2: File Exists
                //Switch Case -1: Directory Creation Failed
                //Switch Case -999: Unknown Fail

                case 1:

                    System.out.println("CSV File Does Not Exist");
                    break;

                case 2:

                    System.out.println("CSV File Exists");
                    break;

                case -1:

                    returnKey = -1;
                    break;

                case -999:

                    returnKey = -999;
                    break;

                default:

                    returnKey = -999;
                    break;

            }

        }

        return returnKey;

    }

    private int checkFile(File file, File dir, boolean csv)
    {

        //case 1: File Does Not Exist
        //case 2: File Exists
        //case -1: Directory Creation Failed
        //case -999: Unknown Fail

        try
        {

            boolean makeDir = dir.mkdirs();
            boolean dirExists = dir.exists();

            if(dirExists == true)
            {

                System.out.println("Directory Exists");

            }
            else if(makeDir == true && dirExists == false)
            {

                System.out.println("Directory Created");

            }
            else
            {

                System.out.println("Failed to Create Directory(s)");
                checkFile = null;
                return -1;

            }

        }
        catch (Exception e)
        {

            System.out.println(e);

        }

        boolean fileExists = file.exists();

        if(csv == false)
        {

            checkFile = file;

        }
        else if(csv == true)
        {

            checkDataFile = file;

        }

        if(fileExists == false)
        {

            return 1;

        }
        else if(fileExists == true)
        {

            return 2;

        }

        checkFile = null;
        checkDataFile = null;
        System.out.println("Create Directory Function Failed");
        return -999;

    }

    public boolean createAndSaveFile(boolean delExisting, Context MainActivity, boolean saveDataFile)
    {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity).create();
        alertDialog.setTitle("Save Failed");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which){dialog.dismiss();}});

        System.out.println("Will Save CSV File: " + saveDataFile);

        if(saveFile == checkFile)
        {

            try
            {

                if(delExisting == true)
                {

                    if(saveFile.delete() == true)
                    {

                        System.out.println("Deleted Existing File");

                    }
                    else
                    {

                        alertDialog.setMessage("Failed to Delete Old File");
                        alertDialog.show();
                        System.out.println("Existing File Deletion Failed");
                        return false;

                    }


                }

                if(saveFile.createNewFile() == true)
                {

                    System.out.println("File Created");
                    FileOutputStream contents = new FileOutputStream(saveFile);
                    contents.write(info.getBytes());
                    contents.flush();
                    contents.close();

                    if(saveDataFile == true)
                    {

                        return this.createAndSaveCSVDataFile(delExisting, MainActivity);

                    }

                    return true;

                }
                else
                {

                    alertDialog.setMessage("Failed to Create New File");
                    alertDialog.show();
                    System.out.println("File Creation Failed");
                    return false;

                }

            }
            catch (Exception e)
            {

                System.out.println(e);

            }

        }

        alertDialog.setMessage("Unable to Verify Save Directory");
        alertDialog.show();

        return false;

    }

    private boolean createAndSaveCSVDataFile(boolean delExisting, Context MainActivity)
    {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity).create();
        alertDialog.setTitle("CSV Save Failed");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which){dialog.dismiss();}});

        if(saveDataFile == checkDataFile)
        {

            try
            {

                if(delExisting == true)
                {

                    if(saveDataFile.delete() == true)
                    {

                        System.out.println("Deleted Existing CSV File");

                    }
                    else
                    {

                        alertDialog.setMessage("Failed to Delete Old CSV File");
                        alertDialog.show();
                        System.out.println("Existing CSV File Deletion Failed");
                        return false;

                    }


                }

                if(saveDataFile.createNewFile() == true)
                {

                    System.out.println("CSV File Created");
                    FileOutputStream contents = new FileOutputStream(saveDataFile);
                    contents.write(infoCSV.getBytes());
                    contents.flush();
                    contents.close();
                    return true;

                }
                else
                {

                    alertDialog.setMessage("Failed to Create New CSV File");
                    alertDialog.show();
                    System.out.println("CSV File Creation Failed");
                    return false;

                }

            }
            catch (Exception e)
            {

                System.out.println(e);

            }

        }

        alertDialog.setMessage("Unable to Verify CSV Save Directory");
        alertDialog.show();

        return false;

    }

    public String pullTeamNumber()
    {

        return teamNumberLocation.getText().toString();

    }

    public String pullMatchNumber()
    {

        return matchNumberLocation.getText().toString();

    }

    public String getInfo()
    {

        return info;

    }

    public void teamNumberClaimFocus()
    {

        teamNumberLocation.clearFocus();
        teamNumberLocation.requestFocus();

    }

    public void matchNumberClaimFocus()
    {

        matchNumberLocation.clearFocus();
        matchNumberLocation.requestFocus();

    }

    public String getGameName()
    {

        return gameName;

    }


}
