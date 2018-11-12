package grading;

import java.util.ArrayList;

public class AggregateComponent extends Component
{
	private ArrayList<Component> subcomponents; // subcomponents that make up this course work
	
	public AggregateComponent(String componentName, int componentWeightage, ArrayList<Component> subcomponents)
	{
		super(componentName, componentWeightage); 
		this.subcomponents = subcomponents;
	}
	public AggregateComponent(String componentName, int componentWeightage)
	{
		super(componentName, componentWeightage); 
		this.subcomponents = new ArrayList<Component>();
	}
	
	public ArrayList<Component> getSubcomponents()
	{
		return subcomponents;
	}
	
	public void addSubcomponent(Component subcomponent)
	{
		subcomponents.add(subcomponent);
	}
	
	@Override
	public String toString()
	{
		String subcomponentString = "";
		
		for(Component subcomponent: subcomponents)
		{
			subcomponentString += "\t\t" + subcomponent.toString() + '\n';
		}
		
		return name + " (" + weightage + "%)\n" + subcomponentString;
	}
}
