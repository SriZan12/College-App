package Admin.Courses.SubCourses.BCA.Semesters.SixthSem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentSixthSemBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BCA.Semesters.FifthSem.BCAFifthSemVpagerAdapter;


public class Sixth_Sem_Fragment extends Fragment {

    FragmentSixthSemBinding sixthSemBinding;
    String Role;

    public Sixth_Sem_Fragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sixthSemBinding =  FragmentSixthSemBinding.inflate(inflater, container, false);
        int tabCount = sixthSemBinding.bcaSixthSemTabLayout.getTabCount();

        BCASixthSemVPagerAdapter vPagerAdapter = new BCASixthSemVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        sixthSemBinding.bcaSixthSemViewpager.setAdapter(vPagerAdapter);

        sixthSemBinding.bcaSixthSemTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                sixthSemBinding.bcaSixthSemViewpager.setCurrentItem(tab.getPosition());
                sixthSemBinding.bcaSixthSemTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        sixthSemBinding.bcaSixthSemViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(sixthSemBinding.bcaSixthSemTabLayout));

        return sixthSemBinding.getRoot();
    }
}