
package org.me.strokeime;

import android.graphics.RectF;
import java.io.BufferedReader;
import java.io.IOException;

public final class Util {
    public static final boolean pointInOval(RectF oval, float x, float y) {
        float dx, dy, c, d;

        dx = x - oval.centerX();
        dy = y - oval.centerY();

        d = oval.height();
        c = d/oval.width();

        return dx*c*dx*c + dy*dy <= d*d/4;
    }

    public static final int hexCharToIndex(char x) {
        if(x >= '0' && x <= '9')
            return x - '0';
        if(x >= 'a' && x <= 'z')
            return x - 'a' + 10;
        if(x >= 'A' && x <= 'Z')
            return x - 'A' + 10;
        return -1;
    }

    public static final Action[][] loadActionTable(BufferedReader r) throws IOException {
        String s;
        Action a = null;
        Action[][] res = new Action[16][16];

        while((s = r.readLine()) != null) {
            if(s.equals(""))
                continue;

            switch(s.charAt(2)) {
                case '!':
                    // read layout change
                    // TODO:
                    break;
                case ':':
                    // read char
                    a = new Action(s.charAt(3));
                    break;
                case '=':
                    // read special event like ENTER
                    // TODO:
                    break;
            }

            res[hexCharToIndex(s.charAt(0))][hexCharToIndex(s.charAt(1))] = a;
        }

        return res;
    }
}
