package grading;

/**
 * 
 * @version 1.1
 * @since 2018/11/16
 * @author Jason
 *
 */
public interface Markable
{
	public static final int MAX_MARKS = 100;
	
	public double getMarks();
	public Grade computeGrade();
}
