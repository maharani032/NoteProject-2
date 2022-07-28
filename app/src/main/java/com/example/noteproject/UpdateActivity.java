package com.example.noteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title,description;
    Button cancel,save;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Note");
        setContentView(R.layout.activity_update);

        title=findViewById(R.id.editTextTitleUpdate);
        description=findViewById(R.id.editTextDescriptionUpdate);
        save=findViewById(R.id.buttonSaveUpdate);
        cancel=findViewById(R.id.buttonCancelUpdate);

        getData();

        cancel.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
            finish();
        });
        save.setOnClickListener(view -> UpdateNote());

    }
    private void UpdateNote(){
    String titleLast= title.getText().toString();
    String descriptionLast= description.getText().toString();

    Intent intent= new Intent();
    intent.putExtra("titleLast",titleLast);
    intent.putExtra("descriptionLast",descriptionLast);

    if(noteId!=-1){
        intent.putExtra("noteId",noteId);
        setResult(RESULT_OK,intent);
        finish();
    }

    }
    public void getData(){
        Intent i=getIntent();
        noteId=i.getIntExtra("id",-1);
        String noteTitle= i.getStringExtra("title");
        String noteDescription= i.getStringExtra("description");

        title.setText(noteTitle);
        description.setText(noteDescription);

    }

}