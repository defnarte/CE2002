package courses;

/**
 * This class provides the weightage as a template for the computation of marks.
 * Examples of Component: final exam, coursework
 * 
 * @version 1.0
 * @since 2018/11/11
 * @author Jason
 *
 */
public class ComponentWeightage
{
	private String name; // name of this component, e.g. "exam", "assignment", etc
	private int weightage; // as percentage e.g. 70 -> 70%

	public ComponentWeightage(String name, int weightage)
	{
		this.name = name;
		this.weightage = weightage;
	}
	public ComponentWeightage(ComponentWeightage another)
	{
		this.name = another.name;
		this.weightage = another.weightage;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getWeightage()
	{
		return weightage;
	}
	public void setWeightage(int weightage)
	{
		this.weightage = weightage;
	}
	
	@Override
	public String toString()
	{
		return name + " (" + weightage + "%)";
	}
}
