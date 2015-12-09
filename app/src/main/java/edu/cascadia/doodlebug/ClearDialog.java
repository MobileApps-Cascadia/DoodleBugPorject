package edu.cascadia.doodlebug;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by Scott on 12/2/2015.
 */
public class ClearDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle bundle)
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        View colorDialogView = getActivity().getLayoutInflater().inflate(
                R.layout.fragment_clear, null);
        builder.setView(colorDialogView); // add GUI to dialog

        // set the AlertDialog's message
        builder.setTitle("Are you sure you want to erase?");
        builder.setCancelable(true);


        final DrawingView drawView = getDrawFragment().getDrawingView();
        builder.setPositiveButton(R.string.button_clear,
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        drawView.clear();
                    }
                }
        ); // end call to setPositiveButton

        return builder.create(); // return dialog
    } // end method onCreateDialog

    // gets a reference to the DoodleFragment
    private DrawFragment getDrawFragment()
    {
        if (getActivity() != null && (getActivity().getClass() == MainActivity.class)) {
            MainActivity activity = (MainActivity) getActivity();

            if (activity.getCurrentFragment() != null && activity.getCurrentFragment().getClass() == DrawFragment.class) {
                DrawFragment fragment = (DrawFragment) activity.getCurrentFragment();
                return fragment;
            }
        }

        return null;
    }

    // tell DoodleFragment that dialog is now displayed
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        DrawFragment fragment = getDrawFragment();

        if (fragment != null)
            fragment.setDialogOnScreen(true);
    }

    // tell DoodleFragment that dialog is no longer displayed
    @Override
    public void onDetach()
    {
        super.onDetach();
        DrawFragment fragment = getDrawFragment();

        if (fragment != null)
            fragment.setDialogOnScreen(false);
    }


}
