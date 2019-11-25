package com.rohan.techcenter.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rohan.techcenter.Adapter.ShopAdapter;
import com.rohan.techcenter.Adapter.SliderAdapter;
import com.rohan.techcenter.BL.ShopBL;
import com.rohan.techcenter.Model.Shop;
import com.rohan.techcenter.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView_shops;
    List<Shop> shopList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_shops=view.findViewById(R.id.recyclerViewShops);
        SliderView sliderView = view.findViewById(R.id.imageSlider);

        SliderAdapter adapter= new SliderAdapter(getContext());

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();



        getShops();
        recyclerView_shops.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView_shops.setAdapter(new ShopAdapter(getContext(),shopList));
        return view;
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void getShops(){
        ShopBL shopBL=new ShopBL();
        StrictMode();

        if(shopBL.getShop() != null){
            shopList = shopBL.getShop();
        }
        else {
            Toasty.error(getActivity(),"Error while displaying shops",Toasty.LENGTH_LONG).show();
        }
    }

}
