package Admin.TimeTable.BSW;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWMainTimeTableBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BSW.BSW_VPagerAdapter;
import Admin.Fees.BBSFees.BBSFeesVPagerAdapter;


public class BSW_Main_TimeTable extends Fragment {

   FragmentBSWMainTimeTableBinding bswMainTimeTableBinding;
   String Role;

    public BSW_Main_TimeTable(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bswMainTimeTableBinding =  FragmentBSWMainTimeTableBinding.inflate(inflater, container, false);
        int tabCount =  bswMainTimeTableBinding.bswTimeTableTabLayout.getTabCount();

        BSW_MainTimeTable_VpagerAdapter vpagerAdapter = new BSW_MainTimeTable_VpagerAdapter(getChildFragmentManager(),tabCount,Role);
        bswMainTimeTableBinding.bswTimeTableViewPager.setAdapter(vpagerAdapter);

        bswMainTimeTableBinding.bswTimeTableTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bswMainTimeTableBinding.bswTimeTableViewPager.setCurrentItem(tab.getPosition());
                bswMainTimeTableBinding.bswTimeTableTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bswMainTimeTableBinding.bswTimeTableViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bswMainTimeTableBinding.bswTimeTableTabLayout));
        return bswMainTimeTableBinding.getRoot();
    }
}