package com.rohan.techcenter.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.rohan.techcenter.R;

import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout ra_firstName,ra_lastName,ra_phone,ra_address,ra_email,ra_password;
    private String ra_userimagename,ra_usertype;
    private Button btnRegister;
    private TextView ra_tvLogin;

    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{7,}" +               //at least 7 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        ra_firstName=findViewById(R.id.ra_firstName);
        ra_lastName=findViewById(R.id.ra_lastName);
        ra_phone=findViewById(R.id.ra_phone);
        ra_address=findViewById(R.id.ra_address);
        ra_email=findViewById(R.id.ra_email);
        ra_password=findViewById(R.id.ra_password);

        ra_userimagename="userimg.jpeg";
        ra_usertype="user";

        btnRegister=findViewById(R.id.ra_btnRegister);
        btnRegister.setOnClickListener(this);

        ra_tvLogin=findViewById(R.id.ra_tvLogin);
        ra_tvLogin.setOnClickListener(this);
    }

    private boolean validateEmail(){
        String emailInput=ra_email.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()){
            ra_email.setError("Field Can't Be Empty!");
            return false;
        }
        else if (Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            ra_email.setError("Please enter a valid email address");
            return false;
        }
        else {
            ra_email.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput=ra_password.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()){
            ra_password.setError("Field Can't Be Empty!");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            ra_password.setError("Password too weak");
            return false;
        }
        else {
            ra_password.setError(null);
            return true;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ra_btnRegister:
                if (validateEmail() && validatePassword()){
                    Toasty.success(RegisterActivity.this,"Registered Successfully!",Toasty.LENGTH_LONG).show();
                }
                break;

            case R.id.ra_tvLogin:
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}