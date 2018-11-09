import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task8 {
    public static void main(String args[]) {
        String filename = "Students.txt";
        String filename2 = "Courses.txt"
        ArrayList<Student> StudentsAl = StudentDB.readStudents(filename);
        ArrayList<Course> CourseAl = CourseDB.readCourses(filename2);

        // Get the courseID and do an error check (is courseID inside the database?)
            i = ...
            course = CourseAl.get(i)

        // Get the ID of the student and do an error check (is student's ID inside the database?)
            id = ...

        // Loop through matNos of that course to find the student's index
            index = ...
            // Once found, ask for exam marks. (We assume the first component is exam)
            marks = ...
            course.getMarks()[index].setCourseWorkMarks(marks)


    }
}
