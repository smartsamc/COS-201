
import java.util.ArrayList;
import java.util.Scanner;

public class StudentCourseManagement {

    // Store courses
    static ArrayList<Course> courses = new ArrayList<>();

    // Read user input
    static Scanner input = new Scanner(System.in);

    // Display menu
    public static void displayMenu() {
        System.out.println("\n===== STUDENT COURSE MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Exit");
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

        // Create course object
        Course course = new Course(code, title, units);

        // Add course to ArrayList
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

    // Main method
    public static void main(String[] args) {

        int choice = 0;

        while (choice != 3) {

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
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please select 1-3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        input.close();
    }
}