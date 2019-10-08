package com.rohan.techcenter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rohan.techcenter.BL.ProductBL;
import com.rohan.techcenter.Model.Product;
import com.rohan.techcenter.R;
import com.rohan.techcenter.URL.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_row_product_view,parent,false);
        ProductViewHolder holder=new ProductViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductBL productBL = new ProductBL();
        final Product product=productList.get(position);
        holder.tv_name.setText(product.getProduct_name());
        holder.tv_price.setText(product.getProduct_price());
        //holder.tv_totalRating.setText(productBL.getTotalRating(product.getProduct_name()));

        String url= URL.BASE_URL+product.getProduct_imagename();
        Picasso.get().load(url).into(holder.img_product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView img_product;
        TextView tv_name;
        TextView tv_price;
        TextView tv_totalRating;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            img_product=itemView.findViewById(R.id.sample_productImage);
            tv_name=itemView.findViewById(R.id.sample_productName);
            tv_price=itemView.findViewById(R.id.sample_productPrice);
            tv_totalRating=itemView.findViewById(R.id.sample_productRating);

        }

    }

    public void filterList(List<Product> productList){
        productList = productList;
        notifyDataSetChanged();

    }
}
