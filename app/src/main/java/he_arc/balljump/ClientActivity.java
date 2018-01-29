package he_arc.balljump;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientActivity extends AppCompatActivity {

    private Socket socket;

    private static final int SERVERPORT = 8080;
    private String SERVER_IP = "192.168.1.109";
    private String state="Failed";
    private TextView textConnected;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        textConnected=(TextView) findViewById(R.id.textViewConnect);

        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new SendThread()).start();
            }
        });

        Button buttonConnect = findViewById(R.id.buttonConnect);
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.EditText01);
                str = et.getText().toString();
                new Thread(new ClientThread()).start();
            }
        });
    }

    public void updateText()
    {
        textConnected.clearComposingText();
        textConnected.setText(state);
    }

    class ClientThread implements Runnable {

        @Override
        public void run() {

            try {

                InetAddress serverAddr = InetAddress.getByName(str);

                socket = new Socket(serverAddr, SERVERPORT);

                state="Connected";


            } catch (UnknownHostException e1) {
                state="Failed";
                e1.printStackTrace();
            } catch (IOException e1) {
                state="Failed";
                e1.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateText();
                }
            });
            //updateText();
        }


    }

    class SendThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("click");
                EditText et = (EditText) findViewById(R.id.EditText01);
                String str = et.getText().toString();
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())),
                        true);

                try {
                    FileInputStream fis = openFileInput("score.txt");
                    InputStreamReader ois = new InputStreamReader(fis);
                    BufferedReader bf = new BufferedReader(ois);

                    String receiveString = "";

                    while ( (receiveString = bf.readLine()) != null ) {
                        out.println(receiveString);
                    }

                    bf.close();
                    ois.close();
                    fis.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }



            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
