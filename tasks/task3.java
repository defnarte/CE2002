import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task3 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt"
        ArrayList<Student> StudentsAl = StudentDB.readStudents(filename);
        ArrayList<Course> CourseAl = CourseDB.readCourses(filename2);


        // Get the ID of the student and do an error check (is student's ID inside the database?)

        // Get the courseID and do an error check (is courseID inside the database?)

        // Check vacancies of lecture/tutorial/lab. If there are vacancies for all of them, continue.
            // Loop through all lectures with vacancies and print out their indexes
                // Get the desired index and do an error check (has the student already registered for this index?)
                    // Add the index to the student's lessons 
                    // Minus one from the lesson's vacancy

            // Loop through all tutorials with vacancies and print out their indexes
                // Get the desired index and do an error check (has the student already registered for this index?)
                    // Add the index to the student's lessons 
                    // Minus one from the lesson's vacancy

            // Loop through all labs with vacancies and print out their indexes
                // Get the desired index and do an error check (has the student already registered for this index?)
                    // Add the index to the student's lessons 
                    // Minus one from the lesson's vacancy

            // Repeat each loop block for every lesson type we have
    }
}
