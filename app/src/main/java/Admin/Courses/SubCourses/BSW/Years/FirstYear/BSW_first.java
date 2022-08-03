package Admin.Courses.SubCourses.BSW.Years.FirstYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.relianceinternationalcollege.databinding.FragmentBSWFirstBinding;
import com.google.android.material.tabs.TabLayout;



public class BSW_first extends Fragment {

    FragmentBSWFirstBinding bswFirstBinding;
    String Role;

    public BSW_first(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        bswFirstBinding =  FragmentBSWFirstBinding.inflate(inflater, container, false);


        int tabCount = bswFirstBinding.bswFirstYearTabLayout.getTabCount();

        assert getFragmentManager() != null;
        BSW_FirstVPagerAdpter vPagerAdpter = new BSW_FirstVPagerAdpter(getChildFragmentManager(),tabCount,Role);
        bswFirstBinding.bswFirstYearViewpager.setAdapter(vPagerAdpter);

        bswFirstBinding.bswFirstYearTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bswFirstBinding.bswFirstYearViewpager.setCurrentItem(tab.getPosition());
                bswFirstBinding.bswFirstYearTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bswFirstBinding.bswFirstYearViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(bswFirstBinding.bswFirstYearTabLayout));
        return bswFirstBinding.getRoot();
    }
}