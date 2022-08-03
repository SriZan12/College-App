package Admin.Courses.SubCourses.BCA.Semesters.SeventhSem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentSeventhSemBinding;
import com.google.android.material.tabs.TabLayout;



public class Seventh_Sem_Fragment extends Fragment {

    FragmentSeventhSemBinding seventhSemBinding;
    String Role;

    public Seventh_Sem_Fragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        seventhSemBinding =  FragmentSeventhSemBinding.inflate(inflater, container, false);
        int tabCount = seventhSemBinding.bcaSeventhSemTabLayout.getTabCount();

        BCASeventhSemVPagerAdapter vPagerAdapter = new BCASeventhSemVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        seventhSemBinding.bcaSeventhSemViewpager.setAdapter(vPagerAdapter);

        seventhSemBinding.bcaSeventhSemTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                seventhSemBinding.bcaSeventhSemViewpager.setCurrentItem(tab.getPosition());
                seventhSemBinding.bcaSeventhSemTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        seventhSemBinding.bcaSeventhSemViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(seventhSemBinding.bcaSeventhSemTabLayout));

        return seventhSemBinding.getRoot();
    }
}