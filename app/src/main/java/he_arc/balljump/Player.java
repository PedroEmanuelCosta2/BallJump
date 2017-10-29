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
    private boolean playing = false;

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
            dx = 12;
        }else if(moving == -1){
            dx = -12;
        }else{
            dx = 0;
        }

        x += dx*2;

        if(x > GamePanel.WIDTH){
            x = -width;
        }
        if(x < -width){
            x = GamePanel.WIDTH;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
    }

    public void setMoving(int moving){this.moving = moving;}
    public boolean isPlaying() {return playing;}
    public void setPlaying(boolean playing) {this.playing = playing;}
}
