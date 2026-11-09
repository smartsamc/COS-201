
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        System.out.println("5. Save Courses");
        System.out.println("6. Load Courses");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    // Add a new course with input validation
    public static void addCourse() {

        String code;
        String title;
        int units;

        // Validate course code
        while (true) {
            System.out.print("Enter course code: ");
            code = input.nextLine().trim().toUpperCase();

            if (code.isEmpty()) {
                System.out.println("Course code cannot be empty.");
                continue;
            }

            boolean duplicate = false;

            for (Course course : courses) {
                if (course.getCode().equalsIgnoreCase(code)) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println("This course code already exists.");
            } else {
                break;
            }
        }

        // Validate course title
        while (true) {
            System.out.print("Enter course title: ");
            title = input.nextLine().trim();

            if (title.isEmpty()) {
                System.out.println("Course title cannot be empty.");
            } else {
                break;
            }
        }

        // Validate course units
        while (true) {
            System.out.print("Enter course units: ");
            String unitInput = input.nextLine().trim();

            try {
                units = Integer.parseInt(unitInput);

                if (units <= 0) {
                    System.out.println("Units must be greater than zero.");
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid units. Enter a whole number.");
            }
        }

        // Create and add course
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

    // Save courses to a file
    public static void saveCourses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("courses.txt"))) {

            for (Course course : courses) {
                writer.println(
                    course.getCode() + "|" +
                    course.getTitle() + "|" +
                    course.getUnits()
                );
            }

            System.out.println("Courses saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    // Load courses from file with validation
    public static void loadCourses() {

        ArrayList<Course> loadedCourses = new ArrayList<>();
        int skipped = 0;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("courses.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|", -1);

                // Check that the record contains three fields
                if (data.length != 3) {
                    skipped++;
                    continue;
                }

                String code = data[0].trim().toUpperCase();
                String title = data[1].trim();

                // Check for empty code or title
                if (code.isEmpty() || title.isEmpty()) {
                    skipped++;
                    continue;
                }

                try {
                    int units = Integer.parseInt(data[2].trim());

                    // Units must be positive
                    if (units <= 0) {
                        skipped++;
                        continue;
                    }

                    // Check for duplicate course codes
                    boolean duplicate = false;

                    for (Course course : loadedCourses) {
                        if (course.getCode().equalsIgnoreCase(code)) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (duplicate) {
                        skipped++;
                        continue;
                    }

                    loadedCourses.add(new Course(code, title, units));

                } catch (NumberFormatException e) {
                    skipped++;
                }
            }

            // Replace current courses with valid loaded courses
            courses.clear();
            courses.addAll(loadedCourses);

            System.out.println("Courses loaded successfully!");
            System.out.println("Courses loaded: " + loadedCourses.size());

            if (skipped > 0) {
                System.out.println("Invalid records skipped: " + skipped);
            }

            viewCourses();

        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {
        int choice = 0;

        while (choice != 7) {
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
                        saveCourses();
                        break;

                    case 6:
                        loadCourses();
                        break;

                    case 7:
                        System.out.println("Thank you for using the system!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please select 1-7.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        input.close();
    }
}
