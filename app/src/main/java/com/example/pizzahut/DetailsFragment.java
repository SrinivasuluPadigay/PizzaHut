package com.example.pizzahut;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.pizzahut.models.ItemCustomization;
import com.example.pizzahut.models.MenuItemDetails;
import com.example.pizzahut.models.SpecialInstructions;
import com.example.pizzahut.models.Toppings;

import java.util.ArrayList;

public class DetailsFragment extends Fragment {
    private static final String ARG_PARAM_MENU_ITEM = "ARG_PARAM_MENU_ITEM";

    private MenuItemDetails menuItemDetails;
    private Toppings toppings = new Toppings();
    private SpecialInstructions specialInstructions = new SpecialInstructions();
    private ArrayList<String> sauceList = new ArrayList<>();
    private String selectedCrust = null;
    private String selectedSize = null;
    private String selectedServingCheeseQuantity;
    private Integer selectedServingQuantity = 0;
    private Integer garlicDipQuantity = 0;
    private Integer ranchDipQuantity = 0;
    private Integer marinaraSauceDipQuantity = 0;

    private ArrayList<String> toppingsList = new ArrayList<>();

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

        selectedServingQuantity = Integer.valueOf(binding.textViewItemQuantity.getText().toString());
        binding.imageViewDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedServingQuantity == 0){
                    Toast.makeText(getActivity(), "Minimum quantity one", Toast.LENGTH_LONG).show();
                }else {
                    selectedServingQuantity = selectedServingQuantity - 1;
                    binding.textViewItemQuantity.setText(String.valueOf(selectedServingQuantity));
                }
            }
        });

        binding.imageViewIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedServingQuantity = selectedServingQuantity + 1;
                binding.textViewItemQuantity.setText(String.valueOf(selectedServingQuantity));
            }
        });

        garlicDipQuantity = Integer.valueOf(binding.textViewItemQuantityGarlicDipping.getText()
                                    .toString());
        binding.imageViewDecrementGarlicDipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                garlicDipQuantity = garlicDipQuantity - 1;
                if (garlicDipQuantity < 0){
                    garlicDipQuantity = 0;
                }
                binding.textViewItemQuantityGarlicDipping.setText(String.valueOf(garlicDipQuantity));
            }
        });

        binding.imageViewIncrementGarlicDipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                garlicDipQuantity = garlicDipQuantity + 1;
                binding.textViewItemQuantityGarlicDipping.setText(String.valueOf(garlicDipQuantity));
            }
        });

        ranchDipQuantity = Integer.valueOf(binding.textViewItemQuantityRanchDipping.getText()
                .toString());
        binding.imageViewDecrementRanchDipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ranchDipQuantity = ranchDipQuantity - 1;
                if (ranchDipQuantity < 0){
                    ranchDipQuantity = 0;
                }
                binding.textViewItemQuantityRanchDipping.setText(String.valueOf(ranchDipQuantity));
            }
        });

        binding.imageViewIncrementRanchDipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ranchDipQuantity = ranchDipQuantity + 1;
                binding.textViewItemQuantityRanchDipping.setText(String.valueOf(ranchDipQuantity));
            }
        });

        marinaraSauceDipQuantity = Integer.valueOf(binding.textViewItemQuantityMarinaraSauceDipping.getText()
                .toString());
        binding.imageViewDecrementMarinaraSauceDipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marinaraSauceDipQuantity = marinaraSauceDipQuantity - 1;
                if (marinaraSauceDipQuantity < 0){
                    marinaraSauceDipQuantity = 0;
                }
                binding.textViewItemQuantityMarinaraSauceDipping.setText(String.valueOf(marinaraSauceDipQuantity));
            }
        });

        binding.imageViewIncrementMarinaraSauceDipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marinaraSauceDipQuantity = marinaraSauceDipQuantity + 1;
                binding.textViewItemQuantityMarinaraSauceDipping.setText(String.valueOf(marinaraSauceDipQuantity));
            }
        });

        binding.ButtonAddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedCrust == null){
                    Toast.makeText(getActivity(), "Select Crust", Toast.LENGTH_LONG).show();
                }else if (selectedSize == null){
                    Toast.makeText(getActivity(), "Select Pizza Size", Toast.LENGTH_LONG).show();
                }else if (selectedServingQuantity == null){
                    Toast.makeText(getActivity(), "Select Number of Pizza", Toast.LENGTH_LONG).show();
                }else if (sauceList.size() < 1){
                    Toast.makeText(getActivity(), "Select Sauce", Toast.LENGTH_LONG).show();
                }else if (selectedServingCheeseQuantity == null){
                    Toast.makeText(getActivity(), "Select Cheese Quantity", Toast.LENGTH_LONG).show();
                }else{
                    addToOrder();
                }
            }
        });

    }

    private void addToOrder(){
        ItemCustomization itemCustomization = new ItemCustomization();
        itemCustomization.setServing_crust(selectedCrust);
        itemCustomization.setServing_size(selectedSize);
        itemCustomization.setServing_quantity(selectedServingQuantity);
        itemCustomization.setServing_sauce(sauceList.get(0));
        itemCustomization.setCheese_quantity(selectedServingCheeseQuantity);
        itemCustomization.setToppings(toppings);
        itemCustomization.setSpecialInstructions(specialInstructions);
        itemCustomization.setGarlic_dip_quantity(garlicDipQuantity);
        itemCustomization.setRanch_dip_quantity(ranchDipQuantity);
        itemCustomization.setMarinara_sauce_dip_quantity(marinaraSauceDipQuantity);
        mListener.confirmationFragment(itemCustomization, menuItemDetails);
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
                    sauceList.add(0, sauce);
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
                selectedServingCheeseQuantity =  parent.getItemAtPosition(position).toString();
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
                toppings.setHam(hamTopping);
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
                String beef = parent.getItemAtPosition(position).toString();
                toppings.setBeef(beef);
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
                String bacon = parent.getItemAtPosition(position).toString();
                toppings.setBacon(bacon);
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
                String salami = parent.getItemAtPosition(position).toString();
                toppings.setSalami(salami);
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
                String pepperoni = parent.getItemAtPosition(position).toString();
                toppings.setPepperoni(pepperoni);
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
                String sausage = parent.getItemAtPosition(position).toString();
                toppings.setItalian_sausage(sausage);
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
                String chicken = parent.getItemAtPosition(position).toString();
                toppings.setPremium_chicken(chicken);
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
                String steak = parent.getItemAtPosition(position).toString();
                toppings.setPhilly_steak(steak);
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
                String sauce = parent.getItemAtPosition(position).toString();
                toppings.setHot_buffalo_sauce(sauce);
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
                String jalapenoPeppers = parent.getItemAtPosition(position).toString();
                toppings.setJalapeno_peppers(jalapenoPeppers);
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
                String onions = parent.getItemAtPosition(position).toString();
                toppings.setOnions(onions);
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
                String bananaPeppers = parent.getItemAtPosition(position).toString();
                toppings.setBanana_peppers(bananaPeppers);
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
                String tomatoes = parent.getItemAtPosition(position).toString();
                toppings.setDiced_tomatoes(tomatoes);
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
                String olives = parent.getItemAtPosition(position).toString();
                toppings.setBlack_olives(olives);
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
                String mushrooms = parent.getItemAtPosition(position).toString();
                toppings.setMushrooms(mushrooms);
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
                String pineapple = parent.getItemAtPosition(position).toString();
                toppings.setPineapple(pineapple);

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
                String provoloneCheese = parent.getItemAtPosition(position).toString();
                toppings.setShredded_provolone_cheese(provoloneCheese);
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
                String cheddarCheese = parent.getItemAtPosition(position).toString();
                toppings.setCheddar_cheese_blend(cheddarCheese);
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
                String greenPeppers = parent.getItemAtPosition(position).toString();
                toppings.setGreen_peppers(greenPeppers);
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
                String spinach = parent.getItemAtPosition(position).toString();
                toppings.setSpinach(spinach);
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
                String fetaCheese = parent.getItemAtPosition(position).toString();
                toppings.setFeta_cheese(fetaCheese);
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
                String parmesanAsiago = parent.getItemAtPosition(position).toString();
                toppings.setShredded_parmesan_asiago(parmesanAsiago);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerCutType = binding.spinnerCutType;
        ArrayAdapter<CharSequence> adapterCutType = ArrayAdapter.createFromResource(getActivity(),
                R.array.cut_type_list, android.R.layout.simple_spinner_item);
        adapterCutType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCutType.setAdapter(adapterCutType);
        spinnerCutType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cutType = parent.getItemAtPosition(position).toString();
                specialInstructions.setCut_type(cutType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerBakeType = binding.spinnerBakeType;
        ArrayAdapter<CharSequence> adapterBakeType = ArrayAdapter.createFromResource(getActivity(),
                R.array.bake_type_list, android.R.layout.simple_spinner_item);
        adapterBakeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBakeType.setAdapter(adapterBakeType);
        spinnerBakeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String bakeType = parent.getItemAtPosition(position).toString();
                specialInstructions.setBake_type(bakeType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinnerSeasoning = binding.spinnerSeasoning;
        ArrayAdapter<CharSequence> adapterSeasoning = ArrayAdapter.createFromResource(getActivity(),
                R.array.seasoning_type_list, android.R.layout.simple_spinner_item);
        adapterSeasoning.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeasoning.setAdapter(adapterSeasoning);
        spinnerSeasoning.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seasoning = parent.getItemAtPosition(position).toString();
                specialInstructions.setSeasoning(seasoning);
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
        void confirmationFragment(ItemCustomization itemCustomization, MenuItemDetails menuItemDetails);
        void goToMenuFragment();
        void logout();
    }
}