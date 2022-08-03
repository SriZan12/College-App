package Admin.Courses.SubCourses.BBS.Years.ThirdYear;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentThirdYearBinding;
import com.google.android.material.tabs.TabLayout;


public class Third_Year extends Fragment {

    FragmentThirdYearBinding thirdYearBinding;
    String Role;

    public Third_Year(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thirdYearBinding =  FragmentThirdYearBinding.inflate(inflater, container, false);


        int tabCount = thirdYearBinding.bbsThirdYearTabLayout.getTabCount();

        assert getFragmentManager() != null;
      ThirdYearVPagerAdapter thirdYearVPagerAdapter = new ThirdYearVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        thirdYearBinding.bbsThirdYearYearViewpager.setAdapter(thirdYearVPagerAdapter);

        thirdYearBinding.bbsThirdYearTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                thirdYearBinding.bbsThirdYearYearViewpager.setCurrentItem(tab.getPosition());
                thirdYearBinding.bbsThirdYearTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        thirdYearBinding.bbsThirdYearYearViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(thirdYearBinding.bbsThirdYearTabLayout));

        return thirdYearBinding.getRoot();
    }
}