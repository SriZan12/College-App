package Admin.Library;

public class BookModel {

    private String BookName;
    private String BookAuthor;
    private String BookCategory;
    private String BookLowerCase;

    public BookModel(String bookName, String bookAuthor, String bookCategory, String bookLowerCase) {
        BookName = bookName;
        BookAuthor = bookAuthor;
        BookCategory = bookCategory;
        BookLowerCase = bookLowerCase;
    }

    public BookModel() {
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        BookAuthor = bookAuthor;
    }

    public String getBookCategory() {
        return BookCategory;
    }

    public void setBookCategory(String bookCategory) {
        BookCategory = bookCategory;
    }

    public String getBookLowerCase() {
        return BookLowerCase;
    }

    public void setBookLowerCase(String bookLowerCase) {
        BookLowerCase = bookLowerCase;
    }
}
