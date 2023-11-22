package com.example.pizzahut;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzahut.databinding.FragmentMenuBinding;
import com.example.pizzahut.databinding.MenuListItemBinding;
import com.example.pizzahut.models.MenuItemDetails;

import java.util.ArrayList;


public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }

    ArrayList<MenuItemDetails> menuItemDetailsList;

    FragmentMenuBinding binding;

    MenuAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cart_item) {
            mListener.goToConfirmationFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        menuItemDetailsList = Utilities.getMenuList();

        adapter = new MenuAdapter();
        binding.recyclerViewMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewMenu.setAdapter(adapter);

    }

    class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

        @NonNull
        @Override
        public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MenuListItemBinding binding = MenuListItemBinding.inflate(getLayoutInflater(),
                    parent, false);
            return new MenuViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
            MenuItemDetails menuItemDetails = menuItemDetailsList.get(position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.goToDetailsFragment(menuItemDetails);
                }
            });
            holder.setUpUI(menuItemDetails);

        }

        @Override
        public int getItemCount() {
            return menuItemDetailsList.size();
        }

        class MenuViewHolder extends RecyclerView.ViewHolder {
            MenuListItemBinding mBinding;
            public MenuViewHolder(@NonNull MenuListItemBinding itemBinding) {
                super(itemBinding.getRoot());
                mBinding = itemBinding;
            }

            public void setUpUI(MenuItemDetails menuItem){
                Integer imageIndex = menuItem.getItemImage();
                mBinding.imageViewPizza.setImageResource(Utilities.getMenuImage(imageIndex));
                mBinding.textViewDescription.setText(menuItem.getItemDescription());
                mBinding.textViewTittle.setText(menuItem.getItemTitle());
            }
        }
    }


    MenuFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (MenuFragmentListener) context;

    }

    interface MenuFragmentListener{
        void goToDetailsFragment(MenuItemDetails menuItemDetails);
        void goToConfirmationFragment();
        void logout();
    }
}