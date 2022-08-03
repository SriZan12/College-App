package Admin.TimeTable.BCA;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBBSMainTimeTableBinding;
import com.example.relianceinternationalcollege.databinding.FragmentBCAMainTimeTableBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Fees.BBSFees.BBSFeesVPagerAdapter;


public class BCA_Main_TimeTable extends Fragment {

    FragmentBCAMainTimeTableBinding bcaMainTimeTableBinding;
    String Role;

    public BCA_Main_TimeTable(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bcaMainTimeTableBinding =  FragmentBCAMainTimeTableBinding.inflate(inflater, container, false);
        int tabCount =  bcaMainTimeTableBinding.bcaTimeTableTabLayout.getTabCount();

       BCA_MainTimeTable_VpagerAdapter vpagerAdapter = new BCA_MainTimeTable_VpagerAdapter(getChildFragmentManager(),tabCount,Role);
       bcaMainTimeTableBinding.bcaTimeTableViewPager.setAdapter(vpagerAdapter);

        bcaMainTimeTableBinding.bcaTimeTableTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bcaMainTimeTableBinding.bcaTimeTableViewPager.setCurrentItem(tab.getPosition());
                bcaMainTimeTableBinding.bcaTimeTableTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bcaMainTimeTableBinding.bcaTimeTableViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bcaMainTimeTableBinding.bcaTimeTableTabLayout));
        return bcaMainTimeTableBinding.getRoot();
    }
}