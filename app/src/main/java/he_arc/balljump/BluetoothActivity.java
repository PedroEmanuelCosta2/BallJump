package he_arc.balljump;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import 	android.bluetooth.BluetoothAdapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import 	android.content.Intent;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private final static int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_DISCOVERABLE_BT = 0;
    private final ArrayList<String> array=new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            System.out.println("onReceive perso");
            String action = intent.getAction();
            // Quand la recherche trouve un terminal
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // On récupère l'object BluetoothDevice depuis l'Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // On ajoute le nom et l'adresse du périphérique dans un ArrayAdapter (par exemple pour l'afficher dans une ListView)
                adapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (blueAdapter == null)
        {
            TextView text = findViewById(R.id.textTest);
            text.setText("Bluetooth pas trouvé");
        }
        else
        {
            TextView text = findViewById(R.id.textTest);
            text.setText("Bluetooth  trouvé");
        }

        if (!blueAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        // Create the adapter to convert the array to views
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, array);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listDevices);
        listView.setAdapter(adapter);
        Set<BluetoothDevice> devices = blueAdapter.getBondedDevices();
        for (BluetoothDevice blueDevice : devices) {
            array.add(blueDevice.getName());
        }
        array.add("test");

        // On crée un BroadcastReceiver pour ACTION_FOUND
        // Inscrire le BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter); // N'oubliez pas de le désinscrire lors du OnDestroy() !


        blueAdapter.startDiscovery();


        System.out.println("test");
        /*Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        boolean timeVisible=false;
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 1000); // Cette ligne permet de définir une durée de visibilité de notre choix
        startActivity(discoverableIntent);*/

    }
}
