package edu.cascadia.doodlebug;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class ColorDialog extends DialogFragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String CURRENT_COLOR = "current color";
    private int previousColor;
    private ChangeColorListener mListener;

    public static ColorDialog newInstance(int color) {
        ColorDialog fragment = new ColorDialog();
        Bundle args = new Bundle();
        args.putInt(CURRENT_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    public ColorDialog() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            previousColor = getArguments().getInt(CURRENT_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_color_dialog, container, false);
        final RadioGroup r = (RadioGroup) v.findViewById(R.id.color_choices);

        addColorChoice(r, "Black", Color.BLACK);
        addColorChoice(r, "White", Color.WHITE);
        addColorChoice(r, "Yellow", Color.YELLOW);
        addColorChoice(r, "Blue", Color.BLUE);
        addColorChoice(r, "Red", Color.RED);
        addColorChoice(r, "Green", Color.GREEN);
        addColorChoice(r, "Magenta", Color.MAGENTA);

        return v;
    }

    void addColorChoice(RadioGroup group, String label, final int color) {
        final RadioButton b = new RadioButton(getActivity());

        b.setText(label);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onColorChange(color);
            }
        });
        final boolean selected = color == previousColor;
        if (!selected) b.setAlpha(0.5f);
        b.setChecked(selected);
        group.addView(b);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ChangeColorListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ChangeColorListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ChangeColorListener {
        void onColorChange(int color);
    }
}