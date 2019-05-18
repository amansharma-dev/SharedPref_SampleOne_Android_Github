package com.example.sharedpref_sampleone_android_github;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SigninActivity extends AppCompatActivity {


    public static final String EMAIL_KEY = "email_key";

    private EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        email = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MY_PREFS,MODE_PRIVATE);

        String userEmail = sharedPreferences.getString(EMAIL_KEY,"");


        if(!TextUtils.isEmpty(userEmail)){

            email.setText(userEmail);

        }
    }

    public void signIn(View view) {

        String user_email = email.getText().toString();
        String user_password = password.getText().toString();

        if(TextUtils.isEmpty(user_email) || !isEmailValid(user_email)){

            Snackbar.make(view,"Enter Valid Email Id", Snackbar.LENGTH_SHORT).setAction("ok",null).show();
        }
        else if(TextUtils.isEmpty(user_password) || !isPasswordValid(user_password)){

            Snackbar.make(view,"Enter Valid Password", Snackbar.LENGTH_SHORT).setAction("ok",null).show();
        }
        else{
            getIntent().putExtra(EMAIL_KEY,user_email);
            setResult(RESULT_OK,getIntent());
            finish();
        }

    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length()>4;
    }
}
