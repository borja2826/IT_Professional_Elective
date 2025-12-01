package com.example.simplenotes; // <-- IMPORTANT: Kung iba ang package name mo, palitan mo ito or retain mo yung original.

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextNote;
    Button buttonSave;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI
        editTextNote = findViewById(R.id.editTextNote);
        buttonSave = findViewById(R.id.buttonSave);

        // Setup storage (para ma-save ang note)
        sharedPreferences = getSharedPreferences("MyNotes", Context.MODE_PRIVATE);

        // Load saved note kapag binuksan ang app
        String savedNote = sharedPreferences.getString("note_key", "");
        editTextNote.setText(savedNote);

        // Save button action
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteText = editTextNote.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("note_key", noteText);
                editor.apply();

                Toast.makeText(MainActivity.this, "Note Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
