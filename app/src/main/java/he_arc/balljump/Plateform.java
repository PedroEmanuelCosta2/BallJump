package he_arc.balljump;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by pedrocosta on 03.11.17.
 */

public class Plateform extends ObjectGame{

    private Paint paint;
    private int breakable;

    public Plateform(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 20;
        this.dy = 0;
        Random random = new Random();
        this.breakable = random.nextInt(40);
        this.paint = new Paint();
        if (this.breakable == 0) {
            this.paint.setColor(Color.RED);
        }else{
            this.paint.setColor(Color.GREEN);
        }
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

    public int getBreakable(){
        return this.breakable;
    }
}
