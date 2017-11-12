package he_arc.balljump;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.provider.Settings;

/**
 * Created by pedrocosta on 28.10.17.
 */

public class Player extends ObjectGame{
    private Bitmap image;
    private int width, height;
    private int moving;
    private int velocity = 0;
    private boolean playing = false;
    private int speed=15;
    private int gravity=1;
    private int speedJump=15;
    private int speedMax=15;
    private Bitmap bitmap;

    public Player(Bitmap image){
        this.image = image;
        this.width = 80;
        this.height = 80;
        this.x = GamePanel.WIDTH/2 - this.width/2;
        this.y = GamePanel.HEIGHT - this.height;
        this.dx = 0;
        this.dy = 0;
        bitmap = Bitmap.createScaledBitmap(image, width, height,false);
    }

    public void update(){
        if(moving == 1){
            dx = 5;
        }else if(moving == -1){
            dx = -5;
        }else{
            dx=0;
        }

        x += dx*2;

        if(x > GamePanel.WIDTH){
            x = -width;
        }
        if(x < -width){
            x = GamePanel.WIDTH;
        }

        constantJump();
    }


    public boolean collision(Plateform p)
    {
        boolean isInX = (x + width/3 >= p.getX()-width/3) && ((x + (width/3)*2) <= (p.getX() + p.getWidth() + width/3));
        boolean isInY = (y+height>=p.getY()) && (y+height<=p.getY()+p.getHeight());

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

    private void constantJump() {

        velocity = -speed;

        if(speed>-speedMax)
        {
            speed-=gravity;
        }

        dy = velocity;

        y += dy*2;

        if (y > GamePanel.HEIGHT-height){
            y = GamePanel.HEIGHT-height;
            jump();
        }
    }

    public void draw(Canvas canvas){

        canvas.drawBitmap(bitmap,x,y,null);

    }

    public void setMoving(int moving){this.moving = moving;}
    public boolean isPlaying() {return playing;}
    public void setPlaying(boolean playing) {this.playing = playing;}
}
