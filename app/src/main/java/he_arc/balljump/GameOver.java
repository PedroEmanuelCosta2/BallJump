package he_arc.balljump;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by pedrocosta on 08.12.17.
 */

public class GameOver extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        Button replay = (Button) findViewById(R.id.replay);
        Button goHome = (Button) findViewById(R.id.goHome);

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(GameOver.this, SensorAccelerationActivity.class));
            }
        });

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                startActivity(new Intent(GameOver.this, MenuActivity.class));

            }
        });
    }


}
