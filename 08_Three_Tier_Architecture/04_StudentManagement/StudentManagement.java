// Presentation Tier
import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        
        while (true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 1) {
                System.out.print("Student ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Marks: ");
                double marks = sc.nextDouble();
                service.addStudent(id, name, marks);
                System.out.println("Student added!");
                
            } else if (choice == 2) {
                System.out.print("Student ID: ");
                int id = sc.nextInt();
                String info = service.viewStudent(id);
                System.out.println(info != null ? info : "Student not found!");
                
            } else if (choice == 3) {
                System.out.print("Student ID: ");
                int id = sc.nextInt();
                System.out.print("New Marks: ");
                double marks = sc.nextDouble();
                boolean success = service.updateMarks(id, marks);
                System.out.println(success ? "Marks updated!" : "Student not found!");
                
            } else if (choice == 4) {
                System.out.print("Student ID: ");
                int id = sc.nextInt();
                boolean success = service.deleteStudent(id);
                System.out.println(success ? "Student deleted!" : "Student not found!");
                
            } else if (choice == 5) {
                service.viewAllStudents();
                
            } else if (choice == 6) {
                System.out.println("Goodbye!");
                break;
            }
        }
        sc.close();
    }
}
