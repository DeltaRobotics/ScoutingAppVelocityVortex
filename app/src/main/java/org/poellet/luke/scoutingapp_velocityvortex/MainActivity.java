package org.poellet.luke.scoutingapp_velocityvortex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

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

        //Capball Moved
            private RadioSet autoCapballMoved;
            private RadioButton autoCapballMoved_No;
            private RadioButton autoCapballMoved_Yes;

        //Beacons Scored
            private RadioSet autoBeaconsScored;
            private RadioButton autoBeaconsScored_0;
            private RadioButton autoBeaconsScored_1;
            private RadioButton autoBeaconsScored_2;

        //Parking
            private RadioSet autoParking;
            private RadioButton autoParking_not;
            private RadioButton autoParking_corner_par;
            private RadioButton autoParking_corner;
            private RadioButton autoParking_center_par;
            private RadioButton autoParking_center;


    //Tele

        //Corner Vortex
            private TextView teleCornerOut;
            private Counter teleCorner;
            public void teleCornerUp(View v) {teleCorner.addModifier();}
            public void teleCornerDown(View v) {teleCorner.removeModifier();}

        //Center Vortex
            private TextView teleCenterOut;
            private Counter teleCenter;
            public void teleCenterUp(View v) {teleCenter.addModifier();}
            public void teleCenterDown(View v) {teleCenter.removeModifier();}

        //Center Vortex
            private TextView teleBeaconsPressedOut;
            private Counter teleBeaconsPressed;
            public void teleBeaconsPressedUp(View v) {teleBeaconsPressed.addModifier();}
            public void teleBeaconsPressedDown(View v) {teleBeaconsPressed.removeModifier();}

        //Beacons Scored
            private RadioSet teleBeaconsScored;
            private RadioButton teleBeaconsScored_0;
            private RadioButton teleBeaconsScored_1;
            private RadioButton teleBeaconsScored_2;
            private RadioButton teleBeaconsScored_3;
            private RadioButton teleBeaconsScored_4;


    //End

        //Capball Status
            private RadioSet endCapballStatus;
            private RadioButton endCapballStatus_not;
            private RadioButton endCapballStatus_low;
            private RadioButton endCapballStatus_high;
            private RadioButton endCapballStatus_capped;


    //Setup System Controls------DON'T CHANGE----------

        private TextView banner_title;
        private String game_name;

        private InfoManager infoManager;
        private EditText teamNumber;
        private EditText matchNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActionBar();

        banner_title = (TextView)findViewById(R.id.text_banner_game_name);
        game_name = getString(R.string.game_name);
        banner_title.setText(game_name);

        infoManager = new InfoManager();

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
        if (id == R.id.action_moveTo_settings)
        {
            setContentView(R.layout.activity_settings);
            this.setupActionBar();
            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText("Settings");
            return true;
        }

        if (id == R.id.action_moveTo_about)
        {

            setContentView(R.layout.activity_about);
            this.setupActionBar();
            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText("About");
            return true;
        }

        //Temporary-----------------------------------------
        if (id == R.id.action_moveTo_main)
        {

            setContentView(R.layout.activity_main);
            this.setupActionBar();
            this.setupMatch();
            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText(game_name);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    private void setupActionBar()
    {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }



    private void setup()
    {

        //Auto

            //Corner Vortex
                autoCorner = new Counter(0, true, 0, false, 1, 0);

            //Corner Vortex
                autoCenter = new Counter(0, true, 0, false, 1, 0);

            //Capball Moved
                autoCapballMoved = new RadioSet(true);

            //Beacons Scored
                autoBeaconsScored = new RadioSet(false);

            //Parking
                autoParking = new RadioSet(true);


        //Tele

            //Corner Vortex
                teleCorner = new Counter(0, true, 0, false, 1, 0);

            //Corner Vortex
                teleCenter = new Counter(0, true, 0, false, 1, 0);

            //Beacons Pressed
                teleBeaconsPressed = new Counter(0, true, 0, false, 1, 0);
        
            //Beacons Scored
                teleBeaconsScored = new RadioSet(false);


        //End

            //Capball Status
                endCapballStatus = new RadioSet(true);

        this.setupMatch();

        //infoManager

            infoManager.startAutoSection();
            infoManager.addCounter(autoCorner, "Particles Scored in Corner Vortex:");
            infoManager.addCounter(autoCenter, "Particles Scored in Center Vortex:");
            infoManager.addRadioSet(autoCapballMoved, "Was the Capball Moved:");
            infoManager.addRadioSet(autoBeaconsScored, "Number of Beacons Scored:");
            infoManager.addRadioSet(autoParking, "Parked:");

            infoManager.startTeleSection();
            infoManager.addCounter(teleCorner, "Particles Scored in Corner Vortex:");
            infoManager.addCounter(teleCenter, "Particles Scored in Center Vortex:");
            infoManager.addCounter(teleBeaconsPressed, "Beacons Pressed:");
            infoManager.addRadioSet(teleBeaconsScored, "Number of Beacons Scored:");

            infoManager.startEndSection();
            infoManager.addRadioSet(endCapballStatus, "Capball Scored:");

    }

    private void setupMatch()
    {

        //Auto

            //Corner Vortex
                autoCornerOut = (TextView)findViewById(R.id.counter_auto_corner);
                autoCorner.setOutput(autoCornerOut);

            //Center Vortex
                autoCenterOut = (TextView)findViewById(R.id.counter_auto_center);
                autoCenter.setOutput(autoCenterOut);

            //Capball Moved
                autoCapballMoved.resetButtonsArray();
                autoCapballMoved_No = (RadioButton)findViewById(R.id.radio_auto_cbmoved_no);
                autoCapballMoved.addRadioButtonWithText(autoCapballMoved_No, "No", true);
                autoCapballMoved_Yes = (RadioButton)findViewById(R.id.radio_auto_cbmoved_yes);
                autoCapballMoved.addRadioButtonWithText(autoCapballMoved_Yes, "Yes", false);

            //Beacons Scored
                autoBeaconsScored.resetButtonsArray();
                autoBeaconsScored_0 = (RadioButton)findViewById(R.id.radio_auto_beacons_0);
                autoBeaconsScored.addRadioButton(autoBeaconsScored_0, true);
                autoBeaconsScored_1 = (RadioButton)findViewById(R.id.radio_auto_beacons_1);
                autoBeaconsScored.addRadioButton(autoBeaconsScored_1, false);
                autoBeaconsScored_2 = (RadioButton)findViewById(R.id.radio_auto_beacons_2);
                autoBeaconsScored.addRadioButton(autoBeaconsScored_2, false);

            //Parking
                autoParking.resetButtonsArray();
                autoParking_not = (RadioButton)findViewById(R.id.radio_auto_parking_not);
                autoParking.addRadioButtonWithText(autoParking_not, "Not Parked", true);
                autoParking_corner_par = (RadioButton)findViewById(R.id.radio_auto_parking_corner_par);
                autoParking.addRadioButtonWithText(autoParking_corner_par, "Partial Corner", false);
                autoParking_corner = (RadioButton)findViewById(R.id.radio_auto_parking_corner);
                autoParking.addRadioButtonWithText(autoParking_corner, "Full Corner", false);
                autoParking_center_par = (RadioButton)findViewById(R.id.radio_auto_parking_center_par);
                autoParking.addRadioButtonWithText(autoParking_center_par, "Partial Center", false);
                autoParking_center = (RadioButton)findViewById(R.id.radio_auto_parking_center);
                autoParking.addRadioButtonWithText(autoParking_center, "Center", false);


        //Tele

            //Corner Vortex
                teleCornerOut = (TextView)findViewById(R.id.counter_tele_corner);
                teleCorner.setOutput(teleCornerOut);

            //Center Vortex
                teleCenterOut = (TextView)findViewById(R.id.counter_tele_center);
                teleCenter.setOutput(teleCenterOut);

            //Center Vortex
                teleBeaconsPressedOut = (TextView)findViewById(R.id.counter_tele_beacons_pressed);
                teleBeaconsPressed.setOutput(teleBeaconsPressedOut);

            //Beacons Scored
                teleBeaconsScored.resetButtonsArray();
                teleBeaconsScored_0 = (RadioButton)findViewById(R.id.radio_tele_beacons_0);
                teleBeaconsScored.addRadioButton(teleBeaconsScored_0, true);
                teleBeaconsScored_1 = (RadioButton)findViewById(R.id.radio_tele_beacons_1);
                teleBeaconsScored.addRadioButton(teleBeaconsScored_1, false);
                teleBeaconsScored_2 = (RadioButton)findViewById(R.id.radio_tele_beacons_2);
                teleBeaconsScored.addRadioButton(teleBeaconsScored_2, false);
                teleBeaconsScored_3 = (RadioButton)findViewById(R.id.radio_tele_beacons_3);
                teleBeaconsScored.addRadioButton(teleBeaconsScored_3, false);
                teleBeaconsScored_4 = (RadioButton)findViewById(R.id.radio_tele_beacons_4);
                teleBeaconsScored.addRadioButton(teleBeaconsScored_4, false);


        //End

            //Capball Status
                endCapballStatus.resetButtonsArray();
                endCapballStatus_not = (RadioButton)findViewById(R.id.radio_end_capball_not);
                endCapballStatus.addRadioButtonWithText(endCapballStatus_not, "Not Scored", true);
                endCapballStatus_low = (RadioButton)findViewById(R.id.radio_end_capball_low);
                endCapballStatus.addRadioButtonWithText(endCapballStatus_low, "Low Goal", false);
                endCapballStatus_high = (RadioButton)findViewById(R.id.radio_end_capball_high);
                endCapballStatus.addRadioButtonWithText(endCapballStatus_high, "High Goal", false);
                endCapballStatus_capped = (RadioButton)findViewById(R.id.radio_end_capball_capped);
                endCapballStatus.addRadioButtonWithText(endCapballStatus_capped, "Capped", false);


        //infoManager--DON'T CHANGE--
            teamNumber = (EditText)findViewById(R.id.enter_team_number);
            infoManager.setTeamNumberLoction(teamNumber);
            matchNumber = (EditText)findViewById(R.id.enter_match_number);
            infoManager.setMatchNumberLocation(matchNumber);

    }

    public void clear(View v)
    {

        infoManager.resetValues();

    }

    public void save(View v)
    {

        infoManager.saveFile(MainActivity.this);

    }

}
