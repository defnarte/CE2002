import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import courses.Course;
import lessons.Lesson;
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
//		System.out.println(studentAl.get(index).getfullName());
//		System.out.println(studentAl.get(index).getID());
//		System.out.println(studentAl.get(index).getCoursesRegistered());
//		
//		System.out.println(courseAl.get(index).getLessons().get(index).getLessonID());
//		System.out.println(courseAl.get(index).getLessons().get(index).getLessonType());
//		System.out.println(courseAl.get(index).getLessons().get(index).getTotalSize());
//		System.out.println(courseAl.get(index).getLessons().get(index).getVacancy());
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the course code: ");
		String courseCode = sc.next();	
		
		for (int i=0; i<courseAl.size(); i++) {
			if (courseAl.get(i).getCourseCode().equals(courseCode)) {
				System.out.println("All lesson types under this course: ");
				ArrayList<Lesson> lessons = courseAl.get(i).getLessons();
				ArrayList<String> lessonListType = new ArrayList<String>();
				for (int j = 0; j<lessons.size(); j++) {
					lessonListType.add(lessons.get(j).getLessonType());
				}
				// Find all unique lesson types
				Set<String> uniqueLessonType = new HashSet<String>(lessonListType);
				Iterator<String> it = uniqueLessonType.iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
				}
				System.out.println("Enter a lesson type to print by: ");
				String lessonType = sc.next();
				System.out.printf("All indexes for %s:\n",lessonType);
				for (Lesson lesson : courseAl.get(i).getLessons()) {
					System.out.println(lesson.getLessonID());
				}
				System.out.println("Select an option:");
				System.out.println("1 - Print student list by index");
				System.out.println("2 - Print all students");
				int choice = sc.nextInt();
				switch (choice) {
					case 1:
						System.out.println("Enter an index: ");
						String lessonIndex = sc.next();
						courseAl.get(i).printSomeStudents(lessonIndex);
						break;
					case 2:
						courseAl.get(i).printAllStudents(lessonType);
					default: 
						break;
				}
			}			
		}
	}
}
