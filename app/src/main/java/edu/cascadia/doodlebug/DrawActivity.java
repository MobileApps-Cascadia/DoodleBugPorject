package edu.cascadia.doodlebug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class DrawActivity extends Activity {

    RelativeLayout drawLayout;
    int CAMERA_PIC_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

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

        //camera image button click
        //prepare launch camera and taken picture
        ImageButton ButtonClick = (ImageButton) findViewById(R.id.imageButttonTakePic);
        ButtonClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                // request code

                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });
    }

    //retaken picture as anytime
    //result return from camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if( requestCode == CAMERA_PIC_REQUEST)
        {
            //  data.getExtras()
            Bitmap imgTaken = (Bitmap) data.getExtras().get("data");
            Drawable d = new BitmapDrawable(getResources(), imgTaken);

            drawLayout.setBackground(d);
        }
        else
        {
            Toast.makeText(DrawActivity.this, "Picture NOt taken", Toast.LENGTH_LONG);
        }
        super.onActivityResult(requestCode, resultCode, data);
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
