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

        Button replay = findViewById(R.id.buttonReplay);
        Button goHome = findViewById(R.id.buttonHome);

        replay.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(GameOver.this, Game.class));
            }
        });

        goHome.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(new Intent(GameOver.this, MenuActivity.class));
            }
        });
    }


}
