package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.Shop;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ShopBL {
    TCAPI tcAPI;
    public static List<Shop> shopList1;

    public List<Shop>  getShop(){
        tcAPI= URL.getInstance().create(TCAPI.class);
        Call<List<Shop>> shopList=tcAPI.getShops();

        try {
            Response<List<Shop>> shopResponse = shopList.execute();
            System.out.println("Getting data");
            if (shopResponse.isSuccessful()) {
                shopList1 = shopResponse.body();

                return shopList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return shopList1;
    }
}
