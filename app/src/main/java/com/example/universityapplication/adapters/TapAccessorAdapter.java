package com.example.universityapplication.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.universityapplication.fragments.LoginFragment;
import com.example.universityapplication.fragments.RegisterFragment;

public class TapAccessorAdapter extends FragmentPagerAdapter {
    public TapAccessorAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                LoginFragment loginFragment = new LoginFragment();
                return  loginFragment ;
            case 1 :
                RegisterFragment registerFragment = new RegisterFragment();
                return  registerFragment ;

            default: return  null ;

        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Login" ;
            case 1 :
                return  "Register" ;
            default:
                return null ;
        }

    }
}
