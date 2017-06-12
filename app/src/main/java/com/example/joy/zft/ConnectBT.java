package com.example.joy.zft;

import android.Manifest;
import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import java.io.IOException;
import java.util.UUID;

import static android.R.attr.x;


/**
 * Created by Joy on 6/6/2017.
 */

        public class ConnectBT extends AsyncTask<String, Void, BluetoothSocket>  // UI thread
{
    private boolean ConnectSuccess = true; //if it's here, it's almost connected
    private BluetoothAdapter myBluetoothi = null;
  String ee;
    String address = "";

    BluetoothAdapter myBluetooth = null;
    public BluetoothSocket btSocket = null;
    public AsyncResponse delegate = null;
    private boolean isBtConnected = false;

    static private UUID myUUID=UUID
            .fromString("00001101-0000-1000-8000-00805F9B34FB");

    private Context context;
    private Activity a;
    private ProgressDialog pDialog;
    ProgressDialog x;


    public ConnectBT(Context context,Activity a) {

        this.context = context;
        this.a=a;
        myBluetoothi = BluetoothAdapter.getDefaultAdapter();
        if(myBluetoothi == null)
        {
            //Show a mensag. that thedevice has no bluetooth adapter
            Toast.makeText(context, "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
            //finish apk
            a.finish();
        }
        else
        {
            if (myBluetoothi.isEnabled())
            { }
            else
            {
                //Ask to the user turn the bluetooth on
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                a.startActivityForResult(turnBTon,1);
            }
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        x = new ProgressDialog(context);  //<<-- Couldnt Recognise
        x.setMessage("Connecting .....");
        x.setIndeterminate(false);
        x.setCancelable(false);
        x.show();
    }

    @Override

    protected BluetoothSocket doInBackground (String... devices)  //while the progress dialog is shown, the connection is done in background
            {   address=devices[0];


                try
                {if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device

                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    //connects to the device's address and checks if it's available


                    btSocket = dispositivo.createRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    btSocket.connect();
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    //btSocket.connect();//start connection
                }
                }
                catch (IOException e)
                {
                    ConnectSuccess = false;//if the try failed, you can check the exception here

                    ee=e.getMessage();
                }
                return btSocket;
            }


    @Override
            protected void onPostExecute(BluetoothSocket result) //after the doInBackground, it checks if everything went fine
            {
                //super.onPostExecute(result);
                delegate.processFinish(result);
                x.dismiss();
                if (!ConnectSuccess)
                {
                    pDialog = new ProgressDialog(context);  //<<-- Couldnt Recognise
                    pDialog.setMessage(ee);
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pDialog.dismiss();
                            a.finish();
                        }
                    }, 2000);


                }
                else
                {
                    pDialog = new ProgressDialog(context);  //<<-- Couldnt Recognise
                    pDialog.setMessage("Success");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                    isBtConnected = true;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pDialog.dismiss();
                        }
                    }, 2000);



                }

            }

        }
