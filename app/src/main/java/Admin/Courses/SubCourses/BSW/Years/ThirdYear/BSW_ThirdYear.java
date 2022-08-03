package Admin.Courses.SubCourses.BSW.Years.ThirdYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWThirdYearBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BSW.Years.SecondYear.BswSecondVPagerAdapter;


public class BSW_ThirdYear extends Fragment {

    FragmentBSWThirdYearBinding bswThirdYearBinding;
    String Role;

    public BSW_ThirdYear(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bswThirdYearBinding =  FragmentBSWThirdYearBinding.inflate(inflater, container, false);

        int tabCount = bswThirdYearBinding.bswThirdYearTabLayout.getTabCount();

        assert getFragmentManager() != null;
        BSW_ThirdYearVPagerAdapter vPagerAdapter = new BSW_ThirdYearVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        bswThirdYearBinding.bswThirdYearViewpager.setAdapter(vPagerAdapter);

        bswThirdYearBinding.bswThirdYearTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bswThirdYearBinding.bswThirdYearViewpager.setCurrentItem(tab.getPosition());
                bswThirdYearBinding.bswThirdYearTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        bswThirdYearBinding.bswThirdYearViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(bswThirdYearBinding.bswThirdYearTabLayout));

        return bswThirdYearBinding.getRoot();
    }
}