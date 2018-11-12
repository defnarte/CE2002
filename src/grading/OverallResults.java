package grading;

import java.util.ArrayList;

public class OverallResults implements Gradeable
{
	private int overallMarks;
	private ArrayList<ComponentResult> componentResultList;
	
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
	public ArrayList<ComponentResult> getComponentResultList()
	{
		return componentResultList;
	}
	
	public Grade computeGrade()
	{
		return Grade.F; // placeholder
	}
}
