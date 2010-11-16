
package org.me.strokeime;

import android.view.View;
import android.view.MotionEvent;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.content.res.Resources;
import android.util.TypedValue;
import static org.me.strokeime.Layout.*;

public class InputView extends View {
    // colors
    private ColorTheme mColorTheme;
    private Paint mFillPaint;
    private Paint mStrokePaint;
    private Paint mTextPaint;
    private Paint mHotTextPaint;
    private Paint mBackTextPaint;

    private float mTextSize;
    private float mHotTextSize;
    private float mBackTextSize;

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

    private void createPaints() {
        mFillPaint = new Paint();
        mFillPaint.setStyle(Paint.Style.FILL);

        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(1.5f);

        mTextPaint = new Paint(Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);

        mHotTextPaint = new Paint(Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mHotTextPaint.setStyle(Paint.Style.FILL);

        mBackTextPaint = new Paint(Paint.SUBPIXEL_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        mBackTextPaint.setStyle(Paint.Style.FILL);
    }
    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createPaints();
    }
    public InputView(Context context) {
        super(context);
        createPaints();
    }

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

        mFillPaint.setColor(mColorTheme.bg);
        mStrokePaint.setColor(mColorTheme.fg);
        mTextPaint.setColor(mColorTheme.txt);
        mHotTextPaint.setColor(mColorTheme.txtHot);
        mBackTextPaint.setColor(mColorTheme.txtBack);

        invalidate();
    }

    private final int getZone (float x, float y) {
        int w, h;
        w = getWidth();
        h = getHeight();
        // add some additional vertical space for the panel
        if(x < 0 || y < -0.1*h || x > w || y > h) {
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

        float hs = getWidth()/9f;    // horizontal step
        float vs = 1.1f * mTextSize; // vertical step

        float top = 0.02f * mTextSize;
        drawZoneKeys(canvas, LT, 0f,              top, hs, vs);
        drawZoneKeys(canvas, MT, mVerticalLine1X, top, hs, vs);
        drawZoneKeys(canvas, RT, mVerticalLine2X, top, hs, vs);
        top = h - 3*vs - 0.2f * mTextSize;
        drawZoneKeys(canvas, LB, 0f,              top, hs, vs);
        drawZoneKeys(canvas, MB, mVerticalLine1X, top, hs, vs);
        drawZoneKeys(canvas, RB, mVerticalLine2X, top, hs, vs);

        top = mCenterAreaRect.top + 0.1f * mTextSize;
        drawZoneKeys(canvas, MC, mVerticalLine1X, top, hs, vs*0.8f);
        //org.me.strokeime.gliphs.GliphEnter g = new org.me.strokeime.gliphs.GliphEnter();
        //g.draw(canvas, 200f, 40f, mTextPaint);
    }

    private final void drawZoneKeys(Canvas canvas, int zone, float bx, float by, float hs, float vs) {
        drawKey(canvas, mShiftState, zone, OT, bx,      bx+3*hs, by + mBackTextSize);
        drawKey(canvas, mShiftState, zone, LT, bx,      bx+hs,   by+vs);
        drawKey(canvas, mShiftState, zone, MT, bx+hs,   bx+2*hs, by+vs);
        drawKey(canvas, mShiftState, zone, RT, bx+2*hs, bx+3*hs, by+vs);
        drawKey(canvas, mShiftState, zone, MC, bx+hs,   bx+2*hs, by+2*vs);
        drawKey(canvas, mShiftState, zone, LB, bx,      bx+hs,   by+3*vs);
        drawKey(canvas, mShiftState, zone, MB, bx+hs,   bx+2*hs, by+3*vs);
        drawKey(canvas, mShiftState, zone, RB, bx+2*hs, bx+3*hs, by+3*vs);
    }
    private final void drawKey(Canvas canvas, int shiftState, int strokeStart, int strokeEnd, float x1, float x2, float y) {
        Key k = mLayout.getKey(shiftState, strokeStart, strokeEnd);
        if(k == null) return; // for unused strokes

        Paint p;
        if(strokeEnd == OT)
            p = mBackTextPaint;
        else if(strokeStart == strokeEnd)
            p = mHotTextPaint;
        else
            p = mTextPaint;

        if(k.gliph == null) {
            canvas.drawText(k.label, x1 + (x2 - x1 - p.measureText(k.label))/2.0f, y, p);
        } else {
            k.gliph.draw(canvas,     x1 + (x2 - x1 - k.gliph.measureWidth(p))/2.0f,     y, p);
        }
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

        mHotTextSize = mTextSize = h/11.0f;
        mBackTextSize = h/3.5f;
        mTextPaint.setTextSize(mTextSize);
        mHotTextPaint.setTextSize(mHotTextSize);
        mBackTextPaint.setTextSize(mBackTextSize);
    }
}
