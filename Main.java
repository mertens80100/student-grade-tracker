import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static String fileName = "grades.txt";

    public static void main(String[] args) {
        loadCourses();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Student Grade Tracker ===");
            System.out.println("1 - Add Course");
            System.out.println("2 - List Courses & GPA");
            System.out.println("3 - Remove Course");
            System.out.println("0 - Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Course name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Credit: ");
                int credit = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Midterm score: ");
                double mid = Double.parseDouble(scanner.nextLine().trim());
                System.out.print("Final score: ");
                double fin = Double.parseDouble(scanner.nextLine().trim());

                courses.add(new Course(name, credit, mid, fin));
                saveCourses();
                System.out.println("Course saved.");
            } else if (choice.equals("2")) {
                listAndCalculateGpa();
            } else if (choice.equals("3")) {
                System.out.print("Course name to remove: ");
                String target = scanner.nextLine().trim();
                boolean removed = false;
                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getName().equalsIgnoreCase(target)) {
                        courses.remove(i);
                        removed = true;
                        break;
                    }
                }
                if (removed) {
                    saveCourses();
                    System.out.println("Course removed.");
                } else {
                    System.out.println("Course not found.");
                }
            } else if (choice.equals("0")) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void listAndCalculateGpa() {
        if (courses.isEmpty()) {
            System.out.println("No courses added yet.");
            return;
        }
        double totalPoints = 0;
        int totalCredits = 0;
        for (Course c : courses) {
            System.out.println(c);
            totalPoints += c.getGradePoint() * c.getCredit();
            totalCredits += c.getCredit();
        }
        if (totalCredits > 0) {
            double gpa = totalPoints / totalCredits;
            System.out.println("-----------------------------");
            System.out.println("Total Credits: " + totalCredits);
            System.out.println("GPA: " + String.format("%.2f", gpa));
        }
    }

    private static void loadCourses() {
        File f = new File(fileName);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                if (p.length == 4) {
                    courses.add(new Course(p[0], Integer.parseInt(p[1]), Double.parseDouble(p[2]), Double.parseDouble(p[3])));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }

    private static void saveCourses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Course c : courses) {
                pw.println(c.toFileLine());
            }
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }
}
