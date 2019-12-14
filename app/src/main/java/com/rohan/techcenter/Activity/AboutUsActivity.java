package com.rohan.techcenter.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.rohan.techcenter.R;

public class AboutUsActivity extends AppCompatActivity {
    TextView tv_aboutUs_text;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        actionBar=getSupportActionBar();
        actionBar.setTitle("About Us");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3d5afe")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_aboutUs_text=findViewById(R.id.tv_aboutUs_text);

        tv_aboutUs_text.setText("TechCenter is user-friendly android application for purchasing computer products"+"\r\n"+"\r\n" +
                "Developer: Rohan Maharjan"+"\r\n"+"\r\n" +
                "This application has been developed to understand aspects of mobile shopping apps and their impact on customer behaviour."+"\r\n"+"\r\n"
        );
    }

    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }
}
