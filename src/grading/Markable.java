package grading;

/**
 * 
 * @version 1.1
 * @since 2018/11/16
 * @author Jason
 *
 */
// Interface markable which allows for the getting of marks and computation of grades. 
// The MAX_MARKS is 100 (static final).
public interface Markable
{
	public static final int MAX_MARKS = 100;
	
	public double getMarks();
	public Grade computeGrade();
}
