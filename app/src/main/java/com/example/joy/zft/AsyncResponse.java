package com.example.joy.zft;

import android.bluetooth.BluetoothSocket;

/**
 * Created by Joy on 6/8/2017.
 */

public interface AsyncResponse {
    void processFinish(BluetoothSocket output);
}
