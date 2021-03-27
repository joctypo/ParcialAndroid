package com.example.parcialuno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private Button rojo,verde,amarillo,vista,confirmar;
    private EditText posx,posy,recordatorio;
    private TCPSingelton tcp;
    private int colorescogido;
    private boolean bverde,brojo,bamarillo;
    private String pox,poy,pocolor,porecor,botoncito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rojo = findViewById(R.id.rojo);
        verde = findViewById(R.id.verde);
        amarillo = findViewById(R.id.amarillo);
        posx = findViewById(R.id.posx);
        posy = findViewById(R.id.posy);
        recordatorio = findViewById(R.id.recordatorio);
        vista = findViewById(R.id.vista);
        confirmar=findViewById(R.id.confirmar);
        tcp = TCPSingelton.getInstance();

        rojo.setOnClickListener(
                v -> {
                    brojo=true;
                    bverde=false;
                    bamarillo=false;
                    if( brojo==true){
                        pocolor="1";
                        Toast.makeText(this,"Rojo",Toast.LENGTH_LONG).show();
                    }

                }
        );

        verde.setOnClickListener(
                v -> {
                    brojo=false;
                    bverde=true;
                    bamarillo=false;
                    if(bverde==true){
                        pocolor="2";
                        Toast.makeText(this,"Verde",Toast.LENGTH_LONG).show();
                    }

                }
        );

        amarillo.setOnClickListener(
                v -> {
                    brojo=false;
                    bverde=false;
                    bamarillo=true;
                    if(bamarillo==true){
                        pocolor="3";
                        Toast.makeText(this,"Amarillo",Toast.LENGTH_LONG).show();
                    }

                }
        );

        vista.setOnClickListener(

                v -> {
                if(bamarillo==true || brojo == true || bverde==true){

                    pox= posx.getText().toString();
                    poy= posy.getText().toString();
                    porecor = recordatorio.getText().toString();
                    botoncito = "1";
                    tcp.mensaje(pox,poy,pocolor,porecor,botoncito);
                    Toast.makeText(this,"Previsualizando",Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(this,"Debes oprimir un color antes",Toast.LENGTH_LONG).show();



                }


                }

        );

        confirmar.setOnClickListener(
                v -> {
                    pox= posx.getText().toString();
                    poy= posy.getText().toString();
                    porecor = recordatorio.getText().toString();
                    botoncito = "2";
                    tcp.mensaje(pox,poy,pocolor,porecor,botoncito);
                    Toast.makeText(this,"Confirmado y fijado",Toast.LENGTH_LONG).show();
                }
        );

    }





}