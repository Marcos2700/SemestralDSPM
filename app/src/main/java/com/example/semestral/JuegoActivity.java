package com.example.semestral;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class JuegoActivity extends AppCompatActivity {

    private ImageView image1, image2, image3, image4, image5, image6, image7;
    private TextView natural, artificial;
    private Button validateButton;
    private HashMap<ImageView, TextView> correctConnections;
    private ArrayList<Connection> userConnections;
    private float startX;
    private float startY;
    private ImageView selectedImage;
    private DrawView drawView;

    @SuppressLint({"ClickableViewAccessibility", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego_screen);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);

        natural = findViewById(R.id.word1);
        artificial = findViewById(R.id.word2);
        validateButton = findViewById(R.id.validateButton);
        drawView = new DrawView(this);
        addContentView(drawView, new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        correctConnections = new HashMap<>();
        correctConnections.put(image1, natural);
        correctConnections.put(image2, artificial);
        correctConnections.put(image3, natural);
        correctConnections.put(image4, artificial);
        correctConnections.put(image5, natural);
        correctConnections.put(image6, artificial);
        correctConnections.put(image7, artificial);

        userConnections = new ArrayList<>();

        image1.setOnTouchListener(imageTouchListener);
        image2.setOnTouchListener(imageTouchListener);
        image3.setOnTouchListener(imageTouchListener);
        image4.setOnTouchListener(imageTouchListener);
        image5.setOnTouchListener(imageTouchListener);
        image6.setOnTouchListener(imageTouchListener);
        image7.setOnTouchListener(imageTouchListener);
        natural.setOnTouchListener(wordTouchListener);
        artificial.setOnTouchListener(wordTouchListener);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JuegoActivity.this, Calificacion.class);
                boolean allCorrect = validateConnections();
                i.putExtra("correctas", allCorrect);
                startActivity(i);
            }
        });
    }

    private DrawView.OnTouchListener imageTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                selectedImage = (ImageView) v;
                int[] location = new int[2];
                v.getLocationOnScreen(location);
                startX = v.getX() + (float) v.getWidth() / 2;
                startY = v.getY() + (float) v.getHeight() / 2;
            }
            return true;
        }
    };

    private DrawView.OnTouchListener wordTouchListener = new View.OnTouchListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN && selectedImage != null) {
                int[] location = new int[2];
                v.getLocationOnScreen(location);
                float endX = v.getX() + (float) v.getWidth() / 2;
                float endY = v.getY() + (float) v.getHeight() / 2;
                userConnections.add(new Connection(selectedImage, (TextView) v));
                drawView.addLine(startX, startY, endX, endY);
                selectedImage = null;
            }
            return true;
        }
    };

    private boolean validateConnections() {
        boolean allCorrect = true;
        for (Connection connection : userConnections) {
            if (correctConnections.get(connection.image) != connection.word) {
                allCorrect = false;
                break;
            }
        }
        return allCorrect;
    }
}