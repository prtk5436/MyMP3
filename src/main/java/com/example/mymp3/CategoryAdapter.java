package com.example.mymp3;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HindiFragment();
        } else if (position == 1) {
            return new MarathiFragment();
        } else if (position == 2) {
            return new PanjabiFragment();
        } else {
            return new EnglishFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}