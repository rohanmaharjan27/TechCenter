package com.rohan.techcenter.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rohan.techcenter.R;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        getSupportActionBar().hide();
    }
}