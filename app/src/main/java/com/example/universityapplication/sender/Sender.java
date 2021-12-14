package com.example.universityapplication.sender;


import android.content.Intent;


import com.example.universityapplication.activities.LoginActivity;
import com.example.universityapplication.activities.TimelineActivity;
import androidx.appcompat.app.AppCompatActivity;

public class Sender  extends  AppCompatActivity {

    public void SendUserToLoginActivity() {

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
