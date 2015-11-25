package edu.cascadia.doodlebug;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.LinearLayout;

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
    }

    // This defines the touch listener
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DROP:
                    switch (mView.getId()){
                        case R.id.imgCat:
                            // Dropped, reassign View to ViewGroup
                            View view = (View) event.getLocalState();
                            ViewGroup owner = (ViewGroup) view.getParent();
                            owner.removeView(view);
                            LinearLayout container = (LinearLayout) v;
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);
                            break;
                        case R.id.imgDog:
                            // Dropped, reassign View to ViewGroup
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);
                            break;
                        case R.id.imgDolphin:
                            // Dropped, reassign View to ViewGroup
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);
                            break;
                        case R.id.imgHeart:
                            // Dropped, reassign View to ViewGroup
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);
                            break;
                        case R.id.imgMustache:
                            // Dropped, reassign View to ViewGroup
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);
                            break;
                        case R.id.imgSmilie:
                            // Dropped, reassign View to ViewGroup
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);
                            break;
                        case R.id.imgSunglasses:
                            // Dropped, reassign View to ViewGroup
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);
                            break;

                    }

                case DragEvent.ACTION_DRAG_ENDED:
                    return true;
                default:
                    break;
            }
            return true;
        }
    } // End of adding stickers




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
