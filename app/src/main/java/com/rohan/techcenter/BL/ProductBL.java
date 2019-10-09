package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.Product;
import com.rohan.techcenter.Model.Rating;
import com.rohan.techcenter.Model.TotalRatingModel;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductBL {
    TCAPI tcAPI;
    public static List<Product> productList1;
    List<Rating> ratingList;


    public List<Product>  getProduct(){
        tcAPI= URL.getInstance().create(TCAPI.class);
        Call<List<Product>> productList=tcAPI.getProduct();

        try {
            Response<List<Product>> productResponse = productList.execute();
            if (productResponse.isSuccessful()) {
                productList1 = productResponse.body();

                return productList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return productList1;
    }

    public String getTotalRating(String productName){
        tcAPI= URL.getInstance().create(TCAPI.class);
        Call<TotalRatingModel> productList=tcAPI.getTotalRating(productName);

        try {
            Response<TotalRatingModel> productResponse = productList.execute();
            if (productResponse.isSuccessful()) {
                ratingList = productResponse.body().getRatings();
                int count = productResponse.body().getCount();
                float total = 0.0f;
                for (Rating rating:ratingList) {
                    total += Float.parseFloat(rating.getRating());
                }
                float rating = total/count;
                return ""+rating;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return "0.0";
    }

}
