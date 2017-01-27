package org.poellet.luke.scoutingapp_velocityvortex;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Luke Poellet on 1/26/2017.
 * Modified by Luke Poellet on 1/26/2017.
 * Layout Modified by Nolan Eastburn on 1/26/2017.
 */

public class MainActivity extends AppCompatActivity {

    //Auto-------------------------------------------

        //Corner Vortex
        private TextView autoCornerOut;
        private Counter autoCorner;
        public void autoCornerUp(View v) {autoCorner.addModifier();}
        public void autoCornerDown(View v) {autoCorner.removeModifier();}

        //Center Vortex
        private TextView autoCenterOut;
        private Counter autoCenter;
        public void autoCenterUp(View v) {autoCenter.addModifier();}
        public void autoCenterDown(View v) {autoCenter.removeModifier();}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setup();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setup()
    {

        //Auto

            //Corner Vortex
            autoCornerOut = (TextView)findViewById(R.id.counter_auto_corner);
            autoCorner = new Counter(0, true, 0, false, 1, 0, autoCornerOut);

            //Corner Vortex
            autoCenterOut = (TextView)findViewById(R.id.counter_auto_center);
            autoCenter = new Counter(0, true, 0, false, 1, 0, autoCenterOut);

    }
}
