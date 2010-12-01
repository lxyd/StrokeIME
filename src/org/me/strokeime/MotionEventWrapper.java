
package org.me.strokeime;

import android.view.MotionEvent;

public interface MotionEventWrapper {
    int getActionMasked(MotionEvent e);
    int getPointerId(MotionEvent e, int pointerIndex);
    float getX(MotionEvent e, int pointerIndex);
    float getY(MotionEvent e, int pointerIndex);
}
