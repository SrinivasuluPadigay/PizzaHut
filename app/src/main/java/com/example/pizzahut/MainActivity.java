package com.example.pizzahut;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pizzahut.models.MenuItemDetails;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener,
        RegisterFragment.RegisterFragmentListener, MenuFragment.MenuFragmentListener {

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
                .commit();
    }

    @Override
    public void goToDetailsFragment(MenuItemDetails menuItemDetails) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, DetailsFragment.newInstance(menuItemDetails))
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
}