package com.example.mini_projet_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.OnSwipe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_loadUsers;
    TextView tv_quit;
    ListView lv_users;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_loadUsers = findViewById(R.id.btn_loadUsers);
        tv_quit = findViewById(R.id.tv_quit);
        lv_users = findViewById(R.id.lv_users);

        btn_loadUsers.setOnClickListener(this);

        tv_quit.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void swipeLeft() {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btn_loadUsers) {
            UsersAdapter adapter = new UsersAdapter(this, getUsers());

            lv_users.setAdapter(adapter);
        }
    }

    private ArrayList<User> getUsers() {
        ArrayList<User> usersFullNames = new ArrayList<>();

        try {
            InputStream inputStream = getAssets().open("users.json");
            int code;
            StringBuilder stringBuilder = new StringBuilder();
            String jsongString;

            code = inputStream.read();
            while (code != -1) {
                stringBuilder.append((char) code);
                code = inputStream.read();
            }

            jsongString = stringBuilder.toString();

            JSONObject jsonObject = new JSONObject(jsongString);
//                JSONArray jsonArray = (JSONArray) jsonObject.get("users");
            JSONArray jsonArray = jsonObject.getJSONArray("users");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject user = jsonArray.getJSONObject(i);
                JSONObject userName = user.getJSONObject("name");
//                String fullName = String.format("%s %s\n", userName.get("first"), userName.get("last"));
                usersFullNames.add(new User(userName.getString("first"), userName.getString("last"),
                        user.getString("gender"), user.getString("city")));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return usersFullNames;
    }
}