package consoleIO;

import courses.ComponentWeightage;
import grading.Markable;
import registration.*;

/**
 * 
 * @version 1.6
 * @since 2018/11/13
 * @author Jason
 *
 */
public class MarksEntryInterface
{
	public static void enterMarks(CourseRegistrationRecord record)
	{
		boolean markEntrySucess = false;
		
		do
		{
			System.out.println("For which component (or subcomponent) do you want to enter marks?");
			
			for(ComponentWeightage componentWeightage: record.getRegisteredCourse().getAllComponentsWeightage())
			{
				System.out.println(componentWeightage);
			}
			
			System.out.print("Enter your choice: ");
			String componentNameInput = ConsoleInputInterface.consoleScanner.nextLine();
			
			String markEntryPrompt = "Enter the raw marks (out of " + Markable.MAX_MARKS +") for " + componentNameInput + ": ";
			int rawMarks = ConsoleInputInterface.getUserPositiveIntInput(markEntryPrompt, Markable.MAX_MARKS);
			
			markEntrySucess = record.setTargetComponentResult(componentNameInput, rawMarks);
			
			if(markEntrySucess)
			{
				System.out.println("Mark entry successful");
			}
			else
			{
				System.out.println("Mark entry failed");
			}
		} while(!markEntrySucess);
		
		System.out.println("Results for " + record.getRegisteredCourse().getCourseCode() + 
							":\n" + record.getOverallResults());
	}
}
