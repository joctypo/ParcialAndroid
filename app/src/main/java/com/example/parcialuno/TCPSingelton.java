package com.example.parcialuno;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class TCPSingelton extends Thread {

    private static TCPSingelton unicainstancia;
    private BufferedWriter bwriter;

    public static TCPSingelton  getInstance(){
        if(unicainstancia==null){

            unicainstancia= new TCPSingelton();
            unicainstancia.start();
        }

        return unicainstancia;
    }


    private TCPSingelton(){


    }

    private Socket socket;

    @Override
    public void run(){

        try {
            Socket socket = new Socket("10.0.2.2",5000);
            OutputStream os= socket.getOutputStream();
            OutputStreamWriter osw= new OutputStreamWriter(os);
            bwriter= new BufferedWriter(osw);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void mensaje(String px, String py, String pcolor, String precordar,String pbotoncito){

    new Thread(
            () -> {

                Gson gson = new Gson();
                Recordatorio rec = new Recordatorio(px,py,pcolor,precordar,pbotoncito);
                //Serialización
                String coordStr = gson.toJson(rec);

                try {

                    bwriter.write(coordStr+"\n");
                    bwriter.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
    ).start();


}

}
