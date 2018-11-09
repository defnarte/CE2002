import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task7 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt";
        StudentDB students = new StudentDB();
        students.readStudents(filename);
        CourseDB courses = new CourseDB();
        courses.readCourses(filename2);

        // Get the courseID and do an error check (is courseID inside the database?)

        // Get the ID of the student and do an error check (is student's ID inside the database?)

        // Loop through matNos of that course to find the student's index
            // Once found, loop through the size of courseWork and ask for input. Skip the first coursework (assumed to be exam)




    }
}
