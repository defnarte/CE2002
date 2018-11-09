import java.io.IOException;

public class test {
	public static void main(String args[]) throws IOException {
		String filename = "Students.txt";
		String filename2 = "Courses.txt";
		StudentDB students = new StudentDB();
		students.readStudents(filename);
		CourseDB courses = new CourseDB();
		courses.readCourses(filename2);
		
		students.printListCourseID("CZ2002");
		students.printListLessonID("2231");
	}
}
