package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.Wishlist;
import com.rohan.techcenter.Model.WishlistMessageModel;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class WishlistBL {
    private String email;
    private String product_name;
    private String product_price;
    private String product_category;
    private String product_rating;
    private String date_added;
    private String product_imagename;
    boolean isSuccess=false;
    boolean isDeleted=false;

    TCAPI tcAPI;

    public static List<Wishlist> wishlist1;

    public WishlistBL(String email, String product_name, String product_price, String product_category, String product_rating, String date_added, String product_imagename) {
        this.email = email;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_category = product_category;
        this.product_rating = product_rating;
        this.date_added = date_added;
        this.product_imagename = product_imagename;
    }

    public WishlistBL(){}

    public boolean addToWishlist(){
        TCAPI tcAPI= URL.getInstance().create(TCAPI.class);
        Wishlist wishlist =new Wishlist("",email,product_name,product_price,product_category,product_rating,date_added,product_imagename);
        Call<WishlistMessageModel> wishlistModelCall=tcAPI.addToWishlist(wishlist);

        try {
            Response<WishlistMessageModel> wishlistResponse = wishlistModelCall.execute();
            if (wishlistResponse.body().getMessage_success()!= null) {
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

    public List<Wishlist> getWishlist(String wishlistEmail){
        tcAPI= URL.getInstance().create(TCAPI.class);
        Call<List<Wishlist>> wishList=tcAPI.getWishlist(wishlistEmail);

        try {
            Response<List<Wishlist>> wishlistResponse = wishList.execute();
            System.out.println("Getting data");
            if (wishlistResponse.isSuccessful()) {
                wishlist1 = wishlistResponse.body();

                return wishlist1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return wishlist1;
    }

    public boolean deleteFromWishlist(String id) {
        tcAPI = URL.getInstance().create(TCAPI.class);
        Call<Void> voidCall = tcAPI.deleteWishlistRow(id);
        try {
            Response<Void> deleteResponse = voidCall.execute();
            if (deleteResponse.isSuccessful()) {
                isDeleted = true;
            } else {
                isDeleted = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
}
