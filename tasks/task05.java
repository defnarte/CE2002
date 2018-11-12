import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task5 {
    public static void main(String args[]) {
		String filename = "Students.txt";
		String filename2 = "Courses.txt";
		StudentDB students = new StudentDB();
		students.readStudents(filename);
		CourseDB courses = new CourseDB();
		courses.readCourses(filename2);

        // Get the courseID and do an error check (is courseID inside the database?)

        // Ask user what lesson type to print student list for
        	// Case 1: Lecture
        		// Find the lessonIDs for all lessons in the course with lessonType "Lec"
        		// Search StudentDB and print the names of students that registered for the lessonIDs
        	// Case 2: Tutorial
        		// Ask if user would like to print for a specific tutorial or ALL of them
        		// All
        			// Find the lessonIDs for all lessons in the course with lessonType "Tut"
        			// Search StudentDB and print the names of students that registered for the lessonIDs
        		// Specific 
        			// Ask for lessonID
        			// Search StudentDB and print the names of students that registered for the lessonID
        	// Case 3: Lab
        		// Same procedure as case 2

        	// Extend accordingly
        	// Case x: ...

    }
}
