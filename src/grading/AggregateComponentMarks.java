package grading;

import java.util.ArrayList;

public class AggregateComponentMarks extends ComponentMarks
{
	private ArrayList<Component> subcomponents; // subcomponents that make up this course work
	
	public AggregateComponentMarks(Component weightageComponent, ArrayList<Component> subcomponents)
	{
		// initialise marks to 0; marks would be aggregated later
		super(weightageComponent, 0); 
		this.subcomponents = subcomponents;
	}
	
	public ArrayList<Component> getSubcomponents()
	{
		return subcomponents;
	}
	
	public void addSubcomponent(Component subcomponent)
	{
		subcomponents.add(subcomponent);
	}

	public double computeAggregatedMarks()
	{
		for(Component subcomponent: subcomponents)
		{
			if(subcomponent instanceof ComponentMarks)
			{
				double subcomponentMarks = ((ComponentMarks) subcomponent).computeWeightedMarks();
				super.setMarks(super.getMarks() + subcomponentMarks);
			}
		}
		return super.getMarks();
	}
	
	@Override
	public double computeWeightedMarks()
	{
		return super.getWeightage() * computeAggregatedMarks() / 100;
	}
}
