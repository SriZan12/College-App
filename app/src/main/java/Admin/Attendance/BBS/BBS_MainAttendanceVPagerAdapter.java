package Admin.Attendance.BBS;

import android.app.role.RoleManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Attendance.BBS.FirstYear.BBSFirstYear_Attendance;
import Admin.Attendance.BBS.FourthYear.BBSFourthYear_Attendance;
import Admin.Attendance.BBS.SecondYear.BBSSecondYear_Attendance;
import Admin.Attendance.BBS.ThirdYear.BBSThirdYear_Attendance;

public class BBS_MainAttendanceVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BBS_MainAttendanceVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BBSFirstYear_Attendance(Role);
            case 1:
                return new BBSSecondYear_Attendance(Role);
            case 2:
                return new BBSThirdYear_Attendance(Role);
            case 3:
                return new BBSFourthYear_Attendance(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
