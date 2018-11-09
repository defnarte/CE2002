import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task6 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt";
        StudentDB students = new StudentDB();
        students.readStudents(filename);
        CourseDB courses = new CourseDB();
        courses.readCourses(filename2);

        // Get the courseID and do an error check (is courseID inside the database?)

        // Ask for number of components

            // Loop for each component

        // Set the weightage for the course

    }
}
