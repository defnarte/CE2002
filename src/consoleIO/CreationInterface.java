package consoleIO;

import universityMembers.FacultyMember;
import courses.*;

/**
 * Control class for creation of course
 * 
 * @version 1.2
 * @since 2018/11/12
 * @author Jason
 *
 */
public class CreationInterface
{
	public static Course createCourse(FacultyMember courseCoordinator)
	{
		System.out.println("\n---Creating new course---");

		// start of getting course meta data
		System.out.print("Enter course code: ");
		String courseCode = ConsoleInputInterface.consoleScanner.nextLine();

		System.out.print("Enter course name: ");
		String courseName = ConsoleInputInterface.consoleScanner.nextLine();

		String intakePrompt = "Enter number of intake for " + courseCode + ": ";
		int maxNumOfIntakes = ConsoleInputInterface.getUserPositiveIntInput(intakePrompt);
		// end of getting course meta data

		Course newCourse = new Course(courseCode, courseName, courseCoordinator, maxNumOfIntakes);

		createCourseComponents(newCourse);

		System.out.println("\nCreated course:\n" + newCourse.toString());
		
		return newCourse;
	}

	public static void createCourseComponents(Course course)
	{
		String numOfComponentsPrompt = "Enter number of components for " + course.getCourseCode() + ": ";
		int numOfComponents = ConsoleInputInterface.getUserPositiveIntInput(numOfComponentsPrompt);

		int componentsTotalWeightage = 100;

		for (int i = 1; i <= numOfComponents; ++i)
		{
			System.out.println("\n---Creating component " + i + "---");

			// start of getting component's meta data
			System.out.print("Enter the name of this component: ");
			ConsoleInputInterface.consoleScanner.nextLine(); // read in the newline char in buffer
			String componentName = ConsoleInputInterface.consoleScanner.nextLine();

			String componentWeightagePrompt = "Enter the weightage of " + componentName + " out of 100 ("
					+ componentsTotalWeightage + " remaining): ";
			int componentWeightage = ConsoleInputInterface.getUserPositiveIntInput(componentWeightagePrompt,
					componentsTotalWeightage);
			// end of getting component's meta data

			componentsTotalWeightage -= componentWeightage;

			String typeOfComponentPrompt = "Is " + componentName
					+ ":\n(1) standalone, or \n(2) made up of subcomponents?\nEnter your choice: ";
			int userChoice = ConsoleInputInterface.getUserPositiveIntInput(typeOfComponentPrompt);

			if (userChoice == 1)
			{
				ComponentWeightage newComponentWeightage = new ComponentWeightage(componentName, componentWeightage);
				course.addComponentWeightage(newComponentWeightage);
			}
			else
			{
				AggregateComponentWeightage newAggregateComponentWeightage = 
						new AggregateComponentWeightage(componentName, componentWeightage);

				String numOfSubcomponentsPrompt = "Enter number of subcomponents for " + 
													newAggregateComponentWeightage.getName() + ": ";
				int numOfSubcomponents = ConsoleInputInterface.getUserPositiveIntInput(numOfSubcomponentsPrompt);

				int subcomponentsTotalWeightage = 100;

				for (int j = 1; j <= numOfSubcomponents; ++j)
				{
					System.out.print("Enter the name of subcomponent " + j + ": ");
					ConsoleInputInterface.consoleScanner.nextLine(); // read in the newline char in buffer
					String subcomponentName = ConsoleInputInterface.consoleScanner.nextLine();

					String subcomponentWeightagePrompt = "Enter the weightage of " + 
							subcomponentName + " within " + newAggregateComponentWeightage.getName() + 
								" out of 100 (" + subcomponentsTotalWeightage + " remaining): ";
					int subcomponentWeightage = ConsoleInputInterface
							.getUserPositiveIntInput(subcomponentWeightagePrompt, subcomponentsTotalWeightage);
					
					subcomponentsTotalWeightage -= subcomponentWeightage;

					ComponentWeightage newSubcomponentWeightage = 
							new ComponentWeightage(subcomponentName, subcomponentWeightage);
					newAggregateComponentWeightage.addSubcomponentWeightage(newSubcomponentWeightage);
					
					if(subcomponentsTotalWeightage == 0)
					{
						System.out.println(); // just printing newline
						break;
					}
						
				}

				course.addComponentWeightage(newAggregateComponentWeightage);
			}
			
			if(componentsTotalWeightage == 0)
				break;
		}

	}
}
