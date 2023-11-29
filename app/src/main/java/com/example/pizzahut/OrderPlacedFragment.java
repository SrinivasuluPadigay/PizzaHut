package com.example.pizzahut;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzahut.databinding.FragmentOrderPlacedBinding;
import com.example.pizzahut.models.ItemCustomization;
import com.example.pizzahut.models.MenuItemDetails;


public class OrderPlacedFragment extends Fragment {

    private static final String ARG_PARAM_ITEMS_SELECTED = "ARG_PARAM_ITEMS_SELECTED";
    private static final String ARG_PARAM_MENU_ITEM = "ARG_PARAM_MENU_ITEM";
    private static final String ARG_PARAM_PIZZA_PRICE = "ARG_PARAM_PIZZA_PRICE";
    private static final String ARG_PARAM_ORDER_ID = "ARG_PARAM_ORDER_ID";

    private MenuItemDetails menuItemDetails;
    private ItemCustomization itemCustomization;
    private double pizzaPrice;
    private String orderId;

    public OrderPlacedFragment() {
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.home_item) {
            mListener.goToMenuFragment();
            return true;
        } else if(item.getItemId() == R.id.logout_item){
            mListener.logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    public static OrderPlacedFragment newInstance(ItemCustomization itemCustomization,
                                                  MenuItemDetails menuItemDetails, double pizzaPrice,
                                                  String orderId) {
        OrderPlacedFragment fragment = new OrderPlacedFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ITEMS_SELECTED, itemCustomization);
        args.putSerializable(ARG_PARAM_MENU_ITEM, menuItemDetails);
        args.putDouble(ARG_PARAM_PIZZA_PRICE, pizzaPrice);
        args.putString(ARG_PARAM_ORDER_ID, orderId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            menuItemDetails = (MenuItemDetails) getArguments().getSerializable(ARG_PARAM_MENU_ITEM);
            itemCustomization = (ItemCustomization) getArguments().getSerializable(ARG_PARAM_ITEMS_SELECTED);
            pizzaPrice = getArguments().getDouble(ARG_PARAM_PIZZA_PRICE);
            orderId = getArguments().getString(ARG_PARAM_ORDER_ID);
        }
    }

    FragmentOrderPlacedBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderPlacedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textViewOrderId.setText("Order ID:" + orderId);

        double tax = pizzaPrice * 0.1;
        double totalPrice = pizzaPrice + tax;
        binding.textViewTotalAmount.setText(String.format("%.2f", pizzaPrice) + " $");
        binding.textViewTotalTax.setText(String.format("%.2f", tax) + " $");
        binding.textViewTotalFinalAmount.setText(String.format("%.2f", totalPrice) + " $");
    }

    OrderPlacedListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OrderPlacedListener) context;
    }

    interface OrderPlacedListener{
        void goToMenuFragment();
        void logout();
    }
}