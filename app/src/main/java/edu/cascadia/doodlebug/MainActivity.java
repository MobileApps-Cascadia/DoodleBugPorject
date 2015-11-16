package edu.cascadia.doodlebug;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity
        implements StartupFragment.OnMenuSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(new StartupFragment());
    }

    void addFragment(Fragment f) { addFragment(getFragmentManager(), f); }

    void addFragment(FragmentManager fm, Fragment f) {
        fm.beginTransaction()
                .add(R.id.fragmentContainer, f, null)
                .addToBackStack(null)
                .commit();
    }

    void popAddFragment(Fragment f) {
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, f, null)
                .addToBackStack(null)
                .commit();
    }

    public void startCanvas() { addFragment(DrawFragment.newInstance(false)); }
    public void startCamera() { addFragment(DrawFragment.newInstance(true)); }

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
}