package com.example.ifsp.apeiara.controll;

        import com.example.ifsp.apeiara.model.User;
        import com.example.ifsp.apeiara.service.Receiver;
        import com.example.ifsp.apeiara.service.Transmitter;

        import java.io.IOException;

/**
 * Created by Henrique on 3/25/2016.
 */
public class DoLogin {
    private static final String TAG="DoRequest";


    public User create(final User user){
        try {

            //Starts to transmit
            new Transmitter().Execute("login", DoXML.writeLogin(user).getBytes("UTF-8"));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return receive();
    }

    public User receive(){

        User us = new User();

        //Starts to receive
        try {
            String[] result=Receiver.Execute();

            if(result[0].equalsIgnoreCase("USER"))
                 us = DoXML.readLogin(result[1]);

            else
                return null;

            // Log.d(TAG, result);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return us;
    }

    public void logout(final User user) {
       try {
            //Starts to transmit
            new Transmitter().Execute("logout", DoXML.writeUser(user).getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void auth(final User user){
        try {
            //Starts to transmit
            new Transmitter().Execute("authenticate", DoXML.writeUser(user).getBytes("UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
