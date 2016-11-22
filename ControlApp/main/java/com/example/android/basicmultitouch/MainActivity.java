/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.basicmultitouch;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;
import java.util.Set;

import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * This is an example of keeping track of individual touches across multiple
 * {@link MotionEvent}s.
 * <p>
 * This is illustrated by a View ({@link TouchDisplayView}) that responds to
 * touch events and draws coloured circles for each pointer, stores the last
 * positions of this pointer and draws them. This example shows the relationship
 * between MotionEvent indices, pointer identifiers and actions.
 *
 * @see MotionEvent
 */
public class MainActivity extends Activity
{
    TouchDisplayView mView;
    BluetoothAdapter btAdapter;
    Set<BluetoothDevice>pairedDevices;
    ListView lv;

    Button b1, b2, b3, b4;

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ImageButton forwardArrow = (ImageButton) findViewById(R.id.forward_arrow);
        ImageButton backArrow = (ImageButton) findViewById(R.id.back_arrow);
        ImageButton leftArrow = (ImageButton) findViewById(R.id.left_arrow);
        ImageButton rightArrow = (ImageButton) findViewById(R.id.right_arrow);
        ImageButton upArrow = (ImageButton) findViewById(R.id.up_arrow);
        ImageButton downArrow = (ImageButton) findViewById(R.id.down_arrow);
        //Button bluetooth = (Button) findViewById(R.id.bluetooth);

        //lv = (ListView)findViewById(R.id.listView);
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        setContentView(R.layout.layout_mainactivity);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void click(final View v) {
        int direction = 0;
        if (direction == UP) {

        } else if (direction == DOWN) {

        } else if (direction == LEFT) {

        } else if (direction == RIGHT) {

        } else {

        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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

            //lv.setAdapter(adapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Devices", Toast.LENGTH_LONG).show();
        }
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, BluetoothActivity.class);
        startActivity(intent);
    }
}
