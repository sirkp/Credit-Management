package com.example.creditmanagement;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.creditmanagement.MainActivity.transactions;
import static com.example.creditmanagement.MainActivity.users;
import static com.example.creditmanagement.SpinnerActivity.spinnerUserName;

public class ViewUser extends AppCompatActivity {
    DatabaseHelper myDb;
    TransactionDatabase myTb;

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
        final User user = getUser(userName);

        //intialising database
        myDb = new DatabaseHelper(this);
        myTb=new TransactionDatabase((this));

        //setting views
        TextView tvUserName = (TextView) findViewById(R.id.tv_user_name);
        TextView tvUserEmail = (TextView)findViewById(R.id.tv_user_email);
        final TextView tvUserCredit = (TextView)findViewById(R.id.tv_user_credit);

        tvUserName.setText(""+user.getName());
        tvUserEmail.setText(""+user.getEmail());
        tvUserCredit.setText(""+user.getCredit());

        final EditText etCredit = (EditText) findViewById(R.id.et_credit_amount);

        final Spinner spinner = (Spinner) findViewById(R.id.users_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.userss_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new SpinnerActivity());

        Button btTransfer = (Button) findViewById(R.id.bt_transfer);
        btTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sAmount = etCredit.getText().toString();
                if(sAmount.equals(""))
                sAmount="0";
                User recieverUser=getUser(spinnerUserName);
                //Log.e("check",spinnerUserName+" samount: "+sAmount);
                int amount=Integer.parseInt(sAmount);

                if(user.isTransactionPossible(amount)){
                    user.subCredit(amount);
                    recieverUser.addCredit(amount);
                    etCredit.setText("");
                    tvUserCredit.setText(""+user.getCredit());
                    transactions.add(new Transaction(user.getName(),recieverUser.getName(),amount));
                    intialiseDatabase(new Transaction(user.getName(),recieverUser.getName(),amount));
                    Toast.makeText(ViewUser.this,"Transfer Successfull",Toast.LENGTH_SHORT).show();
                     updateData(user);
                     updateData(recieverUser);
                }
                else
                {
                    Toast.makeText(ViewUser.this,"Transaction not possible",Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    public void updateData(User user){
        boolean isUpdated= myDb.updateData(user.getName(),user.getCredit());
        Log.e("isUpdated: ",""+isUpdated);
    }

    public void intialiseDatabase(Transaction transaction){
        boolean isInserted=false;
        isInserted = myTb.insertData(transaction);
        Log.e("isInserted: ",""+isInserted);
    }


}
