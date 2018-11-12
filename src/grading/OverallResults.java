package grading;

import java.util.ArrayList;

import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;

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
	
	public Grade computeGrade()
	{
		return Grade.F; // placeholder
	}
}
