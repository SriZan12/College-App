package Admin.Courses.SubCourses.BSW;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import Admin.Courses.SubCourses.BSW.Years.FourthYear.BSW_Fourth;
import Admin.Courses.SubCourses.BSW.Years.SecondYear.BSW_Second;
import Admin.Courses.SubCourses.BSW.Years.FirstYear.BSW_first;
import Admin.Courses.SubCourses.BSW.Years.ThirdYear.BSW_ThirdYear;

public class BSW_VPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BSW_VPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BSW_first(Role);
            case 1:
                return new BSW_Second(Role);
            case 2:
                return new BSW_ThirdYear(Role);
            case 3:
                return new BSW_Fourth(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
