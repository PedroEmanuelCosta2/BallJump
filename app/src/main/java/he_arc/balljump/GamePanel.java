package he_arc.balljump;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;

/**
 * Created by pedrocosta on 28.10.17.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = 532;
    public static final int HEIGHT = 850;
    private MainThread mainThread;
    private BackGround backGround;
    private Player player;
    private ArrayList<Plateform> plateformArrayList;
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
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.doodler));
        plateformArrayList = new ArrayList<Plateform>();
        plateformsGeneration(150, 60);
        mainThread.setRunning(true);
        mainThread.start();
        player.setPlaying(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                player.jump();
        }

        return super.onTouchEvent(event);
    }

    public void update(){
        if(player.isPlaying()){

            player.update();

            for(Plateform p : plateformArrayList)
            {

                if(p.getY() - (player.getY() + 60) <= 5 && p.getY() - (player.getY() + 60) >= -5){
                    if(player.collision(p)){
                        player.jump();
                    }
                }
            }

            plateformsDestruction();
        }
    }

    private void plateformsDestruction()
    {
        for(int i = 0; i < plateformArrayList.size(); i++){
            if(plateformArrayList.get(i).outOfScreen()){
                plateformArrayList.remove(i);
            }
        }
    }

    private void plateformsGeneration(int limit, int decrement)
    {
        for (int i = HEIGHT; i > limit ; i-= decrement){
            Plateform plateform = new Plateform((WIDTH-50) - ((int) (Math.random() * ((500) + 1))) , HEIGHT - i);
            plateformArrayList.add(plateform);
        }
    }

    private void drawPlateforms(Canvas canvas)
    {
        for(Plateform p : plateformArrayList){
            p.draw(canvas);
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            backGround.draw(canvas);
            player.draw(canvas);
            drawPlateforms(canvas);
            canvas.restoreToCount(savedState);
        }
    }


}
