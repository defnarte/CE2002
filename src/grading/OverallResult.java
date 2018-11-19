package grading;

import java.util.ArrayList;

import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;

/**
 * This entity class holds the overall result obtained by a Student for a certain Course.
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
	/**
	 * This constructor takes in componentWeightageList as template for initialisation of its
	 * componentResultList.
	 * 
	 * @param componentWeightageList
	 */
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
	
	/**
	 * Setter for componentResultList.
	 * 
	 * @param componentResultList
	 */
	public void setOverallResults(ArrayList<ComponentResult> componentResultList)
	{
		this.componentResultList = componentResultList;
	}
	
	@Override
	/**
	 * Overrided getMarks method that return an overall marks that takes all components and their
	 * weightages into account.
	 */
	public double getMarks()
	{
		double overallMarks = 0;
		
		for(ComponentResult componentResult: componentResultList)
		{
			overallMarks += componentResult.computeWeightedMarks();
		}
		
		return overallMarks;
	}
	/**
	 * Getter for componentResultList.
	 * 
	 * @return ArrayList of ComponentResult
	 */
	public ArrayList<ComponentResult> getComponentResultList()
	{
		return componentResultList;
	}
	
	/**
	 * This method searches for a ComponentResult that matches the target name passed in as parameter,
	 * and set its marks to raw marks.
	 * 
	 * @param componentName
	 * @param rawMarks
	 * @return true if Component Result has been found and set, and false otherwise
	 */
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
	
	/**
	 * This method returns a Component that matches the target name passed in, 
	 * or null if the Component cannot be found.
	 * 
	 * @param componentName
	 * @return ComponentResult that matches the target name, or null if ComponentResult cannot be found
	 */
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
	/**
	 * Overrided computeGrade method that return a Grade based on the the overall marks and the Grade Scoring Table.
	 * 
	 * @return Grade that student got for a Course
	 */
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
	/**
	 * Overrided toString method that returns the overall marks obtained as well as the corresponding Grade as String.
	 * 
	 * @return overall marks and Grade
	 */
	public String toString()
	{
		return "Overall Result: " + getMarks() + "% (Grade: " + computeGrade() + ')';
	}
}
