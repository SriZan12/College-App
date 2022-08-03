package Admin.Courses.SubCourses.BSW;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivitySubBswBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.CoursesListActivity;
import Admin.Courses.SubCourses.BBS.BBS_VpagerAdapter;
import Admin.Courses.SubCourses.BBS.SubBBS_Activity;

public class SubBSW_Activity extends AppCompatActivity {

    ActivitySubBswBinding bswBinding;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bswBinding = ActivitySubBswBinding.inflate(getLayoutInflater());
        setContentView(bswBinding.getRoot());

        Role = getIntent().getStringExtra("role");

        int tabCount =  bswBinding.tabLayout.getTabCount();

       BSW_VPagerAdapter vPagerAdapter = new BSW_VPagerAdapter(getSupportFragmentManager(),tabCount,Role);
       bswBinding.viewpager.setAdapter(vPagerAdapter);

        bswBinding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bswBinding.viewpager.setCurrentItem(tab.getPosition());
                bswBinding.tabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bswBinding.viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bswBinding.tabLayout));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}