package Admin.Courses.SubCourses.BBS.Years.FirstYear;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Courses.SubCourses.BBS.Years.FirstYear.StudentFragment;
import Admin.Courses.SubCourses.BBS.Years.FirstYear.SubjectsFragment;

public class YearAdapter extends FragmentPagerAdapter {
    int tabCount;
    String Role;

    public YearAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StudentFragment(Role);
            case 1:
                return new SubjectsFragment(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
