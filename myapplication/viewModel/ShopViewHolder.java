package com.example.myapplication.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.Product;
import com.example.myapplication.repositories.ShopRepo;

import java.util.List;

public class ShopViewHolder extends ViewModel {
    ShopRepo shopRepo=new ShopRepo();
    MutableLiveData<Product> mutableProduct=new MutableLiveData<>();

    public LiveData<List<Product>> getProducts(){
        return shopRepo.getProducts();



    }
    public void setProduct(Product product){
        mutableProduct.setValue(product);
    }
    public LiveData<Product> getProduct(){
        return  mutableProduct;
    }
}
