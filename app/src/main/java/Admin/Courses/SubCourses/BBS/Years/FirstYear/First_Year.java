package Admin.Courses.SubCourses.BBS.Years.FirstYear;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentFirstYearBinding;
import com.google.android.material.tabs.TabLayout;

import Teacher.TeacherActivity;


public class First_Year extends Fragment {


    FragmentFirstYearBinding firstYearBinding;
    Activity activity;
    String Role;

    public First_Year(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firstYearBinding = FragmentFirstYearBinding.inflate(inflater, container, false);

        int tabCount = firstYearBinding.bbsFirstYearTabLayout.getTabCount();

        assert getFragmentManager() != null;
        YearAdapter yearAdapter = new YearAdapter(getChildFragmentManager(),tabCount,Role);
        firstYearBinding.bbsFirstYearViewpager.setAdapter(yearAdapter);

        firstYearBinding.bbsFirstYearTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                firstYearBinding.bbsFirstYearViewpager.setCurrentItem(tab.getPosition());
                firstYearBinding.bbsFirstYearTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        firstYearBinding.bbsFirstYearViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(firstYearBinding.bbsFirstYearTabLayout));

        return firstYearBinding.getRoot();
    }
}