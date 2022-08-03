package Admin.Notes;

public class NotesModel {

    private String SubjectName;
    private String NotesTitle;
    private String LowerCaseNotesTitle;
    private String SubjectTeacher;
    private String Standard;
    private String PdfUrl;

    public NotesModel(String subjectName, String notesTitle, String lowerCaseNotesTitle,String subjectTeacher,String standard, String pdfUrl) {
        SubjectName = subjectName;
        NotesTitle = notesTitle;
        LowerCaseNotesTitle = lowerCaseNotesTitle;
        SubjectTeacher = subjectTeacher;
        Standard = standard;
        PdfUrl = pdfUrl;
    }

    public NotesModel() {
    }


    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getNotesTitle() {
        return NotesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        NotesTitle = notesTitle;
    }

    public String getSubjectTeacher() {
        return SubjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        SubjectTeacher = subjectTeacher;
    }

    public String getStandard() {
        return Standard;
    }

    public void setStandard(String standard) {
        Standard = standard;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }

    public String getLowerCaseNotesTitle() {
        return LowerCaseNotesTitle;
    }

    public void setLowerCaseNotesTitle(String lowerCaseNotesTitle) {
        LowerCaseNotesTitle = lowerCaseNotesTitle;
    }
}
