package Admin.TimeTable;

public class TimeTableModel {
    private String subject;
    private String time;
    private String period;
    private String teacher;

    public TimeTableModel(String subject, String time, String period, String teacher) {
        this.subject = subject;
        this.time = time;
        this.period = period;
        this.teacher = teacher;
    }

    public TimeTableModel() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
