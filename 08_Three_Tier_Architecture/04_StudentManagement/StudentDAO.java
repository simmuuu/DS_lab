// Data Tier
import java.util.HashMap;

public class StudentDAO {
    private HashMap<Integer, Student> students = new HashMap<>();
    
    static class Student {
        int id;
        String name;
        double marks;
        
        Student(int id, String name, double marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }
    }
    
    public void addStudent(int id, String name, double marks) {
        students.put(id, new Student(id, name, marks));
    }
    
    public String getStudent(int id) {
        if (students.containsKey(id)) {
            Student s = students.get(id);
            return "ID: " + s.id + ", Name: " + s.name + ", Marks: " + s.marks + ", Grade: " + getGrade(s.marks);
        }
        return null;
    }
    
    public boolean updateMarks(int id, double marks) {
        if (students.containsKey(id)) {
            students.get(id).marks = marks;
            return true;
        }
        return false;
    }
    
    public boolean deleteStudent(int id) {
        return students.remove(id) != null;
    }
    
    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            System.out.println("\n--- All Students ---");
            for (Student s : students.values()) {
                System.out.println("ID: " + s.id + ", Name: " + s.name + ", Marks: " + s.marks + ", Grade: " + getGrade(s.marks));
            }
        }
    }
    
    private String getGrade(double marks) {
        if (marks >= 90) return "A";
        if (marks >= 80) return "B";
        if (marks >= 70) return "C";
        if (marks >= 60) return "D";
        return "F";
    }
}
