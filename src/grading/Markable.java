package grading;

/**
 * Interface markable which allows for the getting of marks and computation of grades. 
 * The maximum number of marks a Student can get for a Course Component is 100 (100%).
 *
 */
public interface Markable
{
	public static final int MAX_MARKS = 100;
	
	public double getMarks();
	public Grade computeGrade();
}
