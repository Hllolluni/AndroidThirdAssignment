package com.eutraining.llolluni.androidthirdassignment;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class UtilClass {

    public static void showMessage(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();
    }
}
