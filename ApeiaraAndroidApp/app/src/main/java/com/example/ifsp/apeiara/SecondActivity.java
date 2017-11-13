package com.example.ifsp.apeiara;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ifsp.apeiara.controll.DoRequest;
import com.example.ifsp.apeiara.controll.DoXML;
import com.example.ifsp.apeiara.model.Request;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henrique on 3/19/2016.
 */
public class SecondActivity extends FragmentActivity implements OnMapReadyCallback {

    private static String TAG="MAP";
    private GoogleMap mMap;
    private LocationManager lm;
    private Request req;
    private int reqN;

    private TextView txt_cuidando;
    private TextView txt_date;
    private TextView txt_type;
    private TextView txt_phone;

    private Button btnAtende;
    private Button btnPassa;
    private Button btnCall;

    private  LatLng posCuidador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);

        //Retrieves request information passed by intent extra
        Intent i = getIntent();
        reqN=i.getIntExtra("REQ", 0);
        req = DoRequest.reqs.get(reqN);

        setContentView(R.layout.cuidador_show_request);

        txt_cuidando = (TextView) findViewById(R.id.txt_req_cuidando);
        txt_date = (TextView) findViewById(R.id.txt_req_data);
        txt_type = (TextView) findViewById(R.id.txt_req_tipo);
        txt_phone = (TextView) findViewById(R.id.txt_req_phone);

        btnAtende = (Button) findViewById(R.id.btnAtende);
        btnPassa = (Button) findViewById(R.id.btnPassa);
        btnCall = (Button) findViewById(R.id.btnCall);

        txt_cuidando.setText(req.getCUIDANDO().getNAME());
        txt_date.setText(req.getDATE());
        txt_type.setText(req.getTYPE());
        txt_phone.setText(req.getCUIDANDO().getPHONE());




        lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_frag);
        mapFragment.getMapAsync(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(SecondActivity.this, ResultActivity.class);
        startActivity(i);
        finish();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng posCuidando = new LatLng(Double.parseDouble(req.getLATITUDE()), Double.parseDouble(req.getLONGITUDE()));
        posCuidador = new LatLng(lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude(), lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude());
        //LatLng posCuidando = new LatLng(-23.563108, -46.6566188);

        List<Marker> markersList = new ArrayList<Marker>();

        markersList.add(mMap.addMarker(new MarkerOptions().position(posCuidando).title("Cuidando").visible(true).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.marker)))));
        markersList.add(mMap.addMarker(new MarkerOptions().position(posCuidador).title("Você").visible(true)));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker m : markersList) {
            builder.include(m.getPosition());
        }

        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12);

        mMap.addPolyline( new PolylineOptions().add(posCuidando).add(posCuidador));
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,  width, height, padding));

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posCuidando, 14));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posCuidador,14));

    }

    public void AnswerReq(View v) {

        new AlertDialog.Builder(this)
                .setTitle("Atender Pedido")
                .setMessage("Você atenderá " + req.getCUIDANDO().getNAME() + " com o pedido " + req.getTYPE() + "?")

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        req.setSTATUS("DONE");
                        req.setCUIDADOR(LoginActivity.currentUser);
                        new DoRequest().answer(req);

                        DoRequest.reqs.remove(req);
                        ResultActivity.values.remove(reqN);

                        saveDateIn();

                        Toast.makeText(SecondActivity.this, "Answering request!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SecondActivity.this, ResultActivity.class));
                        finish();


                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void RefuseReq(View v) {
        final EditText input = new EditText(this);
        input.setHint("Justificativa");
        new AlertDialog.Builder(this)
                .setTitle("Repassar Pedido")
                .setMessage("Você NÃO atenderá " + req.getCUIDANDO().getNAME() + " com o pedido " + req.getTYPE() + "?")
                .setView(input)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        req.setLATITUDE(String.valueOf(posCuidador.latitude));
                        req.setLONGITUDE(String.valueOf(posCuidador.longitude));
                        req.setSTATUS(input.getText().toString());
                        req.setCUIDADOR(LoginActivity.currentUser);

                        new DoRequest().refuse(req);
                        DoRequest.reqs.remove(req);
                        ResultActivity.values.remove(reqN);

                        saveDateIn();

                        Toast.makeText(SecondActivity.this, "Repassing request to other Cuidador",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SecondActivity.this, ResultActivity.class));
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void doCall(View v){
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" +req.getCUIDANDO().getPHONE()));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);
    }

    public void saveDateIn() {

        this.getSharedPreferences("REQS", 0).edit().clear().commit();

        if (DoRequest.reqs.size() > 0) {
            Log.d(TAG, "Saving data in cache");

            SharedPreferences.Editor editor = getSharedPreferences("REQS", MODE_PRIVATE).edit();
            editor.putInt("REQ", DoRequest.reqs.size());

            int n = 0;
            for (Request req : DoRequest.reqs) {
                editor.putString("REQ" + n, DoXML.writeRequest(req));
                n++;
            }
            editor.apply();

            // updateListView();
        }
    }
}