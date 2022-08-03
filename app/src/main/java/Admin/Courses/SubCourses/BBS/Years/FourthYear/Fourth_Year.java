package Admin.Courses.SubCourses.BBS.Years.FourthYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentFourthYearBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BBS.Years.FirstYear.YearAdapter;


public class Fourth_Year extends Fragment {

   FragmentFourthYearBinding fourthYearBinding;
   String Role;

    public Fourth_Year(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fourthYearBinding =  FragmentFourthYearBinding.inflate(inflater, container, false);


        int tabCount = fourthYearBinding.bbsFourthYearTabLayout.getTabCount();

        assert getFragmentManager() != null;
       FourthYearVPagerAdapter fourthYearVPagerAdapter = new FourthYearVPagerAdapter(getChildFragmentManager(),tabCount,Role);
       fourthYearBinding.bbsFourthYearViewPager.setAdapter(fourthYearVPagerAdapter);

        fourthYearBinding.bbsFourthYearTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fourthYearBinding.bbsFourthYearViewPager.setCurrentItem(tab.getPosition());
                fourthYearBinding.bbsFourthYearTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fourthYearBinding.bbsFourthYearViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(fourthYearBinding.bbsFourthYearTabLayout));

        return fourthYearBinding.getRoot();
    }
}