package grading;

import java.util.ArrayList;

import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;

/**
 * 
 * @version 1.1
 * @since 2018/11/12
 * @author Jason
 *
 */
public class OverallResults implements Gradeable
{
	private ArrayList<ComponentResult> componentResultList;
	
	public OverallResults(ArrayList<ComponentWeightage> componentWeightageList)
	{
		componentResultList = new ArrayList<ComponentResult>();
		
		for(ComponentWeightage componentWeightage: componentWeightageList)
		{
			ComponentResult newComponentResult;
			
			if(componentWeightage instanceof AggregateComponentWeightage)
			{
				newComponentResult = new AggregateComponentResult((AggregateComponentWeightage)componentWeightage);
			}
			else
			{
				newComponentResult = new ComponentResult(componentWeightage);
			}
			
			componentResultList.add(newComponentResult);
		}
	}
	
	public double getOverallMarks()
	{
		double overallMarks = 0;
		
		for(ComponentResult componentResult: componentResultList)
		{
			overallMarks += componentResult.computeWeightedMarks();
		}
		
		return overallMarks;
	}
	public ArrayList<ComponentResult> getComponentResultList()
	{
		return componentResultList;
	}
	public boolean setTargetComponentResult(String targetName, int rawMarks)
	{
		for(ComponentResult componentResult: componentResultList)
		{
			if(componentResult.getName() == targetName && !(componentResult instanceof AggregateComponentResult))
			{
				componentResult.setMarks(rawMarks);
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Grade computeGrade()
	{
		return Grade.F; // placeholder
	}
	
	@Override
	public String toString()
	{
		String overallResultsString = "";
		
		for(ComponentResult componentResult: componentResultList)
		{
			overallResultsString += componentResult.toString();
		}
		
		return overallResultsString;
	}
}
