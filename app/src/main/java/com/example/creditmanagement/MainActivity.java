package com.example.creditmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<User> users=new ArrayList<>();
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUsers();
        Button viewAllUser = (Button)findViewById(R.id.bt_all_user);
        viewAllUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAllUsers.class);
                Log.e("check----","user");
                startActivity(intent);
            }
        });

        myDb = new DatabaseHelper(this);

        Cursor cursor = myDb.getAllData();
        if(cursor.getCount()==0)
            intialiseDatabase();

        viewAll();
        getRow();

    }

    public void viewAllTransaction(View view){
        Intent intent = new Intent(MainActivity.this, ViewAllTransaction.class);
        startActivity(intent);
    }


    public void intialiseDatabase(){
        boolean isInserted=false;
        for(int i=0;i<10;i++)
            isInserted = myDb.insertData(users.get(i));
    }


    public void updateId(){
        for(int i=0;i<10;i++)
            myDb.updateId(""+(i+15),""+(i+1));
    }

    public void deleteData(){
        int noOfRows = myDb.deleteData("1");
    }

    public void viewAll(){
        Cursor cursor = myDb.getAllData();
        if(cursor.getCount()==0){
            Toast.makeText(MainActivity.this, "No data could br retrieved",Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        String name="",email="",credit="";
        while(cursor.moveToNext()){
            buffer.append("Id: "+cursor.getString(0)+"\n");
            name= buffer.append("Name: "+cursor.getString(1)+"\n").toString();
            email=buffer.append("Email: "+cursor.getString(2)+"\n").toString();
            credit=buffer.append("Credit: "+cursor.getString(3)+"\n").toString();
        }
        Log.e("DATABASE",buffer.toString());

    }

    public void getRow() {
        for (int i = 0; i < 10; i++) {
            Cursor cursor = myDb.getRow(""+(i+1));
            if (cursor.getCount() == 0) {
                Toast.makeText(MainActivity.this, "No data could br retrieved", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            String name = "", email = "";
            int credit = 0;
            if (cursor.moveToNext()) {
                name = cursor.getString(0);
                email = cursor.getString(1);
                credit = cursor.getInt(2);
            }
            users.get(i).setCredit(credit);
            Log.e("DATABASE", name + " " + email + " " + credit);
        }
    }

    public void setUsers(){
        users.add(new User("Pradeep Kumar", "pradeepkumar0560@gmail.com", 100));
        users.add(new User("Priyank Gupta", "priyankgupta21@gmail.com", 120));
        users.add(new User("Prashant Sengar", "prashantsengar73@gmail.com", 130));
        users.add(new User("Dhiraj Sharma", "dhirajsharma99@gmail.com", 200));
        users.add(new User("Amit Yadav", "amit.yadav0560@gmail.com", 170));
        users.add(new User("Akhand Singh", "kalinbaiya@gmail.com", 180));
        users.add(new User("Sonu Kumar", "sonukumar.htk@gmail.com", 140));
        users.add(new User("Pawandeep Kumar", "pawandeep.85@gmail.com", 145));
        users.add(new User("Rahul Sharma", "rahul.rules@gmail.com", 125));
        users.add(new User("Dev Kant Chouhan", "devkant.chouhan400@gmail.com", 135));
    }

}
