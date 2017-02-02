package org.poellet.luke.scoutingapp_velocityvortex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Luke Poellet on 1/29/2017.
 * Modified by Luke Poellet on 1/29/2017.
 */

public class InfoManager
{

    private EditText teamNumberLocation;
    private EditText matchNumberLocation;

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
    private File checkFile = null;
    String info = "";

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

    public int createFileInfoAndPath(Context MainActivity)
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

                info = info + "\n" + "----------Autonomous----------" + "\n";

            }

            if(teleStartLocation == loop)
            {

                info = info + "\n" + "----------Tele-Op-------------" + "\n";

            }

            if(endStartLocation == loop)
            {

                info = info + "\n" + "----------End Game------------" + "\n";

            }

            if(arrayOrder[loop].equals(counterKey))
            {

                info = info + arrayOutputText[loop] + " " + arrayCounter[counterPlace].getStringValue()  + "\n";
                counterPlace = counterPlace + 1;

            }else if(arrayOrder[loop].equals(radioSetKey))
            {

                info = info + arrayOutputText[loop] + " " + arrayRadioSet[radioSetPlace].getStringValue()  + "\n";
                radioSetPlace = radioSetPlace + 1;

            }else
            {

                info = "ERROR";
                return -2;

            }

        }

        //System.out.println(teamNumber);
        //System.out.println(matchNumber);
        //System.out.println(info);

        saveFile = new File(Environment.getExternalStorageDirectory().getPath() + File.separator +
                gameName + File.separator + teamNumber + File.separator +
                teamNumber + "-" + matchNumber + ".txt");
        saveDir = new File(Environment.getExternalStorageDirectory().getPath() + File.separator +
                gameName + File.separator + teamNumber);

        switch(this.checkFile())
        {

            //Switch Case 1: File Does Not Exists
            //Switch Case 2: File Exists
            //Switch Case -1: Directory Creation Failed
            //Switch Case -999: Unknown Fail

            case 1:

                System.out.println("File Does Not Exist");
                return 1;

            case 2:

                System.out.println("File Exists");
                return 2;

            case -1:

                return -1;

            case -999:

                return -999;

            default:

                return -999;

        }

    }

    private int checkFile()
    {

        //case 1: File Does Not Exist
        //case 2: File Exists
        //case -1: Directory Creation Failed
        //case -999: Unknown Fail

        try
        {

            boolean makeDir = saveDir.mkdirs();
            boolean dirExists = saveDir.exists();

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

        boolean fileExists = saveFile.exists();

        if(fileExists == false)
        {

            checkFile = saveFile;
            return 1;

        }
        else if(fileExists == true)
        {

            checkFile = saveFile;
            return 2;

        }

        checkFile = null;
        return -999;

    }

    public boolean createAndSaveFile(boolean delExisting, Context MainActivity)
    {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity).create();
        alertDialog.setTitle("Save Failed");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which){dialog.dismiss();}});

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

    public String pullTeamNumber()
    {

        return teamNumberLocation.getText().toString();

    }

    public String pullMatchNumber()
    {

        return matchNumberLocation.getText().toString();

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

}
