package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentSecondFRagBinding;
import com.example.myapplication.viewModel.ShopViewHolder;


public class secondFRag extends Fragment {
FragmentSecondFRagBinding fragmentSecondFRagBinding;
ShopViewHolder shopViewHolder;
    public secondFRag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentSecondFRagBinding=FragmentSecondFRagBinding.inflate(inflater, container,false);
        return  fragmentSecondFRagBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopViewHolder= new ViewModelProvider(requireActivity()).get(ShopViewHolder.class);
        fragmentSecondFRagBinding.setShopViewHolder(shopViewHolder);
    }
}