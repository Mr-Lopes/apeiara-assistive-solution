package com.example.ifsp.apeiara;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ifsp.apeiara.controll.DoRequest;
import com.example.ifsp.apeiara.controll.DoXML;
import com.example.ifsp.apeiara.model.Request;
import com.example.ifsp.apeiara.service.Receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Henrique on 3/27/2016.
 */
public class ResultActivity extends Activity {


    private static String TAG = "MONITOR";
    private TextView showTxtSize;
    //  private ExpandableListView ListRequest;
    private ListView ListRequest;
    private Future threadHandler;
    private boolean threadFlag=false;
    private static  ArrayAdapter<String> myAdapter;
    public static List<String> values = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sets visual stuff
        setContentView(R.layout.cuidador_initial);
        showTxtSize = (TextView) findViewById(R.id.txtViewSize);
        ListRequest = (ListView) findViewById(R.id.list_request);

        Log.d(TAG, "onCreate");

        setListView();
        getDateIn();
        updateTxtSize();
        doBackground();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveDateIn();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
         Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveDateIn();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  updateListView();
        Log.d(TAG, "onResume");

        updateView();
        updateTxtSize();
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }

    public void doBackground() {

        threadFlag=true;
        Log.d(TAG, "Starting background service");

        threadHandler= Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                while (threadFlag) {
                   new DoRequest().receive(ResultActivity.this);
                }
            }
        });
   }

    public void saveDateIn(){

        this.getSharedPreferences("REQS", 0).edit().clear().commit();

        if (DoRequest.reqs.size() > 0) {
            Log.d(TAG, "Saving data in cache");
            SharedPreferences.Editor editor = getSharedPreferences("REQS", MODE_PRIVATE).edit();
            editor.putInt("REQ", DoRequest.reqs.size());

            for (int n=0; n<DoRequest.reqs.size();n++) {
                editor.putString("REQ" + n, DoXML.writeRequest(DoRequest.reqs.get(n)));
            }
            editor.apply();

           // updateListView();
        }
    }

    public void getDateIn() {

        if (DoRequest.reqs.size() == 0) {

            Log.d(TAG, "Getting data saved in cache");
            //Gets the requests saved in the app
            SharedPreferences prefs = getSharedPreferences("REQS", MODE_PRIVATE);
            final int count = prefs.getInt("REQ", 0);
            Request req;

            for (int i = 0; i < count; i++) {
                req = DoXML.readRequest(prefs.getString("REQ" + i, null));

                if (req != null) {
                    DoRequest.reqs.add(req);
                    values.add(" - cuidando: " + req.getCUIDANDO().getNAME()+
                            "\n - id             : " + String.valueOf(req.getID()) +
                            "\n - date        : " + req.getDATE() +
                            "\n - type         : " + req.getTYPE() +
                            "\n - status     : " + req.getSTATUS());


                }
            }

            myAdapter.notifyDataSetChanged();

            //saveDateIn();
            //updates the list view
            // updateListView();
        }
    }

    public void doLogout(View v) {

        if(!threadHandler.isDone())
        {
            try {
                Log.d(TAG, "Stopping background services.");

                threadFlag=false;
                Receiver.forceStop = true;
                Receiver.servsock.close();
                threadHandler.cancel(true);

            } catch (Exception ex){
                Log.d(TAG, "Erro trying to terminate background services.");
            }
        }

        new LoginActivity().doLogout(ResultActivity.this);
        startActivity(new Intent(ResultActivity.this, LoginActivity.class));
        this.finish();
    }

    public void setListView(){

        myAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        myAdapter.setNotifyOnChange(true);
        ListRequest.setAdapter(myAdapter);

        this.runOnUiThread(new Runnable() {
            public void run() {
                myAdapter.notifyDataSetChanged();
            }
        });


        ListRequest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(ResultActivity.this, SecondActivity.class);
                i.putExtra("REQ", position);
                startActivity(i);

                if(!threadHandler.isDone())
                {
                    try {
                        Log.d(TAG, "Stopping background services.");

                        threadFlag=false;
                        Receiver.forceStop = true;
                        Receiver.servsock.close();
                        threadHandler.cancel(true);

                    } catch (Exception ex){
                        Log.d(TAG, "Erro trying to terminate background services.");
                    }
                }
               finish();
            }
        });
    }

    public static void updateView(){
        myAdapter.notifyDataSetChanged();


    }

    public void updateTxtSize(){

    if(DoRequest.reqs.size()<1) {
        showTxtSize.setText("Parabéns, você não tem nenhum pedido de ajuda de seus Cuidandos");
    } else if(DoRequest.reqs.size()==1) {
        showTxtSize.setText("Você  tem 1  pedido de ajuda de seus Cuidandos");
    } else {
        showTxtSize.setText("Você tem "+DoRequest.reqs.size() + " pedidos de ajuda de seus Cuidandos");
    }
}

}



