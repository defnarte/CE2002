package grading;

/**
 * 
 * @version 1.0
 * @since 2018/11/12
 * @author Jason
 *
 */

//Grade Scoring Table
//A+	85 to 100	marks	10
//A 	80 to 84 	marks	9
//A- 	75 to 79 	marks	8
//B+ 	70 to 74 	marks	7
//B 	65 to 69 	marks	6
//B- 	60 to 64 	marks	5
//C+ 	55 to 59 	marks	4
//C 	50 to 54 	marks	3
//C- 	45 to 49 	marks	2
//D 	40 to 44 	marks	1	
//F 	 0 to 39 	marks	0

enum Grade
{
	F,
	D, D_PLUS,
	C, C_PLUS,
	B_MINUS, B, B_PLUS,
	A_MINUS, A, A_PLUS,
}

public interface Markable
{
	public double getMarks();
	public Grade computeGrade();
}
