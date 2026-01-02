# 3-Tier Architecture - Student Management System

## Overview
Student record management system implementing 3-tier architecture with CRUD operations and grade calculation.

## Files
- **StudentManagement.java** - Presentation Tier (UI)
- **StudentService.java** - Application Tier (Business Logic)
- **StudentDAO.java** - Data Tier (Data Access)

## Architecture
```
┌─────────────────────┐
│  Presentation Tier  │  StudentManagement.java
│  (User Interface)   │  - Menu, Input, Display
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│  Application Tier   │  StudentService.java
│  (Business Logic)   │  - CRUD operations routing
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│     Data Tier       │  StudentDAO.java
│   (Data Access)     │  - Student storage, Grade calculation
└─────────────────────┘
```

## Code Explanation

### StudentManagement.java (Presentation)
```java
// Menu: Add, View, Update Marks, Delete, View All, Exit
Scanner sc                    → User input
StudentService service        → Business logic reference
```

### StudentService.java (Application)
```java
addStudent(id, name, marks)   → Add new student
viewStudent(id)               → Get student details
updateMarks(id, marks)        → Update student marks
deleteStudent(id)             → Remove student
viewAllStudents()             → List all students
```

### StudentDAO.java (Data)
```java
HashMap<Integer, Student> students  → Student storage
class Student { id, name, marks }   → Student entity
getGrade(marks)                     → Calculate grade (A/B/C/D/F)
```

### Grading System
```
90+ = A
80-89 = B
70-79 = C
60-69 = D
<60 = F
```

## Features
- Add Student
- View Student (with grade)
- Update Marks
- Delete Student
- View All Students

## How to Run
```bash
javac *.java
java StudentManagement
```

---

## Viva Questions

### Basic Questions
1. **What is CRUD?**
   - Create, Read, Update, Delete - four basic database operations.

2. **What is an Entity class?**
   - Class representing a data object (Student with id, name, marks).

3. **How is grade calculated?**
   - Based on marks: 90+=A, 80-89=B, 70-79=C, 60-69=D, <60=F

4. **What does getOrDefault() do?**
   - Returns value if key exists, otherwise returns default value.

5. **Why use Integer instead of int for HashMap key?**
   - HashMap requires objects; Integer is wrapper class for int.

### Intermediate Questions
6. **How would you add more student fields?**
   - Add fields to Student class (address, phone, course), update DAO methods.

7. **How to add search by name?**
   - Iterate HashMap values, filter by name match.

8. **How to validate input data?**
   - Check marks range (0-100), name not empty, ID positive.

9. **How to sort students by marks?**
   - Convert to List, use Collections.sort() with Comparator.

10. **How to add course/subject management?**
    - Create Course entity, link students to courses, marks per subject.

### Advanced Questions
11. **What is ORM?**
    - Object-Relational Mapping - maps Java objects to database tables (Hibernate).

12. **How to persist data after program ends?**
    - Use file storage (serialization) or database (JDBC, JPA).

13. **What is serialization?**
    - Converting object state to byte stream for storage/transmission.

14. **How to implement authentication?**
    - Add admin login, password hashing, role-based access.

15. **How to generate reports?**
    - Calculate statistics (average, top performers), export to PDF/Excel.

16. **What is database normalization?**
    - Organizing data to reduce redundancy and improve integrity (1NF, 2NF, 3NF).

17. **How to handle bulk operations?**
    - Batch processing, transactions, pagination for large datasets.
