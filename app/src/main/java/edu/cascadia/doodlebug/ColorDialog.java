package edu.cascadia.doodlebug;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ColorDialog extends Fragment {
    private ColorListener mListener;
    private static final String CURRENT_COLOR = "current color";
    int mColor;

    public static ColorDialog newInstance(int currentColor) {
        ColorDialog fragment = new ColorDialog();
        Bundle args = new Bundle();
        args.putInt(CURRENT_COLOR, currentColor);
        fragment.setArguments(args);
        return fragment;
    }

    public ColorDialog() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColor = getArguments().getInt(CURRENT_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_dialog, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ColorListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ColorListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ColorListener {
        public void onColorChange(int color);
    }
}
