package grading;

import consoleIO.ConsoleInputInterface;
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
	/**
	 * Enter the marks for a course, for each of its components.
	 * @param record The registration record shared by the student and the course.
	 */
	public static void enterMarksForCourse(Registration record)
	{
		for(ComponentResult componentResult: record.getOverallResult().getComponentResultList())
			enterMarksForComponent(componentResult);
		
		System.out.println("Results for " + record.getRegisteredCourse().getCourseCode() + 
							":\n" + record.getOverallResult());
	}
	/**
	 * Enter the marks for a specific component in a course.
	 * @param componentResult The component result which is part of OverallResults in a registration record.
	 */
	private static void enterMarksForComponent(ComponentResult componentResult)
	{
		if(componentResult instanceof AggregateComponentResult)
		{
			AggregateComponentResult aggregateComponentResult = (AggregateComponentResult) componentResult;
			
			for(ComponentResult subcomponentResult: aggregateComponentResult.getSubcomponentResultList())
				enterMarksForComponent(subcomponentResult);
		}
		else
		{
			String markEntryPrompt = "Enter the raw marks (out of " + Markable.MAX_MARKS + 
					") for " + componentResult.getName() + ": ";
			int rawMarks = ConsoleInputInterface.getUserPositiveIntInput(markEntryPrompt, Markable.MAX_MARKS);
			componentResult.setMarks(rawMarks);
		}
	}

}
