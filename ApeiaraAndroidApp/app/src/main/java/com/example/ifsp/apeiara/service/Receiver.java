package com.example.ifsp.apeiara.service;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Henrique on 3/22/2016.
 */
public class Receiver {

    private static final String TAG = "RECEIVER";
    public static boolean forceStop;
    public static ServerSocket servsock;

    public static String[] Execute() throws IOException {

        String[] result=new String[2];
        DataInputStream dis = null;
        servsock = null;
        Socket sock = null;

        forceStop=false;


        try {
            servsock = new ServerSocket(Config.port);
            while (true) {

                if (forceStop) {
                    Log.d(TAG, "Service Stopped.");
                    break;

                } else {

                    Log.d(TAG, ": Waiting....");
                    try {

                        // Opens channels of communication
                        sock = servsock.accept();
                        Log.d(TAG, "Connected to " + sock.getRemoteSocketAddress().toString().substring(1, sock.getRemoteSocketAddress().toString().indexOf(":")));

                        // Receives an input of data
                        dis = new DataInputStream(sock.getInputStream());

                        // Gets the length and data
                        String type = dis.readUTF();
                        int dataLength = dis.readInt();
                        byte[] data = new byte[dataLength];

                        dis.readFully(data);

                        Log.d(TAG, ": XML downloaded.");

                        result[0] = type;
                        result[1] = new String(data, "UTF-8");

                        return result;

                    } catch (Exception e) {
                       Log.d(TAG,e.getMessage());

                    } finally {
                        if (dis != null)
                            dis.close();
                    }
                }
            }
        }finally {
            if (servsock != null)
                servsock.close();

            if(forceStop)
            {
                Log.d(TAG,"Finally stopping it.");
                sock.close();
            }
        }
    return null;
    }
}
