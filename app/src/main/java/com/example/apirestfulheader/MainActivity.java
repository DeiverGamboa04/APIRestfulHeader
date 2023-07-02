package com.example.apirestfulheader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void BtIngresar (View view){
        EditText Nombre = findViewById(R.id.txtNombre);
        EditText Contraseña = findViewById(R.id.txtclave);
        String nombre;
        String  contraseña;
        nombre=Nombre.getText().toString();
        contraseña=Contraseña.getText().toString();
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> dt = new HashMap<String, String>();

        dt.put("correo",nombre);
        dt.put("clave",contraseña);

        WebService ws= new WebService(" https://api.uealecpeterson.net/public/login"
                ,dt, MainActivity.this, MainActivity.this);
        ws.execute("POST");

    }

    public void btborrar (View view)
    {
        EditText Nombre =findViewById(R.id.txtNombre);
        EditText Contraseña =findViewById(R.id.txtclave);
        Nombre.setText("");
        Contraseña.setText("");
    }
    @Override
    public void processFinish (String result) throws JSONException {
        TextView respuesta =findViewById(R.id.Respu);
        JSONObject jsonrespuesta = new JSONObject(result);


        if (jsonrespuesta.has("error"))
        {
            respuesta.setText(jsonrespuesta.getString("error"));
        }
        else
        {

            Bundle b = new Bundle();
            b.putString("TOKEN", jsonrespuesta.getString("access_token"));
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            intent.putExtras(b);
            startActivity(intent);

        }

    }
}