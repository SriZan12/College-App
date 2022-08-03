package Admin.Courses.SubCourses.BCA.Semesters.ThirdSem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BCAThirdSemVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BCAThirdSemVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCAThirdSemStudent(Role);
            case 1:
                return new BCAThirdSemSubject(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
