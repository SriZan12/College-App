package Admin.Attendance.BCA;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.databinding.FragmentBCAMainAttendanceBinding;
import com.google.android.material.tabs.TabLayout;

public class BCA_MainAttendance extends Fragment {

    FragmentBCAMainAttendanceBinding mainAttendanceBinding;
    String Role;

    public BCA_MainAttendance(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainAttendanceBinding =  FragmentBCAMainAttendanceBinding.inflate(inflater, container, false);

        int tabCount =  mainAttendanceBinding.bcaAttendanceTabLayout.getTabCount();

       BCA_MainAttendance_VPagerAdapter adapter = new BCA_MainAttendance_VPagerAdapter(getChildFragmentManager(),tabCount,Role);
        mainAttendanceBinding.bcaAllAttendance.setAdapter(adapter);

        mainAttendanceBinding.bcaAttendanceTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mainAttendanceBinding.bcaAllAttendance.setCurrentItem(tab.getPosition());
                mainAttendanceBinding.bcaAttendanceTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mainAttendanceBinding.bcaAllAttendance.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( mainAttendanceBinding.bcaAttendanceTabLayout));


        return mainAttendanceBinding.getRoot();
    }
}