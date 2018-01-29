package he_arc.balljump;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MenuActivity.this, SensorAccelerationActivity.class);
                startActivity(i);

            }
        });


        Button buttonShare = findViewById(R.id.buttonShare);
        buttonShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, BluetoothActivity.class);
                startActivity(i);
            }
        });

        Button buttonStatistics = findViewById(R.id.buttonStatistics);
        buttonStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, StatisticsAtivity.class);
                startActivity(i);
            }
        });

        Button buttonCredit = findViewById(R.id.buttonCredit);
        buttonCredit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MenuActivity.this, CreditActivity.class);
                startActivity(i);

            }
        });
    }
}
