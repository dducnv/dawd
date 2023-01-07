package com.example.dawd_practical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dawd_practical.models.Feedback;
import com.example.dawd_practical.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Feedback> feedbacks;
    DBHelper db;
    Button btnSave;
    Button btnUpdate;
    Button btnDelete;
    private EditText editName;
    private EditText editEmail;
    private EditText editMessage;
    Feedback feedbackId;
    ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DBHelper(this);
        btnSave = (Button) findViewById(R.id.buttonSubmit);
        editName = (EditText) findViewById(R.id.fullName);
        editEmail = (EditText) findViewById(R.id.emailInput);
        editMessage = (EditText) findViewById(R.id.messageInput);
        feedbacks = db.getAll();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String message = editMessage.getText().toString();
                boolean isValidate =  isValidForm();
                if(!isValidate){
                    Toast toast = Toast.makeText(context, "Please enter full form!", duration);
                    toast.show();
                    return;
                }
                Feedback feedback = new Feedback(name, email, message);
                db.add(feedback);
                feedbacks.add(feedback);
                resetForm();
                Toast toast = Toast.makeText(context, "Add success!", duration);
                toast.show();
            }
        });

    }
    public boolean isValidForm() {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String message = editMessage.getText().toString();
        boolean isValid = true;
        if(name.length() == 0) {
            isValid = false;
        }
        if(email.length() == 0) {
            isValid = false;
        }
        if(message.length() == 0) {
            isValid = false;
        }
        return isValid;
    }
    public void resetForm() {
        editName.setText("");
        editEmail.setText("");
        editMessage.setText("");
    }
}