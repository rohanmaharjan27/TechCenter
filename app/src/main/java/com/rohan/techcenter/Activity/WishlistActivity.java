package com.rohan.techcenter.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import com.rohan.techcenter.Adapter.WishlistAdapter;
import com.rohan.techcenter.BL.WishlistBL;
import com.rohan.techcenter.Model.Wishlist;
import com.rohan.techcenter.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class WishlistActivity extends AppCompatActivity {
    ActionBar actionBar;
    RecyclerView recyclerView;
    public static WishlistAdapter wishlistAdapter;;
    SharedPreferences preferences;
    List<Wishlist> wishList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Wishlist");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3d5afe")));
        actionBar.setDisplayHomeAsUpEnabled(true);

        recyclerView= findViewById(R.id.recyclerViewWishlist);
        getWishlist();
        wishlistAdapter = new WishlistAdapter(this,wishList);
        recyclerView.setAdapter(wishlistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(WishlistActivity.this));


    }

    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    public void getWishlist(){

        preferences = WishlistActivity.this.getSharedPreferences("loginPreference", Context.MODE_PRIVATE);
        String wishlistEmail = preferences.getString("email", "");

        WishlistBL wishlistBL=new WishlistBL();
        StrictMode();

        if( wishlistBL.getWishlist(wishlistEmail) != null) {
            wishList = wishlistBL.getWishlist(wishlistEmail);
        }
        else {
            Toasty.error(WishlistActivity.this,"Error while displaying wishlist",Toasty.LENGTH_LONG).show();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        this.finish();
        return true;
    }

}
