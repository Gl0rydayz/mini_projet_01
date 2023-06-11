package com.example.mini_projet_01;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter {
    ArrayList<User> users;

    LayoutInflater inflater;

    public UsersAdapter(Context context, ArrayList<User> users) {
        this.users = users;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_users, null);

        TextView tv_itemUsersFullName = view.findViewById(R.id.tv_itemUsersFullName);
        TextView tv_itemUsersCity = view.findViewById(R.id.tv_itemUsersCity);
        TextView tv_itemUsersGender = view.findViewById(R.id.tv_itemUsersNumber);

        tv_itemUsersFullName.setText(users.get(i).fullName());
        tv_itemUsersCity.setText(users.get(i).getCity());
        tv_itemUsersGender.setText(users.get(i).getGender());

        if (tv_itemUsersGender.getText().equals("male")) {
            view.setBackgroundColor(Color.CYAN);
        } else {
            view.setBackgroundColor(Color.MAGENTA);
        }

        tv_itemUsersGender.setText("#" + (i + 1));

        return view;
    }
}
