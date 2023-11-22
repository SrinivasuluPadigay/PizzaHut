package com.example.pizzahut;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.pizzahut.databinding.FragmentSelectSizeBinding;

import java.util.ArrayList;


public class SelectSizeFragment extends Fragment {


    public SelectSizeFragment() {
    }

    FragmentSelectSizeBinding binding;

    ArrayList<String> sizeList;

    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectSizeBinding.inflate(inflater, container, false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sizeList = Utilities.getSizeList();
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, sizeList);
        binding.listView.setAdapter(arrayAdapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String size = sizeList.get(position);
                mListener.sendSize(size);
            }
        });
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.sendSize(null);
            }
        });

    }

    SelectSizeListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SelectSizeListener) context;
    }

    interface SelectSizeListener {
        void sendSize(String selectedSize);
    }
}