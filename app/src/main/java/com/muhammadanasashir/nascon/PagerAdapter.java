package com.muhammadanasashir.nascon;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TabFragmentCS();
            case 1: return new TabFragmentEE();
            case 2: return new TabFragmentBusiness();
            case 3: return new TabFragmentSports();
            case 4: return new TabFragmentSocials();
            default: return null;
        }
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}