package he_arc.balljump;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.content.Context.SENSOR_SERVICE;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by pedrocosta on 28.10.17.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = 532;
    public static final int HEIGHT = 850;
    private MainThread mainThread;
    private BackGround backGround;
    private Player player;
    private SensorAccelerationActivity sensorAccelerationActivity;



    public GamePanel(Context context){
        super(context);

        //Add the callback to the surface to intercept events
        getHolder().addCallback(this);

        mainThread = new MainThread(getHolder(),this);

        //Make gamePanel focusable so it can handle events
        setFocusable(true);

        //sensorAccelerationActivity = new SensorAccelerationActivity(this);
    }

    public void updatePlayer(float value)
    {
        if(player!=null)
        {
            player.setPlaying(true);
            if(value<-0.1)
            {
                player.setMoving(-1);
            }
            else if(value>0.1)
            {
                player.setMoving(1);
            }
            else
            {
                player.setMoving(0);
            }
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;

        while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        backGround = new BackGround(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.doodler),100,100);
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                player.setPlaying(true);
                if (event.getX() > getWidth()/2){
                    player.setMoving(1);
                }else {
                    player.setMoving(-1);
                }
                return true;
            case MotionEvent.ACTION_UP:
                player.setMoving(0);
                return false;
        }

        return super.onTouchEvent(event);
    }

    public void update(){
        if(player.isPlaying()){
            player.update();
        }
    }

    @Override
    public void draw(Canvas canvas){
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            backGround.draw(canvas);
            player.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }


}