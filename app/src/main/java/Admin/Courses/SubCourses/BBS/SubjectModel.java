package Admin.Courses.SubCourses.BBS;

public class SubjectModel {

    private String SubjectName;
    private String Teacher;
    private String SubjectCode;

    public SubjectModel(String subjectName, String teacher, String subjectCode) {
        SubjectName = subjectName;
        Teacher = teacher;
        SubjectCode = subjectCode;
    }

    public SubjectModel() {
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        SubjectCode = subjectCode;
    }
}
