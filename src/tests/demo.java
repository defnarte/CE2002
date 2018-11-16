package tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import consoleIO.ConsoleInputInterface;
import courses.Course;
import database.DatabaseIO;
import grading.ComponentResult;
import lessons.Lesson;
import registration.CourseRegistrationRecord;
import universityMembers.Student;

public class demo {
	public static void main(String args[]) throws IOException {
		
		String studentFilename = "Students.txt";
		String courseFilename = "Courses.txt";
		
		DatabaseIO database = new DatabaseIO();
		ArrayList<Course> courses = null/* = database.readCourseDB(courseFilename)*/;
		ArrayList<Student> students = database.readStudentDB(studentFilename,courses);
		
		
//		int index = 1;
//		System.out.println(studentAl.get(index).getfullName());
//		System.out.println(studentAl.get(index).getID());
//		System.out.println(studentAl.get(index).getCoursesRegistered().get(1).getOverallResults().getMarks());
//		
//		System.out.println(courseAl.get(index).getLessons().get(index).getLessonID());
//		System.out.println(courseAl.get(index).getLessons().get(index).getLessonType());
//		System.out.println(courseAl.get(index).getLessons().get(index).getTotalSize());
//		System.out.println(courseAl.get(index).getLessons().get(index).getVacancy());
		
		System.out.println("Enter the course code: ");
		String courseCode = ConsoleInputInterface.consoleScanner.next();	
		
		// Tasks 4 and 5
		for (int i=0; i<courses.size(); i++) 
		{
			if (courses.get(i).getCourseCode().equals(courseCode)) 
			{
				System.out.println("All lesson types under this course: ");
				ArrayList<Lesson> lessons = courses.get(i).getLessons();
				ArrayList<String> lessonListType = new ArrayList<String>();
				for (int j = 0; j<lessons.size(); j++) 
				{
					lessonListType.add(lessons.get(j).getLessonType());
				}
				// Find all unique lesson types
				Set<String> uniqueLessonType = new HashSet<String>(lessonListType);
				Iterator<String> it = uniqueLessonType.iterator();
				while (it.hasNext()) 
				{
					System.out.println(it.next());
				}
				System.out.println("Enter a lesson type to print by: ");
				String lessonType = ConsoleInputInterface.consoleScanner.next();
				System.out.printf("All indexes for %s:\n",lessonType);
				for (Lesson lesson : courses.get(i).getLessons()) 
				{
					System.out.println(lesson.getLessonID());
				}
				System.out.println("Select an option:");
				System.out.println("1 - Print student list by index");
				System.out.println("2 - Print all students");
				System.out.println("3 - Check avaliable slot");
				int choice = ConsoleInputInterface.consoleScanner.nextInt();
				switch (choice) 
				{
					case 1:
						System.out.println("Enter an index: ");
						String lessonIndex = ConsoleInputInterface.consoleScanner.next();
						courses.get(i).printSomeStudents(lessonIndex);
						break;
					case 2:
						courses.get(i).printAllStudents(lessonType);
						break;
					case 3: 
						System.out.println("Enter an index: ");
						String lessonIndex2 = ConsoleInputInterface.consoleScanner.next();
						int vacancy = courses.get(i).getLesson(lessonIndex2).getVacancy();
						int totalSize = courses.get(i).getLesson(lessonIndex2).getTotalSize();
						System.out.printf("%s %d/%d\n",lessonIndex2,vacancy,totalSize);
						break;
					default:
						break;
				}
			}			
		}
		// Tasks 9 and 10
		System.out.println("Enter the course code: ");
		courseCode = ConsoleInputInterface.consoleScanner.next();	
		for (int i=0; i<courses.size(); i++) 
		{
			if (courses.get(i).getCourseCode().equals(courseCode)) 
			{

				System.out.println("Printing Course Statistics:");
				ArrayList<CourseRegistrationRecord> courseRegistrations = courses.get(i).getRegistrations();
				double[] mean = new double[courseRegistrations.get(0).getOverallResults().getComponentResultList().size()];
				double overall = 0;  
				for (int j = 0; j<courseRegistrations.size(); j++) 
				{
					ArrayList<ComponentResult> resultsList = courseRegistrations.get(j).getOverallResults().getComponentResultList();
					overall += courseRegistrations.get(j).getOverallResults().getMarks();
					for (int k=0; k<resultsList.size(); k++) {
						mean[k] += resultsList.get(k).getMarks();
					}
				}
				System.out.println("Overall mean: " + overall/courseRegistrations.size());
				int numberOfComponents = courseRegistrations.get(0).getOverallResults().getComponentResultList().size();
				for (int l=0; l<courseRegistrations.get(0).getOverallResults().getComponentResultList().size(); l++) {
					
					System.out.println("Mean for " +courseRegistrations.get(0).getOverallResults().getComponentResultList().get(l).getName() + " : " + mean[l]/numberOfComponents);
				}
			}
		}
		System.out.println("Enter the StudentID: ");
		String studentID = ConsoleInputInterface.consoleScanner.next();	
		for (int i=0; i<students.size(); i++) 
		{
			if (students.get(i).getID().equals(studentID)) 
			{

				System.out.println("Printing Student Transcript for " + students.get(i).getID() + ", " + students.get(i).getfullName() + ":");
				ArrayList<CourseRegistrationRecord> coursesRegistered = students.get(i).getCoursesRegistered();
				for (CourseRegistrationRecord courseRegistered:coursesRegistered) 
				{
					System.out.println(courseRegistered.getRegisteredCourse().getCourseCode() + " " 
							+ courseRegistered.getRegisteredCourse().getName() + " "
							+ courseRegistered.getOverallResults().getMarks() + " "
							+ courseRegistered.getOverallResults().computeGrade());
					ArrayList<ComponentResult> componentResultList = courseRegistered.getOverallResults().getComponentResultList();
					for (ComponentResult componentResult:componentResultList) {
						System.out.println("       " + componentResult.getName() + " " + componentResult.getMarks());
					}
				}
			}
		}
	}
}
