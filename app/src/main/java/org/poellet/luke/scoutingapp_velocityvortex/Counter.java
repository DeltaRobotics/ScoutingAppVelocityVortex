package org.poellet.luke.scoutingapp_velocityvortex;

import android.widget.TextView;

/**
 * Created by Luke Poellet on 1/26/2017.
 * Modified by Luke Poellet on 1/26/2017.
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

    private TextView output;

    public Counter(int minPass, boolean hasMinPass, int maxPass, boolean hasMaxPass, int modifierPass, int startValuePass, TextView outputPass)
    {

        min = minPass;
        hasMin = hasMinPass;

        max = maxPass;
        hasMax = hasMaxPass;

        modifier = modifierPass;

        startValue = startValuePass;

        output = outputPass;

        this.setValue(startValuePass);

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

        output.setText(this.getStringValue());

    }

    public String getStringValue()
    {

        return Integer.toString(curValue);

    }

    public int getIntegerValue()
    {

        return curValue;

    }

    public void reset()
    {

        this.setValue(startValue);

    }

}
