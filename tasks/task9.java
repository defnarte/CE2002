import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task9 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt";
        StudentDB students = new StudentDB();
        students.readStudents(filename);
        CourseDB courses = new CourseDB();
        courses.readCourses(filename2);

        // Get the courseID and do an error check (is courseID inside the database?)

        // Ask which component to display for grade percentage
            // Case 1 : Exam + Coursework 
                // Loop through "ArrayList<CourseWork[]> marks" in course
                // Perform computation using weightage and marks in each coursework object and take their sum (this gives the total marks scored by a student)
                // Convert the total marks of each student to grades and print statistics
            // Case 2 : Exam only
                // Loop through "ArrayList<CourseWork[]> marks" in course
                // Perform computation using SPECIFIC weightage and marks in each coursework object and take their sum (i.e avoid coursework marks)
                // Convert the total marks of each student to grades and print statistics
            // Case 3: Coursework only
                // Loop through "ArrayList<CourseWork[]> marks" in course
                // Perform computation using SPECIFIC weightage and marks in each coursework object and take their sum (i.e avoid exam marks)
                // Convert the total marks of each student to grades and print statistics


    }
}
