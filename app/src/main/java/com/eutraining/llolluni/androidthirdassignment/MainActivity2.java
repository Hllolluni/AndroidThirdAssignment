package com.eutraining.llolluni.androidthirdassignment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    EditText addressName;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        database = openOrCreateDatabase("ContactDB.db", MODE_PRIVATE, null);
        addressName = findViewById(R.id.editTextAddressName);
        addressName.setText("");
    }

    public void selectAll(View view) {
        Cursor cursor = database.rawQuery("Select * from Address", null);
        StringBuilder builder = new StringBuilder();

        while (cursor.moveToNext()) {
            builder.append("Address name: ").append(cursor.getString(0)).append("\n");
            builder.append("Phone number: ").append(cursor.getString(1)).append("\n");
            builder.append("Email: ").append(cursor.getString(2)).append("\n");
            builder.append("Street: ").append(cursor.getString(3)).append("\n");
            builder.append("City: ").append(cursor.getString(4)).append("\n");
            builder.append("State: ").append(cursor.getString(5)).append("\n");
            builder.append("Zip: ").append(cursor.getString(6)).append("\n\n");
        }

        showMessage("Addresses", builder.toString());
    }

    public void selectOne(View view) {
        if (!addressName.getText().toString().isEmpty()) {
            String addrName = addressName.getText().toString();
            Cursor cursor = database.rawQuery("Select * from Address where ADDRESS_NAME=?", new String[]{addrName});
            if (cursor.moveToNext()){
                StringBuilder builder = new StringBuilder();
                builder.append("Address name: ").append(cursor.getString(0)).append("\n");
                builder.append("Phone number: ").append(cursor.getString(1)).append("\n");
                builder.append("Email: ").append(cursor.getString(2)).append("\n");
                builder.append("Street: ").append(cursor.getString(3)).append("\n");
                builder.append("City: ").append(cursor.getString(4)).append("\n");
                builder.append("State: ").append(cursor.getString(5)).append("\n");
                builder.append("Zip: ").append(cursor.getString(6));
                showMessage("Address found", builder.toString());
            }else {
                showMessage("Error", "No address found...");
            }
        } else {
            showMessage("Error", "Please specify the address name.");
        }

        addressName.setText("");
    }

    public void updateOne(View view) {
        if (!addressName.getText().toString().isEmpty()) {
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("addressName", addressName.getText().toString());
            startActivity(intent);
        } else {
            showMessage("Error", "Please specify the address name.");
        }
        addressName.setText("");
    }

    public void deleteOne(View view) {
        if (!addressName.getText().toString().isEmpty()) {
            String addrName = addressName.getText().toString();
            database.execSQL("Delete from Address where ADDRESS_NAME like ?", new String[]{addrName});
            showMessage("Success", "Address is deleted...");
        } else {
            showMessage("Error", "Please specify the address name.");
        }

        addressName.setText("");
    }

    public void showMessage(String title, String message) {
        UtilClass.showMessage(this, title, message);
    }
}