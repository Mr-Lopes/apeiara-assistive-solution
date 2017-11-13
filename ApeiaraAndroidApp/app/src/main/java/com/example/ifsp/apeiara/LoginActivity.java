package com.example.ifsp.apeiara;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ifsp.apeiara.controll.DoLogin;
import com.example.ifsp.apeiara.controll.DoXML;
import com.example.ifsp.apeiara.model.User;

public class LoginActivity extends Activity {

    private EditText email;
    private EditText password;
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Verifies if the user has already logged it
        SharedPreferences prefs = getSharedPreferences("USER", MODE_PRIVATE);
        String restoredText = prefs.getString("USER", null);

        if (restoredText!=null) {
            currentUser = DoXML.readLogin(restoredText);

            if (currentUser.getCATEGORY().equalsIgnoreCase("CUIDANDO")) {
                startActivity(new Intent(LoginActivity.this, FirstActivity.class));

            } else if (currentUser.getCATEGORY().equalsIgnoreCase("CUIDADOR")) {

                //Registers the user logged
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new DoLogin().auth(currentUser);
                    }
                }).run();

                startActivity(new Intent(LoginActivity.this, ResultActivity.class));
            }
        } else {

            setContentView(R.layout.login);

            email = (EditText) findViewById(R.id.txt_Email);
            password = (EditText) findViewById(R.id.txt_Password);


        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
    }

    public void doLogin(View v) {

        Toast.makeText(this, "Logando-se", Toast.LENGTH_LONG).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                User us = new User();
                us.setEMAIL(email.getText().toString());
                us.setPASS(password.getText().toString());

                currentUser = new DoLogin().create(us);
            }
        }).run();


        if (currentUser != null) {
            Toast.makeText(this, "Logado",Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor = getSharedPreferences("USER", MODE_PRIVATE).edit();
            editor.putString("USER", DoXML.writeUser(currentUser));
            editor.apply();

            if (currentUser.getCATEGORY().equalsIgnoreCase("CUIDANDO")) {
                startActivity(new Intent(LoginActivity.this, FirstActivity.class));
                finish();
            } else if (currentUser.getCATEGORY().equalsIgnoreCase("CUIDADOR")) {
                startActivity(new Intent(LoginActivity.this, ResultActivity.class));
                finish();
            }

        } else {
            Toast.makeText(this, "Email/Password wrong",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void doLogout(Context c){

       new DoLogin().logout(currentUser);

       SharedPreferences.Editor editor = c.getSharedPreferences("USER", MODE_PRIVATE).edit();
       editor.clear();
       editor.apply();
       currentUser=null;
       Toast.makeText(c, "Deslongando-se", Toast.LENGTH_SHORT).show();


    }

    public void doCancel(View v)

    {
        this.finish();
    }
}
