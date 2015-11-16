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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_startup, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int which) {
        if (mListener != null) {
            mListener.onMenuItemSelect(which);
        }
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
        public void onMenuItemSelect(int which);
    }

}
