
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
import static org.me.strokeime.Layout.*;

public class InputView extends View {
    // colors
    private ColorTheme mColorTheme;
    private Paint mFillPaint;
    private Paint mStrokePaint;
    private Paint mStrokePaintBright;
    private Paint mStrokePaintDim;

    private float mFontSize;

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
        invalidate();
    }

    public final void setColorTheme(ColorTheme colorTheme) {
        mColorTheme = colorTheme;

        mFillPaint = new Paint();
        mFillPaint.setColor(mColorTheme.bg);

        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        mStrokePaint.setColor(mColorTheme.fg);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(1);

        mStrokePaintBright = new Paint(mStrokePaint);
        mStrokePaintBright.setColor(mColorTheme.fgBright);

        mStrokePaintDim = new Paint(mStrokePaint);
        mStrokePaintDim.setColor(mColorTheme.fgDim);

        invalidate();
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
        int w = getWidth();
        int h = getHeight();

        canvas.drawPaint(mFillPaint);

        canvas.drawLine(0, 0, w, 0, mStrokePaint);
        canvas.drawLine(mVerticalLine1X, 0, mVerticalLine1X, h, mStrokePaint);
        canvas.drawLine(mVerticalLine2X, 0, mVerticalLine2X, h, mStrokePaint);
        canvas.drawLine(0, mHorizontalLineY, w, mHorizontalLineY, mStrokePaint);
        canvas.drawOval(mCenterAreaRect, mFillPaint);
        canvas.drawOval(mCenterAreaRect, mStrokePaint);

        float hs = w/9f; // horizontal step
        float vs = h/6f; // vertical step
        // begin point
        float bx;
        float by;
        // TODO: draw letters
        // Labels on the LT area:
        bx = 0.2f * mFontSize;
        by = 1.2f * mFontSize;
        drawKey(canvas, mShiftState, LT, LT, bx,           by          );
        drawKey(canvas, mShiftState, LT, MT, bx + hs,      by          );
        drawKey(canvas, mShiftState, LT, RT, bx + hs + hs, by          );
        drawKey(canvas, mShiftState, LT, LB, bx,           by + vs + vs);
        drawKey(canvas, mShiftState, LT, MB, bx + hs,      by + vs + vs);
        drawKey(canvas, mShiftState, LT, RB, bx + hs + hs, by + vs + vs);
    }

    private void drawKey(Canvas canvas, int shiftState, int strokeStart, int strokeEnd, float x, float y) {
        Key k = mLayout.getKey(shiftState, strokeStart, strokeEnd);
        if(k == null) return; // for unused strokes
        // TODO: add support for drawable keys
        canvas.drawText(k.label, x, y, mStrokePaint);
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

        mFontSize = h/10.0f;
        mStrokePaint.setTextSize(mFontSize);
    }
}
