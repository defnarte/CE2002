import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class task7 {
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
            // Once found, loop through the size of courseWork and ask for input. Skip the first coursework (assumed to be exam)
            for (int j = 0; j < courseWork.size() < j++) {
                marks = ...
                course.getMarks()[index].get(i+1).setCourseWorkMarks(marks)
            }



    }
}
