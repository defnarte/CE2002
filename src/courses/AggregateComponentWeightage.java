package courses;

import java.util.ArrayList;

public class AggregateComponentWeightage extends ComponentWeightage
{
	private ArrayList<ComponentWeightage> subcomponents;
	
	// overloaded constructors for various ways of creation
	public AggregateComponentWeightage(String componentName, 
			int componentWeightage, ArrayList<ComponentWeightage> subcomponents)
	{
		super(componentName, componentWeightage); 
		this.subcomponents = subcomponents;
	}
	public AggregateComponentWeightage(String componentName, int componentWeightage)
	{
		super(componentName, componentWeightage); 
		this.subcomponents = new ArrayList<ComponentWeightage>();
	}
	public AggregateComponentWeightage(AggregateComponentWeightage another)
	{
		super(another.getName(), another.getWeightage());
		this.subcomponents = another.subcomponents;
	}
	// end of constructors
	
	public ArrayList<ComponentWeightage> getSubcomponentWeightageList()
	{
		return subcomponents;
	}
	
	public void addSubcomponentWeightage(ComponentWeightage subcomponent)
	{
		subcomponents.add(subcomponent);
	}
	
	@Override
	public String toString()
	{
		String subcomponentString = "";
		
		for(ComponentWeightage subcomponent: subcomponents)
		{
			subcomponentString += "\t\t" + subcomponent.toString() + '\n';
		}
		
		return super.toString() + subcomponentString;
	}
}
