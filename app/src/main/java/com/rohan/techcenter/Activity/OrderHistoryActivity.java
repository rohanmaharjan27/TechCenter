package com.rohan.techcenter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.rohan.techcenter.Adapter.OrderHistoryAdapter;
import com.rohan.techcenter.BL.OrderHistoryBL;
import com.rohan.techcenter.Model.OrderHistory;
import com.rohan.techcenter.R;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class OrderHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<OrderHistory> orderHistoryList = new ArrayList<>();
    public static OrderHistoryAdapter orderHistoryAdapter;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        recyclerView= findViewById(R.id.recyclerViewOrderHistory);
        getOrderHistory();
        orderHistoryAdapter= new OrderHistoryAdapter(OrderHistoryActivity.this,orderHistoryList);
        recyclerView.setAdapter(orderHistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void getOrderHistory(){
        preferences = OrderHistoryActivity.this.getSharedPreferences("loginPreference", Context.MODE_PRIVATE);
        String userEmail = preferences.getString("email", "");

        OrderHistoryBL orderHistoryBL=new OrderHistoryBL();
        StrictMode();

        if(orderHistoryBL.getOrderHistory(userEmail) != null){
            orderHistoryList = orderHistoryBL.getOrderHistory(userEmail);
        }
        else {
            Toasty.error(this,"Error while displaying order history", Toasty.LENGTH_LONG).show();
        }
    }
}
