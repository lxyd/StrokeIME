
package org.me.strokeime;

public class ColorTheme {
    public final int bg;
    public final int fg;
    public final int txt; 
    public final int txtBack;
    public final int txtHot;

    public ColorTheme(int bg, int fg, int txt, int txtHot, int txtBack) {
        this.bg = bg;
        this.fg = fg;
        this.txt = txt;
        this.txtHot = txtHot;
        this.txtBack = txtBack;
    }
}
