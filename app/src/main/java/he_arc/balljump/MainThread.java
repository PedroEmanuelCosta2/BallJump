package he_arc.balljump;

import android.graphics.Canvas;
import android.provider.Settings;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.Console;

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
        while (running){
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (this.surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(canvas!=null)
                {
                    try {
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
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
}
