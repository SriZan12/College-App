package Admin.Courses.SubCourses.BSW.Years.SecondYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWSecondBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BSW.Years.FirstYear.BSW_FirstVPagerAdpter;


public class BSW_Second extends Fragment {

    FragmentBSWSecondBinding bswSecondBinding;
    String Role;

    public BSW_Second(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bswSecondBinding =  FragmentBSWSecondBinding.inflate(inflater, container, false);

        int tabCount = bswSecondBinding.bswSecondYearTabLayout.getTabCount();

        assert getFragmentManager() != null;
        BswSecondVPagerAdapter vPagerAdapter = new BswSecondVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        bswSecondBinding.bswSecondYearViewpager.setAdapter(vPagerAdapter);

        bswSecondBinding.bswSecondYearTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bswSecondBinding.bswSecondYearViewpager.setCurrentItem(tab.getPosition());
                bswSecondBinding.bswSecondYearTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        bswSecondBinding.bswSecondYearViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(bswSecondBinding.bswSecondYearTabLayout));

        return bswSecondBinding.getRoot();
    }
}