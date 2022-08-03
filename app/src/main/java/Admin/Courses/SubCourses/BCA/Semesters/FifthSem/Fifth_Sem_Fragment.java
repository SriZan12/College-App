package Admin.Courses.SubCourses.BCA.Semesters.FifthSem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentFifthSemBinding;
import com.google.android.material.tabs.TabLayout;


public class Fifth_Sem_Fragment extends Fragment {

    FragmentFifthSemBinding fifthSemBinding;
    String Role;

    public Fifth_Sem_Fragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fifthSemBinding =  FragmentFifthSemBinding.inflate(inflater, container, false);

        int tabCount = fifthSemBinding.bcaFifthSemTabLayout.getTabCount();

        BCAFifthSemVpagerAdapter vPagerAdapter = new BCAFifthSemVpagerAdapter(getChildFragmentManager(),tabCount,Role);
        fifthSemBinding.bcaFifthSemViewpager.setAdapter(vPagerAdapter);

        fifthSemBinding.bcaFifthSemTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fifthSemBinding.bcaFifthSemViewpager.setCurrentItem(tab.getPosition());
                fifthSemBinding.bcaFifthSemTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fifthSemBinding.bcaFifthSemViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(fifthSemBinding.bcaFifthSemTabLayout));

        return fifthSemBinding.getRoot();
    }
}