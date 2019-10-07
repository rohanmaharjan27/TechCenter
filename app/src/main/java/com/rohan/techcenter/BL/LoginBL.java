package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.AuthUser;
import com.rohan.techcenter.Model.User;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBL {
    private String email;
    private String password;
    private String message_1;
    String token;
    public static String sp_email;

    public LoginBL(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String checkUser(){
        TCAPI tcAPI= URL.getInstance().create(TCAPI.class);
        User user=new User("","","","",email,password,"","");
        Call<AuthUser> usersCall=tcAPI.getUser(user);

        try{
            Response<AuthUser> loginresponse=usersCall.execute();
            if(loginresponse.body().getToken()!=null){
                token = loginresponse.body().getToken();
                return token;
            }
            else if (loginresponse.body().getMessage()!=null){
                message_1=loginresponse.body().getMessage();
                return message_1;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "error";
    }
}
