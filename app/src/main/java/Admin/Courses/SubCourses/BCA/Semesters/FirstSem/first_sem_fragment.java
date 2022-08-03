package Admin.Courses.SubCourses.BCA.Semesters.FirstSem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentFirstSemBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BSW.Years.FirstYear.BSW_FirstVPagerAdpter;


public class first_sem_fragment extends Fragment {

    FragmentFirstSemBinding firstSemBinding;
    String Role;

    public first_sem_fragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firstSemBinding =  FragmentFirstSemBinding.inflate(inflater, container, false);
        int tabCount = firstSemBinding.bcaFirstSemTabLayout.getTabCount();

        assert getFragmentManager() != null;
        BCAFirstSemVPagerAdapter vPagerAdapter = new BCAFirstSemVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        firstSemBinding.bcaFirstSemViewpager.setAdapter(vPagerAdapter);

        firstSemBinding.bcaFirstSemTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                firstSemBinding.bcaFirstSemViewpager.setCurrentItem(tab.getPosition());
                firstSemBinding.bcaFirstSemTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        firstSemBinding.bcaFirstSemViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(firstSemBinding.bcaFirstSemTabLayout));
        return firstSemBinding.getRoot();
    }
}