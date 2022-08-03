package Admin.Fees;

public class FeesModel {
    String StudentName;
    String TotalFees;
    String TotalPaid;
    String Outstanding;
    String lowercaseStudentName;

    public FeesModel(String studentName, String totalFees, String totalPaid, String outstanding, String lowercaseStudentName) {
        StudentName = studentName;
        TotalFees = totalFees;
        TotalPaid = totalPaid;
        Outstanding = outstanding;
        this.lowercaseStudentName = lowercaseStudentName;
    }

    public FeesModel() {
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getTotalFees() {
        return TotalFees;
    }

    public void setTotalFees(String totalFees) {
        TotalFees = totalFees;
    }

    public String getTotalPaid() {
        return TotalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        TotalPaid = totalPaid;
    }

    public String getOutstanding() {
        return Outstanding;
    }

    public void setOutstanding(String outstanding) {
        Outstanding = outstanding;
    }

    public String getLowercaseStudentName() {
        return lowercaseStudentName;
    }

    public void setLowercaseStudentName(String lowercaseStudentName) {
        this.lowercaseStudentName = lowercaseStudentName;
    }
}
