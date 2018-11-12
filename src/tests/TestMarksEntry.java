package tests;

import courses.Course;
import consoleIO.CreationInterface;
import grading.OverallResults;
import registration.*;
import universityMembers.FacultyMember;
import universityMembers.Student;

/**
 * 
 * @version 1.1
 * @since 2018/11/12
 * @author Jason
 *
 */
public class TestMarksEntry
{
	public static void main(String[] args)
	{
		FacultyMember testCoordinator = new FacultyMember("S1234567","Jane Doe");
		System.out.println(testCoordinator + " constructed successfully");
		
		Course testCourse = CreationInterface.createCourse(testCoordinator);
		System.out.println(testCourse.getCourseCode() + ' ' + testCourse.getName() + " constructed successfully");
		
		Student testStudent = new Student("S9876543A","John Doe");
		
		CourseRegistrationRecord newRecord = CourseRegistrationHandler.register(testStudent, testCourse);
		System.out.println(newRecord + " constructed successfully");
		
		
	}
}
