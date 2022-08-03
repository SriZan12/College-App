package Admin.Attendance.BBS;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentBBSMainAttendanceBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Attendance.BCA.BCA_MainAttendance_VPagerAdapter;

public class BBS_MainAttendance extends Fragment {

    FragmentBBSMainAttendanceBinding bbsMainAttendanceBinding;

    String Role;

    public BBS_MainAttendance(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       bbsMainAttendanceBinding =   FragmentBBSMainAttendanceBinding.inflate(inflater, container, false);
        int tabCount =  bbsMainAttendanceBinding.bbsTabLayout.getTabCount();

        BBS_MainAttendanceVPagerAdapter vPagerAdapter = new BBS_MainAttendanceVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        bbsMainAttendanceBinding.bbsViewPager.setAdapter(vPagerAdapter);

        bbsMainAttendanceBinding.bbsTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bbsMainAttendanceBinding.bbsViewPager.setCurrentItem(tab.getPosition());
                bbsMainAttendanceBinding.bbsTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bbsMainAttendanceBinding.bbsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bbsMainAttendanceBinding.bbsTabLayout));

        return bbsMainAttendanceBinding.getRoot();
    }
}