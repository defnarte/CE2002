package grading;

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
