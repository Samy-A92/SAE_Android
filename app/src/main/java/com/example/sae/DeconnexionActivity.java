package com.example.sae_android;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.Toast;


public class DeconnexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deconnexion);

        Button btnDeconnexion = findViewById(R.id.btnDeconnexion);

        btnDeconnexion.setOnClickListener(v -> {

            new AlertDialog.Builder(DeconnexionActivity.this)
                    .setTitle("Confirmation")
                    .setMessage("Voulez-vous vous déconnecter ?")
                    .setPositiveButton("Oui", (dialog, which) -> {

                        // Message
                        Toast.makeText(DeconnexionActivity.this, "Déconnexion réussie", Toast.LENGTH_SHORT).show();

                        // Redirection vers login
                        Intent intent = new Intent(DeconnexionActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    })
                    .setNegativeButton("Non", null)
                    .show();
        });
    }
}