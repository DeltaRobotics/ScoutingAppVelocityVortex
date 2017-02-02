package org.deltaroboticsftc.scoutingapp_velocityvortex;

import android.widget.RadioButton;

/**
 * Created by Luke Poellet on 1/26/2017.
 * Modified by Luke Poellet on 1/29/2017.
 */

public class RadioSet
{

    private RadioButton[] buttonsArray = new RadioButton[20];

    private String[] specialTextArray = new String[20];
    private boolean hasSpecialText = false;

    private int arrayLength = 0;
    private boolean specialTextError = false;

    private int startChecked;

    public RadioSet(boolean hasSpecialTextPass)
    {

        hasSpecialText = hasSpecialTextPass;

    }

    public void resetButtonsArray()
    {

        arrayLength = 0;

    }

    public void addRadioButton(RadioButton newRadioButton, boolean checked)
    {

        buttonsArray[arrayLength] = newRadioButton;

        if(hasSpecialText == true)
        {

            specialTextArray[arrayLength] = "ERROR";
            specialTextError = true;

        }

        buttonsArray[arrayLength].setChecked(false);

        if(checked == true)
        {

            startChecked = arrayLength;
            this.resetToDefault();

        }

        arrayLength = arrayLength + 1;

    }

    public void addRadioButtonWithText(RadioButton newRadioButton, String newSpecialText, boolean checked)
    {

        buttonsArray[arrayLength] = newRadioButton;

        specialTextArray[arrayLength] = newSpecialText;

        buttonsArray[arrayLength].setChecked(false);

        if(checked == true)
        {

            startChecked = arrayLength;
            this.resetToDefault();

        }

        arrayLength = arrayLength + 1;

    }

    public String getStringValue()
    {

        if(arrayLength <= 0 || specialTextError == true)
        {

            return "RADIO_ERROR";

        }

        int checked= getChecked();

        if(checked == -1)
        {

            return "RADIO_ERROR";

        }

        if(hasSpecialText == true)
        {

            return specialTextArray[checked];

        }

        return Integer.toString(checked);

    }

    private int getChecked()
    {

        int loop;

        if(arrayLength == 0)
        {

            return -1;

        }

        for(loop= 0; loop < arrayLength; loop++)
        {

            if(buttonsArray[loop].isChecked())
            {

                return loop;

            }

        }

        return -1;

    }

    public void resetToDefault()
    {

        buttonsArray[startChecked].setChecked(true);

    }

}