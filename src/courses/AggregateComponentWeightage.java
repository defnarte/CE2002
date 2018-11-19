package courses;

import java.util.ArrayList;

/**
 * This entity class is a Component that holds subcomponents as an ArrayList of Component.
 *
 */
public class AggregateComponentWeightage extends ComponentWeightage
{
	private ArrayList<ComponentWeightage> subcomponents;
	
	/**
	 * This constructor initialises the Aggregate Component based on the name, weightage and subcomponents
	 * (as an ArrayList of Component) passed in as arguments.
	 * 
	 * @param componentName
	 * @param componentWeightage
	 * @param subcomponents
	 */
	public AggregateComponentWeightage(String componentName, 
			int componentWeightage, ArrayList<ComponentWeightage> subcomponents)
	{
		super(componentName, componentWeightage); 
		this.subcomponents = subcomponents;
	}
	/**
	 * This constructor initialises the Aggregate Component based on the name and weightage passed in
	 * as argument. Subcomponents is initialised to a new ArrayList of Component.
	 * 
	 * @param componentName
	 * @param componentWeightage
	 */
	public AggregateComponentWeightage(String componentName, int componentWeightage)
	{
		super(componentName, componentWeightage); 
		this.subcomponents = new ArrayList<ComponentWeightage>();
	}
	/**
	 * This constructor initialises the Aggregate Component based on the name, weightage and subcomponents
	 * of another Aggregate Component passed in as argument.
	 * 
	 * @param another AggregateComponentWeightage
	 */
	public AggregateComponentWeightage(AggregateComponentWeightage another)
	{
		super(another.getName(), another.getWeightage());
		this.subcomponents = another.subcomponents;
	}
	
	/**
	 * Getter for subcomponents.
	 * 
	 * @return subcomponents as an ArrayList of ComponentWeightage
	 */
	public ArrayList<ComponentWeightage> getSubcomponentWeightageList()
	{
		return subcomponents;
	}
	/**
	 * Setter for subcomponents.
	 * 
	 * @param subcomponent as an ArrayList of ComponentWeightage
	 */
	public void addSubcomponentWeightage(ComponentWeightage subcomponent)
	{
		subcomponents.add(subcomponent);
	}
	
	@Override
	/**
	 * Overrided toString method that returns its superclass' toString as well as individual
	 * subcomponents toString.
	 * 
	 * @return this component's name and weightage, as well as each subcomponents' name and weightage
	 * as String
	 */
	public String toString()
	{
		String subcomponentString = "";
		
		for(ComponentWeightage subcomponent: subcomponents)
		{
			subcomponentString += "\t" + subcomponent.toString() + '\n';
		}
		
		return super.toString() + subcomponentString;
	}
}
