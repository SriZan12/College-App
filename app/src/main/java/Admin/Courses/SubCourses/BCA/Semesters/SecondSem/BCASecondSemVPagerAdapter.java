package Admin.Courses.SubCourses.BCA.Semesters.SecondSem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BCASecondSemVPagerAdapter extends FragmentPagerAdapter {
    String Role;
    int tabCount;
    public BCASecondSemVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCASecondSemStudent(Role);
            case 1:
                return new BCASecondSemSubject(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
