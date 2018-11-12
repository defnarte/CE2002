package tests;

import courses.ComponentWeightage;
import courses.Course;
import consoleIO.ConsoleInputInterface;
import consoleIO.CreationInterface;
import grading.OverallResults;
import registration.*;
import universityMembers.FacultyMember;
import universityMembers.Student;

/**
 * 
 * @version 1.5
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
		System.out.println(testCourse);
		
//		testCourse.setAllComponentsWeightage();
//		System.out.println(testCourse);
		
		Student testStudent = new Student("S9876543A","John Doe");
		System.out.println(testStudent + " constructed successfully");
		
		CourseRegistrationRecord newRecord = CourseRegistrationHandler.register(testStudent, testCourse);
		System.out.println(newRecord + " constructed successfully");
		
		System.out.println("For which component (or subcomponent) do you want to enter results?");
		
		for(ComponentWeightage componentWeightage: newRecord.getRegisteredCourse().getAllComponentsWeightage())
		{
			System.out.println(componentWeightage);
		}
		
		System.out.print("Enter your choice: ");
		if(ConsoleInputInterface.consoleScanner.hasNextLine())
			ConsoleInputInterface.consoleScanner.nextLine(); // read in newline char in buffer
		String componentNameInput = ConsoleInputInterface.consoleScanner.nextLine();
		
		String markEntryPrompt = "Enter the raw marks (out of 100) for " + componentNameInput + ": ";
		int rawMarks = ConsoleInputInterface.getUserPositiveIntInput(markEntryPrompt, 100);
		
		newRecord.setTargetComponentResult(componentNameInput, rawMarks);
		
		System.out.println(newRecord.getOverallResults());
	}
}
