package Admin.Courses.SubCourses.BBS.Years.SecondYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentSecondYearBinding;
import com.google.android.material.tabs.TabLayout;


public class Second_Year extends Fragment {

   FragmentSecondYearBinding secondYearBinding;
   String Role;

    public Second_Year(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        secondYearBinding =  FragmentSecondYearBinding.inflate(inflater, container, false);
        int tabCount = secondYearBinding.bbsSecondYearTabLayout.getTabCount();

        assert getFragmentManager() != null;
        SecondYearVPagerAdapter secondYearVPagerAdapter = new SecondYearVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        secondYearBinding.bbsSecondYearViewpager.setAdapter(secondYearVPagerAdapter);

        secondYearBinding.bbsSecondYearTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                secondYearBinding.bbsSecondYearViewpager.setCurrentItem(tab.getPosition());
                secondYearBinding.bbsSecondYearTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        secondYearBinding.bbsSecondYearViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(secondYearBinding.bbsSecondYearTabLayout));

        return secondYearBinding.getRoot();
    }
}