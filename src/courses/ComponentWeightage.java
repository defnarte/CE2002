package courses;

/**
 * This entity class provides the weightage as a template for the computation of marks.
 * Examples of Component: final exam, coursework
 *
 */
public class ComponentWeightage
{
	private String name; // name of this component, e.g. "exam", "assignment", etc
	private int weightage; // as percentage e.g. 70 -> 70%

	/**
	 * This constructor initialises the name and weightage of this Component based on the arguments passed in.
	 * 
	 * @param name
	 * @param weightage
	 */
	public ComponentWeightage(String name, int weightage)
	{
		this.name = name;
		this.weightage = weightage;
	}
	/**
	 * This constuctor initialises the name and weightage of this Component based on
	 * the name and weightage of another Component passed in as argument.
	 * 
	 * @param another ComponentWeightage
	 */
	public ComponentWeightage(ComponentWeightage another)
	{
		this.name = another.name;
		this.weightage = another.weightage;
	}

	/**
	 * Getter for component name.
	 * 
	 * @return component name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Setter for component name.
	 * 
	 * @param component name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Getter for component weightage.
	 * 
	 * @return component weightage
	 */
	public int getWeightage()
	{
		return weightage;
	}
	/**
	 * Setter for component weightage.
	 * 
	 * @param component weightage
	 */
	public void setWeightage(int weightage)
	{
		this.weightage = weightage;
	}
	
	@Override
	/**
	 * Overrided toString method that returns component name and weightage as String.
	 * 
	 * @return component name and weightage as String
	 */
	public String toString()
	{
		return name + " (" + weightage + "%)";
	}
}
