package edu.cascadia.doodlebug;

import android.app.Activity;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DrawFragment extends Fragment {
    //private OnFragmentInteractionListener mListener;
    private View.OnTouchListener MyTouchListener;
    private DrawingView mView;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

        // stickers code - added by Hiromi
        // Add stickers - assign the touch listeners to the view which we want to move
        v.findViewById(R.id.imgCat).setOnTouchListener(new ChoiceTouchListener());
        v.findViewById(R.id.imgDog).setOnTouchListener(new ChoiceTouchListener());
        v.findViewById(R.id.imgDolphin).setOnTouchListener(new ChoiceTouchListener());
        v.findViewById(R.id.imgHeart).setOnTouchListener(new ChoiceTouchListener());
        v.findViewById(R.id.imgMustache).setOnTouchListener(new ChoiceTouchListener());
        v.findViewById(R.id.imgSmilie).setOnTouchListener(new ChoiceTouchListener());
        v.findViewById(R.id.imgSunglasses).setOnTouchListener(new ChoiceTouchListener());
        v.findViewById(R.id.drawingView).setOnDragListener(new ChoiceDragListener());

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


    // Listener for touch sticker
    private final class ChoiceTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent){
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                // setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
            else {
                return false;
            }
        }
    }

    // Listener for drag sticker
    private class ChoiceDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                    //Toast.makeText(getActivity(), R.string.drag_started, Toast.LENGTH_SHORT).show();
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //Toast.makeText(getActivity(), R.string.drag_entered, Toast.LENGTH_SHORT).show();
                    int x_cord = (int) event.getX();
                    int y_cord = (int) event.getY();
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //Toast.makeText(getActivity(), R.string.drag_exited, Toast.LENGTH_SHORT).show();
                    x_cord = (int) event.getX();
                    y_cord = (int) event.getY();
                    layoutParams.leftMargin = x_cord;
                    layoutParams.topMargin = y_cord;
                    v.setLayoutParams(layoutParams);
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_LOCATION  :
                    //Toast.makeText(getActivity(), R.string.drag_location, Toast.LENGTH_SHORT).show();
                    x_cord = (int) event.getX();
                    y_cord = (int) event.getY();
                    break;
                case DragEvent.ACTION_DROP:
                    //Toast.makeText(getActivity(), R.string.drag_drop, Toast.LENGTH_SHORT).show();

                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);

                    RelativeLayout container = (RelativeLayout) v.getParent();
                    container.addView(view);
                    container.bringChildToFront(view);
                    view.setX((int)event.getX() + 5 - view.getWidth() / 2);
                    view.setY((int) event.getY() + 5 - view.getHeight() / 2);
                    view.setVisibility(View.VISIBLE);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //Toast.makeText(getActivity(), R.string.drag_ended, Toast.LENGTH_SHORT).show();
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }

}
