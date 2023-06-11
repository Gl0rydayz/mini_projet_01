package com.example.mini_projet_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class UsersAdapter extends BaseAdapter {
    final int DOUBLE_CLICK_TIMEOUT = 250;
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
        ImageView iv_itemUsersCheck = view.findViewById(R.id.iv_itemUsersCheck);

        tv_itemUsersFullName.setText(users.get(i).fullName());
        tv_itemUsersCity.setText(users.get(i).getCity());

        view.setOnLongClickListener(v -> {
            AlertDialog builder = new AlertDialog.Builder(context)
                    .setTitle(String.format("User %d", i + 1))
                    .setMessage(users.get(i).toString())
                    .show();

            return false;
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            long lastClickTime = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    long clickTime = System.currentTimeMillis();
                    if ((clickTime - lastClickTime) <= DOUBLE_CLICK_TIMEOUT) {
                        iv_itemUsersCheck.setVisibility(iv_itemUsersCheck.getVisibility() == view.INVISIBLE ? View.VISIBLE : View.INVISIBLE);
                    } else {
                        lastClickTime = clickTime;
                    }
                }

                return true;
            }
        });

        return view;
    }
}
