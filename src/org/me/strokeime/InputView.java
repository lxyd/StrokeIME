
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
        // TODO: draw areas
        int w = getWidth();
        int h = getHeight();
        Paint f = new Paint();
        Paint s = new Paint(Paint.ANTI_ALIAS_FLAG);

        // TODO: move paints initialization to something like setTheme()
        f.setColor(getResources().getColor(R.color.input_background_dark));
        canvas.drawPaint(f);

        s.setColor(getResources().getColor(R.color.input_foreground_dark));
        s.setStyle(Paint.Style.STROKE);
        s.setStrokeWidth(1);
        //canvas.drawLine(0, 0, w, 0, s);
        canvas.drawLine(w/3, 0, w/3, h, s);
        canvas.drawLine(2*w/3, 0, 2*w/3, h, s);
        canvas.drawLine(0, h/2, w, h/2, s);
        RectF r = new RectF(w/5, h/3, 4*w/5, 2*h/3);
        canvas.drawOval(r, f);
        canvas.drawOval(r, s);
        /*
        Paint myPaint = new Paint();
        myPaint.setColor(getResources().getColor(R.color.input_background_dark));
        canvas.drawPaint(myPaint);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeWidth(1);
        myPaint.setColor(getResources().getColor(R.color.input_foreground_dark));
        canvas.drawRect(3, 3, getWidth() - 3, getHeight() - 3, myPaint);
        */
        invalidate();
    }

    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
        //Resources r = getResources();
        //float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
        //setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (int)px);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        h = Math.min(w, h)/2;
        setMeasuredDimension(w, h);
    }
}
