package com.example.creditmanagement;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.creditmanagement.MainActivity.users;

public class ViewAllUsers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        AllUserAdapter allUserAdapter = new AllUserAdapter(this, users);

        ListView alluser = (ListView) findViewById(R.id.view_all_user);
        alluser.setAdapter(allUserAdapter);

        alluser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getAdapter().getItem(position);
                String userName = user.getName();
                Intent intent = new Intent(ViewAllUsers.this, ViewUser.class);
                intent.putExtra("user_object", userName);
                startActivity(intent);
            }
        });
    }

}
