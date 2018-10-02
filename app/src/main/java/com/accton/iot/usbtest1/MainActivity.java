package com.accton.iot.usbtest1;

import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.bubblecloud.zigbee.network.port.AndroidUsbSerialPort;
//import org.bubblecloud.zigbee.network.port.SerialPortImpl;
import org.bubblecloud.zigbee.v3.SerialPort;
import org.bubblecloud.zigbee.v3.ZigBeeApiDongleImpl;
import org.bubblecloud.zigbee.v3.ZigBeeDongle;
import org.bubblecloud.zigbee.v3.ZigBeeDongleTiCc2531Impl;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final static String TAG = "MainActivity";
    private final static boolean DEBUG = true;

    byte[] networkKey = null; // Default network key
    SerialPort port = null;
    ZigBeeDongle dongle = null;
    ZigBeeApiDongleImpl api = null;
    UsbManager mUsbManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEBUG)
            Log.d(TAG, "Call onCreate.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
    }
t
    @Override
    public void onBackPressed() {
        if (DEBUG)
            Log.d(TAG, "Call onBackPressed.");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            byte[] networkKey = null; // Default network key
            //port = new SerialPortImpl("/dev/ttyACM0");
            port = new AndroidUsbSerialPort(mUsbManager);
            //dongle = new ZigBeeDongleTiCc2531Impl(port, 10644, 15, networkKey, false);
            dongle = new ZigBeeDongleTiCc2531Impl(port, 65535, 15, networkKey, false);
            api = new ZigBeeApiDongleImpl(dongle, false);

            api.startup();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            api.shutdown();
            port.close();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
