
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

    // current stroke initial position
    private int mStrokeStartZone;

    private Action[][] mActionTable;

    private InputEventListener mInputListener = null;

    public InputView(Context context, AttributeSet attrs) { super(context, attrs); }
    public InputView(Context context) { super(context); }

    public final void setInputEventListener (InputEventListener listener) {
        mInputListener = listener;
    }
    public final void setActionTable (Action[][] actionTable){
        mActionTable = actionTable;
        // TODO: invalidate view
    }

    private final int getZone (float x, float y) {
        int w, h;
        w = getWidth();
        h = getHeight();
        if(x < 0 || y < 0 || x > w || y > h) {
            // when point is outside of our View
            return 0xA;
        } else if(Util.pointInOval(mCenterAreaRect, x, y)) {
            return 0x0;
        } else if(y < mHorizontalLineY) {
            if(x < mVerticalLine1X) {
                return 0x1;
            } else if(x < mVerticalLine2X) {
                return 0x2;
            } else {
                return 0x3;
            }
        } else {
            if(x < mVerticalLine1X) {
                return 0x4;
            } else if(x < mVerticalLine2X) {
                return 0x5;
            } else {
                return 0x6;
            }
        }
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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mStrokeStartZone = getZone(event.getX(), event.getY());
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            int strokeEndZone = getZone(event.getX(), event.getY());
            if(mInputListener != null)
                mInputListener.onInput(new InputEvent(this, mActionTable[mStrokeStartZone][strokeEndZone]));
                //mInputListener.onInput(new InputEvent(this, "" + Integer.toHexString(mStrokeStartZone) + " -> " + Integer.toHexString(strokeEndZone) + "\n"));
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
