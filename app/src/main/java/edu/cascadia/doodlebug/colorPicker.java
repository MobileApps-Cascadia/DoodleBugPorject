package edu.cascadia.doodlebug;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class colorPicker extends ActionBarActivity {
    public static String COLOR_SHARED_PREFERENCES = "colorSharedPreferences";
    public static String COLOR_PICKER = "colorPicker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_picker, menu);
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

    private void initColorPicking() {
        int pickColor = getSharedPreferences(COLOR_SHARED_PREFERENCES,
                Context.MODE_PRIVATE).getInt(COLOR_PICKER, R.id.redPick);

        RadioButton rbRed = (RadioButton) findViewById(R.id.redPick);
        RadioButton rbOrange = (RadioButton) findViewById(R.id.orangePick);
        RadioButton rbYellow = (RadioButton) findViewById(R.id.yellowPick);
        RadioButton rbGreen = (RadioButton) findViewById(R.id.greenPick);
        RadioButton rbBlue = (RadioButton) findViewById(R.id.bluePick);
        RadioButton rbViolet = (RadioButton) findViewById(R.id.violetPick);
        RadioButton rbBlack = (RadioButton) findViewById(R.id.blackPick);
        RadioButton rbWhite = (RadioButton) findViewById(R.id.whitePick);

        if (pickColor == R.id.redPick){
            rbRed.setChecked(true);
        }
        else if(pickColor == R.id.orangePick){
            rbOrange.setChecked(true);
        }
        else if(pickColor == R.id.yellowPick){
            rbYellow.setChecked(true);
        }
        else if(pickColor == R.id.greenPick){
            rbGreen.setChecked(true);
        }
        else if(pickColor == R.id.bluePick){
            rbBlue.setChecked(true);
        }
        else if(pickColor == R.id.violetPick){
            rbViolet.setChecked(true);
        }
        else if(pickColor == R.id.blackPick){
            rbBlack.setChecked(true);
        }
        else {
            rbWhite.setChecked(true);

        }
    }

    private void initColorPickClick() {
        RadioGroup rgColor = (RadioGroup) findViewById(R.id.radioGroup1);
        rgColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbRed = (RadioButton) findViewById(R.id.redPick);
                RadioButton rbOrange = (RadioButton) findViewById(R.id.orangePick);
                RadioButton rbYellow = (RadioButton) findViewById(R.id.yellowPick);
                RadioButton rbGreen = (RadioButton) findViewById(R.id.greenPick);
                RadioButton rbBlue = (RadioButton) findViewById(R.id.bluePick);
                RadioButton rbViolet = (RadioButton) findViewById(R.id.violetPick);
                RadioButton rbBlack = (RadioButton) findViewById(R.id.blackPick);
                RadioButton rbWhite = (RadioButton) findViewById(R.id.whitePick);

                if (rbRed.isChecked()){
                    getSharedPreferences(COLOR_SHARED_PREFERENCES,
                            MODE_PRIVATE).edit()
                            .putInt(COLOR_PICKER, R.id.redPick).commit();
                }
                else if (rbOrange.isChecked()){
                    getSharedPreferences(COLOR_SHARED_PREFERENCES,
                            MODE_PRIVATE).edit()
                            .putInt(COLOR_PICKER, R.id.orangePick).commit();
                }
                else if (rbYellow.isChecked()){
                    getSharedPreferences(COLOR_SHARED_PREFERENCES,
                            MODE_PRIVATE).edit()
                            .putInt(COLOR_PICKER, R.id.yellowPick).commit();
                }
                else if (rbGreen.isChecked()){
                    getSharedPreferences(COLOR_SHARED_PREFERENCES,
                            MODE_PRIVATE).edit()
                            .putInt(COLOR_PICKER, R.id.greenPick).commit();
                }
                else if (rbBlue.isChecked()){
                    getSharedPreferences(COLOR_SHARED_PREFERENCES,
                            MODE_PRIVATE).edit()
                            .putInt(COLOR_PICKER, R.id.bluePick).commit();
                }
                else if (rbViolet.isChecked()){
                    getSharedPreferences(COLOR_SHARED_PREFERENCES,
                            MODE_PRIVATE).edit()
                            .putInt(COLOR_PICKER, R.id.violetPick).commit();
                }
                else if (rbBlack.isChecked()){
                    getSharedPreferences(COLOR_SHARED_PREFERENCES,
                            MODE_PRIVATE).edit()
                            .putInt(COLOR_PICKER, R.id.blackPick).commit();
                }
                else {
                    getSharedPreferences(COLOR_SHARED_PREFERENCES,
                            MODE_PRIVATE).edit()
                            .putInt(COLOR_PICKER, R.id.whitePick).commit();
                }
            }
        });
    }
}
