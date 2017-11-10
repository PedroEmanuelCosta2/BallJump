package he_arc.balljump;

import android.graphics.Rect;

/**
 * Created by pedrocosta on 28.10.17.
 */

public abstract class ObjectGame {
    protected int x,y;
    protected int dx,dy;
    protected int width, height;

    public Rect getRectangle(){
        return new Rect(x,y,x+width,y+height);
    }

    /**
     *  GETTERS
     */
    public int getX() {return x;}
    public int getY() {return y;}
    public int getHeight() {return height;}
    public int getWidth() {return width;}
}
