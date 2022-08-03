package Admin.TimeTable.BSW;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.TimeTable.BSW.FirstYear.BSW_FirstYear_TimeTable;
import Admin.TimeTable.BSW.FourthYear.BSW_FourthYear_TimeTable;
import Admin.TimeTable.BSW.SecondYear.BSW_SecondYear_TimeTable;
import Admin.TimeTable.BSW.ThirdYear.BSW_ThirdYear_TimeTable;

public class BSW_MainTimeTable_VpagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BSW_MainTimeTable_VpagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.Role = role;
        this.tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BSW_FirstYear_TimeTable(Role);
            case 1:
                return new BSW_SecondYear_TimeTable(Role);
            case 2:
                return new BSW_ThirdYear_TimeTable(Role);
            case 3:
                return new BSW_FourthYear_TimeTable(Role);

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
