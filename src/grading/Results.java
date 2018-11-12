package grading;

import java.util.ArrayList;

public class Results implements Gradeable
{
	private int overallMarks;
	private ArrayList<ComponentMarks> componentsMarks;
	
//	public Results(ArrayList<Component> components)
//	{
//		marksOfComponents = new ArrayList<ComponentMarks>();
//		
//		for(Component component: components)
//		{
//			ComponentMarks componentMarks = new 
//		}
//	}
	
	public int getOverallMarks()
	{
		return overallMarks;
	}
	public ArrayList<ComponentMarks> getComponentsMarks()
	{
		return componentsMarks;
	}
	
	public Grade computeGrade()
	{
		return Grade.F; // placeholder
	}
}
