package Admin.Fees.BBSFees;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentBBSMainFeesBinding;
import com.google.android.material.tabs.TabLayout;


public class BBSMainFees extends Fragment {

    FragmentBBSMainFeesBinding bbsMainFeesBinding;
    String Role;

    public BBSMainFees(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        bbsMainFeesBinding =  FragmentBBSMainFeesBinding.inflate(inflater, container, false);

        int tabCount =  bbsMainFeesBinding.bbsFeesTabLayout.getTabCount();

        BBSFeesVPagerAdapter vPagerAdapter = new BBSFeesVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        bbsMainFeesBinding.bbsViewPager.setAdapter(vPagerAdapter);

        bbsMainFeesBinding.bbsFeesTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bbsMainFeesBinding.bbsViewPager.setCurrentItem(tab.getPosition());
                bbsMainFeesBinding.bbsFeesTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bbsMainFeesBinding.bbsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bbsMainFeesBinding.bbsFeesTabLayout));
        return bbsMainFeesBinding.getRoot();
    }

}