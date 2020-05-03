package com.muhammadanasashir.nascon;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapterAdmin extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PagerAdapterAdmin(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TabFragmentCSAdmin();
            case 1: return new TabFragmentEEAdmin();
            case 2: return new TabFragmentBusinessAdmin();
            case 3: return new TabFragmentSportsAdmin();
            case 4: return new TabFragmentSocialsAdmin();
            default: return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}