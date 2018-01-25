package he_arc.balljump;

import android.content.Context;
import android.graphics.Bitmap;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pedrocosta on 28.10.17.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = 532;
    public static final int HEIGHT = 850;
    private MainThread mainThread;
    private BackGround backGround;
    private BackGround backGroundAcid;
    private Player player;
    private Bitmap bitmapPlateform;
    private Bitmap bitmapPlateformRed;
    private Paint paint;
    private SensorAccelerationActivity sensorAccelerationActivity;
    private List<Plateform> plateformArrayList;
    private int score = 0;
    private boolean isShifting = false;
    private Plateform lastPlateform;
    int deltaYPlatform=60;

    public GamePanel(SensorAccelerationActivity sensorAccelerationActivity){

        super(sensorAccelerationActivity);
        System.out.println("Constructeur gamepanel");

        this.sensorAccelerationActivity = sensorAccelerationActivity;
        //Add the callback to the surface to intercept events
        getHolder().addCallback(this);

        mainThread = new MainThread(getHolder(),this);

        //Make gamePanel focusable so it can handle events
        setFocusable(true);

    }

    public void updatePlayer(float value)
    {
        if(player!=null)
        {
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
        //boolean retry = true;
        //while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       // }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        backGround = new BackGround(BitmapFactory.decodeResource(getResources(), R.drawable.background),WIDTH,HEIGHT,0,0);
        backGroundAcid = new BackGround(BitmapFactory.decodeResource(getResources(), R.drawable.background_acid),WIDTH,50,0,801);
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.monster1));
        bitmapPlateform = BitmapFactory.decodeResource(getResources(), R.drawable.plateform);
        bitmapPlateform = Bitmap.createScaledBitmap(bitmapPlateform, 100, 20,false);
        bitmapPlateformRed = BitmapFactory.decodeResource(getResources(), R.drawable.plateform_red);
        bitmapPlateformRed = Bitmap.createScaledBitmap(bitmapPlateformRed, 100, 20,false);
        plateformArrayList = new ArrayList<Plateform>();
        plateformsGeneration(HEIGHT-150,0, deltaYPlatform, plateformArrayList);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        mainThread.setRunning(true);
        mainThread.start();
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
        if (score >= 8000){
            deltaYPlatform = 100;
        }
        for(int i = 0; i < plateformArrayList.size(); i++)
        {
            Plateform p = plateformArrayList.get(i);
            if(player.collision(p))
            {
                if(p.getBreakable() == 0){
                    plateformArrayList.remove(i);
                }
                player.jump();
            }
        }

        int delta=-player.update();

        isShifting = delta > 0 ? true : false;

        synchronized (plateformArrayList)
        {
            List<Plateform> plateformArrayListCopy = new ArrayList<Plateform>();
            for(Iterator<Plateform> it = plateformArrayList.iterator(); it.hasNext();)
            {
                it.next();
                for(Plateform p2 : plateformArrayList)
                {
                    p2.shift(delta/4);
                }
                if(lastPlateform.getY()>deltaYPlatform+10)
                {
                    addPlatform(plateformArrayListCopy);
                }

            }
            plateformArrayList.addAll(plateformArrayListCopy);
        }
        plateformsDestruction();

        if (player.gameOver()){
            storeScore();
            sensorAccelerationActivity.gameOver();
        }
    }

    private void storeScore(){
        try {
            FileOutputStream fos = getContext().openFileOutput("score.txt", Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            osw.write(""+score+"\n");

            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void stopThread()
    {
        mainThread.setRunning(false);
        //mainThread.interrupt();
    }

    private void plateformsDestruction()
    {
        for(int i = 0; i < plateformArrayList.size(); i++)
        {
            if(plateformArrayList.get(i).outOfScreen())
            {
                plateformArrayList.remove(i);
            }
        }
    }

    private void plateformsGeneration(int start, int limit, int decrement, List<Plateform> plateformArrayList)
    {
            for (int i = start; i > limit ; i-= decrement)
            {
                Plateform plateform = new Plateform((WIDTH-50) - ((int) (Math.random() * ((500) + 1))) , i,bitmapPlateform,bitmapPlateformRed);
                plateformArrayList.add(plateform);
                lastPlateform=plateform;
            }
    }

    private  void addPlatform(List<Plateform> plateformArrayList)
    {
        Plateform plateform = new Plateform((WIDTH-50) - ((int) (Math.random() * ((500) + 1))) , 10,bitmapPlateform,bitmapPlateformRed);
        plateformArrayList.add(plateform);
        lastPlateform=plateform;
    }
    private void drawPlateforms(Canvas canvas)
    {
        for(Plateform p : plateformArrayList)
        {
            p.draw(canvas);
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas != null){
            System.out.println("draw");
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            backGround.draw(canvas);

            player.draw(canvas);
            drawPlateforms(canvas);
            if (player.getSpeed()>0 && isShifting)
                score += player.getSpeed();
            backGroundAcid.draw(canvas);
            canvas.drawText(score+" pts",10,20, paint);
            canvas.restoreToCount(savedState);
        }
    }


}
