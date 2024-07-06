package com.example.semestral;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Calificacion extends AppCompatActivity {

    private LinearLayout layout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calificacion_screen);

        layout = findViewById(R.id.linearLayout);

        Intent i = getIntent();
        boolean allCorrect = i.getBooleanExtra("correctas", true);

        TextView comentarios = new TextView(this);
        ImageView carita = new ImageView(this);

        if(allCorrect){
            comentarios.setText("Felicidades!!!\nCompletaste el test correctamente.");
            carita.setImageResource(R.drawable.feliz);
        }
        else{
            comentarios.setText("Sabemos que hiciste un gran esfuerzo\nLastimosamente no completaste el test correctamente asi que te retamos a reintentarlo");
            carita.setImageResource(R.drawable.triste);
        }
        layout.addView(comentarios);
        layout.addView(carita);
    }
}
