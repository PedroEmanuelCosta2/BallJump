package he_arc.balljump;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by pedrocosta on 03.11.17.
 */

public class Plateform extends ObjectGame{

    private Paint paint;
    private int id;

    public Plateform(int x, int y, int id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        this.width = 100;
        this.height = 20;
        this.dy = 0;
        this.paint = new Paint();
        this.paint.setColor(Color.GREEN);
        this.paint.setStrokeWidth(3);
    }

    public void shift(int shift)
    {
        dy = shift;
        y += dy;
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
        canvas.drawRect(getRectangle(), this.paint);
    }

    public int getId(){return this.id;}
}
