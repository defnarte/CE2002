import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task6 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt"
        ArrayList<Student> StudentsAl = StudentDB.readStudents(filename);
        ArrayList<Course> CourseAl = CourseDB.readCourses(filename2);

        // Get the courseID and do an error check (is courseID inside the database?)
            i = ...
            course = CourseAl.get(i)
        // Ask for number of components
            n = ...
            Double[] weightage = new double[n] 
            // Loop for each component
                weightage[i] = ...
        // Set the weightage for the course
            course.setWeightage(weightage)
    }
}
