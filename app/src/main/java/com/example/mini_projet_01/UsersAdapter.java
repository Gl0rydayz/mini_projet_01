package com.example.mini_projet_01;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        tv_itemUsersFullName.setText(users.get(i).fullName());
        tv_itemUsersCity.setText(users.get(i).getCity());

//        view.setOnLongClickListener(v -> {
//            AlertDialog builder = new AlertDialog.Builder(context)
//                    .setTitle(String.format("User %d", i + 1))
//                    .setMessage(users.get(i).toString())
//                    .show();
//
//            return false;
//        });


        View finalView = view;
        view.setOnTouchListener(new OnSwipeTouchListener(context) {
            @Override
            public void swipeLeft() {
                finalView.setBackgroundColor(Color.RED);
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Attention")
                        .setMessage("Do you want to delete this user ? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int j) {
                                //changed i with "j"
                                users.remove(i);
                                notifyDataSetChanged(); //refresh the listView after we remove the item
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finalView.setBackgroundColor(Color.TRANSPARENT);
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return view;
    }
}
