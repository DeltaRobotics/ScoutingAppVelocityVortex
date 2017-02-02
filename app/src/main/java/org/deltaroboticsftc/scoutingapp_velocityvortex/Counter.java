package org.deltaroboticsftc.scoutingapp_velocityvortex;

import android.widget.TextView;

/**
 * Created by Luke Poellet on 1/26/2017.
 * Modified by Luke Poellet on 1/29/2017.
 */

public class Counter
{

    private int min;
    private boolean hasMin;

    private int max;
    private boolean hasMax;

    private int modifier;

    private int startValue;
    private int curValue;

    private TextView output = null;

    public Counter(int minPass, boolean hasMinPass, int maxPass, boolean hasMaxPass, int modifierPass, int startValuePass)
    {

        min = minPass;
        hasMin = hasMinPass;

        max = maxPass;
        hasMax = hasMaxPass;

        modifier = modifierPass;

        startValue = startValuePass;

    }

    public void addModifier()
    {

        this.setValue(curValue + modifier);

    }

    public void removeModifier()
    {

        this.setValue(curValue - modifier);

    }

    public void setValue(int v)
    {

        if(hasMin == true && min > v)
        {

            curValue = min;

        }else if(hasMax == true && max < v)
        {

            curValue = max;
        }else
        {

            curValue = v;

        }

        this.changeOutput();

    }

    private void changeOutput()
    {

        if(output != null)
        {

            output.setText(this.getStringValue());
            //System.out.println(curValue);

        }

    }

    public String getStringValue()
    {

        return Integer.toString(curValue);

    }

    public int getIntegerValue()
    {

        return curValue;

    }

    public void setOutput(TextView outputPass)
    {

        output = outputPass;
        this.changeOutput();

    }

    public void resetToDefault()
    {

        this.setValue(startValue);

    }

}
