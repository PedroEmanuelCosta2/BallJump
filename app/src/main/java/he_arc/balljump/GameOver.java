package he_arc.balljump;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by pedrocosta on 08.12.17.
 */

public class GameOver extends AppCompatActivity{

    private Button replay;
    private Button goHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        replay = (Button) findViewById(R.id.replay);
        goHome = (Button) findViewById(R.id.goHome);

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOver.this, Game.class));
            }
        });

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOver.this, MenuActivity.class));
            }
        });
    }


}
