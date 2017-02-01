package org.poellet.luke.scoutingapp_velocityvortex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.widget.EditText;

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

    public void saveFile(Context MainActivity)
    {

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
            return;

        }

        if(matchNumber.equals(""))
        {

            this.matchNumberClaimFocus();
            alertDialogMissingData.setMessage("Please Enter a Match Number");
            alertDialogMissingData.show();
            return;

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
                loop = orderNext;

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

        try
        {

            if(saveDir.mkdirs() == true)
            {

                System.out.println("Directory Created");

            }
            else
            {

                System.out.println("Directory Exists or Creation Failed");

            }

        }
        catch (Exception e)
        {

            System.out.println(e);

        }

        switch(this.checkFile(saveFile))
        {

            //case 1: File Does Not Exists
            //case 2: File Exists
            //case -999: Unknown Fail

            case 1:

                this.createAndSaveFile(info, saveFile, false);

                break;

            case 2:

                System.out.println("File Exists");

                AlertDialog alertDialogFileExists = new AlertDialog.Builder(MainActivity).create();
                alertDialogFileExists.setTitle("File Exists");
                alertDialogFileExists.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which)
                {

                    dialog.dismiss();
                    matchNumberClaimFocus();

                }});


                alertDialogFileExists.setButton(AlertDialog.BUTTON_POSITIVE, "OVERWRITE", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which)
                {

                    dialog.dismiss();
                    createAndSaveFile(info, saveFile, true);

                }});

                alertDialogFileExists.setMessage("Do You Want to Overwrite This File?");
                alertDialogFileExists.show();

                break;

            case -999:
                break;

            default:
                break;

        }

    }

    private int checkFile(File check)
    {

        //case 1: File Does Not Exist
        //case 2: File Exists
        //case -999: Unknown Fail

        if(check.exists() == false)
        {

            return 1;

        }
        else if(check.exists() == true)
        {

            return 2;

        }

        return -999;

    }

    private boolean createAndSaveFile(String info, File saveFile, boolean delExisting)
    {

        try
        {

            if(delExisting == true)
            {

                if(saveFile.delete() == false)
                {

                    System.out.println("Existing File Deletion Failed");
                    return false;

                }

                System.out.println("Deleted Existing File");

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

                System.out.println("File Creation Failed");
                return false;

            }

        }
        catch (Exception e)
        {

            System.out.println(e);

        }

        return false;

    }

    private String pullTeamNumber()
    {

        return teamNumberLocation.getText().toString();

    }

    private String pullMatchNumber()
    {

        return matchNumberLocation.getText().toString();

    }

    private void teamNumberClaimFocus()
    {

        teamNumberLocation.clearFocus();
        teamNumberLocation.requestFocus();

    }

    private void matchNumberClaimFocus()
    {

        matchNumberLocation.clearFocus();
        matchNumberLocation.requestFocus();

    }

}
