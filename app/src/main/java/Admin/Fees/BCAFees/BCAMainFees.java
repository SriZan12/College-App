package Admin.Fees.BCAFees;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBCAMainFeesBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Fees.BBSFees.BBSFeesVPagerAdapter;


public class BCAMainFees extends Fragment {

    FragmentBCAMainFeesBinding bcaMainFeesBinding;
    String Role;

    public BCAMainFees(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bcaMainFeesBinding =  FragmentBCAMainFeesBinding.inflate(inflater, container, false);

        int tabCount =  bcaMainFeesBinding.bcaFeesTabLayout.getTabCount();

        BCAFeesVPagerAdapter vPagerAdapter = new BCAFeesVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        bcaMainFeesBinding.bcaFeesViewPager.setAdapter(vPagerAdapter);

        bcaMainFeesBinding.bcaFeesTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bcaMainFeesBinding.bcaFeesViewPager.setCurrentItem(tab.getPosition());
                bcaMainFeesBinding.bcaFeesTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bcaMainFeesBinding.bcaFeesViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bcaMainFeesBinding.bcaFeesTabLayout));

        return bcaMainFeesBinding.getRoot();
    }
}