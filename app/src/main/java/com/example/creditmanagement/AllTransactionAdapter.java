package com.example.creditmanagement;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AllTransactionAdapter extends ArrayAdapter<Transaction> {
    public  AllTransactionAdapter(Context context, ArrayList<Transaction> allTransaction){
        super(context,0,allTransaction);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemview = convertView;
        if(listItemview==null)
            listItemview= LayoutInflater.from(getContext()).inflate(R.layout.all_transaction_format,parent,false);

        Transaction currentTransaction=getItem(position);

        TextView transactionSender= (TextView)listItemview.findViewById(R.id.transaction_sender);
        TextView transactionReciever= (TextView)listItemview.findViewById(R.id.transaction_reciever);
        TextView transactionAmount=(TextView)listItemview.findViewById((R.id.transaction_credit));

        transactionSender.setText(currentTransaction.getSenderName());
        transactionReciever.setText(currentTransaction.getRecieverName());
        transactionAmount.setText(""+currentTransaction.getAmount());

        return listItemview;
    }
}
