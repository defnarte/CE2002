package grading;

/**
 * Interface weightable which allows for the computation of marks, taking Component weightage into account,
 * in addition to getting of marks and grade.
 *
 */
public interface Weightable extends Markable
{
	double computeWeightedMarks();
}
