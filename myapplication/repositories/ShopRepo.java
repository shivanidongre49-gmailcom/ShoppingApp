package com.example.myapplication.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {
    private FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
//    //private DatabaseReference  root= FirebaseDatabase.getInstance().getReference("image");
   private DatabaseReference databaseReference=firebaseDatabase.getInstance().getReference();


    //private static final String TAG = ;
    private MutableLiveData<List<Product>> mutableProductList;
    public LiveData<List<Product>> getProducts(){
    if(mutableProductList==null){
        mutableProductList= new MutableLiveData<>();
        loadProducts();

    }
    return mutableProductList;
    }

    private void loadProducts(){
        final List<Product> productList= new ArrayList<>();
       databaseReference.child("prodDetails").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               Iterable<DataSnapshot> children=snapshot.getChildren();
//               GenericTypeIndicator<List<Product>> t = new GenericTypeIndicator<List<Product>>() {};
//               List<Product> messages = snapshot.getValue(t);
               for (DataSnapshot dataSnapshot : children) {
                   Product product=dataSnapshot.getValue(Product.class);
                   productList.add(product);
                   //productList.add(new Product(dataSnapshot.getValue(Product.class));
                   }
               }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               throw error.toException();
           }
       });

//        String link= String.valueOf(root.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    Product product=dataSnapshot.getValue(Product.class);
//                    productList.add(product);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        }));
//
//










        productList.add(new Product(UUID.randomUUID().toString(),"first", 500, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/dustbin.jpg?alt=media&token=65f2aef7-3eb0-488a-ad52-53238fb18eeb"));





//        productList.add(new Product(UUID.randomUUID().toString(),"secondProd", 300, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/shoes.jpg?alt=media&token=e553e4cd-6a63-47f8-a75b-be0e3c84a532"));
//        productList.add(new Product(UUID.randomUUID().toString(),"third", 300, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/lights.jpg?alt=media&token=f50cbca1-21d2-4387-b4fa-c0ed811b96ff"));
//        productList.add(new Product(UUID.randomUUID().toString(),"fourth", 400, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/cushion.jpg?alt=media&token=0313371f-1e24-4e8a-8ac3-3d14157bb615"));
//        productList.add(new Product(UUID.randomUUID().toString(),"iMac 21", 1299, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/dustbin.jpg?alt=media&token=65f2aef7-3eb0-488a-ad52-53238fb18eeb"));
//        productList.add(new Product(UUID.randomUUID().toString(),"iMac 21", 1299, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/dustbin.jpg?alt=media&token=65f2aef7-3eb0-488a-ad52-53238fb18eeb"));
//        productList.add(new Product(UUID.randomUUID().toString(),"iMac 21", 1299, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/dustbin.jpg?alt=media&token=65f2aef7-3eb0-488a-ad52-53238fb18eeb"));
//        productList.add(new Product(UUID.randomUUID().toString(),"iMac 21", 1299, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/dustbin.jpg?alt=media&token=65f2aef7-3eb0-488a-ad52-53238fb18eeb"));
//        productList.add(new Product(UUID.randomUUID().toString(),"iMac 21", 1299, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/dustbin.jpg?alt=media&token=65f2aef7-3eb0-488a-ad52-53238fb18eeb"));
//        productList.add(new Product(UUID.randomUUID().toString(),"iMac 21", 1299, true,"https://firebasestorage.googleapis.com/v0/b/my-application-android1.appspot.com/o/dustbin.jpg?alt=media&token=65f2aef7-3eb0-488a-ad52-53238fb18eeb"));


        mutableProductList.setValue(productList);




    }
}
