package com.example.mohitkumar.offilnepayment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    IntentFilter intentFilter;
    WifiP2pManager p2pManager;
    WifiP2pManager.Channel channel;
    BroadcastReceiver receiver;
    ListView listView;
    Button button;
    TextView textView;
    ArrayAdapter<String> wifiarrayadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        button = (Button) findViewById(R.id.search_butt);
        p2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = p2pManager.initialize(this, getMainLooper(), null);
        receiver = new WifiDirectManager(p2pManager,channel,MainActivity.this);


        listView = (ListView) findViewById(R.id.list_view);
        wifiarrayadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(wifiarrayadapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //search(v);
                p2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(),"Searching for the nearby devices...",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int reason) {
                        Toast.makeText(getApplicationContext(),"Error code : " + reason,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


//        p2pManager.requestPeers(channel, new WifiP2pManager.PeerListListener() {
//            @Override
//            public void onPeersAvailable(WifiP2pDeviceList peers) {
//                ArrayList<WifiP2pDeviceList> arrayList = new ArrayList<WifiP2pDeviceList>();
//                ArrayAdapter<WifiP2pDeviceList> arrayAdapter = new ArrayAdapter<WifiP2pDeviceList>(MainActivity.this,
//                        android.R.layout.simple_list_item_1,arrayList);
//                listView.setAdapter(arrayAdapter);
//            }
//        });
        
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                WifiP2pDevice device = (WifiP2pDevice) parent.getItemAtPosition(position);
//                WifiP2pConfig config = new WifiP2pConfig();
//                config.deviceAddress = device.deviceAddress;
//                p2pManager.connect(channel, config, new WifiP2pManager.ActionListener() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onFailure(int reason) {
//
//                    }
//                });
//            }
//        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void displaypeers(WifiP2pDeviceList peerlist) {

        wifiarrayadapter.clear();
        for(WifiP2pDevice peer : peerlist.getDeviceList()) {
            wifiarrayadapter.add(peer.deviceName + "\n" + peer.deviceAddress);
        }
    }
}
