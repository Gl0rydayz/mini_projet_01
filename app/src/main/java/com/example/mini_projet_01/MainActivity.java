package com.example.mini_projet_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_loadUsers;
    TextView tv_quit;
    ListView lv_users;
    ProgressBar progressBar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_loadUsers = findViewById(R.id.btn_loadUsers);
        tv_quit = findViewById(R.id.tv_quit);
        lv_users = findViewById(R.id.lv_users);
        //region Initialize progressBar
        progressBar = new ProgressBar(this);
        //endregion

        //region Generate an Id for the progressBar and set it's visibility to INVISIBLE
        progressBar.setId(View.generateViewId());
        progressBar.setVisibility(View.INVISIBLE);
        //endregion

        // Add the ProgressBar to the layout
        ConstraintLayout constraintLayout = findViewById(R.id.myCLayout);
        constraintLayout.addView(progressBar);

        // Center the ProgressBar
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(progressBar.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 0);
        constraintSet.connect(progressBar.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM, 0);
        constraintSet.connect(progressBar.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 0);
        constraintSet.connect(progressBar.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 0);
        constraintSet.applyTo(constraintLayout);

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
//            UsersAdapter adapter = new UsersAdapter(this, getUsers());
//
//            lv_users.setAdapter(adapter);
            //region Handle the Progressbar
            progressBar.setVisibility(View.VISIBLE); // Show progress bar before loading users
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<User> users = getUsers();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            UsersAdapter adapter = new UsersAdapter(MainActivity.this, users);
                            lv_users.setAdapter(adapter);
                            progressBar.setVisibility(View.INVISIBLE); // Hide progress bar after loading users
                        }
                    });
                }
            }).start();
            //endregion
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
                DelayThread delayThread = new DelayThread();
                delayThread.start();
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return usersFullNames;
    }

    class DelayThread extends Thread {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}