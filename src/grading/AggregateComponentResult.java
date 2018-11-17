package grading;

import java.util.ArrayList;

import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;

/**
 * 
 * @version 1.0
 * @since 2018/11/12
 * @author Jason
 *
 */
public class AggregateComponentResult extends ComponentResult
{
	private ArrayList<ComponentResult> subcomponentResultList;
	
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
	
	public ArrayList<ComponentResult> getSubcomponentResultList()
	{
		return subcomponentResultList;
	}
	
	public int getWeightage()
	{
		return super.getWeightage();
	}

	@Override
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
	public double computeWeightedMarks()
	{
		return super.getWeightage() * getMarks() / 100.0;
	}
	
	@Override
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
