package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.myapplication.adapter.Shoplist;
import com.example.myapplication.databinding.FragmentShowBinding;
import com.example.myapplication.models.Product;
import com.example.myapplication.viewModel.ShopViewHolder;

import java.util.List;


public class ShowFragment extends Fragment implements Shoplist.ShopInterface {

    private static final String TAG = "ShowFragment";
    private NavController navController;
    com.example.myapplication.databinding.FragmentShowBinding fragmentShowBinding;
    private Shoplist shopList;

    private ShopViewHolder shopViewHolder;


    public ShowFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentShowBinding=FragmentShowBinding.inflate(inflater,container, false);


        return fragmentShowBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopList=new Shoplist(this);

        fragmentShowBinding.frag.setAdapter(shopList);
        fragmentShowBinding.frag.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));
        fragmentShowBinding.frag.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        shopViewHolder= new ViewModelProvider(requireActivity()).get(ShopViewHolder.class);
        shopViewHolder.getProducts().observe(getViewLifecycleOwner(),
                new Observer<List<Product>>() {
                    @Override
                    public void onChanged(List<Product> products) {
                        shopList.submitList(products);
                    }
                });
         //navController= Navigation.findNavController(view);
    }


        @Override
    public void addItem(Product product) {

    }

    @Override

        public void clickItem(Product product) {
        Log.d(TAG, "clickItem: "+product.toString());
        shopViewHolder.setProduct(product);
        navController.navigate(R.id.action_showFragment_to_secondFRag);



    }
}