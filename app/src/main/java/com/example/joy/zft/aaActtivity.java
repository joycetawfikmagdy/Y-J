package com.example.joy.zft;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class aaActtivity extends AppCompatActivity implements AsyncResponse {
    public BluetoothAdapter myBluetoothi;
    BluetoothSocket btSocket;
    private ProgressDialog pDialog;
    Exception x;
    TextView lumn;
    Button btnFwd, btnBwd, btnDis, btnLeft, btnRight, btnStop, btnFwdR, btnFwdL, btnBwdR, btnBwdL;
    SeekBar brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aa_acttivity);

        Bundle AA = getIntent().getExtras();

        String address = AA.getString("Address");
        ConnectBT connectbt = new ConnectBT(this, aaActtivity.this);
        connectbt.delegate = this;
        connectbt.execute(address);

        btnFwd = (Button) findViewById(R.id.forward);
        btnFwdR = (Button) findViewById(R.id.fwd_r);
        btnFwdL = (Button) findViewById(R.id.fwd_l);
        btnLeft = (Button) findViewById(R.id.left);
        btnRight = (Button) findViewById(R.id.right);
        btnBwd = (Button) findViewById(R.id.backward);
        btnBwdR = (Button) findViewById(R.id.bwd_l);
        btnBwdL = (Button) findViewById(R.id.bwd_r);
        btnDis = (Button) findViewById(R.id.disconnect);
        brightness = (SeekBar) findViewById(R.id.BrightnessBar);
        lumn = (TextView) findViewById(R.id.lumn);


        btnFwd.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
                    Forward();
                else if (e.getAction() == MotionEvent.ACTION_UP)
                    Neutral();
                return true;
            }
        });

        btnBwd.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
                    Backward();
                else if (e.getAction() == MotionEvent.ACTION_UP)
                    Neutral();
                return true;
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v)
            {
                Disconnect();
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
                    Left();
                else if (e.getAction() == MotionEvent.ACTION_UP)
                    Neutral();
                return true;
            }
        });

        btnRight.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
                    Right();
                else if (e.getAction() == MotionEvent.ACTION_UP)
                    Neutral();
                return true;
            }
        });


        btnBwdR.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
                    BackwardRight();
                else if (e.getAction() == MotionEvent.ACTION_UP)
                    Neutral();
                return true;
            }
        });

        btnBwdL.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
                    BackwardLeft();
                else if (e.getAction() == MotionEvent.ACTION_UP)
                    Neutral();
                return true;
            }
        });

        btnFwdR.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
                    ForwardRight();
                else if (e.getAction() == MotionEvent.ACTION_UP)
                    Neutral();
                return true;
            }
        });

        btnFwdL.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch (View v, MotionEvent e)
            {
                if (e.getAction() == MotionEvent.ACTION_DOWN)
                    ForwardLeft();
                else if (e.getAction() == MotionEvent.ACTION_UP)
                    Neutral();
                return true;
            }
        });

        brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

        {
            @Override
            public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
                if (fromUser) {
                    lumn.setText(String.valueOf(progress));
                    try {
                        Byte val = (byte) ((Byte.parseByte(String.valueOf(progress)) * 255) / 100);
                        btSocket.getOutputStream().write(("*"+val.toString()).getBytes());
                    } catch (IOException e) {
                        msg(e.getMessage());
                    }
                }
            }

            @Override
            public void onStartTrackingTouch (SeekBar seekBar){

            }

            @Override
            public void onStopTrackingTouch (SeekBar seekBar){

            }
        });
    }





    public void processFinish(BluetoothSocket output) {
        btSocket = output;

        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
    }

    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
                finish(); //return to the first layout
            }
            catch (IOException e)
            { msg(e.getMessage());}
        }
    }

    private void Forward()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("F".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }

    private void ForwardRight()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("FR".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }

    private void ForwardLeft()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("FL".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }

    private void Backward()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("B".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }


    private void BackwardLeft()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("BL".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }


    private void BackwardRight()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("BR".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }



    private void Left()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("L".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }


    private void Right()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("R".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }

    private void Neutral()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("N".getBytes());
            }
            catch (IOException e)
            {
                msg(e.getMessage());
            }
        }
    }

    private void msg(String s)
    {
        Toast temp = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG);
        temp.show();
    }



}
