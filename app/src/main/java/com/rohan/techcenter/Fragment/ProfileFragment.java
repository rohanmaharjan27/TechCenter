package com.rohan.techcenter.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rohan.techcenter.Activity.AboutUsActivity;
import com.rohan.techcenter.Activity.OrderHistoryActivity;
import com.rohan.techcenter.Activity.ProfileUpdateActivity;
import com.rohan.techcenter.Activity.WishlistActivity;
import com.rohan.techcenter.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment implements View.OnClickListener{
    CircleImageView pu_userImage;
    TextView tv_fullname,tv_purchases,tv_wishlisted;
    Button btn_wishlist,btn_history,btn_editProfile,btn_aboutUs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile,container,false);

        pu_userImage=view.findViewById(R.id.user_img);
        tv_fullname=view.findViewById(R.id.profile_name);
        tv_purchases=view.findViewById(R.id.tv_purchases);
        tv_wishlisted=view.findViewById(R.id.tv_wishlisted);

        btn_wishlist=view.findViewById(R.id.btn_wishlist);
        btn_wishlist.setOnClickListener(this);

        btn_history=view.findViewById(R.id.btn_viewHistory);
        btn_history.setOnClickListener(this);

        btn_editProfile=view.findViewById(R.id.btn_editProfile);
        btn_editProfile.setOnClickListener(this);

        btn_aboutUs=view.findViewById(R.id.btn_aboutUs);
        btn_aboutUs.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_wishlist:
                Intent intent1=new Intent(getContext(), WishlistActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_viewHistory:
                Intent intent2=new Intent(getContext(), OrderHistoryActivity.class);
                startActivity(intent2);
                break;

            case R.id.btn_editProfile:
                Intent intent3=new Intent(getContext(), ProfileUpdateActivity.class);
                startActivity(intent3);
                break;

            case R.id.btn_aboutUs:
                Intent intent4=new Intent(getContext(), AboutUsActivity.class);
                startActivity(intent4);
                break;

        }
    }
}
