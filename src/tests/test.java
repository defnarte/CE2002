package tests;
import java.io.IOException;
import java.util.ArrayList;

import Course;
import CourseDB;
import Student;
import StudentDB;

public class test {
	public static void main(String args[]) throws IOException {
		String filename = "Students.txt";
		ArrayList<Student> StudentAl = StudentDB.readStudents(filename);
		Student stu = StudentAl.get(0);
		
		System.out.println("Printing details for student:");
		System.out.println(stu.getName());
		System.out.println(stu.getID());
		System.out.println(stu.getCourses());
		System.out.println(stu.getLessons());
		
		
		System.out.println("");
		
		String filename2 = "Courses.txt";
		ArrayList<Course> CoursesAl = CourseDB.readCourses(filename2);
		Course course = CoursesAl.get(0);
		
		System.out.println("Printing details for course:");
		System.out.println(course.getCourse());
		System.out.println(course.getCourseID());
		System.out.println(course.getmatNos());
		System.out.println(course.getMarks().get(0)[0].getCourseWorkMarks());
		System.out.println(course.getMarks().get(0)[0].getWeightage());
		System.out.println(course.getMarks().get(1)[0].getCourseWorkMarks());
		System.out.println(course.getMarks().get(1)[0].getWeightage());
		System.out.println(course.getLessons().get(0).getLessonID());
		System.out.println(course.getLessons().get(0).getLessonType());
		System.out.println(course.getLessons().get(0).getVacancy());
	}
}
