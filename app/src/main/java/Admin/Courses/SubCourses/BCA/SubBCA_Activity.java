package Admin.Courses.SubCourses.BCA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.relianceinternationalcollege.R;
import com.example.relianceinternationalcollege.databinding.ActivitySubBcaBinding;
import com.google.android.material.tabs.TabLayout;

import Admin.Courses.CoursesListActivity;
import Admin.Courses.SubCourses.BSW.BSW_VPagerAdapter;

public class SubBCA_Activity extends AppCompatActivity {

    private static final String TAG = "This";
    ActivitySubBcaBinding bcaBinding;
    String Role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bcaBinding = ActivitySubBcaBinding.inflate(getLayoutInflater());
        setContentView(bcaBinding.getRoot());

        int tabCount =  bcaBinding.BcaTabLayout.getTabCount();
        Role = getIntent().getStringExtra("role");

        BCAVPagerAdapter bcavPagerAdapter = new BCAVPagerAdapter(getSupportFragmentManager(),tabCount,Role);
        bcaBinding.BcaViewpager.setAdapter(bcavPagerAdapter);

        bcaBinding.BcaTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bcaBinding.BcaViewpager.setCurrentItem(tab.getPosition());
                bcaBinding.BcaTabLayout.getTabAt(tab.getPosition()).select();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        bcaBinding.BcaViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( bcaBinding.BcaTabLayout));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}