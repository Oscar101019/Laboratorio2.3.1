package com.example.dss22.a231almacexterno;


import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText textBox;
    static final int READ_BLOCK_SIZE = 100;
    boolean sdDisponible = false;
    boolean sdAccesoEscritura = false;
    String estado = Environment.getExternalStorageState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = (EditText) findViewById(R.id.editText);
    }

    public void onClickSave(View view) {

        if (estado.equals(Environment.MEDIA_MOUNTED))
        {
            sdDisponible = true;
            sdAccesoEscritura = true;
        }
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            sdDisponible = true;
            sdAccesoEscritura = false;
        }
        else
        {
            sdDisponible = false;
            sdAccesoEscritura = false;
        }

        String str = textBox.getText().toString();
        try {
            File myFile = Environment.getExternalStorageDirectory();
            File f = new File(myFile.getAbsolutePath(), "prueba_sd.txt");
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            new FileOutputStream(f));

            fout.write(str);
            fout.close();
            textBox.setText("");
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
        }
    }

    public void onClickLoad(View view) {

        try{
            File myFile = Environment.getExternalStorageDirectory();

            File f = new File(myFile.getAbsolutePath(), "prueba_sd.txt");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));

            String str = fin.readLine();
            fin.close();
            textBox.setText(str);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(view.getContext(),"NO jala!!", Toast.LENGTH_LONG).show();
        }
    }
}



