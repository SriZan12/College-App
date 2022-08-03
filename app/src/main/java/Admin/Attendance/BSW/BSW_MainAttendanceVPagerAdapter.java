package Admin.Attendance.BSW;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Attendance.BSW.FirstYear.BSWFirstYear_Attendance;
import Admin.Attendance.BSW.FourthYear.BSWFourthYear_Attendance;
import Admin.Attendance.BSW.SecondYear.BSWSecondYear_Attendance;
import Admin.Attendance.BSW.ThirdYear.BSWThirdYear_Attndance;

public class BSW_MainAttendanceVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BSW_MainAttendanceVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BSWFirstYear_Attendance(Role);
            case 1:
                return new BSWSecondYear_Attendance(Role);
            case 2:
                return new BSWThirdYear_Attndance(Role);
            case 3:
                return new BSWFourthYear_Attendance(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
