package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.Product;

public class Shoplist extends ListAdapter<Product, Shoplist.ShopViewHolder>  {
    ShopInterface shopInterface;
    public Shoplist(ShopInterface shopInterface ) {
        super(Product.itemCallback);
        this.shopInterface=shopInterface;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        com.example.myapplication.databinding.EachrowBinding eachrowBinding= com.example.myapplication.databinding.EachrowBinding.inflate(layoutInflater,parent,false);
        eachrowBinding.setShopInterface(shopInterface);
        return  new ShopViewHolder(eachrowBinding);

    }








    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Product product=getItem(position);
        holder.eachrowBinding.setProduct(product);
    }

    class ShopViewHolder extends RecyclerView.ViewHolder{

        com.example.myapplication.databinding.EachrowBinding eachrowBinding;
        public ShopViewHolder(com.example.myapplication.databinding.EachrowBinding binding) {
            super(binding.getRoot());
            this.eachrowBinding=binding;
//            this.eachrowBinding.getRoot().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }
    public interface ShopInterface{
        void addItem(Product product);
        void clickItem(Product product);
    }
}
