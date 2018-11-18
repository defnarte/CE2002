package grading;

import courses.ComponentWeightage;

/**
 * 
 * @version 1.1
 * @since 2018/11/16
 * @author Jason
 *
 */
public class ComponentResult implements Weightable
{
	private ComponentWeightage weightage;
	private int marks;
	
	public ComponentResult(ComponentWeightage weightage, int marks)
	{
		this.weightage = weightage;
		this.marks = marks;
	}
	public ComponentResult(ComponentWeightage weightage)
	{
		this.weightage = weightage;
		this.marks = 0;
	}
	
	public String getName()
	{
		return weightage.getName();
	}
	public int getWeightage()
	{
		return weightage.getWeightage();
	}
	
	@Override
	public double getMarks()
	{
		return marks;
	}
	public void setMarks(int marks)
	{
		this.marks = marks;
	}
	
	@Override
	public Grade computeGrade()
	{
		int marks = (int) Math.round(getMarks());
		
		if (marks < 40) 
		{
			// Grade: F
			return Grade.values()[0];
		}
		else if (marks >= 85)
		{
			// Grade: A+
			return Grade.values()[10];
		}
		else 
		{
			// Grade: D to A, with mark interval of 5
			// range from 40 to 84
			marks = ((int) Math.round(getMarks())-40)/5 + 1;
			return Grade.values()[marks];
		}
	}
	
	@Override
	public double computeWeightedMarks()
	{
		return getWeightage() * marks / 100.0;
	}
	
	@Override
	public String toString()
	{
		return getName() + " (" + getWeightage() + "%): " + String.format("%.1f", (double)marks) + '\n';
	}
}
