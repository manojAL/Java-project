package edu.ccrm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// Enums
enum Semester { SPRING, SUMMER, FALL }

enum Grade { 
    A(4.0), B(3.0), C(2.0), D(1.0), F(0.0);
    private final double points;
    Grade(double points) { this.points = points; }
    public double getPoints() { return points; }
}

// Interface demonstrating abstraction
interface Displayable {
    void displayInfo();
}

// Abstract class demonstrating inheritance
abstract class Person implements Displayable {
    protected String id;
    protected String fullName;
    protected String email;
    
    public Person(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }
    
    // Getters and setters demonstrating encapsulation
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
}

// Student class
class Student extends Person {
    private String regNo;
    private LocalDate enrollmentDate;
    private List<String> enrolledCourses;
    private boolean active;
    
    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.enrollmentDate = LocalDate.now();
        this.enrolledCourses = new ArrayList<>();
        this.active = true;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Student ID: " + id);
        System.out.println("Registration: " + regNo);
        System.out.println("Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Status: " + (active ? "Active" : "Inactive"));
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Courses Enrolled: " + enrolledCourses.size());
    }
    
    public void enrollInCourse(String courseCode) {
        if (!enrolledCourses.contains(courseCode)) {
            enrolledCourses.add(courseCode);
            System.out.println("Successfully enrolled in " + courseCode);
        } else {
            System.out.println("Already enrolled in " + courseCode);
        }
    }
    
    // Getters
    public String getRegNo() { return regNo; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public List<String> getEnrolledCourses() { return enrolledCourses; }
    public boolean isActive() { return active; }
    
    // Setter
    public void setActive(boolean active) { this.active = active; }
}

// Course class with Builder pattern
class Course implements Displayable {
    private String code;
    private String title;
    private int credits;
    private String instructor;
    private Semester semester;
    private String department;
    private boolean active;
    
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
        this.department = builder.department;
        this.active = true;
    }
    
    // Builder pattern
    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String instructor;
        private Semester semester;
        private String department;
        
        public Builder code(String code) { this.code = code; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder credits(int credits) { this.credits = credits; return this; }
        public Builder instructor(String instructor) { this.instructor = instructor; return this; }
        public Builder semester(Semester semester) { this.semester = semester; return this; }
        public Builder department(String department) { this.department = department; return this; }
        
        public Course build() {
            return new Course(this);
        }
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Credits: " + credits);
        System.out.println("Instructor: " + instructor);
        System.out.println("Semester: " + semester);
        System.out.println("Department: " + department);
        System.out.println("Status: " + (active ? "Active" : "Inactive"));
    }
    
    // Getters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    public boolean isActive() { return active; }
    
    // Setter
    public void setActive(boolean active) { this.active = active; }
    
    @Override
    public String toString() {
        return code + " - " + title + " (" + credits + " credits)";
    }
}

// Enrollment class
class Enrollment {
    private String studentId;
    private String courseCode;
    private double marks;
    private Grade grade;
    private LocalDate enrollmentDate;
    
    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.enrollmentDate = LocalDate.now();
        this.marks = 0.0;
        this.grade = Grade.F;
    }
    
    public void setMarks(double marks) {
        this.marks = marks;
        // Determine grade based on marks
        if (marks >= 90) this.grade = Grade.A;
        else if (marks >= 80) this.grade = Grade.B;
        else if (marks >= 70) this.grade = Grade.C;
        else if (marks >= 60) this.grade = Grade.D;
        else this.grade = Grade.F;
    }
    
    // Getters
    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public double getMarks() { return marks; }
    public Grade getGrade() { return grade; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
}

// Singleton pattern for application configuration
class AppConfig {
    private static AppConfig instance;
    private String dataPath;
    
    private AppConfig() {
        this.dataPath = "./data/";
    }
    
    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }
    
    public String getDataPath() { return dataPath; }
}

// Custom exceptions
class DuplicateEnrollmentException extends Exception {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}

class MaxCreditLimitExceededException extends Exception {
    public MaxCreditLimitExceededException(String message) {
        super(message);
    }
}

// Main application class
public class CampusCourseRecordsManager {
    private List<Student> students;
    private List<Course> courses;
    private List<Enrollment> enrollments;
    private AppConfig config;
    private Scanner scanner;
    
    public CampusCourseRecordsManager() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.enrollments = new ArrayList<>();
        this.config = AppConfig.getInstance();
        this.scanner = new Scanner(System.in);
        
        // Add some sample data for testing
        initializeSampleData();
    }
    
    private void initializeSampleData() {
        // Add sample courses
        courses.add(new Course.Builder()
            .code("CS101")
            .title("Introduction to Programming")
            .credits(3)
            .instructor("Dr. Smith")
            .semester(Semester.FALL)
            .department("Computer Science")
            .build());
            
        courses.add(new Course.Builder()
            .code("MATH201")
            .title("Calculus I")
            .credits(4)
            .instructor("Prof. Johnson")
            .semester(Semester.FALL)
            .department("Mathematics")
            .build());
            
        courses.add(new Course.Builder()
            .code("PHY101")
            .title("Physics Fundamentals")
            .credits(3)
            .instructor("Dr. Brown")
            .semester(Semester.SPRING)
            .department("Physics")
            .build());
    }
    
    public void start() {
        System.out.println("=== Campus Course Records Manager ===");
        System.out.println("Welcome to CCRM System!");
        
        boolean running = true;
        
        while (running) {
            displayMainMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        manageStudents();
                        break;
                    case 2:
                        manageCourses();
                        break;
                    case 3:
                        manageEnrollments();
                        break;
                    case 4:
                        manageGrades();
                        break;
                    case 5:
                        generateReports();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Thank you for using CCRM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("4. Grade Management");
        System.out.println("5. Reports");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
    
    private void manageStudents() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== STUDENT MANAGEMENT ===");
            System.out.println("1. Add Student");
            System.out.println("2. List All Students");
            System.out.println("3. View Student Profile");
            System.out.println("4. Update Student");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    listStudents();
                    break;
                case 3:
                    viewStudentProfile();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        // Check if student ID already exists
        boolean idExists = students.stream()
            .anyMatch(s -> s.getId().equals(id));
        if (idExists) {
            System.out.println("Student ID already exists!");
            return;
        }
        
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine();
        
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        Student student = new Student(id, regNo, name, email);
        students.add(student);
        System.out.println("Student added successfully!");
    }
    
    private void listStudents() {
        System.out.println("\n--- All Students ---");
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        
        // Using Stream API for demonstration
        students.stream()
            .filter(Student::isActive)
            .forEach(student -> {
                System.out.println("ID: " + student.getId() + " | Reg No: " + student.getRegNo() + 
                                 " | Name: " + student.getFullName());
            });
    }
    
    private void viewStudentProfile() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        students.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst()
            .ifPresentOrElse(
                Student::displayInfo,
                () -> System.out.println("Student not found!")
            );
    }
    
    private void updateStudent() {
        System.out.print("Enter student ID to update: ");
        String id = scanner.nextLine();
        
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.print("Enter new name (current: " + student.getFullName() + "): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    student.setFullName(newName);
                }
                
                System.out.print("Enter new email (current: " + student.getEmail() + "): ");
                String newEmail = scanner.nextLine();
                if (!newEmail.isEmpty()) {
                    student.setEmail(newEmail);
                }
                
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }
    
    private void manageCourses() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== COURSE MANAGEMENT ===");
            System.out.println("1. Add Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Search Courses by Instructor");
            System.out.println("4. Search Courses by Department");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    listCourses();
                    break;
                case 3:
                    searchCoursesByInstructor();
                    break;
                case 4:
                    searchCoursesByDepartment();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private void addCourse() {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        
        // Check if course code already exists using Stream API
        boolean codeExists = courses.stream()
            .anyMatch(c -> c.getCode().equalsIgnoreCase(code));
        if (codeExists) {
            System.out.println("Course code already exists!");
            return;
        }
        
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();
        
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        
        System.out.println("Available semesters: SPRING, SUMMER, FALL");
        System.out.print("Enter semester: ");
        String semesterStr = scanner.nextLine().toUpperCase();
        
        Semester semester;
        try {
            semester = Semester.valueOf(semesterStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid semester! Defaulting to FALL.");
            semester = Semester.FALL;
        }
        
        Course course = new Course.Builder()
            .code(code)
            .title(title)
            .credits(credits)
            .instructor(instructor)
            .semester(semester)
            .department(department)
            .build();
            
        courses.add(course);
        System.out.println("Course added successfully!");
    }
    
    private void listCourses() {
        System.out.println("\n--- All Courses ---");
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        
        courses.stream()
            .filter(Course::isActive)
            .forEach(course -> {
                System.out.println(course.toString());
            });
    }
    
    private void searchCoursesByInstructor() {
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();
        
        System.out.println("\n--- Courses by " + instructor + " ---");
        List<Course> instructorCourses = courses.stream()
            .filter(c -> c.getInstructor().equalsIgnoreCase(instructor) && c.isActive())
            .toList();
            
        if (instructorCourses.isEmpty()) {
            System.out.println("No courses found for this instructor.");
        } else {
            instructorCourses.forEach(Course::displayInfo);
        }
    }
    
    private void searchCoursesByDepartment() {
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        
        System.out.println("\n--- Courses in " + department + " Department ---");
        List<Course> deptCourses = courses.stream()
            .filter(c -> c.getDepartment().equalsIgnoreCase(department) && c.isActive())
            .toList();
            
        if (deptCourses.isEmpty()) {
            System.out.println("No courses found in this department.");
        } else {
            deptCourses.forEach(c -> System.out.println(c.getCode() + " - " + c.getTitle()));
        }
    }
    
    private void manageEnrollments() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== ENROLLMENT MANAGEMENT ===");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. View Student's Enrollments");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    viewStudentEnrollments();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private void enrollStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        
        // Check if student exists
        Student student = students.stream()
            .filter(s -> s.getId().equals(studentId))
            .findFirst()
            .orElse(null);
            
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        // Check if course exists
        Course course = courses.stream()
            .filter(c -> c.getCode().equalsIgnoreCase(courseCode))
            .findFirst()
            .orElse(null);
            
        if (course == null) {
            System.out.println("Course not found!");
            return;
        }
        
        // Check if already enrolled
        boolean alreadyEnrolled = enrollments.stream()
            .anyMatch(e -> e.getStudentId().equals(studentId) && 
                          e.getCourseCode().equalsIgnoreCase(courseCode));
        
        if (alreadyEnrolled) {
            System.out.println("Student is already enrolled in this course!");
            return;
        }
        
        Enrollment enrollment = new Enrollment(studentId, courseCode);
        enrollments.add(enrollment);
        student.enrollInCourse(courseCode);
        System.out.println("Enrollment successful!");
    }
    
    private void viewStudentEnrollments() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        
        List<Enrollment> studentEnrollments = enrollments.stream()
            .filter(e -> e.getStudentId().equals(studentId))
            .toList();
            
        if (studentEnrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }
        
        System.out.println("\n--- Enrollments ---");
        studentEnrollments.forEach(e -> {
            System.out.println("Course: " + e.getCourseCode() + 
                             " | Enrollment Date: " + e.getEnrollmentDate() +
                             " | Marks: " + e.getMarks() +
                             " | Grade: " + e.getGrade());
        });
    }
    
    private void manageGrades() {
        System.out.println("\n=== GRADE MANAGEMENT ===");
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        
        Enrollment enrollment = enrollments.stream()
            .filter(e -> e.getStudentId().equals(studentId) && 
                        e.getCourseCode().equalsIgnoreCase(courseCode))
            .findFirst()
            .orElse(null);
            
        if (enrollment == null) {
            System.out.println("Enrollment not found!");
            return;
        }
        
        System.out.print("Enter marks (0-100): ");
        double marks = scanner.nextDouble();
        scanner.nextLine();
        
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks! Must be between 0 and 100.");
            return;
        }
        
        enrollment.setMarks(marks);
        System.out.println("Marks recorded successfully!");
        System.out.println("Grade assigned: " + enrollment.getGrade());
    }
    
    private void generateReports() {
        System.out.println("\n=== REPORTS ===");
        System.out.println("1. Course Statistics");
        System.out.println("2. Student Transcript");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                showCourseStatistics();
                break;
            case 2:
                generateTranscript();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private void showCourseStatistics() {
        System.out.println("\n--- Course Statistics ---");
        System.out.println("Total courses: " + courses.size());
        
        // Using Stream API for aggregation
        java.util.Map<String, Long> coursesByDept = courses.stream()
            .collect(java.util.stream.Collectors.groupingBy(
                Course::getDepartment, 
                java.util.stream.Collectors.counting()
            ));
            
        System.out.println("\nCourses by department:");
        coursesByDept.forEach((dept, count) -> 
            System.out.println(dept + ": " + count + " courses")
        );
    }
    
    private void generateTranscript() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        
        Student student = students.stream()
            .filter(s -> s.getId().equals(studentId))
            .findFirst()
            .orElse(null);
            
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\n=== TRANSCRIPT ===");
        student.displayInfo();
        
        List<Enrollment> studentEnrollments = enrollments.stream()
            .filter(e -> e.getStudentId().equals(studentId))
            .toList();
            
        if (studentEnrollments.isEmpty()) {
            System.out.println("No course enrollments found.");
            return;
        }
        
        System.out.println("\nCourse Grades:");
        System.out.println("Course Code\tMarks\tGrade\tGrade Points");
        System.out.println("--------------------------------------------");
        
        studentEnrollments.forEach(e -> {
            System.out.println(e.getCourseCode() + "\t\t" + 
                             e.getMarks() + "\t" + 
                             e.getGrade() + "\t" + 
                             e.getGrade().getPoints());
        });
        
        // Calculate GPA
        double totalPoints = studentEnrollments.stream()
            .mapToDouble(e -> e.getGrade().getPoints())
            .sum();
        double gpa = studentEnrollments.isEmpty() ? 0 : totalPoints / studentEnrollments.size();
        
        System.out.println("--------------------------------------------");
        System.out.printf("GPA: %.2f%n", gpa);
    }
    
    public static void main(String[] args) {
        CampusCourseRecordsManager app = new CampusCourseRecordsManager();
        app.start();
    }
}