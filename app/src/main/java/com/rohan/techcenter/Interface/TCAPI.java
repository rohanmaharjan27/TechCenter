package com.rohan.techcenter.Interface;

import com.rohan.techcenter.Model.AuthUser;
import com.rohan.techcenter.Model.Product;
import com.rohan.techcenter.Model.Register;
import com.rohan.techcenter.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TCAPI {

    @POST("users/register")
    Call<Register> addUser(@Body User user);

    @POST ("users/login")
    Call<AuthUser> getUser(@Body User user);

    @GET("products/product")
    Call<List<Product>> getProduct();

}
