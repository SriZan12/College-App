package Admin.Courses.SubCourses.BSW.Years.FourthYear;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BSW_FourthYearVPagerAdapter extends FragmentPagerAdapter {
    int tabCount;
    String Role;

    public BSW_FourthYearVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BSW_FourthYearStudent(Role);
            case 1:
                return new BSW_FourthYearSubject(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
