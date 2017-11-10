package he_arc.balljump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import static android.support.v4.content.ContextCompat.startActivity;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = new Intent(Game.this, SensorAccelerationActivity.class);

        startActivity(i);
    }
}
