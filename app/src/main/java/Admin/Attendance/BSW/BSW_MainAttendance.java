package Admin.Attendance.BSW;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.FragmentBBSMainAttendanceBinding;
import com.example.relianceinternationalcollege.databinding.FragmentBSWMainAttendanceBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Attendance.BCA.BCA_MainAttendance_VPagerAdapter;


public class BSW_MainAttendance extends Fragment {

    FragmentBSWMainAttendanceBinding bswMainAttendanceBinding;
    String Role;

    public BSW_MainAttendance(String role) {
        this.Role = role;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        bswMainAttendanceBinding =  FragmentBSWMainAttendanceBinding.inflate(inflater, container, false);

        int tabCount =  bswMainAttendanceBinding.bswTabLayout.getTabCount();

        BSW_MainAttendanceVPagerAdapter adapter = new BSW_MainAttendanceVPagerAdapter(getChildFragmentManager(),tabCount,Role);
        bswMainAttendanceBinding.bswViewPager.setAdapter(adapter);

        bswMainAttendanceBinding.bswTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bswMainAttendanceBinding.bswViewPager.setCurrentItem(tab.getPosition());
                bswMainAttendanceBinding.bswTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bswMainAttendanceBinding.bswViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bswMainAttendanceBinding.bswTabLayout));


        return bswMainAttendanceBinding.getRoot();
    }
}