package edu.cascadia.doodlebug;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity
        implements StartupFragment.OnMenuSelectListener,
                   ColorDialog.ChangeColorListener {

    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragmentInitially(new StartupFragment());
    }

    void addFragmentInitially(Fragment f) {
        currentFragment = f;

        getFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, f, null)
                .commit();
    }

    void addFragment(Fragment f) {
        currentFragment = f;

        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, f, null)
                .addToBackStack(null)
                .commit();
    }

    public void startCanvas() { addFragment(DrawFragment.newInstance(false)); }
    public void startCamera() { addFragment(DrawFragment.newInstance(true)); }

    public void onColorChange(int color) {
        if (currentFragment != null && currentFragment.getClass() == DrawFragment.class) {
            DrawFragment fragment = (DrawFragment) currentFragment;
            fragment.getDrawingView().setDrawingColor(color);
        }

    }

    public void showColorDialog(View v) {
        ColorDialog colorDialog = new ColorDialog();
        colorDialog.show(getFragmentManager(), "color dialog");
    }

    public void showLineWidthDialog(View v) {
        LineWidthDialog lineWidthDialog = new LineWidthDialog();
        lineWidthDialog.show(getFragmentManager(), "line width dialog");
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
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
}