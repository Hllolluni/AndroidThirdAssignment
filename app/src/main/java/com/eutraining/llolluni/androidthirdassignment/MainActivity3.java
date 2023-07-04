package com.eutraining.llolluni.androidthirdassignment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    SQLiteDatabase database;
    EditText editTextNewName;
    EditText editTextNewPhone;
    EditText editTextNewEmail;
    EditText editTextNewStreet;
    EditText editTextNewCity;
    EditText editTextNewState;
    EditText editTextNewZip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        database = openOrCreateDatabase("ContactDB.db", MODE_PRIVATE, null);
        editTextNewName = findViewById(R.id.editTextNewName);
        editTextNewPhone = findViewById(R.id.editTextNewPhone);
        editTextNewEmail = findViewById(R.id.editTextNewEmailAddress);
        editTextNewStreet = findViewById(R.id.editTextNewStreet);
        editTextNewCity = findViewById(R.id.editTextNewCity);
        editTextNewState = findViewById(R.id.editTextNewState);
        editTextNewZip = findViewById(R.id.editTextNewZip);
    }

    public void updateAddress(View view) {
        Bundle bundle = getIntent().getExtras();

        if (!editTextNewName.getText().toString().isEmpty() &&
                !editTextNewPhone.getText().toString().isEmpty() &&
                !editTextNewEmail.getText().toString().isEmpty() &&
                !editTextNewStreet.getText().toString().isEmpty() &&
                !editTextNewCity.getText().toString().isEmpty() &&
                !editTextNewState.getText().toString().isEmpty() &&
                !editTextNewZip.getText().toString().isEmpty() &&
                bundle != null
        ) {
            String addressName = bundle.getString("addressName");

            String name = editTextNewName.getText().toString();
            String phoneNumber = editTextNewPhone.getText().toString();
            String email = editTextNewEmail.getText().toString();
            String street = editTextNewEmail.getText().toString();
            String city = editTextNewCity.getText().toString();
            String state = editTextNewState.getText().toString();
            String zipCode = editTextNewZip.getText().toString();
            database.execSQL("Update Address set " +
                    "ADDRESS_NAME=?, " +
                    "PHONE_NUMBER=?, " +
                    "EMAIL=?, " +
                    "STREET=?, " +
                    "CITY=?, " +
                    "STATE=?, "+
                    "ZIP=? where ADDRESS_NAME=?", new String[]{name, phoneNumber, email, street, city, state, zipCode, addressName});
            showMessage("Success", "The address is updated.");
        } else {
            showMessage("Error", "Please enter all information about the contact!");
        }

        editTextNewName.setText("");
        editTextNewPhone.setText("");
        editTextNewEmail.setText("");
        editTextNewStreet.setText("");
        editTextNewCity.setText("");
        editTextNewState.setText("");
        editTextNewZip.setText("");
    }

    public void showMessage(String title, String message) {
        UtilClass.showMessage(this, title, message);
    }
}