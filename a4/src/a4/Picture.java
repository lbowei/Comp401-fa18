//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package a4;

import java.util.Iterator;

public interface Picture {
    int getWidth();

    int getHeight();

    Pixel getPixel(int var1, int var2);

    Picture paint(int var1, int var2, Pixel var3);

    Picture paint(int var1, int var2, Pixel var3, double var4);

    Picture paint(int var1, int var2, int var3, int var4, Pixel var5);

    Picture paint(int var1, int var2, int var3, int var4, Pixel var5, double var6);

    Picture paint(int var1, int var2, double var3, Pixel var5);

    Picture paint(int var1, int var2, double var3, Pixel var5, double var6);

    Picture paint(int var1, int var2, Picture var3);

    Picture paint(int var1, int var2, Picture var3, double var4);

    String getCaption();

    void setCaption(String var1);

    SubPicture extract(int var1, int var2, int var3, int var4);

    Iterator<Pixel> sample(int var1, int var2, int var3, int var4);

    Iterator<SubPicture> window(int var1, int var2);

    Iterator<SubPicture> tile(int var1, int var2);

    Iterator<Pixel> zigzag();
}
