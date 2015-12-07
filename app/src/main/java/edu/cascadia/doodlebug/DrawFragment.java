package edu.cascadia.doodlebug;

import android.app.Activity;
import android.content.ClipDescription;
import android.content.Intent;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DrawFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private View.OnTouchListener MyTouchListener;
    private DrawingView mView;
    private ImageView selectedImage = null;

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

        // Add stickers - assign the touch listeners to the view which we want to move
        mView.findViewById(R.id.imgCat).setOnTouchListener(new MyTouchListener());
        mView.findViewById(R.id.imgDog).setOnTouchListener(new MyTouchListener());
        mView.findViewById(R.id.imgDolphin).setOnTouchListener(new MyTouchListener());
        mView.findViewById(R.id.imgHeart).setOnTouchListener(new MyTouchListener());
        mView.findViewById(R.id.imgMustache).setOnTouchListener(new MyTouchListener());
        mView.findViewById(R.id.imgSmilie).setOnTouchListener(new MyTouchListener());
        mView.findViewById(R.id.imgSunglasses).setOnTouchListener(new MyTouchListener());
        mView.findViewById(R.id.drawingView).setOnDragListener(new MyDragListener());


        imageCat.setOnTouchListener(new ChoiceTouchListener());
        imageDog.setOnTouchListener(new ChoiceTouchListener());
        imageDolphin.setOnTouchListener(new ChoiceTouchListener());
        imageHeart.setOnTouchListener(new ChoiceTouchListener());
        imageMustache.setOnTouchListener(new ChoiceTouchListener());
        imageSmilie.setOnTouchListener(new ChoiceTouchListener());
        imageSunglasses.setOnTouchListener(new ChoiceTouchListener());

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
        v.findViewById(R.id.imgCat).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick (View view){
                String viewID = "imgCat";
                selectSticker(view, viewID);
                return true;
            }
        });

        v.findViewById(R.id.imgDog).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick (View view){
                String viewID = "imgDog";
                selectSticker(view, viewID);
                return true;
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

    // sticker click method
    public void selectSticker(View view, String imageViewID){
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        String imgView = imageViewID;
        selectedImage = (ImageView) view.findViewById(R.id.imgView);

        ClipData dragData = new ClipData(view.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(selectedImage);

        view.startDrag(dragData, myShadow, null, 0);
    }

}
