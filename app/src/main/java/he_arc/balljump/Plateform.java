package he_arc.balljump;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by pedrocosta on 03.11.17.
 */

public class Plateform extends ObjectGame{

    private Bitmap image;
    private Rect source;
    private Rect destination;
    private Paint paint;

    public Plateform(int x, int y)
    {
        this.x = x;
        this.y = y;
        //this.image = image;
        this.width = 100;
        this.height = 20;
        this.dy = 0;
        //this.source = new Rect(this.x,this.y,this.image.getWidth(), this.image.getHeight());
        //this.destination = new Rect(this.x,this.y,this.width, this.height);
        this.paint = new Paint();
        this.paint.setColor(Color.GREEN);
        this.paint.setStrokeWidth(3);
    }

    public void shift()
    {
        dy = 12;
        y += dy*2;
    }

    public boolean outOfScreen()
    {
        if(y>GamePanel.HEIGHT){
            return true;
        }else{
            return false;
        }
    }

    public void draw(Canvas canvas){
        //canvas.drawBitmap(image, source,destination,null);
        canvas.drawRect(getRectangle(), this.paint);
    }
}
