package edu.cascadia.doodlebug;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        final ViewGroup group = (ViewGroup) v.findViewById(R.id.color_choices);

        addColorChoice(group, 0xFFFFFF66);
        addColorChoice(group, 0xFF66CCFF);
        addColorChoice(group, 0xFFFF6666);
        addColorChoice(group, 0xFF66FF66);
        addColorChoice(group, Color.BLACK);

        return v;
    }

    class ColorChoice extends View {
        private ShapeDrawable drawable;
        private int r = 70;

        public ColorChoice(Context c, final int color) {
            super(c);
            int x = getLeft();
            int y = getTop();

            drawable = new ShapeDrawable(new OvalShape());
            drawable.getPaint().setColor(color);
            drawable.setBounds(x, y, x + r, y + r);

            setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onColorChange(color);
                    dismiss();
                }
            });
        }

        @Override
        public void onDraw(Canvas c) { drawable.draw(c); }

        @Override
        public void onMeasure(int ws, int hs) {
            setMeasuredDimension(r+20, r+20);
        }
    }

    void addColorChoice(ViewGroup group, final int color) {
        group.addView(new ColorChoice(getActivity(), color));
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