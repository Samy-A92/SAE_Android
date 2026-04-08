package com.example.sae;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText editTextLogin = findViewById(R.id.editTextLogin);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})

        TextView textViewErrorConnecting = findViewById(R.id.textViewErrorConnecting);
        textViewErrorConnecting.setVisibility(View.INVISIBLE);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button buttonConnect = findViewById(R.id.connect);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = editTextLogin.getText().toString();
                String password = editTextPassword.getText().toString();

                if(login.equals("tech") && password.equals("tech")) {
                    SharedPreferences sharedPreferences = getSharedPreferences("Session", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("Connected", true);
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, TechInventoryActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    textViewErrorConnecting.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}