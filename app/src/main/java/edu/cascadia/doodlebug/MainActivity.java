package edu.cascadia.doodlebug;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity
        implements StartupFragment.OnMenuSelectListener,
        CameraFragment.OnPictureTakenListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager()
                .beginTransaction()
                .add(new StartupFragment(), null)
                .addToBackStack(null)
                .commit();
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
        return item.getItemId() == R.id.action_settings
                || super.onOptionsItemSelected(item);
    }

    public void startCamera() {
        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
        fm.beginTransaction()
                .add(new CameraFragment(), null)
                .addToBackStack(null)
                .commit();
    }

    public void startCanvas() {}

    public void onPictureTaken(Bitmap bitmap) {}
}