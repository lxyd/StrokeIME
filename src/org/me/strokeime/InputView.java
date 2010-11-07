
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
    private int mForegroundColor;
    private int mBackgroundColor;
    private Paint mFillPaint;
    private Paint mStrokePaint;

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public InputView(Context context) {
        super(context);
    }

    private InputEventListener inputListener = null;

    public void setInputEventListener (InputEventListener listener) {
        this.inputListener = listener;
    }
    
    public void setColors(int foreground, int background) {
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
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if(inputListener != null)
                inputListener.onInput(new InputEvent(this));
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        // TODO: draw letters
        int w = getWidth();
        int h = getHeight();

        canvas.drawPaint(mFillPaint);

        canvas.drawLine(w/3, 0, w/3, h, mStrokePaint);
        canvas.drawLine(2*w/3, 0, 2*w/3, h, mStrokePaint);
        canvas.drawLine(0, h/2, w, h/2, mStrokePaint);
        RectF r = new RectF(w/5, h/3, 4*w/5, 2*h/3);
        canvas.drawOval(r, mFillPaint);
        canvas.drawOval(r, mStrokePaint);

        invalidate();
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        h = Math.min(w, h)/2;
        setMeasuredDimension(w, h);
    }
}
