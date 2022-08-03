package Admin.TimeTable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.TimeTable.BBS.BBS_Main_TimeTable;
import Admin.TimeTable.BCA.BCA_Main_TimeTable;
import Admin.TimeTable.BSW.BSW_Main_TimeTable;

public class TimeTableVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public TimeTableVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCA_Main_TimeTable(Role);
            case 1:
                return new BBS_Main_TimeTable(Role);
            case 2:
                return new BSW_Main_TimeTable(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
