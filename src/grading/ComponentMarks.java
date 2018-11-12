package grading;

public class ComponentMarks extends Component implements IWeightedMarks
{
	private double marks;
	
	public ComponentMarks(Component weightageComponent, double marks)
	{
		super(weightageComponent);
		this.marks = marks;
	}
	
	public double getMarks()
	{
		return marks;
	}
	public void setMarks(double marks)
	{
		this.marks = marks;
	}
	
	@Override
	public double computeWeightedMarks()
	{
		return super.getWeightage() * marks / 100;
	}
	
	@Override
	public String toString()
	{
		return super.getName() + " (" + super.getWeightage() + "%): " + marks;
	}
}
