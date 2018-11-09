import java.io.IOException;
import java.util.ArrayList;

public class demo {
	public static void main(String args[]) throws IOException {
		
		String filename = "Students.txt";
		StudentDB students = new StudentDB();
		students.readStudents(filename);
		
		// Change this index to switch to the next student
		int index = 1;
		System.out.printf("Printing details for student %d: \n",(index+1));
		System.out.println("Student name: " + students.getStudent(index).getStudentName());
		System.out.println("StudentID: " + students.getStudent(index).getStudentID());
		System.out.println("Courses Enrolled: " + students.getStudent(index).getStudentCourses());
		System.out.println("Indexes registered: " + students.getStudent(index).getStudentLessons());
		
		System.out.println("");
		
		String filename2 = "Courses.txt";
		CourseDB courses = new CourseDB();
		courses.readCourses(filename2);
		
		index = 0;
		System.out.printf("Printing details for %d th course: \n",(index+1));
		System.out.println("Course name: " + courses.getCourse(index).getCourseID());
		System.out.println("Students enrolled: " + courses.getCourse(index).getstudentsMatricNumbers());
		System.out.printf("Marks scored by %d th student for first component: %.2f \n",(index+1),courses.getCourse(index).getcourseWorkMarks().get(index)[0].getMarks());
		System.out.println("Weightage of the first component: " + courses.getCourse(index).getcourseWorkMarks().get(index)[0].getWeightage());
		System.out.printf("Marks scored by %d student for second component: %.2f \n",(index+1),courses.getCourse(index).getcourseWorkMarks().get(index)[1].getMarks());
		System.out.println("Weightage of the second component: " + courses.getCourse(index).getcourseWorkMarks().get(index)[1].getWeightage());
		System.out.printf("LessonID of the %d th lesson: %s \n",(index+1),courses.getCourse(index).getLessons().get(index).getLessonID());
		System.out.printf("LessonType of the %d th lesson: %s \n",(index+1),courses.getCourse(index).getLessons().get(index).getLessonType());
		System.out.printf("Total Size of the %d th lesson: %s \n",(index+1),courses.getCourse(index).getLessons().get(index).getTotalSize());
		System.out.printf("Vacancy of the %d th lesson: %s \n",(index+1),courses.getCourse(index).getLessons().get(index).getVacancy());
	}
}
