package he_arc.balljump;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by pedrocosta on 28.10.17.
 */

public class BackGround {
    private Bitmap image;

    public BackGround(Bitmap image){
        this.image = image;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,0,0,null);
    }
}
