import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task10 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt"
        ArrayList<Student> StudentsAl = StudentDB.readStudents(filename);
        ArrayList<Course> CourseAl = CourseDB.readCourses(filename2);

        // Get the ID of the student and do an error check (is student's ID inside the database?)

        // For each course taken by the student,
        // 1. Compute overall mark and print
        // 2. Convert that mark to a grade and print
        // 3. Print each individual component mark (these are the components of courseWork[])

    }
}
