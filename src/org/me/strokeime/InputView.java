
package org.me.strokeime;

import android.view.View;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.content.res.Resources;
import android.util.TypedValue;

public class InputView extends View {
    // colors
    private int mForegroundColor;
    private int mBackgroundColor;
    private Paint mFillPaint;
    private Paint mStrokePaint;

    // areas sizes
    private RectF mCenterAreaRect = new RectF();
    private float mHorizontalLineY;
    private float mVerticalLine1X;
    private float mVerticalLine2X;

    // current stroke's initial position
    private int mStrokeStartZone;

    private Layout mLayout;
    private int mShiftState;

    private InputEventListener mInputListener = null;

    public InputView(Context context, AttributeSet attrs) { super(context, attrs); }
    public InputView(Context context) { super(context); }

    public final void setInputEventListener (InputEventListener listener) {
        mInputListener = listener;
    }
    public final void setLayout (Layout layout, int shiftState){
        mLayout = layout;
        mShiftState = shiftState;
        // TODO: invalidate view
    }

    public final void setColors(int foreground, int background) {
        mForegroundColor = foreground;
        mBackgroundColor = background;
        mFillPaint = new Paint();
        mFillPaint.setColor(mBackgroundColor);
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setColor(mForegroundColor);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(1);
        // TODO: invalidate view
    }

    private final int getZone (float x, float y) {
        int w, h;
        w = getWidth();
        h = getHeight();
        if(x < 0 || y < 0 || x > w || y > h) {
            // when point is outside of our View
            // TODO: add support for OB,OR,OL
            return Layout.OT;
        } else if(Util.pointInOval(mCenterAreaRect, x, y)) {
            return Layout.MC;
        } else if(y < mHorizontalLineY) {
            if(x < mVerticalLine1X) {
                return Layout.LT;
            } else if(x < mVerticalLine2X) {
                return Layout.MT;
            } else {
                return Layout.RT;
            }
        } else {
            if(x < mVerticalLine1X) {
                return Layout.LB;
            } else if(x < mVerticalLine2X) {
                return Layout.MB;
            } else {
                return Layout.RB;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mStrokeStartZone = getZone(event.getX(), event.getY());
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            int strokeEndZone = getZone(event.getX(), event.getY());
            Action a = mLayout.getAction(mShiftState, mStrokeStartZone, strokeEndZone);
            if(mInputListener != null && a != null)
                mInputListener.onInput(new InputEvent(this, a));
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        // TODO: draw letters
        int w = getWidth();
        int h = getHeight();

        canvas.drawPaint(mFillPaint);

        canvas.drawLine(mVerticalLine1X, 0, mVerticalLine1X, h, mStrokePaint);
        canvas.drawLine(mVerticalLine2X, 0, mVerticalLine2X, h, mStrokePaint);
        canvas.drawLine(0, mHorizontalLineY, w, mHorizontalLineY, mStrokePaint);
        canvas.drawOval(mCenterAreaRect, mFillPaint);
        canvas.drawOval(mCenterAreaRect, mStrokePaint);

        invalidate();
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        h = Math.min(w, h)/2;
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onSizeChanged (int w, int h, int oldw, int oldh) {
        mHorizontalLineY = h/2.0f;
        mVerticalLine1X  = w/3.0f;
        mVerticalLine2X  = 2*w/3.0f;
        mCenterAreaRect.left   = w/5.0f;
        mCenterAreaRect.top    = h/3.0f;
        mCenterAreaRect.right  = 4*w/5.0f;
        mCenterAreaRect.bottom = 2*h/3.0f;
    }
}
