import java.util.ArrayList;
import java.util.Scanner;

public class StudentCourseManagement {

    static ArrayList<Course> courses = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    // Display menu
    public static void displayMenu() {
        System.out.println("\n===== STUDENT COURSE MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Search Course by Code");
        System.out.println("4. Compute Total Units");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    // Add a new course
    public static void addCourse() {
        System.out.print("Enter course code: ");
        String code = input.nextLine().trim().toUpperCase();

        System.out.print("Enter course title: ");
        String title = input.nextLine().trim();

        System.out.print("Enter course units: ");
        int units = Integer.parseInt(input.nextLine().trim());

        Course course = new Course(code, title, units);
        courses.add(course);

        System.out.println("Course added successfully!");
    }

    // Display all courses
    public static void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses have been added yet.");
            return;
        }

        System.out.println("\n===== REGISTERED COURSES =====");

        for (Course course : courses) {
            course.displayCourse();
            System.out.println("----------------------");
        }
    }

    // Search for a course
    public static void searchCourse() {
        System.out.print("Enter course code to search: ");
        String code = input.nextLine().trim().toUpperCase();

        boolean found = false;

        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                System.out.println("\n===== COURSE FOUND =====");
                course.displayCourse();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Course not found.");
        }
    }

    // Calculate total units using recursion
    public static int calculateTotalUnits(int index) {
        if (index == courses.size()) {
            return 0;
        }

        return courses.get(index).getUnits()
                + calculateTotalUnits(index + 1);
    }

    // Display total units
    public static void displayTotalUnits() {
        int total = calculateTotalUnits(0);
        System.out.println("Total credit units: " + total);
    }

    // Main method
    public static void main(String[] args) {
        int choice = 0;

        while (choice != 5) {
            displayMenu();

            try {
                choice = Integer.parseInt(input.nextLine().trim());

                switch (choice) {
                    case 1:
                        addCourse();
                        break;

                    case 2:
                        viewCourses();
                        break;

                    case 3:
                        searchCourse();
                        break;

                    case 4:
                        displayTotalUnits();
                        break;

                    case 5:
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please select 1-5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        input.close();
    }
}