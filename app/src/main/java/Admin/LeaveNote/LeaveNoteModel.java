package Admin.LeaveNote;

import androidx.annotation.NonNull;

public class LeaveNoteModel {
    private String Reason;
    private String StudentName;
    private String StudentClass;
    private String contactNumber;
    private String shortDescription;
    private String Date;
    private long CurrentDate;

    public LeaveNoteModel(String reason, String studentName, String studentClass, String contactNumber, String shortDescription,String Date,long currentDate) {
        Reason = reason;
        StudentName = studentName;
        StudentClass = studentClass;
        this.contactNumber = contactNumber;
        this.shortDescription = shortDescription;
        this.Date = Date;
        this.CurrentDate = currentDate;
    }

    public LeaveNoteModel() {
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(String studentClass) {
        StudentClass = studentClass;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public long getCurrentDate() {
        return CurrentDate;
    }

    public void setCurrentDate(long currentDate) {
        CurrentDate = currentDate;
    }
}
