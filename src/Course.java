
public class Course {

    // Course attributes
    private String code;
    private String title;
    private int units;

    // Constructor
    public Course(String code, String title, int units) {
        this.code = code;
        this.title = title;
        this.units = units;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getUnits() {
        return units;
    }

    // Display course details
    public void displayCourse() {
        System.out.println("Course Code: " + code);
        System.out.println("Course Title: " + title);
        System.out.println("Course Units: " + units);
    }
}