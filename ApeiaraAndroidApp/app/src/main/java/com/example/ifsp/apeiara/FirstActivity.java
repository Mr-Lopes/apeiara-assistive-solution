package com.example.ifsp.apeiara;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ifsp.apeiara.controll.DoRequest;
import com.example.ifsp.apeiara.model.Request;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class FirstActivity extends Activity implements LocationListener {

    private static final int REQUEST_CODE = 1;
    private static final int MAX_RESULT = 1;
    private static final boolean EXTRA_PARTIAL_RESULTS = false;
    public static final String ACTION_VOICE_SEARCH_HANDS_FREE = "android.speech.action.VOICE_SEARCH_HANDS_FREE";
    private TextToSpeech tts;

    private LocationManager lm;
    private String longitude;
    private String latitude;

    private static String RetWS;
    private EditText mEdtResult;

    private static final String TAG = "APEIARA";
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
    private String[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuidando_initial);

        ImageButton btn = (ImageButton) findViewById(R.id.btnMicro);
        TextToSpeech.OnInitListener textlistener= new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Setting speech language
                   tts.setLanguage(new Locale("pt", "pt_BR"));
            }
        }};


            tts = new TextToSpeech(this,textlistener);

        // Verifica se o dispositivo suporta
        // reconhecimento de voz
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH),
                0);

        // Não suporta?
        if (activities.size() == 0) {
            btn.setEnabled(false);
            ExibirMsg("Não suporta reconhecimento de voz");
        }

        Log.d(TAG, "APEIARA:onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuidando_initial);

        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        onLocationChanged(lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));

        speakOut("Aguardando Pedido");
    }

    @Override
    protected void onResume() {
        // turn all on
        Log.d(TAG, "APEIARA:onResume");
        lm.requestLocationUpdates(lm.GPS_PROVIDER, 0, 0f, this);
        super.onResume();

        speakOut("Aguardando Pedido");

    }

    @Override
    protected void onPause() {
        Log.d(TAG, "APEIARA::onPause");
        lm.removeUpdates(this);
        super.onPause();
    }

    @Override
    public void finish() {
        Log.d(TAG, "APEIARA::finish");
        super.finish();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "APEIARA::onStop");
        super.onStop();

        speakOut("Fechando aplicativo");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "APEIARA::onLocationChanged");

       // String altitude = String.valueOf(location.getAltitude());
       // float accurancy = location.getAccuracy();
       // long time = location.getTime();

        this.longitude=(String.valueOf(location.getLongitude()));
        this.latitude=(String.valueOf(location.getLatitude()));

      //  String L1 = getLatitude();
      //  String L2 = getLongitude();

       // getLogradouro(L1, L2, "AJUDA");

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "Provider disabled");
        Toast.makeText(this, "APEIARA - GPS DESABILITADO", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "Provider enabled");
        Toast.makeText(this, "APEIARA - GPS HABILITADO", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "MUDANCA DE STATUS");
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                Log.v(TAG, "Status Alterado: Fora de Serviço");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.v(TAG, "Status Alterado: Temporariamente Indisponivel");

                break;
            case LocationProvider.AVAILABLE:
                Log.v(TAG, "Status Alterado: OK");
                break;
            default:
                Log.v(TAG, "Status Alterado: Erro");
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    // Evento de click do botão
    public void btnMicroClick(View v) {

        speakOut("Faça");

        // Cria a Intent
        Intent intent =
                new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        // Passa os parâmentros via Extra
        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // Opcional: Idioma
        intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                "pt-BR");

        // Opcional: Número máximo de resultado
        intent.putExtra(
                RecognizerIntent.EXTRA_MAX_RESULTS,
                MAX_RESULT);

        // Texto que será exibido no recognize speech
        intent.putExtra(
                RecognizerIntent.EXTRA_PROMPT,
                "Aguardando Palavra Chave");



        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        // Verifica se o retorno teve sucesso
        if (resultCode == RESULT_OK) {

            if (requestCode == REQUEST_CODE) {

                // Recupera uma lista com os resultados
                // encontrados para o que foi "falado"
                ExibirResultado(
                        data.getStringArrayListExtra(
                                RecognizerIntent.EXTRA_RESULTS));


                // Tratamento de erro
            } else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
                ExibirMsg("Erro No Audio");
            } else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR) {
                ExibirMsg("Erro");
            } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
                ExibirMsg("Erro na Conexão");
            } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
                ExibirMsg("Sem Resultados");
            } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
                ExibirMsg("Erro No Servidor");
            }
        }
        super.onActivityResult(requestCode, resultCode,
                data);


    }

    private void ExibirResultado(
            ArrayList<String> resultados) {

        // Exibi a lista em um AlertDialog0
        AlertDialog.Builder builderSingle =
                new AlertDialog.Builder(FirstActivity.this);

        builderSingle.setIcon(R.drawable.micro);
        builderSingle.setTitle("Selecione um Item:");

        // Cria um ArrayAdapter
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(
                        FirstActivity.this,
                        android.R.layout.select_dialog_singlechoice,
                        resultados);

        // Botão Cancelar
        builderSingle.setNegativeButton("Cancelar",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            DialogInterface dialog,
                            int which) {

                        dialog.dismiss();
                    }
                });

        // Seta o Adapter para o dialog e o
        // evento de click
        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            DialogInterface dialog,
                            int which) {


                        if (arrayAdapter.getItem(which).toString().equalsIgnoreCase("ajuda")) {
                            speakOut("OKAY, VOU SOLICITAR AJUDA");
                            ExibirMsg("Solicitando Ajuda");
                            createRequest("AJUDA");
                        }
                        ;

                        if (arrayAdapter.getItem(which).toString().equalsIgnoreCase("socorro")) {
                            speakOut("OKAY, VOU SOLICITAR SOCORRO");
                            ExibirMsg("Solicitando Ajuda Imediata");
                            createRequest("SOCORRO");
                        }
                        ;

                        if (arrayAdapter.getItem(which).toString().equalsIgnoreCase("apoio")) {
                            speakOut("OKAY, VOU SOLICITAR APOIO");
                            ExibirMsg("Solicitando Apoio");
                            createRequest("APOIO");

                        }
                        ;

                        if (arrayAdapter.getItem(which).toString().equalsIgnoreCase("sair")) {
                        /*
                            SharedPreferences.Editor editor = getSharedPreferences("USER", MODE_PRIVATE).edit();
                            editor.clear();
                            editor.apply();
                            ExibirMsg("Deslongando, fucker");
                            startActivity(new Intent(FirstActivity.this, LoginActivity.class));
                            finish();
                            */

                            if(tts != null){

                                tts.stop();
                                tts.shutdown();
                            }
                            new LoginActivity().doLogout(FirstActivity.this);
                            startActivity(new Intent(FirstActivity.this, LoginActivity.class));
                            finish();
                        }
                        ;


                    }
                });

        builderSingle.show();


    }

    private void ExibirMsg(String texto) {
        Toast.makeText(this, texto,
                Toast.LENGTH_SHORT).show();
    }

    private void speakOut(String text){
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private final void createRequest(final String type) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                Request req=new Request(0,sdf.format(Calendar.getInstance().getTime()),type,"NEW", latitude, longitude, LoginActivity.currentUser, LoginActivity.currentUser);
                new DoRequest().create(req);

            };
        }).start();
    }
}