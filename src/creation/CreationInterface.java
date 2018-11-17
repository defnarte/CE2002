package creation;

import consoleIO.ConsoleInputInterface;
import consoleIO.StringFormatType;
import courses.*;
import database.CourseDB;
import database.StudentDB;
import grading.Markable;
import universityMembers.Student;

/**
 * This class displays output and get input from the user to create classes.
 * he/she wants.
 * 
 * @version 1.0
 * @since 2018/11/16
 * @author Jason
 *
 */
public class CreationInterface
{
	
	public static Student setStudentMetadata(StudentDB studentDB)
	{
		String studentNamePrompt = "Enter the name of the student:";
		String studentName = ConsoleInputInterface.getUserStringInput(studentNamePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);
		while (studentDB.checkStudentName(studentName))
		{
			studentNamePrompt = "Name already registered. Enter the student's name:";
			studentName = ConsoleInputInterface.getUserStringInput(studentNamePrompt, StringFormatType.ALPHA_NUMERIC);
		}
		
		String studentIDPrompt = "Enter the student's ID:";
		String studentID = ConsoleInputInterface.getUserStringInput(studentIDPrompt, StringFormatType.ALPHA_NUMERIC);
		while (studentDB.checkStudentID(studentID))
		{
			studentIDPrompt = "ID already registered. Enter the student's ID:";
			studentID = ConsoleInputInterface.getUserStringInput(studentIDPrompt, StringFormatType.ALPHA_NUMERIC);
		}
		return new Student(studentID, studentName);
	}
	
	/**
	 * This method sets a Course's metadata based on user input when the user is creating course.
	 * course metadata:
	 * 1. course code
	 * 2. course name
	 * 3. course maximum intake
	 * 
	 * @return newly created course
	 */
	public static Course setCourseMetadata(CourseDB courseDB)
	{
		System.out.println("\n---Creating new course---");

		System.out.print("Enter course code: ");
		String courseCode = ConsoleInputInterface.consoleScanner.nextLine();
		
		while (courseDB.checkCourseCode(courseCode))
		{
			System.out.print("Course code already registered. Enter course code: ");
			courseCode = ConsoleInputInterface.consoleScanner.nextLine();
		}

		System.out.print("Enter course name: ");
		String courseName = ConsoleInputInterface.consoleScanner.nextLine();
		
		while (courseDB.checkCourseName(courseName))
		{
			System.out.print("Course name already registered. Enter course name: ");
			courseName = ConsoleInputInterface.consoleScanner.nextLine();
		}

		return new Course(courseCode, courseName);
	}
	
	/**
	 * This method sets a Component's metadata based on user input when the user is creating course's component.
	 * component metadata:
	 * 1. component metadata
	 * 2. component weightage
	 * 3. whether component is standalone or an aggregate component
	 * 
	 * @return newly created component of the right type
	 */
	public static ComponentWeightage setComponentMetadata(int componentIndex, int componentsTotalWeightage)
	{
		System.out.println("\n---Creating component " + componentIndex + "---");

		System.out.print("Enter the name of component " + componentIndex + ": ");
		String componentName = ConsoleInputInterface.consoleScanner.nextLine();

		String componentWeightagePrompt = "Enter the weightage of " + componentName + 
				" out of " + Markable.MAX_MARKS +" (" + componentsTotalWeightage + " remaining): ";
		int componentWeightage = ConsoleInputInterface.getUserPositiveIntInput(componentWeightagePrompt,
				componentsTotalWeightage);

		String typeOfComponentPrompt = "Is " + componentName
				+ ":\n(1) standalone, or \n(2) made up of subcomponents?\nEnter your choice: ";
		int userChoice = ConsoleInputInterface.getUserPositiveIntInput(typeOfComponentPrompt, 2);
		
		if (userChoice == 1)
			return new ComponentWeightage(componentName, componentWeightage);
		else
			return new AggregateComponentWeightage(componentName, componentWeightage);
	}
	
	/**
	 * This method sets a Subcomponent's metadata based on user input when the user is creating Component's subcomponent.
	 * subcomponent metadata:
	 * 1. subcomponent metadata
	 * 2. subcomponent weightage
	 * 
	 * @return newly created component of the right type
	 */
	public static ComponentWeightage setSubcomponentMetadata(AggregateComponentWeightage rootComponent, 
			int indexWithinRoot, int subcomponentsTotalWeightage)
	{
		System.out.print("Enter the name of subcomponent " + indexWithinRoot + 
				" within " + rootComponent.getName() + ": ");

		String subcomponentName = ConsoleInputInterface.consoleScanner.nextLine();

		String subcomponentWeightagePrompt = "Enter the weightage of " + subcomponentName + " within "
				+ rootComponent.getName() + " out of " + Markable.MAX_MARKS + " ("
				+ subcomponentsTotalWeightage + " remaining): ";
		int subcomponentWeightage = ConsoleInputInterface.getUserPositiveIntInput(subcomponentWeightagePrompt,
				subcomponentsTotalWeightage);

		return new ComponentWeightage(subcomponentName, subcomponentWeightage);
	}
}
