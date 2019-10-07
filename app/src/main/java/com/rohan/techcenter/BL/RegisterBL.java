package com.rohan.techcenter.BL;

import com.rohan.techcenter.Interface.TCAPI;
import com.rohan.techcenter.Model.Register;
import com.rohan.techcenter.Model.User;
import com.rohan.techcenter.URL.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterBL {
    private String firstname,lastname,phone,address,email,password,userimagename,usertype;
    boolean isSuccess=false;

    public RegisterBL(String firstname, String lastname, String phone, String address, String email, String password, String userimagename, String usertype) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.userimagename = userimagename;
        this.usertype = usertype;
    }

    public boolean addUser(){
        TCAPI tcAPI= URL.getInstance().create(TCAPI.class);
        User user=new User(firstname,lastname,phone,address,email,password,userimagename,usertype);
        Call<Register> userCall=tcAPI.addUser(user);

        try {
            Response<Register> regresponse = userCall.execute();
            if (regresponse.body().getMessage_success()!= null) {
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

}
