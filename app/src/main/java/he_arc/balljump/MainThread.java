package he_arc.balljump;

import android.graphics.Canvas;
import android.provider.Settings;
import android.util.Log;
import android.view.SurfaceHolder;
import 	android.os.SystemClock;

import java.io.Console;

import static android.os.SystemClock.uptimeMillis;

/**
 * Created by pedrocosta on 28.10.17.
 */

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        final int FRAMES_PER_SECOND = 30;
        final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
        long next_game_tick = uptimeMillis();
        // uptimeMillis() returns the current number of milliseconds
        // that have elapsed since the system was started

        long sleep_time = 0;
        while (running){
            canvas = null;
            try {
                this.lock();

                synchronized (this.surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
                next_game_tick += SKIP_TICKS;
                sleep_time = next_game_tick - uptimeMillis();
                if( sleep_time >= 0 ) {
                    Thread.sleep( sleep_time );
                }
                else {
                    System.out.println("WE ARE LATE");
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(canvas!=null)
                {
                    try {
                        unlockAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public void lock(){
        canvas = this.surfaceHolder.lockCanvas();
    }

    public void unlockAndPost(Canvas canvas){
        this.surfaceHolder.unlockCanvasAndPost(canvas);
    }

    public Canvas getCanvas (){
        return this.canvas;
    }
}
