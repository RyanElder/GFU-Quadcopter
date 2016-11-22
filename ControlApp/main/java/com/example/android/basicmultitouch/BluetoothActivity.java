package com.example.android.basicmultitouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import java.util.Set;
import java.util.ArrayList;



public class BluetoothActivity extends Activity {

    TouchDisplayView mView;
    BluetoothAdapter btAdapter;
    Set<BluetoothDevice> pairedDevices;
    ListView lv;

    Button b1, b2, b3, b4, b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        b1 = (Button) findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);

        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void on(View v){
        if (btAdapter == null)
        {
            Toast.makeText(getApplicationContext(), "This device doesn't support Bluetooth", Toast.LENGTH_LONG).show();
        }
        else
        {
            if (!btAdapter.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void off(View v){
        btAdapter.disable();
        Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
    }


    public  void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }


    public void list(View v){
        pairedDevices = btAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());

        if ((list != null))
        {
            //Toast.makeText(getApplicationContext(), "Showing Paired Devices", Toast.LENGTH_SHORT).show();

            Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_SHORT).show();

            //lv = (ListView)findViewById(R.id.listView);

            //final ArrayAdapter adapter = new  ArrayAdapter(this, android.R.layout.simple_list_item_1, list);

            //if (adapter != null)
            //{
            //    lv.setAdapter(adapter);
            //}
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Devices", Toast.LENGTH_LONG).show();
        }
    }

    public void returnToMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
