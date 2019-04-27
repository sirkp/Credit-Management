package com.example.creditmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAllUsers extends AppCompatActivity {
    DatabaseHelper myDb;

    static ArrayList<User> users=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        myDb = new DatabaseHelper(this);
        users.add(new User("Pradeep Kumar","pradeepkumar0560@gmail.com", 100));
        users.add(new User("Priyank Gupta","priyankgupta21@gmail.com", 120));
        users.add(new User("Prashant Sengar","prashantsengar73@gmail.com", 130));
        users.add(new User("Dhiraj Sharma","dhirajsharma99@gmail.com", 200));
        users.add(new User("Amit Yadav","amit.yadav0560@gmail.com", 170));
        users.add(new User("Akhand Singh","kalinbaiya@gmail.com", 180));
        users.add(new User("Sonu Kumar","sonukumar.htk@gmail.com", 140));
        users.add(new User("Pawandeep Kumar","pawandeep.85@gmail.com", 145));
        users.add(new User("Rahul Sharma","rahul.rules@gmail.com", 125));
        users.add(new User("Dev Kant Chouhan","devkant.chouhan400@gmail.com", 135));

        AllUserAdapter allUserAdapter = new AllUserAdapter(this,users);

        ListView alluser = (ListView)findViewById(R.id.view_all_user);
        alluser.setAdapter(allUserAdapter);

        alluser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) parent.getAdapter().getItem(position);
                String userName= user.getName();
                Intent intent  = new Intent(ViewAllUsers.this,ViewUser.class);
                intent.putExtra("user_object",userName);
                startActivity(intent);
            }
        });

        addData();

    }

    public void addData(){
        boolean isInserted = myDb.insertData(users.get(0));
        if(isInserted == true)
            Toast.makeText(ViewAllUsers.this,"Data Inserted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ViewAllUsers.this,"Data not Inserted",Toast.LENGTH_LONG).show();
    }

}
