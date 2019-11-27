package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.Category;
import com.rohan.techcenter.Model.Product;
import com.rohan.techcenter.Model.Rating;
import com.rohan.techcenter.Model.TotalRatingModel;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;
import java.util.ArrayList;
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

    public List<Product> getProductByCategory(String category){
        tcAPI= URL.getInstance().create(TCAPI.class);
        Call<List<Product>> productList=tcAPI.getProductByCategory(category);

        try {
            Response<List<Product>> productResponse = productList.execute();
            System.out.println("Getting data");
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

    public List<Category> getCategory(){
        tcAPI= URL.getInstance().create(TCAPI.class);
        List<Category> categoryList1 = new ArrayList<>();
        Call<List<Category>> categoryList=tcAPI.getCategories();

        try {
            Response<List<Category>> categoryResponse = categoryList.execute();
            System.out.println("Getting data");
            if (categoryResponse.isSuccessful()) {
                categoryList1 = categoryResponse.body();

                return categoryList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return categoryList1;
    }



}
