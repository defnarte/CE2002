package registration;

import java.util.ArrayList;
import universityMembers.Student;
import courses.Course;
import grading.MarksEntryInterface;
import grading.OverallResult;

/**
 * 
 * @version 1.3
 * @since 2018/11/13
 * @author Jason
 *
 */
public class Registration
{
	private Student registeredStudent;
	private Course registeredCourse;
	private ArrayList<Integer> lessonIDArrayList;
	private OverallResult overallResult;
	
	/**
	 * Constructor for registration
	 * @param student The student registered in this course.
	 * @param course The course registered by the student.
	 * @param lessonIDArrayList The lessonIDs under this course.
	 */
	public Registration(Student student, Course course, ArrayList<Integer> lessonIDArrayList)
	{
		this.registeredStudent = student;
		this.registeredCourse = course;
		this.lessonIDArrayList = lessonIDArrayList;
		this.overallResult = new OverallResult(course.getAllComponentsWeightage());
	}
	/**
	 * Get the student under this registration.
	 * @return The student under this registration.
	 */
	public Student getRegisteredStudent()
	{
		return registeredStudent;
	}
	/**
	 * Get the course under this registration.
	 * @return The course under this registration.
	 */
	public Course getRegisteredCourse()
	{
		return registeredCourse;
	}
	/**
	 * Get the lessonIDs in this course registered by the student.
	 * @return The lessonsID associated with the registered course.
	 */
	public ArrayList<Integer> getLessonArrayList() 
	{
		return lessonIDArrayList;
	}
	/**
	 * Get the overall results for the student under this registration.
	 * @return The overall results for the student in this course.
	 */
	public OverallResult getOverallResult()
	{
		return overallResult;
	}
	/**
	 * Set the overall results for the student under this registration.
	 * @param overallResults The overall results for the student in this course.
	 */
	public void setOverallResult(OverallResult overallResults)
	{
		this.overallResult = overallResults;
	}

	/**
	 * Calls MarksEnterInterface which will handle the entering of marks
	 */
	public void enterMarks()
	{
		MarksEntryInterface.enterMarksForCourse(this);
	}
	
	@Override
	/**
	 * Overrided toString() that returns the Course Registration Record which includes both the student and the course.
	 */
	public String toString()
	{
		return "CourseRegistrationRecord [student: " + registeredStudent.toString() + ", course: " + 
				registeredCourse.getCourseCode() + ' ' + registeredCourse.getName() + ']';
	}

}
