package he_arc.balljump;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.Settings;

/**
 * Created by pedrocosta on 28.10.17.
 */

public class Player extends ObjectGame
{
    private int width, height;
    private int moving;
    private int speed=10;
    private int gravity=2;
    private int speedJump=30;
    private int speedMax=30;
    private int speedX=8;
    private Bitmap bitmap;

    public Player(Bitmap image)
    {
        this.width = 80;
        this.height = 80;
        this.x = GamePanel.WIDTH/2 - this.width/2;
        this.y = GamePanel.HEIGHT - this.height - 100;
        this.dx = 0;
        this.dy = 0;
        bitmap = Bitmap.createScaledBitmap(image, width, height,false);
    }

    public int update()
    {
        if(moving == 1)
        {
            dx = speedX;
        }
        else if(moving == -1)
        {
            dx = -speedX;
        }
        else
        {
            dx=0;
        }

        x += dx;

        if(x > GamePanel.WIDTH)
        {
            x = -width;
        }
        if(x < -width)
        {
            x = GamePanel.WIDTH;
        }

        return constantJump();
    }

    public boolean gameOver()
    {
        if (y > GamePanel.HEIGHT)
        {
            return true;
        }else{
            return false;
        }
    }

    public boolean collision(Plateform p)
    {

        boolean isInX = (x + width/3 >= p.getX()-width/3) && ((x + (width/3)*2) <= (p.getX() + p.getWidth() + width/3));
        boolean isInY = (y+height>=p.getY()) && (y+height<=p.getY()+speedMax);

        if (isInX && isInY && speed<0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void jump()
    {
        speed=speedJump;
    }

    private int constantJump()
    {
        int velocity = -speed;

        if(speed>-speedMax)
        {
            speed-=gravity;
        }

        y += velocity;

        if(y<GamePanel.HEIGHT/2)
        {
            y=GamePanel.HEIGHT/2;
            return velocity;
        }
        return 0;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap,x,y,null);
    }

    public void setMoving(int moving)
    {
        this.moving = moving;
    }

    public int getSpeed(){
        return speed;
    }
}
