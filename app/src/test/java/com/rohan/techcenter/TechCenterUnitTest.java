package com.rohan.techcenter;

import com.rohan.techcenter.BL.CartBL;
import com.rohan.techcenter.BL.LoginBL;
import com.rohan.techcenter.BL.ProductBL;
import com.rohan.techcenter.BL.RatingBL;
import com.rohan.techcenter.BL.RegisterBL;
import com.rohan.techcenter.Model.Product;
import com.rohan.techcenter.Model.Rating;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TechCenterUnitTest {

    //REGISTRATION TEST
    @Test
    public void registerTest() {
        RegisterBL registerBL = new RegisterBL("Test FName", "Test LName", "9841267891", "Test Address", "unittest@gmail.com","*test123","userimg.jpeg","user");
        boolean result = registerBL.addUser();
        assertEquals(true, result);
    }


    //LOGIN TEST
    @Test
    public void loginTest() {
        LoginBL loginBL = new LoginBL("rohandai123@gmail.com", "*rohan123");
        String result = loginBL.checkUser();
        assertNotNull(result);
    }


    //FAILED LOGIN TEST
    @Test
    public void failedLoginTest() {
        LoginBL loginBL = new LoginBL("rohandai123@gmail.com", "wrong");
        String result = loginBL.checkUser();
        assertEquals("Invalid",result);
    }

    //VIEW PRODUCTS TEST
    @Test
    public void viewProductsTest()
    {
        ProductBL productBL = new ProductBL();
        List<Product> result =productBL.getProduct();
        String productName = result.get(0).getProduct_name();
        assertEquals("Razer DeathAdder",productName);

    }


    // ADD TO CART TEST
    @Test
    public void  addItemToCartTest()
    {
        CartBL cartBL = new CartBL("rohandai123@gmail.com","Razer DeathAdder","70","2","140");
        boolean result = cartBL.addToCart();
        assertEquals(true,result);
    }


    // RATE PRODUCTS TEST
    @Test
    public void ratingTest()
    {
        Rating rating = new Rating("rohandai123@gmail.com","Razer DeathAdder","5.0");
        RatingBL  ratingBL = new RatingBL();
        String result = ratingBL.addRating(rating);
        assertEquals("Success",result);
    }

    // VIEW RATING TEST
    @Test
    public void viewRatingTest()
    {
        Rating rating = new Rating("rohandai123@gmail.com","Razer DeathAdder");
        RatingBL ratingBL = new RatingBL();
        String result = ratingBL.getMyRating(rating).get(0).getRating();
        assertEquals("5.0",result);
    }

    // UPDATE RATING TEST
    @Test
    public void updateRating(){
        String id="5d8369c908be163940ed2d75";
        Float newRating = 4.5f;
        Rating rating = new Rating(id,newRating);
        RatingBL ratingBL = new RatingBL();

        String message=ratingBL.updateRating(id,rating);
        assertThat(message,is("Rating Updated Successfully"));
    }

    // ADD TO CART TEST
    @Test
    public void  addCartTest()
    {
        CartBL cartBL = new CartBL("9849329276","momo","150","1","momo.jpg");
        boolean result = cartBL.addToCart();
        assertEquals(true,result);
    }

    // GET CATEGORY TEST
    @Test
    public void getCategoryTest(){
        ProductBL productBL = new ProductBL();
        int category = productBL.getCategory().size();
        assertNotEquals(0,category);
    }

//    // Cart
//    @Test
//    public void  addItemToCartTest()
//    {
//        CartBL cartBL = new CartBL("userid: 123","productid: fd1","quantity: 2","price: 200","total: 400");
//        boolean result = cartBL.addToCart();
//        assertEquals(true,result);
//    }
}
