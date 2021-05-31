package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static com.rsschool.android2021.SecondFragment.PREVIOUS_RESULT_KEY;

public class MainActivity extends AppCompatActivity implements FragmentData {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);
    }

    @Override
    public void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment, FirstFragment.TAG);
        transaction.commit();


    }

    @Override
    public void openSecondFragment(int min, int max) {

        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment, SecondFragment.TAG);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {

        final Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(SecondFragment.TAG);
        if (currentFragment != null) {
            Bundle arguments = currentFragment.getArguments();
            int result = arguments != null ? arguments.getInt(SecondFragment.PREVIOUS_RESULT_KEY) : 0;
            openFirstFragment(result);
        } else {
            super.onBackPressed();
        }
    }
}
