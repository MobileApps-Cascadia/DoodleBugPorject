package edu.cascadia.doodlebug;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DrawFragment extends Fragment {

    private DrawingView mView;

    private final static int CAMERA_PIC_REQUEST = 2;
    private static String TAKE_PHOTO = "TAKE_PHOTO";
    private boolean dialogOnScreen = false;

    public DrawFragment() {}

    public static DrawFragment newInstance(Boolean takePhoto) {
        DrawFragment fragment = new DrawFragment();
        Bundle args = new Bundle();
        args.putBoolean(TAKE_PHOTO, takePhoto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle b) {
        if (getArguments().getBoolean(TAKE_PHOTO))
            takePhoto();
        super.onActivityCreated(b);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_draw, container, false);
        v.findViewById(R.id.imageButttonTakePic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
            }
        });

        mView = (DrawingView) v.findViewById(R.id.drawingView);
        return v;
    }

    public void takePhoto() {
        startActivityForResult(
                new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE),
                CAMERA_PIC_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {
            mView.setBackground((Bitmap) data.getExtras().get("data"));
        }
    }

    public DrawingView getDrawingView() {
        return mView;
    }

    public void setDialogOnScreen(boolean visible) {
        dialogOnScreen = visible;
    }


}
