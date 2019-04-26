package com.example.creditmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.creditmanagement.ViewAllUsers.users;


public class ViewAllTransaction extends AppCompatActivity {

    static ArrayList<Transaction> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_transaction);

        AllTransactionAdapter allTransactionAdapter=new AllTransactionAdapter(this, transactions);

        ListView alltransaction = (ListView)findViewById(R.id.all_transaction);
        alltransaction.setAdapter(allTransactionAdapter);

        for(int i=0;i<10;i++)
        Log.e("value :",""+users.get(i).getCredit());
    }
}
