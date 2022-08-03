package Admin.TimeTable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.relianceinternationalcollege.databinding.ActivityTimeTableBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.AdminActivity;
import Admin.Fees.BBSFees.BBSFeesVPagerAdapter;

public class TimeTableActivity extends AppCompatActivity {

    ActivityTimeTableBinding timeTableBinding;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timeTableBinding = ActivityTimeTableBinding.inflate(getLayoutInflater());
        setContentView(timeTableBinding.getRoot());

        int tabCount =  timeTableBinding.timeTableTabLayout.getTabCount();
        Role = getIntent().getStringExtra("role");

        TimeTableVPagerAdapter vPagerAdapter = new TimeTableVPagerAdapter(getSupportFragmentManager(),tabCount,Role);
        timeTableBinding.timeTableViewPager.setAdapter(vPagerAdapter);

        timeTableBinding.timeTableTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                timeTableBinding.timeTableViewPager.setCurrentItem(tab.getPosition());
                timeTableBinding.timeTableTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        timeTableBinding.timeTableViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( timeTableBinding.timeTableTabLayout));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(TimeTableActivity.this, AdminActivity.class));
    }
}