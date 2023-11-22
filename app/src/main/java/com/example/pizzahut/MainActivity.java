package com.example.pizzahut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pizzahut.models.MenuItemDetails;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener,
        RegisterFragment.RegisterFragmentListener, MenuFragment.MenuFragmentListener,
        DetailsFragment.DetailsFragmentListener, SelectCrustFragment.SelectCrustListener,
        SelectSizeFragment.SelectSizeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.root, new LoginFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.root, new MenuFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void createNewAccount() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, new RegisterFragment())
                .commit();
    }

    public void login() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, new LoginFragment())
                .commit();
    }

    public void authSuccessful() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, new MenuFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToDetailsFragment(MenuItemDetails menuItemDetails) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, DetailsFragment.newInstance(menuItemDetails), "details-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToConfirmationFragment() {

    }

    @Override
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, new LoginFragment())
                .commit();
    }

    @Override
    public void selectCrust() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, new SelectCrustFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void selectSize() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, new SelectSizeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendSize(String selectedSize) {
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().
                findFragmentByTag("details-fragment");

        if (detailsFragment!= null){
            detailsFragment.setSelectedSize(selectedSize);
        }
        getSupportFragmentManager().popBackStack();

    }

    @Override
    public void sendCrust(String selectCrust) {
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().
                findFragmentByTag("details-fragment");

        if (detailsFragment!= null){
            detailsFragment.setSelectedCrust(selectCrust);
        }
        getSupportFragmentManager().popBackStack();
    }
}