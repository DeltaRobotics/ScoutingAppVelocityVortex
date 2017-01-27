package org.poellet.luke.scoutingapp_velocityvortex;

import android.widget.RadioButton;

/**
 * Created by Luke Poellet on 1/26/2017.
 * Modified by Luke Poellet on 1/26/2017.
 */

public class RadioSet
{

    private RadioButton[] buttonsArray = new RadioButton[20];

    private String[] specialTextArray = new String[20];
    private boolean hasSpecialText = false;

    private int arrayLength = 0;
    private boolean specialTextError = false;

    public RadioSet(boolean hasSpecialTextPass)
    {

        hasSpecialText = hasSpecialTextPass;

    }

    public void addRadioButton(RadioButton newRadioButton)
    {

        buttonsArray[arrayLength] = newRadioButton;

        if(hasSpecialText == true)
        {

            specialTextArray[arrayLength] = "ERROR";
            specialTextError = true;

        }

        arrayLength = arrayLength + 1;

    }

    public void addRadioButtonWithText(RadioButton newRadioButton, String newSpecialText)
    {

        buttonsArray[arrayLength] = newRadioButton;

        specialTextArray[arrayLength] = newSpecialText;

        arrayLength = arrayLength + 1;

    }

    public String getStringValue()
    {

        if(arrayLength <= 0)
        {

            return "RADIO_ERROR";

        }

        int checked= getChecked();

        if(checked == -1)
        {

            return "RADIO_ERROR";

        }

        if(specialTextError == false)
        {

            return specialTextArray[checked];

        }

        return Integer.toString(checked);

    }

    private int getChecked()
    {

        int forLoop;

        if(arrayLength == 0)
        {

            return -1;

        }

        for(forLoop= 0; forLoop < arrayLength; forLoop++)
        {

            if(buttonsArray[forLoop].isChecked())
            {

                return forLoop;

            }

        }

        return -1;

    }

}
