package grading;

import courses.ComponentWeightage;

public class ComponentResult implements IWeightedMarks
{
	ComponentWeightage weightage;
	private int rawMarks;
	
	public ComponentResult(ComponentWeightage weightage, int rawMarks)
	{
		this.weightage = weightage;
		this.rawMarks = rawMarks;
	}
	
	public String getName()
	{
		return weightage.getName();
	}
	public int getWeightage()
	{
		return weightage.getWeightage();
	}
	
	public int getMarks()
	{
		return rawMarks;
	}
	public void setMarks(int rawMarks)
	{
		this.rawMarks = rawMarks;
	}
	
	@Override
	public double computeWeightedMarks()
	{
		return getWeightage() * rawMarks / 100.0;
	}
	
	@Override
	public String toString()
	{
		return getName() + " (" + getWeightage() + "%): " + rawMarks;
	}
}
