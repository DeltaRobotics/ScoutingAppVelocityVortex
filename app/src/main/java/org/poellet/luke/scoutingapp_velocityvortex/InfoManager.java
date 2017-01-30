package org.poellet.luke.scoutingapp_velocityvortex;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

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
        String info = "";
        int loop;

        counterPlace = 0;
        radioSetPlace = 0;

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity).create();
        alertDialog.setTitle("Missing Data");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which){dialog.dismiss();}});

        if(teamNumber.equals(""))
        {

            teamNumberLocation.clearFocus();
            teamNumberLocation.requestFocus();
            alertDialog.setMessage("Please Enter a Team Number");
            alertDialog.show();
            return;

        }

        if(matchNumber.equals(""))
        {

            teamNumberLocation.clearFocus();
            matchNumberLocation.requestFocus();
            alertDialog.setMessage("Please Enter a Match Number");
            alertDialog.show();
            return;

        }

        for(loop = 0; loop < orderNext; loop++)
        {

            if(autoStartLocation == loop)
            {

                info = info + "\n" + "Autonomous" + "\n";

            }

            if(teleStartLocation == loop)
            {

                info = info + "\n" + "Tele-Op" + "\n";

            }

            if(endStartLocation == loop)
            {

                info = info + "\n" + "End Game" + "\n";

            }

            if(arrayOrder[loop].equals(counterKey))
            {

                info = info + arrayOutputText[loop] + "\n" + arrayCounter[counterPlace].getStringValue()  + "\n";
                counterPlace = counterPlace + 1;

            }else if(arrayOrder[loop].equals(radioSetKey))
            {

                info = info + arrayOutputText[loop] + "\n" + arrayRadioSet[radioSetPlace].getStringValue()  + "\n";
                radioSetPlace = radioSetPlace + 1;

            }else
            {

                info = "ERROR";
                loop = orderNext;

            }

        }

        System.out.println(teamNumber);
        System.out.println(matchNumber);
        System.out.println(info);

    }

    private String pullTeamNumber()
    {

        return teamNumberLocation.getText().toString();

    }

    private String pullMatchNumber()
    {

        return matchNumberLocation.getText().toString();

    }

}
