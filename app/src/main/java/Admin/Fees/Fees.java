package Admin.Fees;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.relianceinternationalcollege.databinding.ActivityFeesBinding;
import com.google.android.material.tabs.TabLayout;

public class Fees extends AppCompatActivity {

    ActivityFeesBinding feesBinding;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feesBinding = ActivityFeesBinding.inflate(getLayoutInflater());
        setContentView(feesBinding.getRoot());

        int tabCount =  feesBinding.attendanceTabLayout.getTabCount();

        Role = getIntent().getStringExtra("role");

        FeesVPagerAdapter feesVPagerAdapter = new FeesVPagerAdapter(getSupportFragmentManager(),tabCount,Role);
        feesBinding.feesViewPager.setAdapter(feesVPagerAdapter);

        feesBinding.attendanceTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                feesBinding.feesViewPager.setCurrentItem(tab.getPosition());
                feesBinding.attendanceTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        feesBinding.feesViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( feesBinding.attendanceTabLayout));


    }
}