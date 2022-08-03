package Admin.Courses.SubCourses.BCA.Semesters.ThirdSem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentThirdSemBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BCA.Semesters.SecondSem.BCASecondSemVPagerAdapter;

public class third_Sem_Fragment extends Fragment {

    FragmentThirdSemBinding thirdSemBinding;
    String Role;

    public third_Sem_Fragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        thirdSemBinding =  FragmentThirdSemBinding.inflate(inflater, container, false);
        int tabCount = thirdSemBinding.bcaThirdSemTabLayout.getTabCount();

        BCAThirdSemVPagerAdapter vPagerAdapter = new BCAThirdSemVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        thirdSemBinding.bcaThirdSemViewpager.setAdapter(vPagerAdapter);

        thirdSemBinding.bcaThirdSemTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                thirdSemBinding.bcaThirdSemViewpager.setCurrentItem(tab.getPosition());
                thirdSemBinding.bcaThirdSemTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        thirdSemBinding.bcaThirdSemViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(thirdSemBinding.bcaThirdSemTabLayout));

        return thirdSemBinding.getRoot();
    }
}