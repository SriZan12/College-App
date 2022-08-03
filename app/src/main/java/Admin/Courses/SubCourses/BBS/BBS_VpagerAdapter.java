package Admin.Courses.SubCourses.BBS;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Courses.SubCourses.BBS.Years.FirstYear.First_Year;
import Admin.Courses.SubCourses.BBS.Years.FourthYear.Fourth_Year;
import Admin.Courses.SubCourses.BBS.Years.SecondYear.Second_Year;
import Admin.Courses.SubCourses.BBS.Years.ThirdYear.Third_Year;

public class BBS_VpagerAdapter extends FragmentPagerAdapter {
    int tabCount;
    String Role;

    public BBS_VpagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            return new First_Year(Role);
            case 1:
                return new Second_Year(Role);
            case 2:
                return new Third_Year(Role);
            case 3:
                return new Fourth_Year(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
