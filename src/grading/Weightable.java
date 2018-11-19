package grading;

/**
 * 
 * @version 1.0
 * @since 2018/11/12
 * @author Jason
 *
 */
// Interface weightable which allows for the computation of marks.
public interface Weightable extends Markable
{
	double computeWeightedMarks();
}
