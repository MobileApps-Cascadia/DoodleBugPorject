package edu.cascadia.doodlebug;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StartupFragment extends Fragment {

    private OnMenuSelectListener mListener;

    public StartupFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_startup, container, false);

        menuItemListener(v, R.id.cameraButton, new View.OnClickListener() {
            public void onClick(View view) {
                if (mListener != null) mListener.startCamera();
            }
        });

        menuItemListener(v, R.id.drawButton, new View.OnClickListener() {
            public void onClick(View view) {
                if (mListener != null) mListener.startCanvas();
            }
        });

        return v;
    }

    void menuItemListener(View v, int id, View.OnClickListener listen) {
        v.findViewById(id).setOnClickListener(listen);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMenuSelectListener) activity;
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

    public interface OnMenuSelectListener {
        void startCamera();
        void startCanvas();
    }

}
