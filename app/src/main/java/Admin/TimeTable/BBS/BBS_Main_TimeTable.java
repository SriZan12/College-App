package Admin.TimeTable.BBS;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBBSMainTimeTableBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Fees.BBSFees.BBSFeesVPagerAdapter;

public class BBS_Main_TimeTable extends Fragment {

    FragmentBBSMainTimeTableBinding bbsMainTimeTableBinding;
    String Role;

    public BBS_Main_TimeTable(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bbsMainTimeTableBinding =  FragmentBBSMainTimeTableBinding.inflate(inflater, container, false);
        int tabCount =  bbsMainTimeTableBinding.bbsTimeTableTabLayout.getTabCount();

       BBS_Main_VPagerAdapter bbs_main_vPagerAdapter = new BBS_Main_VPagerAdapter(getChildFragmentManager(),tabCount,Role);
       bbsMainTimeTableBinding.bbsViewPager.setAdapter(bbs_main_vPagerAdapter);

        bbsMainTimeTableBinding.bbsTimeTableTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bbsMainTimeTableBinding.bbsViewPager.setCurrentItem(tab.getPosition());
                bbsMainTimeTableBinding.bbsTimeTableTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bbsMainTimeTableBinding.bbsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bbsMainTimeTableBinding.bbsTimeTableTabLayout));
        return bbsMainTimeTableBinding.getRoot();
    }
}