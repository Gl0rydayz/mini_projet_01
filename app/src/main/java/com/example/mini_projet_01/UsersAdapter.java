package com.example.mini_projet_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
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

        tv_itemUsersFullName.setText(users.get(i).fullName());
        tv_itemUsersCity.setText(users.get(i).getCity());
        view.setOnLongClickListener(v -> {
            AlertDialog builder = new AlertDialog.Builder(context)
                    .setTitle(String.format("User %d", i + 1))
                    .setMessage(users.get(i).toString())
                    .show();

            return false;
        });

        return view;
    }
}
