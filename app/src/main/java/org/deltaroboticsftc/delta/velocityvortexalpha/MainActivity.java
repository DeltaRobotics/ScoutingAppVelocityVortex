package org.deltaroboticsftc.delta.velocityvortexalpha;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
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
            public void storeAutoCapballMoved(View v) {autoCapballMoved.storeCurChecked();}

        //Beacons Scored
            private RadioSet autoBeaconsScored;
            private RadioButton autoBeaconsScored_0;
            private RadioButton autoBeaconsScored_1;
            private RadioButton autoBeaconsScored_2;
            public void  storeAutoBeaconsScored(View v) {autoBeaconsScored.storeCurChecked();}

        //Parking
            private RadioSet autoParking;
            private RadioButton autoParking_not;
            private RadioButton autoParking_corner_par;
            private RadioButton autoParking_corner;
            private RadioButton autoParking_center_par;
            private RadioButton autoParking_center;
            public void  storeAutoParking(View v) {autoParking.storeCurChecked();}


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
            public void  storeTeleBeaconsScored(View v) {teleBeaconsScored.storeCurChecked();}


    //End

        //Capball Status
            private RadioSet endCapballStatus;
            private RadioButton endCapballStatus_not;
            private RadioButton endCapballStatus_low;
            private RadioButton endCapballStatus_high;
            private RadioButton endCapballStatus_capped;
            public void  storeEndCapballStatus(View v) {endCapballStatus.storeCurChecked();}


    //Misc

            //Comments
                private EditText comments;


    //Setup System Controls------DON'T CHANGE----------

        private TextView banner_title;
        private String game_name;

        private InfoManager infoManager;
        private EditText teamNumber;
        private EditText matchNumber;

        private Settings settings;
        private CheckBox csvFileSaveLocation;

        private void setupSettings()
        {

            csvFileSaveLocation = (CheckBox)findViewById(R.id.box_setting_outputFile);
            settings.setCsvFileSaveLocation(csvFileSaveLocation);

        }
        public void setCsvFileSave(View v) {settings.setCsvFileSave();}

        public Typeface face;
        private final int REQUEST_KEY = 1;


    //Font

        Typeface customFont;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        face = Typeface.createFromAsset(getAssets(),"fonts/SFTransRobotics.ttf");

        game_name = getString(R.string.game_name);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
        {

            this.finishOnCreate();

        }
        else
        {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_KEY);
            this.setupPermissionsPage();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == REQUEST_KEY)
        {

            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                // We can now safely use the API we requested access to
                System.out.println("Permissions granted");
                this.finishOnCreate();

            }
            else
            {

                // Permission was denied or request was cancelled
                System.out.println("Permissions denied");
                this.setupPermissionsPage();

            }
        }

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
        if (id == R.id.action_moveTo_main_edit)
        {

            setContentView(R.layout.activity_main);
            this.setupActionBar();
            this.setupMatch();
            infoManager.editMatch(comments);
            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText(game_name);
            this.applyFontToTitlesMain();
            return true;

        }

        if (id == R.id.action_moveTo_main_new)
        {

            infoManager.resetValues();
            setContentView(R.layout.activity_main);
            this.setupActionBar();
            this.setupMatch();
            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText(game_name);
            this.applyFontToTitlesMain();
            return true;

        }

        if (id == R.id.action_moveTo_settings)
        {

            infoManager.setMisc(comments);
            infoManager.storeMatchInfo();
            setContentView(R.layout.activity_settings);
            this.setupActionBar();
            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText("Settings");
            this.setupSettings();
            settings.pageLoad();
            this.applyFontToTitlesSettings();
            return true;

        }

        if (id == R.id.action_moveTo_about)
        {

            infoManager.setMisc(comments);
            infoManager.storeMatchInfo();
            setContentView(R.layout.activity_about);
            this.setupActionBar();
            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText("About");
            this.applyFontToTitlesAbout();
            return true;

        }

        if (id == R.id.action_moveTo_review)
        {

            if(banner_title.getText().equals(game_name))
            {

                infoManager.setMisc(comments);
                infoManager.storeMatchInfo();
                infoManager.createInfo();

            }

            setContentView(R.layout.activity_review);
            this.setupActionBar();
            this.setupReview(false);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    public void requestPermissions(View v)
    {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_KEY);

    }

    private void finishOnCreate()
    {

        setContentView(R.layout.activity_main);
        this.setupActionBar();

        banner_title = (TextView)findViewById(R.id.text_banner_game_name);
        banner_title.setText(game_name);
        infoManager = new InfoManager(game_name);

        this.setup();

    }

    private void setupPermissionsPage()
    {

        setContentView(R.layout.activity_permissions);
        banner_title = (TextView)findViewById(R.id.text_banner_game_name);
        banner_title.setText("Permissions Missing");

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
                autoCorner = new Counter(0, true, 3, true, 1, 0);

            //Corner Vortex
                autoCenter = new Counter(0, true, 3, true, 1, 0);

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


        //Settings

            settings = new Settings(infoManager);


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


        //Font

        changeFont("SFTransRobotics");
        applyFontToTitlesMain();





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
                autoCapballMoved.setChecked(-1);

            //Beacons Scored
                autoBeaconsScored.resetButtonsArray();
                autoBeaconsScored_0 = (RadioButton)findViewById(R.id.radio_auto_beacons_0);
                autoBeaconsScored.addRadioButton(autoBeaconsScored_0, true);
                autoBeaconsScored_1 = (RadioButton)findViewById(R.id.radio_auto_beacons_1);
                autoBeaconsScored.addRadioButton(autoBeaconsScored_1, false);
                autoBeaconsScored_2 = (RadioButton)findViewById(R.id.radio_auto_beacons_2);
                autoBeaconsScored.addRadioButton(autoBeaconsScored_2, false);
                autoBeaconsScored.setChecked(-1);

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
                autoParking.setChecked(-1);


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
                teleBeaconsScored.setChecked(-1);


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
                endCapballStatus.setChecked(-1);


        //Misc

            //Comments
                comments = (EditText) findViewById(R.id.textBox_misc_comments);


        //infoManager--DON'T CHANGE--
            teamNumber = (EditText)findViewById(R.id.enter_team_number);
            infoManager.setTeamNumberLoction(teamNumber);
            matchNumber = (EditText)findViewById(R.id.enter_match_number);
            infoManager.setMatchNumberLocation(matchNumber);

    }

    public void setupReview(boolean saved)
    {

        String banner = infoManager.pullTeamNumber();
        String match = infoManager.pullMatchNumber();
        String info = infoManager.getInfo();

        setContentView(R.layout.activity_review);
        setupActionBar();

        if(saved == true)
        {

            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText(banner);

            TextView text_match = (TextView)findViewById(R.id.sub_title_review_match);
            text_match.setText("Match: " + match);

        }
        else
        {

            if(banner == null || banner.equals(""))
            {

                banner = "Team";

            }

            if(match == null || match.equals(""))
            {

                match = "Match";

            }

            banner_title = (TextView)findViewById(R.id.text_banner_game_name);
            banner_title.setText("Match Not Saved");

            TextView text_match = (TextView)findViewById(R.id.sub_title_review_match);
            text_match.setText(banner + "-" + match);

        }

        TextView text_info = (TextView)findViewById(R.id.text_review_info);
        text_info.setText(info);

    }

    public void save(View v)
    {
        infoManager.setMisc(comments);
        infoManager.storeMatchInfo();

        AlertDialog alertDialogFileSaveFailed = new AlertDialog.Builder(MainActivity.this).create();
        alertDialogFileSaveFailed.setTitle("Save Failed");
        alertDialogFileSaveFailed.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which){dialog.dismiss();}});

        switch(infoManager.createFileInfoAndPath(MainActivity.this, settings.getCsvFileSave()))
        {

            //Return Case 2: File Exists and File Setup Finished
            //Return Case 1: File Does Not Exist and File Setup Finished
            //Return Case 0: Do Nothing
            //Return Case -1: Directory Creation Failed
            //Return Case -2: Info Creation Failed
            //Return Case -999: Unknown Fail

            case 2:

                AlertDialog alertDialogFileExists = new AlertDialog.Builder(MainActivity.this).create();
                alertDialogFileExists.setTitle("File Exists");
                alertDialogFileExists.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which)
                {

                    infoManager.matchNumberClaimFocus();
                    dialog.dismiss();

                }});


                alertDialogFileExists.setButton(AlertDialog.BUTTON_POSITIVE, "OVERWRITE", new DialogInterface.OnClickListener(){public void onClick(DialogInterface dialog, int which)
                {

                    if(infoManager.createAndSaveFile(true, MainActivity.this, settings.getCsvFileSave()) == true)
                    {

                        setupReview(true);

                    }

                    dialog.dismiss();

                }});

                alertDialogFileExists.setMessage("Do You Want to Overwrite This File?");
                alertDialogFileExists.show();

                break;

            case 1:

                if(infoManager.createAndSaveFile(false, MainActivity.this, settings.getCsvFileSave()) == true)
                {

                    this.setupReview(true);

                }

                break;

            case 0:
                break;

            case -1:

                alertDialogFileSaveFailed.setMessage("Failed to Create Save Directory\nERROR: -1");
                alertDialogFileSaveFailed.show();

                break;

            case -2:

                alertDialogFileSaveFailed.setMessage("Failed to Collect Match Data\nERROR: -2");
                alertDialogFileSaveFailed.show();

                break;

            case -999:

                alertDialogFileSaveFailed.setMessage("Failed For Unknown Reason\nERROR: -999");
                alertDialogFileSaveFailed.show();

                break;

            default:

                alertDialogFileSaveFailed.setMessage("Failed For Unknown Reason\nERROR: Unknown");
                alertDialogFileSaveFailed.show();

                break;

        }

    }

    public void clear(View v)
    {

        infoManager.resetValues();

    }

    public void newMatch(View v)
    {

        this.clear(v);
        setContentView(R.layout.activity_main);
        this.setupActionBar();
        this.setupMatch();
        banner_title = (TextView)findViewById(R.id.text_banner_game_name);
        banner_title.setText(game_name);
        this.applyFontToTitlesMain();

    }

    public void editMatch(View v)
    {

        setContentView(R.layout.activity_main);
        this.setupActionBar();
        this.setupMatch();
        infoManager.editMatch(comments);
        banner_title = (TextView)findViewById(R.id.text_banner_game_name);
        banner_title.setText(game_name);
        this.applyFontToTitlesMain();

    }

    public void changeFont(String font)
    {

        customFont = Typeface.createFromAsset(getAssets(), "fonts/" + font + ".ttf");

    }

    public void applyFontToTitlesMain()
    {

        TextView text_banner_game_name = (TextView) findViewById(R.id.text_banner_game_name);
        text_banner_game_name.setTypeface(customFont);

        TextView title_auto = (TextView) findViewById(R.id.title_auto);
        title_auto.setTypeface(customFont);

        TextView title_tele = (TextView) findViewById(R.id.title_tele);
        title_tele.setTypeface(customFont);

        TextView title_end = (TextView) findViewById(R.id.title_end);
        title_end.setTypeface(customFont);

    }

    public void applyFontToTitlesSettings()
    {

        TextView text_banner_game_name = (TextView) findViewById(R.id.text_banner_game_name);
        text_banner_game_name.setTypeface(customFont);

        TextView sub_title_setting_outputFile = (TextView) findViewById(R.id.sub_title_setting_outputFile);
        sub_title_setting_outputFile.setTypeface(customFont);

    }

    public void applyFontToTitlesAbout()
    {

        TextView text_banner_game_name = (TextView) findViewById(R.id.text_banner_game_name);
        text_banner_game_name.setTypeface(customFont);

        TextView sub_title_about_body_theApp = (TextView) findViewById(R.id.sub_title_about_body_theApp);
        sub_title_about_body_theApp.setTypeface(customFont);

        TextView sub_title_about_body_useApp = (TextView) findViewById(R.id.sub_title_about_body_useApp);
        sub_title_about_body_useApp.setTypeface(customFont);

        TextView sub_title_about_body_aboutDelta = (TextView) findViewById(R.id.sub_title_about_body_aboutDelta);
        sub_title_about_body_aboutDelta.setTypeface(customFont);

        TextView sub_title_about_body_readableFile = (TextView) findViewById(R.id.sub_title_about_body_readableFile);
        sub_title_about_body_readableFile.setTypeface(customFont);

        TextView sub_title_about_body_csvFile = (TextView) findViewById(R.id.sub_title_about_body_csvFile);
        sub_title_about_body_csvFile.setTypeface(customFont);

        TextView sub_title_about_body_ratingSys = (TextView) findViewById(R.id.sub_title_about_body_ratingSys);
        sub_title_about_body_ratingSys.setTypeface(customFont);

    }

}