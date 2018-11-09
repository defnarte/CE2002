import java.util.ArrayList;

enum Grade
{
	APLUS, A, AMINUS,
	BPLUS, B, BMINUS,
	CPLUS, C,
	DPLUS, D,
	F,
}

/**
 * This entity class holds a Course and its Lessons a Student enrolled in.
 * 
 * last-updated: 2018-11-09
 * @author Jason
 *
 */
public class Enrolment
{
	private Student enrolledStudent;
	private Course courseEnrolledIn;
	
	private ArrayList<CourseWork> courseWorks;
	private ArrayList<Lesson> courseLessons;
	
	private Grade courseGrade;
}
