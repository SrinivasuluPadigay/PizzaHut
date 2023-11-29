package com.example.pizzahut;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzahut.databinding.FragmentOrderHistoryBinding;
import com.example.pizzahut.databinding.OrderHistoryListItemBinding;
import com.example.pizzahut.models.OrderDetails;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class OrderHistoryFragment extends Fragment {

    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    FragmentOrderHistoryBinding binding;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayList<OrderDetails> orderDetailsList = new ArrayList<>();
    ListenerRegistration listenerRegistration;
    OrderHistoryAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new OrderHistoryAdapter();
        binding.recyclerViewOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewOrder.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        listenerRegistration =  db.collection("orders")
                .whereEqualTo("created_by_uid", FirebaseAuth.getInstance().getUid())
                .orderBy("created_at", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            Log.w("demo", "onEvent: ", error);
                            return;
                        }
                        orderDetailsList.clear();
                        for (QueryDocumentSnapshot document : value){
                            OrderDetails orderDetails = document.toObject(OrderDetails.class);
                            orderDetailsList.add(orderDetails);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }


    class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder>{

        @NonNull
        @Override
        public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            OrderHistoryListItemBinding itemBinding = OrderHistoryListItemBinding.
                    inflate(getLayoutInflater(), parent, false);

            return new OrderViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
            OrderDetails orderDetails = orderDetailsList.get(position);
            holder.setUpUI(orderDetails);
        }

        @Override
        public int getItemCount() {
            return orderDetailsList.size();
        }

        class OrderViewHolder extends RecyclerView.ViewHolder{
            OrderHistoryListItemBinding itemBinding;
            OrderDetails mOrderDetails;
            public OrderViewHolder(@NonNull OrderHistoryListItemBinding orderHistoryListItemBinding) {
                super(orderHistoryListItemBinding.getRoot());
                itemBinding = orderHistoryListItemBinding;
            }

            public void setUpUI(OrderDetails orderDetails){
                if (orderDetails != null & orderDetails.getMenu_item_details() != null) {
                    Integer itemImage = orderDetails.getMenu_item_details().getItemImage();
                    itemBinding.imageOrderPizza.setImageResource(Utilities.getMenuImage(itemImage));
                    itemBinding.textViewOrderHeading.setText("Order ID :\n" + orderDetails.getOrder_id());

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                    Timestamp timestamp = orderDetails.getCreated_at();

                    Date date = timestamp.toDate();

                    String formattedDate = sdf.format(date);
                    itemBinding.textViewOrderTime.setText(formattedDate);
                    itemBinding.textViewOrderPrice.setText(String.format("%.2f", orderDetails.getOrder_price()) + " $");
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (listenerRegistration!=null){
            listenerRegistration.remove();
        }
    }

    orderHistoryListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (orderHistoryListener) context;
    }

    interface orderHistoryListener{
        void goToMenuFragment();
        void logout();
    }
}