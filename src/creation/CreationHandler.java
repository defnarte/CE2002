package creation;

import consoleIO.ConsoleInputInterface;
import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;
import courses.Course;
import database.FacultyDB;
import grading.Markable;
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
	public static FacultyMember createFacultyMember(FacultyDB faculty)
	{
		FacultyMember facultyMem;
		
		do
		{
			String courseCoordinatorPrompt = "Enter the ID of the course coordinator";
			String facultyID = ConsoleInputInterface.getUserStringInput(courseCoordinatorPrompt, 2);
			facultyMem = faculty.getFacultyMember(facultyID);
			
		} while(facultyMem ==null);
		
		return facultyMem;
	}
	
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
		
		createCourseComponents(newCourse);

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
}
