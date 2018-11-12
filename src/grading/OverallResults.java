package grading;

import java.util.ArrayList;

public class OverallResults implements Gradeable
{
	private int overallMarks;
	private ArrayList<ComponentResult> componentsMarks;
	
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
	public ArrayList<ComponentResult> getComponentsMarks()
	{
		return componentsMarks;
	}
	
	public Grade computeGrade()
	{
		return Grade.F; // placeholder
	}
}
