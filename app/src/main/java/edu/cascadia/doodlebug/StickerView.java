package edu.cascadia.doodlebug;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Hiromi on 12/5/2015.
 */
public class StickerView extends View {

    private DrawingView mView;
    private ImageView selectedImage = null;
   // View.OnTouchListener MyTouchListener;

    // stickers to drag & drop
    ImageView imageCat=(ImageView) findViewById(R.id.imgCat);
    ImageView imageDog=(ImageView) findViewById(R.id.imgDog);
    ImageView imageDolphin=(ImageView) findViewById(R.id.imgDolphin);
    ImageView imageHeart=(ImageView) findViewById(R.id.imgHeart);
    ImageView imageMustache=(ImageView) findViewById(R.id.imgMustache);
    ImageView imageSmilie=(ImageView) findViewById(R.id.imgSmilie);
    ImageView imageSunglasses=(ImageView) findViewById(R.id.imgSunglasses);

    private final class ChoiceTouchListener implements OnTouchListener{

        public boolean onTouch(View view, MotionEvent motionEvent){
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                // setup drag
                ClipData data = ClipData.newPlainText("", "");
                DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
            else {
                return false;
            }
        }
    }

    private class ChoiceDragListener implements OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }

            return true;
        }

    }

    //set touch listeners
    imageCat.setOnTouchListener(new ChoiceTouchListener());
    imageDog.setOnTouchListener(new ChoiceTouchListener());
    imageDolphin.setOnTouchListener(new ChoiceTouchListener());
    imageHeart.setOnTouchListener(new ChoiceTouchListener());
    imageMustache.setOnTouchListener(new ChoiceTouchListener());
    imageSmilie.setOnTouchListener(new ChoiceTouchListener());
    imageSunglasses.setOnTouchListener(new ChoiceTouchListener());

    //set drag listeners
    imageCat.setOnDragListener(new ChoiceDragListener());
    imageDog.setOnDragListener(new ChoiceDragListener());
    imageDolphin.setOnDragListener(new ChoiceDragListener());
    imageHeart.setOnDragListener(new ChoiceDragListener());
    imageMustache.setOnDragListener(new ChoiceDragListener());
    imageSmilie.setOnDragListener(new ChoiceDragListener());
    imageSunglasses.setOnDragListener(new ChoiceDragListener());





// ************  old code *******************//
    public StickerView(Context context) {
        super(context);


        selectedImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(selectedImage);

                v.startDrag(dragData, myShadow, null, 0);
                return true;
            }
        });

        selectedImage.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();

                        // Do nothing
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        layoutParams.leftMargin = x_cord;
                        layoutParams.topMargin = y_cord;
                        v.setLayoutParams(layoutParams);
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_ENDED:
                        // Do nothing
                        break;

                    case DragEvent.ACTION_DROP:
                        // Do nothing
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


        selectedImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(selectedImage);

                    selectedImage.startDrag(data, shadowBuilder, selectedImage, 0);
                    selectedImage.setVisibility(View.INVISIBLE);
                    return true;
                } else {
                    return false;
                }
            }
        });



    }
}


