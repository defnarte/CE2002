import java.util.*;

/**
 * This entity class holds information regarding a particular Course.
 * 
 * last-updated: 2018-11-09
 * @author DongJoo
 *
 */
public class Course
{
	private String courseID;
	private String courseName;
	private ArrayList<String> studentsMatricNumbers;
	private double[] weightage;
	private ArrayList<CourseWork[]> courseWorkMarks; 
	private ArrayList<Lesson> lessons;

	// Used when loading an existing database
	public Course(String courseID, String courseName, ArrayList<String> studentsMatricNumbers, ArrayList<CourseWork[]> courseWorkMarks,
			ArrayList<Lesson> lessons)
	{
		this.courseName = courseName;
		this.courseID = courseID;
		this.studentsMatricNumbers = studentsMatricNumbers;
		this.courseWorkMarks = courseWorkMarks;
		this.lessons = lessons;
	}
	// Used when creating a new course
	public Course(String courseName, String courseID)
	{
		this.courseName = courseName;
		this.courseID = courseID;
	}

	public String getCourseID()
	{
		return courseID;
	}
	public String getCourseName()
	{
		return courseName;
	}
	
//	public Faculty getCourseCoordinator()
//	{
//		return courseCoordinator;
//	}
//	public void setCourseCoordinator(Faculty courseCoordinator)
//	{
//		this.courseCoordinator = courseCoordinator;
//	}

	public ArrayList<String> getstudentsMatricNumbers()
	{
		return studentsMatricNumbers;
	}

	public ArrayList<CourseWork[]> getcourseWorkMarks()
	{
		return courseWorkMarks;
	}
	
	public double[] getWeightage() {
		return weightage;
	}

	public ArrayList<Lesson> getLessons()
	{
		return lessons;
	}
	
	public void setWeightage(double[] weightage) {
		this.weightage = weightage;
	}
}
