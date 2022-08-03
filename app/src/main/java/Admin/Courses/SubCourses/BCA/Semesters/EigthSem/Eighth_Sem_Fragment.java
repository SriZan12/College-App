package Admin.Courses.SubCourses.BCA.Semesters.EigthSem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentEighthSemBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.SubCourses.BCA.Semesters.SeventhSem.BCASeventhSemVPagerAdapter;

public class Eighth_Sem_Fragment extends Fragment {

    FragmentEighthSemBinding eighthSemBinding;
    String Role;

    public Eighth_Sem_Fragment(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eighthSemBinding =  FragmentEighthSemBinding.inflate(inflater, container, false);
        int tabCount = eighthSemBinding.bcaEigthSemTabLayout.getTabCount();

        BCAEigthSemVPagerAdapter vPagerAdapter = new BCAEigthSemVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        eighthSemBinding.bcaEigthSemViewpager.setAdapter(vPagerAdapter);

        eighthSemBinding.bcaEigthSemTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                eighthSemBinding.bcaEigthSemViewpager.setCurrentItem(tab.getPosition());
                eighthSemBinding.bcaEigthSemTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        eighthSemBinding.bcaEigthSemViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(eighthSemBinding.bcaEigthSemTabLayout));
        return eighthSemBinding.getRoot();
    }
}