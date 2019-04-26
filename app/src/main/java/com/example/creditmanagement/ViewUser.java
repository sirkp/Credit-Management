package com.example.creditmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.creditmanagement.ViewAllUsers.users;

public class ViewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        //getting user object name from ViewAllUser
        String userName;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userName= null;
            } else {
                userName= extras.getString("user_object");
            }
        } else {
            userName= (String) savedInstanceState.getSerializable("user_object");
        }

        //getting object from name of object
        User user = getUser(userName);

        //setting views
        TextView tvUserName = (TextView) findViewById(R.id.tv_user_name);
        TextView tvUserEmail = (TextView)findViewById(R.id.tv_user_email);
        TextView tvUserCredit = (TextView)findViewById(R.id.tv_user_credit);

        tvUserName.setText(""+user.getName());
        tvUserEmail.setText(""+user.getEmail());
        tvUserCredit.setText(""+user.getCredit());




        Spinner spinner = (Spinner) findViewById(R.id.users_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.userss_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



    }

    User getUser(String name){
        for(int i=0;i<users.size();i++){
            if(name.equals(users.get(i).getName()))
            {
                return users.get(i);

            }
        }
        return null;
    }
}
