import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task1 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt";
        StudentDB students = new StudentDB();
        students.readStudents(filename);
        CourseDB courses = new CourseDB();
        courses.readCourses(filename2);

        // Error check for student's name (Illegal characters)
            // Error check if the student's name already exists in the student database

        // Error check for student's matriculation number (Illegal characters)
            // Error check if the matriculation number already exists in the student database



        Student student = new Student(name, matriculationNumber);
        students.addStudent(student);
        //students.saveStudents("Students.txt");

        // Print list of all students
        students.printStudentList();

    }
}
