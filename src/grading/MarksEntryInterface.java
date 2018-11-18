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
	public static void enterMarksForCourse(Registration record)
	{
		for(ComponentResult componentResult: record.getOverallResult().getComponentResultList())
			enterMarksForComponent(componentResult);
		
		System.out.println("Results for " + record.getRegisteredCourse().getCourseCode() + 
							":\n" + record.getOverallResult());
	}
	
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
