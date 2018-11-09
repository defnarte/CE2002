import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task4 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt";
        StudentDB students = new StudentDB();
        students.readStudents(filename);
        CourseDB courses = new CourseDB();
        courses.readCourses(filename2);

        // Get the courseID and do an error check (is courseID inside the database?)

        // Ask user which lesson type he would like to check vacancies for 
            // Print all indexes with that lesson type
                // Ask user which index and print vacancy/totalSize
    }
}
