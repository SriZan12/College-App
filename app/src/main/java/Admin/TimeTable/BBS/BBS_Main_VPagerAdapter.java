package Admin.TimeTable.BBS;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.TimeTable.BBS.FirstYearTimeTable.BBSFirstYearTimeTable;
import Admin.TimeTable.BBS.FourthYearTimeTable.BBSFourthYearTimeTable;
import Admin.TimeTable.BBS.SecondYearTimeTable.BBSSecondYearTimeTable;
import Admin.TimeTable.BBS.ThirdYearTimeTable.BBSThirdYearTimeTable;

public class BBS_Main_VPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BBS_Main_VPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
            return new BBSFirstYearTimeTable(Role);
            case 1:
                return new BBSSecondYearTimeTable(Role);
            case 2:
                return new BBSThirdYearTimeTable(Role);
            case 3:
                return new BBSFourthYearTimeTable(Role);

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
