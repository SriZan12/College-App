package Admin.Fees;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import Admin.Fees.BBSFees.BBSMainFees;
import Admin.Fees.BCAFees.BCAMainFees;
import Admin.Fees.BSWFees.BSWMainFees;

public class FeesVPagerAdapter extends FragmentPagerAdapter {

    int tabCount;
    String Role;

    public FeesVPagerAdapter(@NonNull FragmentManager fm, int behavior, String role) {
        super(fm, behavior);
        this.tabCount = behavior;
        this.Role = role;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BCAMainFees(Role);
            case 1:
                return new BBSMainFees(Role);
            case 2:
                return new BSWMainFees(Role);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
