package Admin.Courses;

public class CourseModel {
    private String CourseName;
    private String CollegeName;
    private String Year;


    public CourseModel(String courseName,String collegeName,String year) {
        CourseName = courseName;
        CollegeName = collegeName;
        Year = year;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCollegeName() {
        return CollegeName;
    }

    public void setCollegeName(String collegeName) {
        CollegeName = collegeName;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public CourseModel() {
    }
}
