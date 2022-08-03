package Admin.Fees.BSWFees;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBSWMainFeesBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Fees.BCAFees.BCAFeesVPagerAdapter;

public class BSWMainFees extends Fragment {

    FragmentBSWMainFeesBinding bswMainFeesBinding;
    String Role;

    public BSWMainFees(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bswMainFeesBinding =  FragmentBSWMainFeesBinding.inflate(inflater, container, false);
        int tabCount =  bswMainFeesBinding.bswFeesTabLayout.getTabCount();

        BSWFeesVPagerAdapter vPagerAdapter = new BSWFeesVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        bswMainFeesBinding.bswViewPager.setAdapter(vPagerAdapter);

        bswMainFeesBinding.bswFeesTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bswMainFeesBinding.bswViewPager.setCurrentItem(tab.getPosition());
                bswMainFeesBinding.bswFeesTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bswMainFeesBinding.bswViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bswMainFeesBinding.bswFeesTabLayout));
        return bswMainFeesBinding.getRoot();
    }
}