package edu.cascadia.doodlebug;

<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
=======
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
>>>>>>> a33877f6c0f95b9f0482a2fc262458c2415ec673
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
<<<<<<< HEAD
import android.widget.Button;
import android.widget.TextView;
=======
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
>>>>>>> a33877f6c0f95b9f0482a2fc262458c2415ec673

import org.w3c.dom.Text;


<<<<<<< HEAD
public class DrawActivity extends ActionBarActivity {
=======
    RelativeLayout drawLayout;
    int CAMERA_PIC_REQUEST = 2;
    private DrawingView drawingView;
>>>>>>> a33877f6c0f95b9f0482a2fc262458c2415ec673

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

<<<<<<< HEAD
        Button pickBtn = (Button) findViewById(R.id.pickButton);
=======
        drawingView = (DrawingView) findViewById(R.id.drawingView);

        //prepare draw image that return from camera to layout background
        //need to convert picture to bitmap first.
        drawLayout = (RelativeLayout) findViewById(R.id.DrawLayout);
        Bitmap img;
        Drawable drawImg;
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            img = (Bitmap) extra.get("image");
            Drawable d = new BitmapDrawable(getResources(), img);
            drawLayout.setBackground(d);
        }
>>>>>>> a33877f6c0f95b9f0482a2fc262458c2415ec673

        final Context context = this;

        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick = new Intent(context, colorPicker.class );
                startActivity(pick);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_draw, menu);
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
}
