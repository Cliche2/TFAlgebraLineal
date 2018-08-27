package com.algebralineal.tfalgebralineal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.algebralineal.tpalgebralineal.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToEncodeActivity(View view)
    {
        Intent intent = new Intent(this, EncodeActivity.class);
        startActivity(intent);
    }
}
