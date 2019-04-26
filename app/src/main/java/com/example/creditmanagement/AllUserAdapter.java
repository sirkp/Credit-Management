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

public class AllUserAdapter extends ArrayAdapter<User> {

    public AllUserAdapter(Context context, ArrayList<User> allUser){
            super(context,0,allUser);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.all_user_format,parent,false);

        User currentUser = getItem(position);

        TextView userName= (TextView) listItemView.findViewById(R.id.all_user_format_name);
        TextView userEmail = (TextView) listItemView.findViewById(R.id.all_user_format_email);

        userName.setText(currentUser.getName());
        userEmail.setText(currentUser.getEmail());

        return listItemView;
    }
}
