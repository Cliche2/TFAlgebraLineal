package com.example.user.hamming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configurarBotones();
    }

    public void configurarBotones() {

        Button buttonEncode = (Button) findViewById(R.id.buttonEncode);
        View.OnClickListener list1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Encode.class));
            }
        };
        buttonEncode.setOnClickListener(list1);

        Button buttonDecode = (Button) findViewById(R.id.buttonDecode);
        View.OnClickListener list2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Decode.class));
            }
        };
        buttonDecode.setOnClickListener(list2);
    }

}
