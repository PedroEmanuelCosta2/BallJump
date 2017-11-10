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

    public Player(Bitmap image, int width, int height){
        this.image = image;
        this.width = width;
        this.height = height;
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

        //constantJump();
    }

    private void constantJump() {
        if(!jumping){
            incrementJump = 0;
            jumping = true;
        }

        incrementJump+= 12;

        velocity = -12;

        if (incrementJump >= 100){
            velocity = 12;
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
