package grading;

import java.util.ArrayList;

import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;

public class AggregateComponentResult implements IWeightedMarks
{
	AggregateComponentWeightage weightage;
	ArrayList<ComponentResult> subcomponentResultList;
	
	public AggregateComponentResult(AggregateComponentWeightage weightage)
	{
		this.weightage = weightage;
		
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
	
	public int getWeightage()
	{
		return weightage.getWeightage();
	}

	public double getAggregatedMarks()
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
		return weightage.getWeightage() * getAggregatedMarks() / 100.0;
	}
}
