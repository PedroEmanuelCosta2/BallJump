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
    private int incrementJump = 0;
    private boolean playing = false;
    private boolean jumping = false;
    private int speed=20;
    private int gravity=1;

    public Player(Bitmap image){
        this.image = image;
        this.width = 50;
        this.height = 100;
        this.x = GamePanel.WIDTH/2 - this.width/2;
        this.y = GamePanel.HEIGHT - this.height;
        this.dx = 0;
        this.dy = 0;
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

    public boolean collision(Plateform p) {
        if ((x >= p.getX())
            && (x + width <= p.getX() + p.getWidth())
            && (y + height <= p.getY()))
        {
            return true;
        }else{
            return false;
        }
    }

    public void jump()
    {
        speed=20;
    }

    private void constantJump() {

        velocity = -speed;

        if(speed>-20)
        {
            speed-=gravity;
        }

        dy = velocity;

        y += dy*2;

        if (y > GamePanel.HEIGHT-height){
            y = GamePanel.HEIGHT-height;
            jumping = false;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
    }

    public void setMoving(int moving){this.moving = moving;}
    public boolean isPlaying() {return playing;}
    public void setPlaying(boolean playing) {this.playing = playing;}
}
