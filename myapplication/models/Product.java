package com.example.myapplication.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

public class Product {
    private  String id;
    private  String name;
    private  double price;
    private boolean isAvaible;
    private String imageurl;

    public Product(){}
    public Product(String id, String name, double price, boolean isAvaible, String imageurl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvaible = isAvaible;
        this.imageurl = imageurl;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvaible() {
        return isAvaible;
    }

    public void setAvaible(boolean avaible) {
        isAvaible = avaible;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvaible +
                ", imageUrl='" + imageurl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                isAvaible() == product.isAvaible() &&
                getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                getImageurl().equals(product.getImageurl());
    }


    public static DiffUtil.ItemCallback<Product> itemCallback=new DiffUtil.ItemCallback<Product>() {
         @Override
         public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
             return oldItem.getId().equals(newItem.getId());
         }

         @Override
         public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
             return oldItem.equals(newItem);
         }
     };
    @BindingAdapter("android:productImage")
    public  static void loadImage(ImageView imageView, String imageurl){
        Glide.with(imageView)
                .load(imageurl)
                .fitCenter()
                .into(imageView);

    }
}
