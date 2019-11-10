package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.Cart;
import com.rohan.techcenter.Model.CartMessageModel;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CartBL {
    private String email;
    private String product_name;
    private String product_price;
    private String product_quantity;
    private String product_imagename;
    boolean isSuccess=false;
    boolean isDeleted=false;

    TCAPI tcAPI;

    public static List<Cart> cartList1;

    public CartBL(String email, String product_name, String product_price, String product_quantity, String product_imagename) {
        this.email = email;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_imagename = product_imagename;
    }

    public CartBL(){}

    public boolean addToCart(){
        TCAPI tcAPI= URL.getInstance().create(TCAPI.class);
        Cart cart =new Cart("",email,product_name,product_price,product_quantity,product_imagename,"Cash");
        Call<CartMessageModel> cartModelCall=tcAPI.addToCart(cart);

        try {
            Response<CartMessageModel> cartresponse = cartModelCall.execute();
            if (cartresponse.body().getMessage_success()!= null) {
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

    public List<Cart> getCart(String cartPhone){
        tcAPI= URL.getInstance().create(TCAPI.class);
        Call<List<Cart>> cartList=tcAPI.getCart(cartPhone);

        try {
            Response<List<Cart>> cartResponse = cartList.execute();
            System.out.println("Getting data");
            if (cartResponse.isSuccessful()) {
                cartList1 = cartResponse.body();

                return cartList1;
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return cartList1;
    }

    public boolean deleteFromCart(String id) {
        tcAPI = URL.getInstance().create(TCAPI.class);
        Call<Void> voidCall = tcAPI.deleteCartRow(id);
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
