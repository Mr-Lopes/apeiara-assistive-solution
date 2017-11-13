package com.example.ifsp.apeiara.controll;

import android.content.Context;
import android.util.Log;

import com.example.ifsp.apeiara.ResultActivity;
import com.example.ifsp.apeiara.misc.NotifyUser;
import com.example.ifsp.apeiara.model.Request;
import com.example.ifsp.apeiara.service.Receiver;
import com.example.ifsp.apeiara.service.Transmitter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Henrique on 3/22/2016.
 */
public class DoRequest {

    private static final String TAG="DoRequest";
    public static ArrayList<Request> reqs= new ArrayList<Request>();


    public void create(final Request req){
        try {
            //Starts to transmit
            new Transmitter().Execute("request", DoXML.writeRequest(req).getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void answer(final Request req){
        try {
            //Starts to transmit
            new Transmitter().Execute("done", DoXML.writeRequest(req).getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refuse(final Request req){
        try {
            //Starts to transmit
            new Transmitter().Execute("repass", DoXML.writeRequest(req).getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void receive(Context c){
        Request req;
        String[] result;

        //Starts to receive
        try {

            result=Receiver.Execute();

            if(result[0].equalsIgnoreCase("REQUEST")){
                req=DoXML.readRequest(result[1]);
                reqs.add(req);

                ResultActivity.values.add(" - cuidando: " + req.getCUIDANDO().getNAME()+
                        "\n - id             : " + String.valueOf(req.getID()) +
                        "\n - date        : " + req.getDATE() +
                        "\n - type         : " + req.getTYPE() +
                        "\n - status     : " + req.getSTATUS());

                //ResultActivity.myAdapter.notifyDataSetChanged();
                //ResultActivity.myAdapter.notify();

                NotifyUser.showNotification(c);

                Log.d(TAG, "New Request Added. (Size:" + reqs.size() + ")");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
