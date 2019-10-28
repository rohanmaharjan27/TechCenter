package com.rohan.techcenter.Interface;

import com.rohan.techcenter.Model.AuthUser;
import com.rohan.techcenter.Model.Product;
import com.rohan.techcenter.Model.Rating;
import com.rohan.techcenter.Model.Register;
import com.rohan.techcenter.Model.TotalRatingModel;
import com.rohan.techcenter.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TCAPI {

    @POST("users/register")
    Call<Register> addUser(@Body User user);

    @POST ("users/login")
    Call<AuthUser> getUser(@Body User user);

    @GET("products/products")
    Call<List<Product>> getProduct();

    @POST("ratings")
    Call<Register> addRating(@Body Rating rating);

    @POST("ratings/myRating")
    Call<List<Rating>> getMyRating(@Body Rating rating);

    @PUT("ratings/{id}")
    Call<Register> updateRating(@Path("id") String id, @Body Rating rating);

    @GET("ratings/totalrating/{product_name}")
    Call<TotalRatingModel> getTotalRating(@Path("product_name") String productName);

}
