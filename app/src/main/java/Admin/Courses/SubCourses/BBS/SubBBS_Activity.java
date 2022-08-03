package Admin.Courses.SubCourses.BBS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.relianceinternationalcollege.databinding.ActivitySubBbsBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.CoursesListActivity;

public class SubBBS_Activity extends AppCompatActivity {

    ActivitySubBbsBinding bbsBinding;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bbsBinding = ActivitySubBbsBinding.inflate(getLayoutInflater());
        setContentView(bbsBinding.getRoot());

        int tabCount =  bbsBinding.tabLayout.getTabCount();
        Role = getIntent().getStringExtra("role");

        BBS_VpagerAdapter vpagerAdapter = new BBS_VpagerAdapter(getSupportFragmentManager(),tabCount,Role);
        bbsBinding.viewpager.setAdapter(vpagerAdapter);

        bbsBinding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bbsBinding.viewpager.setCurrentItem(tab.getPosition());
                bbsBinding.tabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bbsBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bbsBinding.tabLayout));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}