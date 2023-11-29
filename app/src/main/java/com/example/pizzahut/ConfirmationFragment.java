package com.example.pizzahut;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pizzahut.databinding.FragmentConfirmationBinding;
import com.example.pizzahut.models.ItemCustomization;
import com.example.pizzahut.models.MenuItemDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ConfirmationFragment extends Fragment {

    private static final String ARG_PARAM_ITEMS_SELECTED = "ARG_PARAM_ITEMS_SELECTED";
    private static final String ARG_PARAM_MENU_ITEM = "ARG_PARAM_MENU_ITEM";

    private MenuItemDetails menuItemDetails;
    private ItemCustomization itemCustomization;

    public static ConfirmationFragment newInstance(ItemCustomization itemCustomization,
                                                   MenuItemDetails menuItemDetails) {
        ConfirmationFragment fragment = new ConfirmationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ITEMS_SELECTED, itemCustomization);
        args.putSerializable(ARG_PARAM_MENU_ITEM, menuItemDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            menuItemDetails = (MenuItemDetails) getArguments().getSerializable(ARG_PARAM_MENU_ITEM);
            itemCustomization = (ItemCustomization) getArguments().getSerializable(ARG_PARAM_ITEMS_SELECTED);
        }
    }
    public ConfirmationFragment() {
        // Required empty public constructor
    }

    FragmentConfirmationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConfirmationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Integer menuImage = Utilities.getMenuImage(menuItemDetails.getItemImage());
        binding.imageViewPizzaConfirmation.setImageResource(menuImage);
        binding.textViewPizzaTittleConfirmation.setText(menuItemDetails.getItemTitle());
        binding.textViewPizzaDescription.setText(menuItemDetails.getItemDescription());
        binding.textViewPizzaContent.setText(itemCustomization.toString());

        double pizzaPrice = itemCustomization.calculatePizzaPrice(itemCustomization);
        double tax = pizzaPrice * 0.1;

        double totalPrice = pizzaPrice + tax;

        binding.textViewTotalAmount.setText(String.format("%.2f", pizzaPrice));
        binding.textViewTotalTax.setText(String.format("%.2f", tax));
        binding.textViewTotalFinalAmount.setText(String.format("%.2f", totalPrice));
        binding.buttonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOrder(totalPrice);
            }
        });
    }

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private void addToOrder(double totalPrice) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("orders").document();

        HashMap<String, Object> data = new HashMap<>();
        data.put("order_id", documentReference.getId());
        data.put("created_at", FieldValue.serverTimestamp());
        data.put("created_by_uid", mAuth.getCurrentUser().getUid());
        data.put("order_price", totalPrice);
        data.put("order_details", itemCustomization);
        data.put("menu_item_details", menuItemDetails);
//        data.put("serving_crust", itemCustomization.getServing_crust());
//        data.put("serving_quantity", itemCustomization.getServing_quantity());
//        data.put("serving_sauce", itemCustomization.getServing_sauce());
//        data.put("toppings", itemCustomization.getToppings());
//        data.put("garlic_dip_quantity", itemCustomization.getGarlic_dip_quantity());
//        data.put("ranch_dip_quantity", itemCustomization.getRanch_dip_quantity());
//        data.put("marinara_sauce_dip_quantity", itemCustomization.getMarinara_sauce_dip_quantity());
//        data.put("specialInstructions", itemCustomization.getSpecialInstructions());
        documentReference.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                }else{
                    Toast.makeText(getActivity(),
                            task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    interface ConfirmationListener{
        void goToCheckout();
    }
}