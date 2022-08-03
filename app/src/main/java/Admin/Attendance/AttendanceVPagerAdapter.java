package Admin.Attendance;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Attendance.BBS.BBS_MainAttendance;
import Admin.Attendance.BCA.BCA_MainAttendance;
import Admin.Attendance.BSW.BSW_MainAttendance;

public class AttendanceVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public AttendanceVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCA_MainAttendance(Role);
            case 1:
                return new BBS_MainAttendance(Role);
            case 2:
                return new BSW_MainAttendance(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
