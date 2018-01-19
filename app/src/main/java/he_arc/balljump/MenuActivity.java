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


        Button button = findViewById(R.id.buttonPlay);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
                System.out.println("play clic");
                Intent i = new Intent(MenuActivity.this, SensorAccelerationActivity.class);
                startActivity(i);

            }
        });
    }
}
