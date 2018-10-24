package com.example.ariad.rainesair;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText newPassword, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPassword = (EditText)findViewById(R.id.changepassword_new);
        confirmPassword = (EditText)findViewById(R.id.changepassword_confirm);

        newPassword.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                newPassword.setFocusable(true);
                newPassword.setFocusableInTouchMode(true);

                return false;
            }
        });

        confirmPassword.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                confirmPassword.setFocusable(true);
                confirmPassword.setFocusableInTouchMode(true);

                return false;
            }
        });


    }
}
