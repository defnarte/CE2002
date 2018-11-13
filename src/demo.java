import java.io.IOException;
import java.util.ArrayList;

import courses.Course;
import universityMembers.Student;

public class demo {
	public static void main(String args[]) throws IOException {
		
		String studentFilename = "Students.txt";
		String courseFilename = "Courses.txt";
		
		CombinedDB database = new CombinedDB();
		database.readCourseDB(courseFilename);
		database.readStudentDB(studentFilename);
		
		ArrayList<Student> studentAl = database.getStudentAl();
		ArrayList<Course> courseAl = database.getCourseAl();
		
		int index = 0;
		System.out.println(studentAl.get(index).getfullName());
		System.out.println(studentAl.get(index).getID());
		System.out.println(studentAl.get(index).getCoursesRegistered());

	}
}
