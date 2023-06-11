package com.example.mini_projet_01;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter {
    ArrayList<User> users;

    LayoutInflater inflater;
    Context context;

    public UsersAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.context = context;

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
        ImageButton imb_itemUsersMore = view.findViewById(R.id.imb_itemUsersMore);

        tv_itemUsersFullName.setText(users.get(i).fullName());
        tv_itemUsersCity.setText(users.get(i).getCity());
        imb_itemUsersMore.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("firstName", users.get(i).getFirstName().substring(0,1).toUpperCase() +
                    users.get(i).getFirstName().substring(1).toLowerCase());
            intent.putExtra("lastName", users.get(i).getLastName().toUpperCase());
            intent.putExtra("gender", users.get(i).getGender().equals("male") ? "♂": "♀");
            intent.putExtra("city", users.get(i).getCity());
            context.startActivity(intent);
        });

        return view;
    }
}