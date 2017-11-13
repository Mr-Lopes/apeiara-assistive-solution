package com.example.ifsp.apeiara.service;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Henrique on 3/21/2016.
 */
public class Transmitter {


    private static final String tag = "Transmitter";


    public void Execute(final String type, final byte[] data) {
        //
        //Choose a port which is greater than 1,023! Ports lower than that
        //value
        //are reserved for roobt or super user.
        //
        try {

            Socket mySocket = new Socket(Config.hostname , Config.port);
            Log.d(tag, "Connected to " + Config.hostname);

            DataOutputStream dos = new DataOutputStream(mySocket.getOutputStream());

            dos.writeUTF(type);
            dos.writeInt(data.length);
            dos.write(data);
            dos.flush();

            dos.close();
            mySocket.close();

            Log.d(tag, "XML sent to the host.");

        } catch (UnknownHostException e) {
            Log.e(tag, e.getMessage());
        } catch (IOException e) {
            Log.e(tag, e.getMessage());
        }
    }

}

