package com.example.mini_projet_01;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

public class UserDetailsDialog extends DialogFragment {
    User user;

    public UserDetailsDialog(User user) {
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_users_details, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView tv_itemUsersDetailsFirstName = view.findViewById(R.id.tv_itemUsersDetailsFirstName);
        TextView tv_itemUsersDetailsLastName = view.findViewById(R.id.tv_itemUsersDetailsLastName);
        TextView tv_itemUsersDetailsCity = view.findViewById(R.id.tv_itemUsersDetailsCity);

        tv_itemUsersDetailsFirstName.setText(user.getFirstName());
        tv_itemUsersDetailsLastName.setText(user.getLastName());
        tv_itemUsersDetailsCity.setText(user.getCity());

        if (user.getGender().equals("male")) {
            view.setBackgroundColor(Color.CYAN);
        } else {
            view.setBackgroundColor(Color.MAGENTA);
        }

        super.onViewCreated(view, savedInstanceState);
    }
}
