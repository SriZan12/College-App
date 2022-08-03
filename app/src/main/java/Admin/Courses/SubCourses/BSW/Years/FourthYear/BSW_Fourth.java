package Admin.Courses.SubCourses.BSW.Years.FourthYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWFourthBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BSW.Years.ThirdYear.BSW_ThirdYearVPagerAdapter;

public class BSW_Fourth extends Fragment {

    FragmentBSWFourthBinding fourthBinding;
    String Role;

    public BSW_Fourth(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fourthBinding =  FragmentBSWFourthBinding.inflate(inflater, container, false);
        int tabCount = fourthBinding.bswFourthYearTabLayout.getTabCount();

        assert getFragmentManager() != null;
        BSW_FourthYearVPagerAdapter vPagerAdapter = new BSW_FourthYearVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        fourthBinding.bswFourthYearViewpager.setAdapter(vPagerAdapter);


        fourthBinding.bswFourthYearTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fourthBinding.bswFourthYearViewpager.setCurrentItem(tab.getPosition());
                fourthBinding.bswFourthYearTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        fourthBinding.bswFourthYearViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(fourthBinding.bswFourthYearTabLayout));
        return fourthBinding.getRoot();
    }
}