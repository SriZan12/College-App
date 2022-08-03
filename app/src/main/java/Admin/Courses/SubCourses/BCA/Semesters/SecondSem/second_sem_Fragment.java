package Admin.Courses.SubCourses.BCA.Semesters.SecondSem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentSecondSemBinding;
import com.google.android.material.tabs.TabLayout;

public class second_sem_Fragment extends Fragment {

   FragmentSecondSemBinding secondSemBinding;
   String Role;

    public second_sem_Fragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        secondSemBinding =  FragmentSecondSemBinding.inflate(inflater, container, false);
        int tabCount = secondSemBinding.bcaSecondSemTabLayout.getTabCount();

        BCASecondSemVPagerAdapter vPagerAdapter = new BCASecondSemVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        secondSemBinding.bcaSecondSemViewpager.setAdapter(vPagerAdapter);
        secondSemBinding.bcaSecondSemTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                secondSemBinding.bcaSecondSemViewpager.setCurrentItem(tab.getPosition());
                secondSemBinding.bcaSecondSemTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        secondSemBinding.bcaSecondSemViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(secondSemBinding.bcaSecondSemTabLayout));
        return secondSemBinding.getRoot();
    }
}