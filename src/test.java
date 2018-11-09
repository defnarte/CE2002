import java.io.IOException;
import java.util.ArrayList;

public class test {
	public static void main(String args[]) throws IOException {
		
		String filename = "Students.txt";
		StudentDB students = new StudentDB();
		students.readStudents(filename);
		
		int index = 0;
		System.out.printf("Printing details for student %d: \n",index);
		System.out.println(students.getStudent(index).getName());
		System.out.println(students.getStudent(index).getID());
		System.out.println(students.getStudent(index).getCourses());
		System.out.println(students.getStudent(index).getLessons());
		
		System.out.println("");
		
		String filename2 = "Courses.txt";
		CourseDB courses = new CourseDB();
		courses.readCourses(filename2);
		
		System.out.printf("Printing details for course %d: \n",index);
		System.out.println(courses.getCourse(index).getCourseID());
		System.out.println(courses.getCourse(index).getmatNos());
		System.out.println(courses.getCourse(index).getMarks().get(0)[0].getCourseWorkMarks());
		System.out.println(courses.getCourse(index).getMarks().get(0)[0].getWeightage());
		System.out.println(courses.getCourse(index).getMarks().get(1)[0].getCourseWorkMarks());
		System.out.println(courses.getCourse(index).getMarks().get(1)[0].getWeightage());
		System.out.println(courses.getCourse(index).getLessons().get(0).getLessonID());
		System.out.println(courses.getCourse(index).getLessons().get(0).getLessonType());
		System.out.println(courses.getCourse(index).getLessons().get(0).getVacancy());
	}
}
