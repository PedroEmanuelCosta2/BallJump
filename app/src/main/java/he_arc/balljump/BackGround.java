package he_arc.balljump;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by pedrocosta on 28.10.17.
 */

public class BackGround {
    private Bitmap image;
    private int x;
    private int y;

    public BackGround(Bitmap image, int width, int height,int x, int y){
        this.image = Bitmap.createScaledBitmap(image,width,height,false);
        this.x=x;
        this.y=y;

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
    }
}
