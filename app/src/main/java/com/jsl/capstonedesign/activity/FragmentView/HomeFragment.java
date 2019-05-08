package com.jsl.capstonedesign.activity.FragmentView;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jsl.capstonedesign.R;

public class HomeFragment extends Fragment  {
    View v;

    private Button search;
    private FragmentPagerAdapter adapterViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        final Fragment searchFragment = new SearchResultFragment();
        v = inflater.inflate(R.layout.fragment_home, container, false);


        ViewPager vpPager = (ViewPager)v.findViewById(R.id.home_vpPager);
        adapterViewPager = new MyPagerAdapter(getActivity().getSupportFragmentManager());

        vpPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.home_indicator);
        indicator.setViewPager(vpPager);

        search = (Button)v.findViewById(R.id.home_search_button);

        search.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v)
            {
                openFragment(searchFragment);
            }

        });



        return v;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 6;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeTotalFragment.newInstance(0, "total");
                case 1:
                    return HomeTotalFragment.newInstance(1, "figure");
                case 2:
                    return HomeTotalFragment.newInstance(2, "attraction");
                case 3:
                    return HomeTotalFragment.newInstance(3, "clothes");
                case 4:
                    return HomeTotalFragment.newInstance(4, "building");
                case 5:
                    return HomeTotalFragment.newInstance(5, "etc");
                default:
                    return null;
            }
        }
    }

    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.body_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
