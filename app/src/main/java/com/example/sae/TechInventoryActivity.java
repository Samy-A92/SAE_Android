package com.example.sae;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TechInventoryActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private RecyclerView recyclerView;

    private ArrayList<String> ordinateurs = new ArrayList<>();
    private ArrayList<String> salles = new ArrayList<>();


    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tech_inventory);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Liste de 10 noms d'ordinateurs
        ordinateurs.add("PC-Alpha");
        ordinateurs.add("PC-Beta");
        ordinateurs.add("PC-Gamma");
        ordinateurs.add("PC-Delta");
        ordinateurs.add("PC-Epsilon");
        ordinateurs.add("PC-Zeta");
        ordinateurs.add("PC-Eta");
        ordinateurs.add("PC-Theta");
        ordinateurs.add("PC-Iota");
        ordinateurs.add("PC-Kappa");

        // Liste de 10 noms de salles
        salles.add("Salle 101");
        salles.add("Salle 102");
        salles.add("Salle 103");
        salles.add("Salle 104");
        salles.add("Salle 105");
        salles.add("Salle 106");
        salles.add("Salle 107");
        salles.add("Salle 108");
        salles.add("Salle 109");
        salles.add("Salle 110");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(ordinateurs, salles, position -> {
            afficherDialogueModification(position);
        });
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.ajout_machine_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterDialogueModification();
            }
        });
    }

    private void afficherDialogueModification(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modifier la salle pour " + ordinateurs.get(position));

        final EditText input = new EditText(this);
        input.setText(salles.get(position));
        builder.setView(input);

        builder.setPositiveButton("Modifier", (dialog, id) -> {
            String nouvelleSalle = input.getText().toString();
            salles.set(position, nouvelleSalle);
            adapter.notifyItemChanged(position);
        });

        builder.setNegativeButton("Annuler", null);
        builder.show();
    }

    private void ajouterDialogueModification() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ajouter une nouvelle machine");

        // On gonfle le layout créé à l'étape 1
        View view = getLayoutInflater().inflate(R.layout.dialog_add_machine, null);
        EditText inputNom = view.findViewById(R.id.editNomMachine);
        EditText inputSalle = view.findViewById(R.id.editSalleMachine);
        builder.setView(view);

        builder.setPositiveButton("Ajouter", (dialog, id) -> {
            String nom = inputNom.getText().toString();
            String salle = inputSalle.getText().toString();

            if (!nom.isEmpty() && !salle.isEmpty()) {
                ordinateurs.add(nom);
                salles.add(salle);

                adapter.notifyItemInserted(ordinateurs.size() - 1);

                recyclerView.scrollToPosition(ordinateurs.size() - 1);
            }
        });

        builder.setNegativeButton("Annuler", null);
        builder.show();
    }
}