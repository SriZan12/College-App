package Admin.Fees.BCAFees;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Fees.BCAFees.EighthSemFees.BCAEightSemFees;
import Admin.Fees.BCAFees.FeesFirstSem.BCAFirstSemFees;
import Admin.Fees.BCAFees.FifthSemFees.BCAFifthSemFees;
import Admin.Fees.BCAFees.FourthSem.BCAFourthSemFees;
import Admin.Fees.BCAFees.SecondSemFees.BCASecondSemFees;
import Admin.Fees.BCAFees.SeventhSemFees.BCASeventhSemFees;
import Admin.Fees.BCAFees.SixthSem.BCASixthSemFees;
import Admin.Fees.BCAFees.ThirdSemFees.BCAThirdSemFees;

public class BCAFeesVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BCAFeesVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCAFirstSemFees(Role);
            case 1:
                return new BCASecondSemFees(Role);
            case 2:
                return new BCAThirdSemFees(Role);
            case 3:
                return new BCAFourthSemFees(Role);
            case 4:
                return new BCAFifthSemFees(Role);
            case 5:
                return new BCASixthSemFees(Role);
            case 6:
                return new BCASeventhSemFees(Role);
            case 7:
                return new BCAEightSemFees(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
