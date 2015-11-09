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

    private final int TOUCH_TOLERANCE = 4;

    private float mX, mY; // coordinates for tracking path drawing
    private Bitmap mBitmap; // bitmap for the canvas
    private Canvas mCanvas; // canvas object to draw onto
    private Path mPath; // path for drawing
    private Paint mPaint; // paint to describe the line being drawn
    private Paint mBitmapPaint;
    private Context mContext;

    public DrawingView(Context c) {
        this(c, null);
    }

    public DrawingView(Context c, AttributeSet attr) {
        super(c, attr);
        mContext = c;
        mPath = new Path();

        // set up paint object for the line
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);

        // bitmap paint to draw bitmap
        mBitmapPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final float x = event.getX();
        final float y = event.getY();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                touchEnd();
                invalidate();
                break;
        }

        return true;
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
        // get the change in both x and y directions
        float deltaX = Math.abs(x - mX);
        float deltaY = Math.abs(y - mY);

        // check if greater than touch tolerance
        if (deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE) {
            //draw the line to the new coordinates
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchEnd() {
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        mPath.close();
        mPath.reset();
    }
}
