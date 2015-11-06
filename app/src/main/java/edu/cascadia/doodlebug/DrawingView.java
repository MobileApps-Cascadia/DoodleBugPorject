package edu.cascadia.doodlebug;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    private final int TOUCH_TOLERANCE = 10;

    private float mX, mY; // coordinates for tracking path drawing
    private Bitmap mBitmap; // bitmap for the canvas
    private Canvas mCanvas; // canvas object to draw onto
    private Path mPath; // path for drawing
    private Paint mPaint; // paint to describe the line being drawn
    private Context mContext;

    public DrawingView(Context c) {
        this(c, null);
    }

    public DrawingView(Context c, AttributeSet attr) {
        super(c, attr);
        mContext = c;
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCanvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchEnd();
                invalidate();
                break;
        }

        return false;
    }

    private void touchStart(float x, float y) {
        // set up the path to start drawing from this location
        mPath.reset();
        mPath.moveTo(x, y);

        // add the start coordinates
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        // TODO: implement touchMove method
    }

    private void touchEnd() {
        // TODO: implement touchEnd method
    }
}
