package Admin.Fees.BBSFees;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Fees.BBSFees.FirstYear.BBSFeesFirstYear;
import Admin.Fees.BBSFees.FourthYear.BBSFeesFourthYear;
import Admin.Fees.BBSFees.SecondYear.BBSFeesSecondYear;
import Admin.Fees.BBSFees.ThirdYear.BBSFeesThirdYear;

public class BBSFeesVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public BBSFeesVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BBSFeesFirstYear(Role);
            case 1:
                return new BBSFeesSecondYear(Role);
            case 2:
                return new BBSFeesThirdYear(Role);
            case 3:
                return new BBSFeesFourthYear(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
