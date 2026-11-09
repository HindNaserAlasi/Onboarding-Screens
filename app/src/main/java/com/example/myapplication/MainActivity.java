
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnFragmentInteractionListener,
        Fragment3.OnFragmentInteractionListener {

    private ActivityMainBinding binding;

    private int currentFragment = 1;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            Fragment1 fragment1 =
                    Fragment1.newInstance(null, null);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment1)
                    .commit();
        }

        binding.buttonContinue.setOnClickListener(view -> {

            if (currentFragment == 1) {

                Fragment2 fragment2 =
                        Fragment2.newInstance(null, null);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();

                currentFragment = 2;

            } else if (currentFragment == 2) {

                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_container);

                if (fragment instanceof Fragment2) {
                    Fragment2 fragment2 = (Fragment2) fragment;

                    String enteredName = fragment2.getEnteredName();

                    if (!TextUtils.isEmpty(enteredName)) {
                        fragment2.submitName();
                    } else {
                        Toast.makeText(
                                this,
                                "Enter name!",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }
        });

        getSupportFragmentManager()
                .addOnBackStackChangedListener(() -> {

                    Fragment fragment = getSupportFragmentManager()
                            .findFragmentById(R.id.fragment_container);

                    if (fragment instanceof Fragment1) {
                        currentFragment = 1;
                    } else if (fragment instanceof Fragment2) {
                        currentFragment = 2;
                    } else if (fragment instanceof Fragment3) {
                        currentFragment = 3;
                    }

                    binding.buttonContinue.setVisibility(
                            currentFragment == 3 ? View.GONE : View.VISIBLE);
                });
    }

    @Override
    public void onNameSubmitted(String name) {

        this.name = name;

        Fragment3 fragment3 = Fragment3.newInstance(name);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();

        currentFragment = 3;
        binding.buttonContinue.setVisibility(View.GONE);
    }

    @Override
    public void onConfirmationChanged(boolean isChecked) {

        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (fragment instanceof Fragment3) {
            ((Fragment3) fragment).updateButton(isChecked);
        }
    }
}
