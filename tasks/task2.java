import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task2 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt"
        ArrayList<Student> StudentsAl = StudentDB.readStudents(filename);
        ArrayList<Course> CourseAl = CourseDB.readCourses(filename2);

        // Error check for course name (Illegal characters)
            // Error check if the course name already exists in the course database
                courseName = ...

        // Error check for courseID (Illegal characters)
            // Error check if the courseID already exists in the course database
                courseID = ...

        // Ask for no. of coursework components
            // Loop to enter weightage for coursework 1,2,... up to the number of coursework components
                weightage[i] =

        ArrayList<Lesson> lessons = ArrayList<Lesson>();
        // Loop this block until desired no. of lessons {
            // Ask for type of lesson to be added (with error check)
                lessonType = ...
            // Ask for lessonID (with error check)
                lessonID = ...
            // Ask for number of slots to be made avaliable (with error check)
                vacancy = ...
                Lesson(lessonID,lessonType,vacancy)

        Course course = new Course(courseName, classID, weightage, ArrayList<Lesson> lessons);
        CourseAl.add(course);
        CourseDB.saveCourses("Courses.txt", CourseAl);

        // Print list of all courses
        CourseAl.printCourseList();
    } 
}
