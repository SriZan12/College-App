package Admin.TimeTable.BCA;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.TimeTable.BCA.EigthSemTimeTable.BCA_EightSem_TimeTable;
import Admin.TimeTable.BCA.FifthSemTimeTable.BCA_FifthSem_TimeTable;
import Admin.TimeTable.BCA.FirstSemTimeTable.BCA_FirstSem_TimeTable;
import Admin.TimeTable.BCA.FourthSemTimeTable.BCA_FourthSem_TimeTable;
import Admin.TimeTable.BCA.SecondSemTimeTable.BCA_SecondSem_TimeTable;
import Admin.TimeTable.BCA.SeventhSemTimeTable.BCA_SeventhSem_TimeTable;
import Admin.TimeTable.BCA.SixthSemTimeTable.BCA_SixthSem_TimeTable;
import Admin.TimeTable.BCA.ThirdSemTimeTable.BCA_ThirdSem_TimeTable;

public class BCA_MainTimeTable_VpagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BCA_MainTimeTable_VpagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.Role = role;
        this.tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCA_FirstSem_TimeTable(Role);
            case 1:
                return new BCA_SecondSem_TimeTable(Role);
            case 2:
                return new BCA_ThirdSem_TimeTable(Role);
            case 3:
                return new BCA_FourthSem_TimeTable(Role);
            case 4:
                return new BCA_FifthSem_TimeTable(Role);
            case 5:
                return new BCA_SixthSem_TimeTable(Role);
            case 6:
                return new BCA_SeventhSem_TimeTable(Role);
            case 7:
                return new BCA_EightSem_TimeTable(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
