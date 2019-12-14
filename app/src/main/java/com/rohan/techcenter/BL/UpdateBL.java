package com.rohan.techcenter.BL;

import android.util.Log;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.User;
import com.rohan.techcenter.Model.UserImg;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;

public class UpdateBL {
    MultipartBody.Part image;
    String email;
    User user;
    String imageName;
    TCAPI tcAPI = URL.getInstance().create(TCAPI.class);

    public UpdateBL(MultipartBody.Part image) {
        this.image = image;
    }

    public UpdateBL(String email) {
        this.email = email;
    }

    public UpdateBL(User user) {
        this.user = user;
    }

    public UpdateBL(String email, User user) {
        this.email = email;
        this.user = user;
    }

    public String uploadImage(){
        Call<UserImg> itemCall = tcAPI.uploadImage(image);
        try {
            Response<UserImg> imgResponse = itemCall.execute();
            imageName = imgResponse.body().getUserImageName();
        } catch (IOException e) {
            Log.e("Error is:", e.getMessage());

            e.printStackTrace();
        }
        return imageName;
    }

    public boolean updateUser(){
        Call<Boolean> updateUserCall = tcAPI.updateUser(email,user);
        try{
            Response<Boolean> updateResponse = updateUserCall.execute();
            return  updateResponse.body().booleanValue();
        }catch (IOException e){
            Log.e("Error",e.getMessage());
        }
        return false;
    }
}
