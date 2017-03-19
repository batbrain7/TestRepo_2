package com.example.mohitkumar.offilnepayment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import java.nio.channels.Channel;
import java.util.ArrayList;

/**
 * Created by mohitkumar on 16/03/17.
 */

public class WifiDirectManager extends BroadcastReceiver {

    private WifiP2pManager manager;
    private WifiP2pManager.Channel channel;
    private MainActivity activity;
    WifiP2pManager.PeerListListener peerListListener;
    ArrayList<WifiP2pDevice> devices;
    ArrayList<WifiP2pConfig> configs;

    public WifiDirectManager(WifiP2pManager manager, WifiP2pManager.Channel channel,
                                       MainActivity activity) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

        } else if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {

            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if(state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                activity.textView.setText("Wifi Direct : Enabled");
            } else {
                activity.textView.setText("Wifi Direct : Disabled ");
            }


        } else if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            devices = new ArrayList<WifiP2pDevice>();
            configs = new ArrayList<WifiP2pConfig>();

            if(manager!=null) {
                manager.requestPeers(channel, new WifiP2pManager.PeerListListener() {
                    @Override
                    public void onPeersAvailable(WifiP2pDeviceList peers) {
                        devices.clear();

                        devices.addAll(peers.getDeviceList());
                        activity.displaypeers(peers);

                        devices.addAll(peers.getDeviceList());

                        for(int i=0;i<peers.getDeviceList().size();i++) {
                            WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
                            wifiP2pConfig.deviceAddress = devices.get(i).deviceAddress;
                            configs.add(wifiP2pConfig);
                        }

                    }
                });
            }


        } else if(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

        }

    }
}
