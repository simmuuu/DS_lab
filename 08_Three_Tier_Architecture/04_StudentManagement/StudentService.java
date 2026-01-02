// Application Tier
public class StudentService {
    StudentDAO dao = new StudentDAO();
    
    public void addStudent(int id, String name, double marks) {
        dao.addStudent(id, name, marks);
    }
    
    public String viewStudent(int id) {
        return dao.getStudent(id);
    }
    
    public boolean updateMarks(int id, double marks) {
        return dao.updateMarks(id, marks);
    }
    
    public boolean deleteStudent(int id) {
        return dao.deleteStudent(id);
    }
    
    public void viewAllStudents() {
        dao.viewAllStudents();
    }
}
