package com.example.creditmanagement;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    public static String spinnerUserName;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerUserName = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
