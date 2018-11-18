package creation;

import java.util.ArrayList;
import java.util.HashSet;

import consoleIO.ConsoleInputInterface;
import consoleIO.StringFormatType;
import courses.*;
import database.CourseDB;
import database.StudentDB;
import grading.Markable;
import lessons.Lesson;
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
		// no duplicate student id (matriculation number) allowed
		String studentIDPrompt = "Enter the student's ID:";
		String studentID;
		do
		{
			studentID = ConsoleInputInterface.getUserStringInput(studentIDPrompt, StringFormatType.ALPHA_NUMERIC);
			
			if(studentDB.checkStudentIDExists(studentID))
				System.out.println("Student ID already exist in student database.");
			
		} while(studentDB.checkStudentIDExists(studentID));
		
		// students can share the same name
		String studentNamePrompt = "Enter the name of the student:";
		String studentName = ConsoleInputInterface.getUserStringInput(studentNamePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);
		
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

		// no duplicate course code allowed
		String courseCodePrompt = "Enter course code: ";
		String courseCode;
		do
		{
			courseCode = ConsoleInputInterface.getUserStringInput(courseCodePrompt, StringFormatType.ALPHA_NUMERIC);
			
			if(courseDB.checkCourseCodeExists(courseCode))
				System.out.println("Course code already exist in course database");
			
		} while(courseDB.checkCourseCodeExists(courseCode));

		// courses can share the same name
		String courseNamePrompt ="Enter course name: ";
		String courseName = ConsoleInputInterface.getUserStringInput(courseNamePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);
		

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

		String componentNamePrompt = "Enter the name of component " + componentIndex + ": ";
		String componentName = ConsoleInputInterface.getUserStringInput(componentNamePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);

		String componentWeightagePrompt = "Enter the weightage of " + componentName + 
				" out of " + Markable.MAX_MARKS +" (" + componentsTotalWeightage + " remaining): ";
		int componentWeightage = ConsoleInputInterface.
				getUserPositiveIntInput(componentWeightagePrompt,componentsTotalWeightage);

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
		String subcomponentNamePrompt = "Enter the name of subcomponent " + indexWithinRoot + 
				" within " + rootComponent.getName() + ": ";
		String subcomponentName = ConsoleInputInterface.
				getUserStringInput(subcomponentNamePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);

		String subcomponentWeightagePrompt = "Enter the weightage of " + subcomponentName + " within " + 
				rootComponent.getName() + " out of " + Markable.MAX_MARKS + " (" + subcomponentsTotalWeightage + " remaining): ";
		int subcomponentWeightage = ConsoleInputInterface.
				getUserPositiveIntInput(subcomponentWeightagePrompt,subcomponentsTotalWeightage);

		return new ComponentWeightage(subcomponentName, subcomponentWeightage);
	}
	
	public static String createLessonTypeForCourse(Course course, HashSet<String> uniqueLessonTypes)
	{
		String lessonTypePrompt = "Enter the type of lesson to add: ";
		String lessonType;
		boolean addLessonTypeSuccess;
		
		do
		{
			lessonType = ConsoleInputInterface.
					getUserStringInput(lessonTypePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);
			addLessonTypeSuccess = uniqueLessonTypes.add(lessonType);
			
			if(!addLessonTypeSuccess)
				System.out.println(course + " already has " + lessonType + " lessons");
			
		} while(!addLessonTypeSuccess);
		
		return lessonType;
	}
	
	public static Lesson createLessonForCourse(CourseDB courseDB, String lessonType, int lessonIndex)
	{
		String lessonIDPrompt = "Enter lessonID for " + lessonType + ' ' + lessonIndex + ": ";
		int lessonID;
		boolean lessonExists;
		
		do
		{
			lessonID = ConsoleInputInterface.getUserPositiveIntInput(lessonIDPrompt);
			
			lessonExists = courseDB.checkLessonExists(lessonID);
		
			if(lessonExists)
				System.out.println(lessonID + " already exists in course database");
		
		} while(lessonExists);
		
		int totalSize = ConsoleInputInterface.getUserPositiveIntInput("Enter number of vacancies:");
		return new Lesson(lessonID,lessonType,totalSize);
	}
	
	public static ArrayList<Integer> createRegisteredLessonArrayList(Course course)
	{
		ArrayList<Integer> registeredLessonArrayList = new ArrayList<Integer>();
		
		for (String lessonType : course.getLessonTypes())
		{
			System.out.println("List of lessonID for " + lessonType + ":");
			course.printLessonsByType(lessonType);
			
			String lessonIDPrompt = "Enter lessonID of " + lessonType + "to register for: ";
			int lessonID;
			boolean lessonHasVacancy;
			
			do
			{
				lessonID = ConsoleInputInterface.getUserPositiveIntInput(lessonIDPrompt);
				
				lessonHasVacancy = course.getLesson(lessonID).decrementVacancy();
				if(!lessonHasVacancy)
					System.out.println(lessonID + " has no more vacancy.");
				
			} while(!lessonHasVacancy);
				
			registeredLessonArrayList.add(lessonID);
		}
		
		return registeredLessonArrayList;
	}
}
