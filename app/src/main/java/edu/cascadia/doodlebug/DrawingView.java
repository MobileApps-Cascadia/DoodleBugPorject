package edu.cascadia.doodlebug;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class DrawingView extends View {

    private final int TOUCH_TOLERANCE = 10;

    private float mX, mY; // coordinates for tracking path drawing
    private Bitmap mBitmap; // bitmap for the canvas
    private Canvas mCanvas; // canvas object to draw onto
    private Path mPath; // path for drawing
    private Paint mPaint; // paint to describe the line being drawn
    private Paint mBitmapPaint;
    private Context mContext;

    private final Map<Integer, Point> mPointMap = new HashMap<>(); // HashMap for storing all of the paths
    private final Map<Integer, Path> mPathMap = new HashMap<Integer, Path>(); // HashMap for storing all of the last points for the paths

    public DrawingView(Context c) {
        this(c, null);
    }

    public DrawingView(Context c, AttributeSet attr) {
        super(c, attr);
        mContext = c;

        setWillNotDraw(false);
        setWillNotCacheDrawing(false);

        // set up paint object for the line
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
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

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

        for (Integer key : mPathMap.keySet())
            canvas.drawPath(mPathMap.get(key), mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int actionIndex = event.getActionIndex();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touchStart(event.getX(actionIndex), event.getY(actionIndex), event.getPointerId(actionIndex));
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                touchEnd(event.getPointerId(actionIndex));
                break;
        }

        invalidate();
        return true;
    }

    private void touchStart(float x, float y, int lineID) {

        Point point;
        Path path;

        if (mPathMap.containsKey(lineID)) {
            path = mPathMap.get(lineID);
            path.reset();
            point = mPointMap.get(lineID);
        } else {
            path = new Path();
            point = new Point();
            mPathMap.put(lineID, path);
            mPointMap.put(lineID, point);
        }

        path.moveTo(x, y);
        point.x = (int) x;
        point.y = (int) y;
    }

    private void touchMove(MotionEvent event) {

        for (int i = 0; i < event.getPointerCount(); ++i) {
            int pointerID = event.getPointerId(i);
            int pointerIndex = event.findPointerIndex(pointerID);

            if (mPathMap.containsKey(pointerID)) {
                float x = event.getX(pointerIndex);
                float y = event.getY(pointerIndex);

                Path path = mPathMap.get(pointerID);
                Point point = mPointMap.get(pointerID);

                float deltaX = Math.abs(x - point.x);
                float deltaY = Math.abs(y - point.y);

                // check if greater than touch tolerance
                if (deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE) {
                    //draw the line to the new coordinates
                    path.quadTo(point.x, point.y, (x + point.x) / 2, (y + point.y) / 2);
                    point.x = (int) x;
                    point.y = (int) y;
                }
            }
        }
    }

    private void touchEnd(int lineID) {
        Path path = mPathMap.get(lineID);
        mCanvas.drawPath(path, mPaint);
        path.reset();
    }

    // method for clearing the screen. will leave canvas white afterwards
    private void clear() {
        mPathMap.clear();
        mPointMap.clear();
        mBitmap.eraseColor(Color.WHITE);
        invalidate();
    }

    public int getLineWidth() {
        return (int) mPaint.getStrokeWidth();
    }

    public void setLineWidth(int width) {
        mPaint.setStrokeWidth(width);
    }

    public void setBackground(Bitmap bitmap) {
        mCanvas.drawBitmap(
                Bitmap.createScaledBitmap(bitmap, mCanvas.getWidth(), mCanvas.getHeight(), false),
                0, 0, mBitmapPaint);
    }

    public int getDrawingColor() { return mPaint.getColor(); }

    public void setDrawingColor(int color) { mPaint.setColor(color); }
}
