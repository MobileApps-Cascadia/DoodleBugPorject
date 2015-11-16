//Prepare this class file how to integrate camera in activity
//will need to add permission in manifests xml fil.
//use intent
//after take take picture, send the photo to Draw screen and set background or imageView.
package edu.cascadia.doodlebug;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class CameraActivity extends Activity {
    Button ButtonClick;
    int CAMERA_PIC_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        ButtonClick =(Button) findViewById(R.id.btnTakePicture);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if( requestCode == CAMERA_PIC_REQUEST)
        {
            Bitmap imgTaken = (Bitmap) data.getExtras().get("data");
            ImageView image =(ImageView) findViewById(R.id.imageView2);
            image.setImageBitmap(imgTaken);
        }
        else
        {
            Toast.makeText(CameraActivity.this, "Picture not taken", Toast.LENGTH_LONG);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
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
