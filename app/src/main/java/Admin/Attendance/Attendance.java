package Admin.Attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.relianceinternationalcollege.databinding.ActivityAttendanceBinding;
import com.google.android.material.tabs.TabLayout;


public class Attendance extends AppCompatActivity {

    ActivityAttendanceBinding attendanceBinding;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attendanceBinding = ActivityAttendanceBinding.inflate(getLayoutInflater());
        setContentView(attendanceBinding.getRoot());

        Role = getIntent().getStringExtra("role");

        int tabCount =  attendanceBinding.attendanceTabLayout.getTabCount();

        AttendanceVPagerAdapter attendanceVPagerAdapter = new AttendanceVPagerAdapter(getSupportFragmentManager(),tabCount,Role);
        attendanceBinding.attendanceViewPager.setAdapter(attendanceVPagerAdapter);

        attendanceBinding.attendanceTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                attendanceBinding.attendanceViewPager.setCurrentItem(tab.getPosition());
                attendanceBinding.attendanceTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        attendanceBinding.attendanceViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( attendanceBinding.attendanceTabLayout));

    }
}