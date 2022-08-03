package Admin.Fees.BSWFees;

import android.app.role.RoleManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Fees.BSWFees.FirstYear.BSWFeesFirstYear;
import Admin.Fees.BSWFees.FourthYear.BSWFeesFourthYear;
import Admin.Fees.BSWFees.SecondYear.BSWFeesSecondYear;
import Admin.Fees.BSWFees.ThirdYear.BSWFeesThirdYear;

public class BSWFeesVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BSWFeesVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BSWFeesFirstYear(Role);
            case 1:
                return new BSWFeesSecondYear(Role);
            case 2:
                return new BSWFeesThirdYear(Role);
            case 3:
                return new BSWFeesFourthYear(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
