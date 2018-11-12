package grading;

/**
 * 
 * @version 1.0
 * @since 2018/11/12
 * @author Jason
 *
 */
enum Grade
{
	A_PLUS, A, A_MINUS,
	B_PLUS, B, B_MINUS,
	C_PLUS, C,
	D_PLUS, D,
	F,
}

public interface Gradeable
{

	public Grade computeGrade();
}
