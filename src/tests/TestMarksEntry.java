package tests;

import courses.Course;
import consoleIO.CreationInterface;
import grading.OverallResults;
import universityMembers.FacultyMember;

/**
 * 
 * @version 1.0
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
		System.out.println(testCourse + " constructed successfully");
		
	}
}
