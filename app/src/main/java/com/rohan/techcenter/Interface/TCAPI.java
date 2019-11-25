package com.rohan.techcenter.Interface;

import com.rohan.techcenter.Model.AuthUser;
import com.rohan.techcenter.Model.Cart;
import com.rohan.techcenter.Model.CartMessageModel;
import com.rohan.techcenter.Model.OrderHistory;
import com.rohan.techcenter.Model.Product;
import com.rohan.techcenter.Model.Rating;
import com.rohan.techcenter.Model.Register;
import com.rohan.techcenter.Model.Shop;
import com.rohan.techcenter.Model.TotalRatingModel;
import com.rohan.techcenter.Model.User;
import com.rohan.techcenter.Model.Wishlist;
import com.rohan.techcenter.Model.WishlistMessageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TCAPI {

    @POST("users/register")
    Call<Register> addUser(@Body User user);

    @POST ("users/login")
    Call<AuthUser> getUser(@Body User user);

    @GET("products/")
    Call<List<Product>> getProduct();

    @POST("ratings")
    Call<Register> addRating(@Body Rating rating);

    @POST("ratings/myRating")
    Call<List<Rating>> getMyRating(@Body Rating rating);

    @PUT("ratings/{id}")
    Call<Register> updateRating(@Path("id") String id, @Body Rating rating);

    @GET("ratings/totalrating/{product_name}")
    Call<TotalRatingModel> getTotalRating(@Path("product_name") String productName);

    @POST("carts")
    Call<CartMessageModel> addToCart(@Body Cart cart);

    @GET("carts/{email}")
    Call<List<Cart>> getCart(@Path("email") String email);

    @DELETE("carts/removefromcart/{id}")
    Call<Void> deleteCartRow(@Path("id") String id);

    @POST("orders/multiple")
    Call<CartMessageModel> addOrder(@Body Cart cart);

    @GET("orders/{email}")
    Call<List<OrderHistory>> getOrderHistory(@Path("email") String email);

    @POST("wishlists")
    Call<WishlistMessageModel> addToWishlist(@Body Wishlist wishlist);

    @GET("wishlists/{email}")
    Call<List<Wishlist>> getWishlist(@Path("email") String email);

    @DELETE("wishlists/removefromwishlist/{id}")
    Call<Void> deleteWishlistRow(@Path("id") String id);

    @GET("shops/shop")
    Call<List<Shop>> getShops();

}
