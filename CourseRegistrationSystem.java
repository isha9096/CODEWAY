import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void decreaseCapacity() {
        capacity--;
    }

    public void increaseCapacity() {
        capacity++;
    }
}

class Student {
    private String id;
    private String name;
    private List<String> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

public class CourseRegistrationSystem {
    private Map<String, Course> courses;
    private Map<String, Student> students;

    public CourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses.values()) {
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getSchedule() + ")");
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity() + " slots available\n");
        }
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student == null || course == null) {
            System.out.println("Invalid student or course.");
            return;
        }

        if (course.getCapacity() == 0) {
            System.out.println("Sorry, course is full.");
            return;
        }

        student.registerCourse(courseCode);
        course.decreaseCapacity();
        System.out.println("Student " + student.getName() + " registered for course " + course.getTitle());
    }

    public void dropStudentFromCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student == null || course == null) {
            System.out.println("Invalid student or course.");
            return;
        }

        student.dropCourse(courseCode);
        course.increaseCapacity();
        System.out.println("Student " + student.getName() + " dropped from course " + course.getTitle());
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        // Add courses
        Course course1 = new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 30, "Mon/Wed/Fri 10:00 AM");
        Course course2 = new Course("MAT201", "Calculus", "Differential and integral calculus", 25, "Tue/Thu 2:00 PM");
        system.addCourse(course1);
        system.addCourse(course2);

        // Add students
        Student student1 = new Student("1001", "Alice");
        Student student2 = new Student("1002", "Bob");
        system.addStudent(student1);
        system.addStudent(student2);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display available courses");
            System.out.println("2. Register student for a course");
            System.out.println("3. Drop student from a course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.next();
                    system.registerStudentForCourse(studentId, courseCode);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.next();
                    System.out.print("Enter course code: ");
                    courseCode = scanner.next();
                    system.dropStudentFromCourse(studentId, courseCode);
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
