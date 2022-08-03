package Admin.Courses.SubCourses.BBS;

public class StudentModel {
    private String StudentName;
    private String StudentCourse;
    private String StudentAge;
    private String StudentAddress;
    private String StudentRollNo;
    private String StudentSex;
    private String StudentNumber;

    public StudentModel(String studentName, String studentCourse, String studentAge, String studentAddress, String studentRollNo, String studentSex, String studentNumber) {
        StudentName = studentName;
        StudentCourse = studentCourse;
        StudentAge = studentAge;
        StudentAddress = studentAddress;
        StudentRollNo = studentRollNo;
        StudentSex = studentSex;
        StudentNumber = studentNumber;
    }

    public StudentModel() {
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentCourse() {
        return StudentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        StudentCourse = studentCourse;
    }

    public String getStudentAge() {
        return StudentAge;
    }

    public void setStudentAge(String studentAge) {
        StudentAge = studentAge;
    }

    public String getStudentAddress() {
        return StudentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        StudentAddress = studentAddress;
    }

    public String getStudentRollNo() {
        return StudentRollNo;
    }

    public void setStudentRollNo(String studentRollNo) {
        StudentRollNo = studentRollNo;
    }

    public String getStudentSex() {
        return StudentSex;
    }

    public void setStudentSex(String studentSex) {
        StudentSex = studentSex;
    }

    public String getStudentNumber() {
        return StudentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        StudentNumber = studentNumber;
    }

}
