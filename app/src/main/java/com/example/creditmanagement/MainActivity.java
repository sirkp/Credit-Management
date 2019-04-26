package com.example.creditmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import static com.example.creditmanagement.ViewAllUsers.users;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewAllUser = (Button)findViewById(R.id.bt_all_user);
        viewAllUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAllUsers.class);
                Log.e("check----","user");
                startActivity(intent);
            }
        });

    }

    public void viewAllTransaction(View view){
        Intent intent = new Intent(MainActivity.this, ViewAllTransaction.class);
        startActivity(intent);
    }
}
