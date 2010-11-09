
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

    public static final int zoneCharToIndex(char x) {
        switch(x) {
            case 'c': return 0x0;
            case 'L': return 0x1;
            case 'M': return 0x2;
            case 'R': return 0x3;
            case 'l': return 0x4;
            case 'm': return 0x5;
            case 'r': return 0x6;
            case 'N': return 0xA;
            case 'E': return 0xB;
            case 'S': return 0xC;
            case 'W': return 0xD;
            default:  return -1;
        }
    }

    public static final Action[][] loadActionTable(BufferedReader r) throws IOException {
        String s;
        Action a = null;
        Action[][] res = new Action[16][16];

        while((s = r.readLine()) != null) {
            if(s.equals("") || s.startsWith("#"))
                continue;

            switch(s.charAt(2)) {
                case '!':
                    // read layout change
                    a = Action.createChangeLayoutAction(s.substring(3));
                    break;
                case ':':
                    // read string
                    a = Action.createTextAction(s.substring(3));
                    break;
                case '=':
                    // read special event like ENTER
                    // TODO:
                    break;
            }

            // TODO: log errors
            res[zoneCharToIndex(s.charAt(0))][zoneCharToIndex(s.charAt(1))] = a;
        }

        return res;
    }
}
