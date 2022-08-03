package Admin.Courses.SubCourses.BCA.Semesters.SixthSem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Courses.SubCourses.BCA.Semesters.FirstSem.BCAFirstSemSubject;

public class BCASixthSemVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BCASixthSemVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCASixthSemStudent(Role);
            case 1:
                return new BCASixthSemSubject(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
