package grading;

import java.util.ArrayList;

import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;

/**
 * 
 * @version 1.2
 * @since 2018/11/16
 * @author Jason
 *
 */
public class OverallResult implements Markable
{
	private ArrayList<ComponentResult> componentResultList;
	
	/**
	 * Default constructor for OverallResult
	 */
	public OverallResult()
	{
		this.componentResultList = new ArrayList<ComponentResult>();
	}
	public OverallResult(ArrayList<ComponentWeightage> componentWeightageList)
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
	
	public void setOverallResults(ArrayList<ComponentResult> componentResultList)
	{
		this.componentResultList = componentResultList;
	}
	
	@Override
	public double getMarks()
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
	
	public boolean setComponentResult(String componentName, int rawMarks)
	{
		for(ComponentResult componentResult: componentResultList)
		{
			if(componentResult.getName().equals(componentName) && !(componentResult instanceof AggregateComponentResult))
			{
				componentResult.setMarks(rawMarks);
				return true;
			}
		}
		
		return false;
	}
	
	public ComponentResult getComponentResult(String componentName)
	{
		for(ComponentResult componentResult: componentResultList)
		{
			if(componentResult.getName().equals(componentName))
			{
				return componentResult;
			}
		}
		
		return null;
	}
	
	@Override
	public Grade computeGrade()
	{
		int marks = (int) Math.round(getMarks());
		if (marks < 40) 
		{
			return Grade.values()[0];
		}
		else if (marks >= 85)
		{
			return Grade.values()[10];
		}
		else 
		{
			// how does this work?
			marks = ((int) Math.round(getMarks())-40)/5 + 1;
			return Grade.values()[marks];
		}
	}
	
	@Override
	public String toString()
	{
		return "Overall Result: " + getMarks() + "% (Grade: " + computeGrade() + ')';
	}
}
