package grading;

/**
 * 
 * @version 1.0
 * @since 2018/11/12
 * @author Jason
 *
 */
//Grade Scoring Table
//A+	10
//A 80 marks	9
//A- 75 marks	8
//B+ 70 marks	7
//B 65 marks	6
//B- 60 marks	5
//C+ 55 marks	4
//C 50 marks	3
//C- 45 marks	2
//D 40 marks	1	
//F 0 marks		0
enum Grade
{
	F,
	D, D_PLUS,
	C, C_PLUS,
	B_MINUS, B, B_PLUS,
	A_MINUS, A, A_PLUS,
}

public interface Gradeable
{

	public Grade computeGrade();
}
