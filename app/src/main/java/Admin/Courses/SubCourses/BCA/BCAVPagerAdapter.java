package Admin.Courses.SubCourses.BCA;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Courses.SubCourses.BCA.Semesters.EigthSem.Eighth_Sem_Fragment;
import Admin.Courses.SubCourses.BCA.Semesters.FifthSem.Fifth_Sem_Fragment;
import Admin.Courses.SubCourses.BCA.Semesters.FirstSem.first_sem_fragment;
import Admin.Courses.SubCourses.BCA.Semesters.FourthSem.Fourth_Sem_Fragment;
import Admin.Courses.SubCourses.BCA.Semesters.SeventhSem.Seventh_Sem_Fragment;
import Admin.Courses.SubCourses.BCA.Semesters.SixthSem.Sixth_Sem_Fragment;
import Admin.Courses.SubCourses.BCA.Semesters.SecondSem.second_sem_Fragment;
import Admin.Courses.SubCourses.BCA.Semesters.ThirdSem.third_Sem_Fragment;

public class BCAVPagerAdapter extends FragmentPagerAdapter {
    int tabCount;
    String Role;

    public BCAVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new first_sem_fragment(Role);
            case 1:
                return new second_sem_Fragment(Role);
            case 2:
                return new third_Sem_Fragment(Role);
            case 3:
                return new Fourth_Sem_Fragment(Role);
            case 4:
                return new Fifth_Sem_Fragment(Role);
            case 5:
                return new Sixth_Sem_Fragment(Role);
            case 6:
                return new Seventh_Sem_Fragment(Role);
            case 7:
                return new Eighth_Sem_Fragment(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
