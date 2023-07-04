package com.eutraining.llolluni.androidthirdassignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextStreet;
    EditText editTextCity;
    EditText editTextState;
    EditText editTextZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = openOrCreateDatabase("ContactDB.db", MODE_PRIVATE, null);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextStreet = findViewById(R.id.editTextStreet);
        editTextCity = findViewById(R.id.editTextCity);
        editTextState = findViewById(R.id.editTextState);
        editTextZip = findViewById(R.id.editTextPostalAddress);

        database.execSQL("Create table if not exists Address(" +
                "ADDRESS_NAME TEXT PRIMARY KEY," +
                "PHONE_NUMBER TEXT," +
                "EMAIL TEXT," +
                "STREET TEXT," +
                "CITY TEXT," +
                "STATE TEXT," +
                "ZIP TEXT)"
        );
        database.execSQL("Insert or ignore into Address values('addr1', '876543', 'email', 'street', 'city', 'state', 'zip')");
    }

    public void newContact(View view) {
        if (!editTextName.getText().toString().isEmpty() &&
                !editTextPhone.getText().toString().isEmpty() &&
                !editTextEmail.getText().toString().isEmpty() &&
                !editTextStreet.getText().toString().isEmpty() &&
                !editTextCity.getText().toString().isEmpty() &&
                !editTextState.getText().toString().isEmpty() &&
                !editTextZip.getText().toString().isEmpty()
        ) {
            String name = editTextName.getText().toString();
            String phoneNumber = editTextPhone.getText().toString();
            String email = editTextEmail.getText().toString();
            String street = editTextEmail.getText().toString();
            String city = editTextCity.getText().toString();
            String state = editTextState.getText().toString();
            String zipCode = editTextZip.getText().toString();
            database.execSQL("Insert into Address values(?,?,?,?,?,?,?)", new String[]{name, phoneNumber, email, street, city, state, zipCode});
            showMessage("Success", "New address is added.");
        } else {
            showMessage("Error", "Please enter all information about the contact!");
        }

        editTextName.setText("");
        editTextPhone.setText("");
        editTextEmail.setText("");
        editTextStreet.setText("");
        editTextCity.setText("");
        editTextState.setText("");
        editTextZip.setText("");
    }

    public void otherOptions(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void showMessage(String title, String message) {
        UtilClass.showMessage(this, title, message);
    }
}