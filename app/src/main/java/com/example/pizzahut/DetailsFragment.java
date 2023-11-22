package com.example.pizzahut;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzahut.databinding.FragmentDetailsBinding;
import com.example.pizzahut.databinding.SauceListItemBinding;
import com.example.pizzahut.models.MenuItemDetails;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {
    private static final String ARG_PARAM_MENU_ITEM = "ARG_PARAM_MENU_ITEM";

    private MenuItemDetails menuItemDetails;

    private ArrayList<String> sauceList = new ArrayList<>();
    private String selectedCrust = null;
    private String selectedSize = null;
    private Integer selectedQuantity = 0;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public void setSelectedCrust(String selectedCrust) {
        this.selectedCrust = selectedCrust;
    }

    public void setSelectedSize(String selectedSize) {
        this.selectedSize = selectedSize;
    }

    public static DetailsFragment newInstance(MenuItemDetails menuItemDetails) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_MENU_ITEM, menuItemDetails);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            menuItemDetails = (MenuItemDetails) getArguments().getSerializable(ARG_PARAM_MENU_ITEM);
        }
    }

    FragmentDetailsBinding binding;

    SauceAdapter sauceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sauceAdapter = new SauceAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(sauceAdapter);

        spinnerSetUp();
        if (selectedCrust == null){
            binding.textViewCrustSizeSelected.setText("select <>");
        }else {
            binding.textViewCrustSizeSelected.setText(selectedCrust);
        }

        if (selectedSize == null){
            binding.textViewPizzaSizeSelected.setText("select <>");
        }else {
            binding.textViewPizzaSizeSelected.setText(selectedSize);
        }
        binding.textViewCrustSizeSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectCrust();
            }
        });

        binding.textViewPizzaSizeSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectSize();
            }
        });

        selectedQuantity = Integer.valueOf(binding.textViewItemQuantity.getText().toString());
        binding.imageViewDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedQuantity == 0){
                    Toast.makeText(getActivity(), "Minimum quantity one", Toast.LENGTH_LONG).show();
                }else {
                    selectedQuantity = selectedQuantity - 1;
                    binding.textViewItemQuantity.setText(String.valueOf(selectedQuantity));
                }
            }
        });

        binding.imageViewIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedQuantity = selectedQuantity + 1;
                binding.textViewItemQuantity.setText(String.valueOf(selectedQuantity));
            }
        });

    }

    private void spinnerSetUp() {
        Spinner spinnerSelectedSauce = binding.spinnerSelectedSauce;
        ArrayAdapter<CharSequence> adapterSelectedSauce = ArrayAdapter.createFromResource(getActivity(),
                R.array.sauces, android.R.layout.simple_spinner_item);
        adapterSelectedSauce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectedSauce.setAdapter(adapterSelectedSauce);
        spinnerSelectedSauce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sauce = parent.getItemAtPosition(position).toString();
                if(!sauce.equalsIgnoreCase("none") && !sauceList.contains(sauce)) {
                    sauceList.add(sauce);
                    sauceAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerCheese = binding.spinnerCheese;
        ArrayAdapter<CharSequence> adapterCheese = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterCheese.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCheese.setAdapter(adapterCheese);
        spinnerCheese.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinnerHam = binding.spinnerHam;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHam.setAdapter(adapter);
        spinnerHam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Beef
        Spinner spinnerBeef = binding.spinnerBeef;
        ArrayAdapter<CharSequence> adapterBeef = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterBeef.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBeef.setAdapter(adapterBeef);
        spinnerBeef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Bacon
        Spinner spinnerBacon = binding.spinnerBacon;
        ArrayAdapter<CharSequence> adapterBacon = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterBacon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBacon.setAdapter(adapterBacon);
        spinnerBacon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Salami
        Spinner spinnerSalami = binding.spinnerSalami;
        ArrayAdapter<CharSequence> adapterSalami = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterSalami.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSalami.setAdapter(adapterSalami);
        spinnerSalami.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Pepperoni
        Spinner spinnerPepperoni = binding.spinnerPepperoni;
        ArrayAdapter<CharSequence> adapterPepperoni = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterPepperoni.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPepperoni.setAdapter(adapterPepperoni);
        spinnerPepperoni.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Italian Sausage
        Spinner spinnerSausage = binding.spinnerSausage;
        ArrayAdapter<CharSequence> adapterSausage = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterSausage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSausage.setAdapter(adapterSausage);
        spinnerSausage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinnerChicken
        Spinner spinnerChicken = binding.spinnerChicken;
        ArrayAdapter<CharSequence> adapterChicken = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterChicken.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChicken.setAdapter(adapterChicken);
        spinnerChicken.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Steak
        Spinner spinnerSteak = binding.spinnerSteak;
        ArrayAdapter<CharSequence> adapterSteak = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterSteak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSteak.setAdapter(adapterSteak);
        spinnerSteak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //sauce
        Spinner spinnerSauce = binding.spinnerSauce;
        ArrayAdapter<CharSequence> adapterSauce = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterSauce.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSauce.setAdapter(adapterSauce);
        spinnerSauce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //peppers
        Spinner spinnerJalapenoPeppers = binding.spinnerJalapenoPeppers;
        ArrayAdapter<CharSequence> adapterJalapenoPeppers = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterJalapenoPeppers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJalapenoPeppers.setAdapter(adapterJalapenoPeppers);
        spinnerJalapenoPeppers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //onions
        Spinner spinnerOnions = binding.spinnerOnions;
        ArrayAdapter<CharSequence> adapterOnions = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterOnions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOnions.setAdapter(adapterOnions);
        spinnerOnions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Banana Peppers
        Spinner spinnerBananaPeppers = binding.spinnerBananaPeppers;
        ArrayAdapter<CharSequence> adapterBananaPeppers = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterBananaPeppers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBananaPeppers.setAdapter(adapterBananaPeppers);
        spinnerBananaPeppers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //tomatoes

        Spinner spinnerTomatoes = binding.spinnerTomatoes;
        ArrayAdapter<CharSequence> adapterTomatoes = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterTomatoes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTomatoes.setAdapter(adapterTomatoes);
        spinnerTomatoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinnerOlives = binding.spinnerOlives;
        ArrayAdapter<CharSequence> adapterOlives = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterOlives.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOlives.setAdapter(adapterOlives);
        spinnerOlives.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerMushrooms = binding.spinnerMushrooms;
        ArrayAdapter<CharSequence> adapterMushrooms = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterMushrooms.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMushrooms.setAdapter(adapterMushrooms);
        spinnerMushrooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerPineapple = binding.spinnerPineapple;
        ArrayAdapter<CharSequence> adapterPineapple = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterPineapple.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPineapple.setAdapter(adapterPineapple);
        spinnerPineapple.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinnerProvoloneCheese = binding.spinnerProvoloneCheese;
        ArrayAdapter<CharSequence> adapterProvoloneCheese = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterProvoloneCheese.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvoloneCheese.setAdapter(adapterProvoloneCheese);
        spinnerProvoloneCheese.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerCheddarCheese = binding.spinnerCheddarCheese;
        ArrayAdapter<CharSequence> adapterCheddarCheese = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterCheddarCheese.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCheddarCheese.setAdapter(adapterCheddarCheese);
        spinnerCheddarCheese.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinnerGreenPeppers = binding.spinnerGreenPeppers;
        ArrayAdapter<CharSequence> adapterGreenPeppers = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterGreenPeppers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGreenPeppers.setAdapter(adapterGreenPeppers);
        spinnerGreenPeppers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerSpinach = binding.spinnerSpinach;
        ArrayAdapter<CharSequence> adapterSpinach = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterSpinach.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpinach.setAdapter(adapterSpinach);
        spinnerSpinach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinnerFetaCheese = binding.spinnerFetaCheese;
        ArrayAdapter<CharSequence> adapterFetaCheese= ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterFetaCheese.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFetaCheese.setAdapter(adapterFetaCheese);
        spinnerFetaCheese.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner spinnerParmesanAsiago = binding.spinnerParmesanAsiago;
        ArrayAdapter<CharSequence> adapterAsiago = ArrayAdapter.createFromResource(getActivity(),
                R.array.toppings_quantity, android.R.layout.simple_spinner_item);
        adapterAsiago.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParmesanAsiago.setAdapter(adapterAsiago);
        spinnerParmesanAsiago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String hamTopping = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


     class SauceAdapter extends RecyclerView.Adapter<SauceAdapter.SauceViewHolder>{

        @NonNull
        @Override
        public SauceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            SauceListItemBinding itemBinding = SauceListItemBinding.inflate(getLayoutInflater(),
                    parent, false);
            return new SauceViewHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull SauceViewHolder holder, int position) {
            holder.setupUI(sauceList.get(position));
        }

        @Override
        public int getItemCount() {
            return sauceList.size();
        }

        class SauceViewHolder extends RecyclerView.ViewHolder{
            SauceListItemBinding itemBinding;
            public SauceViewHolder(SauceListItemBinding itemBinding) {
                super(itemBinding.getRoot());
                this.itemBinding = itemBinding;
            }

            public void setupUI(String sauce){

                itemBinding.textViewSauce.setText(sauce);
                itemBinding.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sauceList.remove(sauce);
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }
    DetailsFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (DetailsFragmentListener) context;
    }




    interface DetailsFragmentListener{
        void selectCrust();
        void selectSize();
    }
}