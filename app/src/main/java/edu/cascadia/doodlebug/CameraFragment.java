package edu.cascadia.doodlebug;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CameraFragment.OnPictureTakenListener} interface
 * to handle interaction events.
 */
public class CameraFragment extends Fragment {

    private OnPictureTakenListener mListener;
    private ImageView mImage;
    private Bitmap mPicture;

    public CameraFragment() {
        // Required empty public constructor
    }

    private final static int CAMERA_PIC_REQUEST = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_camera, container, false);
        mImage = (ImageView) v.findViewById(R.id.pictureTaken);
        startActivityForResult(
                new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE),
                CAMERA_PIC_REQUEST);

        v.findViewById(R.id.confirmPicture)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mPicture != null)
                            mListener.onPictureTaken(mPicture);
                    }
                });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {
            mPicture = (Bitmap) data.getExtras().get("data");
            mImage.setImageBitmap(mPicture);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnPictureTakenListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPictureTakenListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnPictureTakenListener {
        void onPictureTaken(Bitmap bitmap);
    }

}
