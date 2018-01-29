package he_arc.balljump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Button buttonClient = findViewById(R.id.buttonSend);
        buttonClient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ShareActivity.this, ClientActivity.class);
                startActivity(i);
            }
        });

        Button buttonServer = findViewById(R.id.buttonGet);
        buttonServer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ShareActivity.this, ServerActivity.class);
                startActivity(i);
            }
        });
    }


}
