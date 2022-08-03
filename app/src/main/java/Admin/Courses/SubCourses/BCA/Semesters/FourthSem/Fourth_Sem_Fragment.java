package Admin.Courses.SubCourses.BCA.Semesters.FourthSem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentFourthSemBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BCA.Semesters.ThirdSem.BCAThirdSemVPagerAdapter;


public class Fourth_Sem_Fragment extends Fragment {

    FragmentFourthSemBinding fourthSemBinding;
    String Role;

    public Fourth_Sem_Fragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fourthSemBinding =  FragmentFourthSemBinding.inflate(inflater, container, false);

        int tabCount = fourthSemBinding.bcaSecondSemTabLayout.getTabCount();

        BCAFourthSemVPagerAdapter vPagerAdapter = new BCAFourthSemVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        fourthSemBinding.bcaSecondSemViewpager.setAdapter(vPagerAdapter);

        fourthSemBinding.bcaSecondSemTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fourthSemBinding.bcaSecondSemViewpager.setCurrentItem(tab.getPosition());
                fourthSemBinding.bcaSecondSemTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fourthSemBinding.bcaSecondSemViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(fourthSemBinding.bcaSecondSemTabLayout));

        return fourthSemBinding.getRoot();
    }
}