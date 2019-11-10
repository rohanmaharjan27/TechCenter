package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.OrderHistory;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class OrderHistoryBL {
    TCAPI tcAPI;
    public static List<OrderHistory> orderHistoryList1;

    public List<OrderHistory>  getOrderHistory(String userEmail){
        tcAPI= URL.getInstance().create(TCAPI.class);
        Call<List<OrderHistory>> orderHistoryList=tcAPI.getOrderHistory(userEmail);

        try {
            Response<List<OrderHistory>> orderHistoryResponse = orderHistoryList.execute();
            System.out.println("Getting data");
            if (orderHistoryResponse.isSuccessful()) {
                orderHistoryList1 = orderHistoryResponse.body();

                return orderHistoryList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return orderHistoryList1;
    }

}
