package Admin.Attendance;

public class AttendanceModel {

    public String isChecked;

    public AttendanceModel(String isChecked) {
        this.isChecked = isChecked;
    }

    public AttendanceModel() {
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }
}
