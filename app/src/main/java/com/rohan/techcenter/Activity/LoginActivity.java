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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout loginEmail;
    private TextInputLayout loginPassword;
    private Button btnLogin;
    private TextView la_tvSignUp;

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
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        loginEmail=findViewById(R.id.la_email);
        loginPassword=findViewById(R.id.la_password);

        btnLogin=findViewById(R.id.la_btnLogin);
        btnLogin.setOnClickListener(this);

        la_tvSignUp=findViewById(R.id.la_tvSignUp);
        la_tvSignUp.setOnClickListener(this);


    }

    private boolean validateEmail(){
        String emailInput=loginEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()){
            loginEmail.setError("Please enter your email");
            return false;
        }
        else if (Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            loginEmail.setError("Please enter a valid email address");
            return false;
        }
        else {
            loginEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String passwordInput=loginPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()){
            loginPassword.setError("Please enter your password");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            loginPassword.setError("Password too weak");
            return false;
        }
        else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.la_btnLogin:
                if (validateEmail() && validatePassword()){
                    Toasty.success(LoginActivity.this,"Login Success!",Toasty.LENGTH_LONG).show();
                }
                break;

            case R.id.la_tvSignUp:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
}
}
