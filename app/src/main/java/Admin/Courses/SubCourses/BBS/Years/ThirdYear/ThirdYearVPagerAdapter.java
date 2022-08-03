package Admin.Courses.SubCourses.BBS.Years.ThirdYear;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ThirdYearVPagerAdapter extends FragmentPagerAdapter {

    int tabcount;
    String Role;

    public ThirdYearVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabcount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ThirdYearStudent(Role);
            case 1:
                return new ThirdYearSubject(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
