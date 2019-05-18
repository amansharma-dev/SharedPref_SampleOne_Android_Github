package com.example.sharedpref_sampleone_android_github;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int SIGNIN_REQUEST = 1001;
    public static final String MY_PREFS = "myPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_signin:
                Intent intent = new Intent(this,SigninActivity.class);
                startActivityForResult(intent,SIGNIN_REQUEST);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == SIGNIN_REQUEST){
            String email = data.getStringExtra(SigninActivity.EMAIL_KEY);
            Toast.makeText(getApplicationContext(),"EmailId is :- "+email,Toast.LENGTH_LONG).show();

            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS,MODE_PRIVATE).edit();
            editor.putString(SigninActivity.EMAIL_KEY,email);
            editor.apply();
        }
    }
}
