package com.example.mini_projet_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_loadUsers, btn_quit;
    RadioButton rb_males, rb_females;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_loadUsers = findViewById(R.id.btn_loadUsers);
        btn_quit = findViewById(R.id.btn_quit);
        rb_males = findViewById(R.id.rb_males);
        rb_females = findViewById(R.id.rb_females);

        btn_loadUsers.setOnClickListener(this);
        btn_quit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_loadUsers) {
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

                String alertDialogTitle = "";

                StringBuilder stringBuilderFullNameAndCity = new StringBuilder();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject user = jsonArray.getJSONObject(i);
                    JSONObject userName = user.getJSONObject("name");
                    String fullName = String.format("%s %s | %s\n", userName.get("first"), userName.get("last"), user.get("city"));
//                    if(user.get("gender").equals("male")) {
//                        stringBuilderFullName.append(fullName);
//                    }
                    if (rb_males.isChecked()) {
                        if (user.get("gender").equals("male")) {
                            stringBuilderFullNameAndCity.append(fullName);
                            alertDialogTitle = "Male users";
                        }
                    } else if (rb_females.isChecked()) {
                        if (user.get("gender").equals("female")) {
                            stringBuilderFullNameAndCity.append(fullName);
                            alertDialogTitle = "Female users";
                        }
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(alertDialogTitle)
                        .setMessage(stringBuilderFullNameAndCity);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } else if (view == btn_quit) {
            finish();
        }
    }
}