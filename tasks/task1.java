import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task1 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt"
        ArrayList<Student> StudentsAl = StudentDB.readStudents(filename);
        ArrayList<Course> CourseAl = CourseDB.readCourses(filename2);

        // Error check for student's name (Illegal characters)
            // Error check if the student's name already exists in the student database

        // Error check for student's matriculation number (Illegal characters)
            // Error check if the matriculation number already exists in the student database



        Student stu = new Student(name, matriculationNumber);
        StudentsAl.add(stu);
        StudentDB.saveStudents("Students.txt", students);

        // Print list of all students
        StudentsAl.printStudentList();

    }
}
