package he_arc.balljump;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by thibaut.piquerez on 03.11.2017.
 */

public class SensorAccelerationActivity extends AppCompatActivity  implements SensorEventListener
{
    private SensorManager sensorManager;

    private Sensor accelerometer;
    private Sensor magnetometer;

    private GamePanel gamePanel;

    private float[] mGravity=null;
    private float[] mGeomagnetic=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("oncreate sensor");
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        gamePanel = new GamePanel(this);
        setContentView(gamePanel);

        gamePanel.setZOrderOnTop(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float roll;
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;
        if (mGravity != null && mGeomagnetic != null)
        {
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success)
            {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                roll = orientation[2];
                gamePanel.updatePlayer(roll);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {

    }

    public void gameOver()
    {

        gamePanel.stopThread();
        finish();
        Intent gameOverIntent = new Intent(SensorAccelerationActivity.this, GameOver.class);
        startActivity(gameOverIntent);

    }
}

