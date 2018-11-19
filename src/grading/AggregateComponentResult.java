package grading;

import java.util.ArrayList;

import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;

/**
 * This entity class holds an ArrayList of subcomponentResult.
 * An example of Aggregate Component: Coursework
 *
 */
public class AggregateComponentResult extends ComponentResult
{
	private ArrayList<ComponentResult> subcomponentResultList;
	
	/**
	 * This constructor initialises its weightage and subcomponentResultList 
	 * based on the weightage passed in to it.
	 * 
	 * @param weightage
	 */
	public AggregateComponentResult(AggregateComponentWeightage weightage)
	{
		super(weightage);
		subcomponentResultList = new ArrayList<ComponentResult>();
		
		for(ComponentWeightage subcomponentWeightage: weightage.getSubcomponentWeightageList())
		{
			ComponentResult subcomponentResult = new ComponentResult(subcomponentWeightage, 0);
			subcomponentResultList.add(subcomponentResult);
		}
		
	}
	
	/**
	 * This method gets the target subcomponent Result based on the target subcomponent name passed in as parameter.
	 * If a subcomponent's name matches the target name, the subcomponent is returned.
	 * Otherwise, if none of the subcomponents' names match, null is return.
	 * 
	 * @param targetName
	 * @return subcomponent if it can be found, and null otherwise
	 */
	public ComponentResult getSubcomponentResult(String targetName)
	{
		for(ComponentResult subcomponentResult: subcomponentResultList)
		{
			if(subcomponentResult.getName() == targetName)
			{
				return subcomponentResult;
			}
		}
		
		return null;
	}
	
	/**
	 * This method searches for a target subcomponent result, and set its marks to the raw marks passed in.
	 * 
	 * @param targetResult
	 * @param rawMarks
	 * @return true if target subcomponent can be found, and false otherwise
	 */
	public boolean setSubcomponentResult(ComponentResult targetResult, int rawMarks)
	{
		for(ComponentResult subcomponentResult: subcomponentResultList)
		{
			if(subcomponentResult.getName() == targetResult.getName())
			{
				subcomponentResult.setMarks(rawMarks);
				return true;
			}
		}
		
		return false;
	}
	/**
	 * This method searches for a target subcomponent result based on the name passed in, 
	 * and set its marks to the raw marks passed in.
	 * 
	 * @param targetResult
	 * @param rawMarks
	 * @return true if target subcomponent can be found, and false otherwise
	 */
	public boolean setSubcomponentResult(String targetName, int rawMarks)
	{
		for(ComponentResult subcomponentResult: subcomponentResultList)
		{
			if(subcomponentResult.getName() == targetName)
			{
				subcomponentResult.setMarks(rawMarks);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Getter for subcomponentResultList.
	 * 
	 * @return an ArrayList of ComponentResult
	 */
	public ArrayList<ComponentResult> getSubcomponentResultList()
	{
		return subcomponentResultList;
	}
	
	@Override
	/**
	 * Overrided getter for weightage.
	 * 
	 * @return weightage
	 */
	public int getWeightage()
	{
		return super.getWeightage();
	}

	@Override
	/**
	 * Overrided getMarks method that returns the marks for this Aggregate Component by taking each 
	 * Subcomponent and their weightage into account.
	 * 
	 * @return an overall marks for this Aggregate Component
	 */
	public double getMarks()
	{
		int aggregatedMarks = 0;
		
		for(ComponentResult subcomponentResult: subcomponentResultList)
		{
			aggregatedMarks += subcomponentResult.computeWeightedMarks();
		}
		
		return aggregatedMarks;
	}
	
	@Override
	/**
	 * Overrided computeMarks method that returns the overall marks for this Component multiplied
	 * by its weightage.
	 * 
	 * @return marks for this Component with its weightage factored in
	 */
	public double computeWeightedMarks()
	{
		return super.getWeightage() * getMarks() / 100.0;
	}
	
	@Override
	/**
	 * Overrided toString that returns this Component's name and weightage, as well as its subcomponents'
	 * names and weightage.
	 * 
	 * @return this Component's name and weightage, and subcomponents' names and weightage.
	 */
	public String toString()
	{
		String subcomponentString = "";
		
		for(ComponentResult subcomponentResult: subcomponentResultList)
		{
			subcomponentString += "\t" + subcomponentResult.toString();
		}
		
		return super.toString() + subcomponentString;
	}
}
