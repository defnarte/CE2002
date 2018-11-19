package grading;

import courses.ComponentWeightage;

/**
 * This entity class holds the result obtained for a Course's Component, and its weightage.
 * 
 */
public class ComponentResult implements Weightable
{
	private ComponentWeightage weightage;
	private int marks;
	
	/**
	 * Constructor for Component Result
	 * @param weightage The weightage of the component.
	 * @param marks The marks scored for the component.
	 */
	public ComponentResult(ComponentWeightage weightage, int marks)
	{
		this.weightage = weightage;
		this.marks = marks;
	}
	/**
	 * Constructor for component result (without marks)
	 * @param weightage The weightage of the component.
	 */
	public ComponentResult(ComponentWeightage weightage)
	{
		this.weightage = weightage;
		this.marks = 0;
	}
	/**
	 * Get the name of the component
	 * @return The name of the component.
	 */
	public String getName()
	{
		return weightage.getName();
	}
	/**
	 * Get the weightage of the component
	 * @return The weightage of the component.
	 */
	public int getWeightage()
	{
		return weightage.getWeightage();
	}
	
	@Override
	/**
	 * Get the marks scored for the component. Overrides the interface Markable method of the same name.
	 */
	public double getMarks()
	{
		return marks;
	}
	/**
	 * Set the marks scored for this component.
	 * @param marks The marks scored for this component.
	 */
	public void setMarks(int marks)
	{
		this.marks = marks;
	}
	/**
	 * Compute the grade obtained for the component based on the Grade Scoring Table in Grade enumerator. 
	 * Overrides the interface Markable method of the same name.
	 */
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
	/**
	 * Compute the weighted marks. Overrides the interface Weightable method of the same name.
	 */
	public double computeWeightedMarks()
	{
		return getWeightage() * marks / 100.0;
	}
	
	@Override
	/**
	 * Overrided toString() to return the name of component, weightage and the marks scored.
	 */
	public String toString()
	{
		return getName() + " (" + getWeightage() + "%): " + String.format("%.1f", (double)marks);
	}
}
