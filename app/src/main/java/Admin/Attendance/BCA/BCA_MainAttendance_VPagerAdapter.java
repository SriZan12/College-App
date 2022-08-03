package Admin.Attendance.BCA;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Attendance.BCA.EightSem.BCAEigthSem_Attendance;
import Admin.Attendance.BCA.FifthSem.BCAFifthSem_Attendance;
import Admin.Attendance.BCA.FirstSem.BCAFirstSem_Attendance;
import Admin.Attendance.BCA.FourthSem.BCAFourthSem_Attendance;
import Admin.Attendance.BCA.SecondSem.BCASecondSem_Attendance;
import Admin.Attendance.BCA.SeventhSem.BCASeventhSem_Attendance;
import Admin.Attendance.BCA.SixthSem.BCASixthSem_Attendance;
import Admin.Attendance.BCA.ThirdSem.BCAThirdSem_Attendance;

public class BCA_MainAttendance_VPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BCA_MainAttendance_VPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCAFirstSem_Attendance(Role);

            case 1:
                return new BCASecondSem_Attendance(Role);

            case 2:
                return new BCAThirdSem_Attendance(Role);

            case 3:
                return new BCAFourthSem_Attendance(Role);

            case 4:
                return new BCAFifthSem_Attendance(Role);

            case 5:
                return new BCASixthSem_Attendance(Role);

            case 6:
                return new BCASeventhSem_Attendance(Role);

            case 7:
                return new BCAEigthSem_Attendance(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
