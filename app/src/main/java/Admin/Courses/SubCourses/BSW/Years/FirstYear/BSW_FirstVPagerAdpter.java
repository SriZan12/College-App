package Admin.Courses.SubCourses.BSW.Years.FirstYear;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BSW_FirstVPagerAdpter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BSW_FirstVPagerAdpter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BswFirstYearStudent(Role);
            case 1:
                return new BswFirstYearSubject(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
