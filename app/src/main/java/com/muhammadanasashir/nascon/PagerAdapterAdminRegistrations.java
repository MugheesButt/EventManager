package com.muhammadanasashir.nascon;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapterAdminRegistrations extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PagerAdapterAdminRegistrations(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TabFragmentCSAdminReg();
            case 1: return new TabFragmentEEAdminReg();
            case 2: return new TabFragmentBusinessAdminReg();
            case 3: return new TabFragmentSportsAdminReg();
            case 4: return new TabFragmentSocialsAdminReg();
            default: return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}