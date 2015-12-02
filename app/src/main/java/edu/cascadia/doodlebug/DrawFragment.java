package edu.cascadia.doodlebug;

import android.app.Activity;
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

public class DrawFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private DrawingView mView;

    public DrawFragment() {}

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
        mView = (DrawingView) v.findViewById(R.id.drawingView);
        return v;
    }

    public void setBackground(Bitmap bitmap) { mView.setBackground(bitmap); }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void startCamera();
    }
}
