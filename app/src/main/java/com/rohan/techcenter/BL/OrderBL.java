package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.Cart;
import com.rohan.techcenter.Model.CartMessageModel;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class OrderBL {
    boolean isSuccess=false;
    public OrderBL(){}

    public boolean addOrder(Cart cart){
        TCAPI tcAPI= URL.getInstance().create(TCAPI.class);
        Call<CartMessageModel> cartModelCall=tcAPI.addOrder(cart);

        try {
            Response<CartMessageModel> cartresponse = cartModelCall.execute();
            if (cartresponse.body().getMessage()!= null) {
                isSuccess=true;
            }
            else{
                isSuccess=false;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
