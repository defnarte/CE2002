package creation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import consoleIO.ConsoleInputInterface;
import consoleIO.StringFormatType;
import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;
import courses.Course;
import database.CourseDB;
import grading.Markable;
import lessons.Lesson;
import universityMembers.*;

/**
 * This class handles the creation of other Classes.
 * 
 * @version 1.4
 * @since 2018/11/16
 * @author Jason
 *
 */
public class CreationHandler
{
	public static Student createStudent()
	{
		Student newStudent = CreationInterface.setStudentMetadata();
		System.out.println("\nCreated student:\n" + newStudent.toString());
		
		return newStudent;
	}
	
	public static Course createCourse(FacultyMember courseCoordinator)
	{
		Course newCourse = CreationInterface.setCourseMetadata();
		newCourse.setCoordinator(courseCoordinator);
		
//		createCourseComponents(newCourse);

		System.out.println("\nCreated course:\n" + newCourse.toString());
		
		return newCourse;
	}

	public static void createCourseComponents(Course course)
	{
		String numOfComponentsPrompt = "Enter number of components for " + course.getCourseCode() + ": ";
		int numOfComponents = ConsoleInputInterface.getUserPositiveIntInput(numOfComponentsPrompt);

		int componentsTotalWeightage = Markable.MAX_MARKS;

		for (int componentIndex = 1; componentIndex <= numOfComponents; ++componentIndex)
		{
			ComponentWeightage newComponentWeightage = CreationInterface.
					setComponentMetadata(componentIndex, componentsTotalWeightage);
			
			componentsTotalWeightage -= newComponentWeightage.getWeightage();
			
			if (newComponentWeightage instanceof AggregateComponentWeightage)
			{
				AggregateComponentWeightage aggregateComponentWeightage = (AggregateComponentWeightage) newComponentWeightage;

				String numOfSubcomponentsPrompt = "Enter number of subcomponents for " + 
													newComponentWeightage.getName() + ": ";
				int numOfSubcomponents = ConsoleInputInterface.getUserPositiveIntInput(numOfSubcomponentsPrompt);

				int subcomponentsTotalWeightage = Markable.MAX_MARKS;

				for (int subComponentIndex = 1; subComponentIndex <= numOfSubcomponents; ++subComponentIndex)
				{
					ComponentWeightage newSubcomponentWeightage = 
							CreationInterface.setSubcomponentMetadata(aggregateComponentWeightage, 
									subComponentIndex, subcomponentsTotalWeightage);
					
					subcomponentsTotalWeightage -= newSubcomponentWeightage.getWeightage();
					
					aggregateComponentWeightage.addSubcomponentWeightage(newSubcomponentWeightage);
					
					if(subcomponentsTotalWeightage == 0)
					{
						//System.out.println(); // just printing newline
						break;
					}
						
				}

				
			}
			
			course.addComponentWeightage(newComponentWeightage);
			
			if(componentsTotalWeightage == 0)
			{
				//System.out.println(); // just printing newline
				break;
			}
		}

	}
	public static void createLessons(Course course,CourseDB courses) 
	{
		ArrayList<Lesson> lessons = new ArrayList<Lesson>();
		HashSet<String> uniqueLessonType = new HashSet<String>();
		boolean choice = true;
		do
		{
			String lessonType = ConsoleInputInterface.
					getUserStringInput("Enter the type of lesson to add:",StringFormatType.ALPHABETICAL_AND_SPACE);
			uniqueLessonType.add(lessonType);
			String numOfLessonsPrompt = "Enter the number of " + lessonType + "s to add:";
			int numOfLessons = ConsoleInputInterface.getUserPositiveIntInput(numOfLessonsPrompt);
			for (int i = 1; i <= numOfLessons; i++)
			{
				System.out.println("Getting input for " + lessonType + " " + i);
				String lessonID = ConsoleInputInterface.getUserStringInput("Enter lessonID:",StringFormatType.NUMERIC);
				while (courses.checkLesson(lessonID))
				{
					lessonID = ConsoleInputInterface.getUserStringInput("LessonID already registered. Please enter a different lessonID:",StringFormatType.NUMERIC);
				}
				int totalSize = ConsoleInputInterface.getUserPositiveIntInput("Enter number of vacancies:");
				Lesson lesson = new Lesson(lessonID,lessonType,totalSize);
				lessons.add(lesson);
			}
			choice = ConsoleInputInterface.getUserBooleanInput("Do you want to add another lesson (Y/N)? ");
		} while (choice);
		for (Lesson lesson:lessons) 
		{
			course.addLesson(lesson);
		}
		Iterator<String> it = uniqueLessonType.iterator();
		ArrayList<String> uniqueLessonTypeList = new ArrayList<String>();
		while (it.hasNext())
		{
			uniqueLessonTypeList.add((String) it.next().toString());
		}
		course.setLessonTypes(uniqueLessonTypeList);
		
	}
}
