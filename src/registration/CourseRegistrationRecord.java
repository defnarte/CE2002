package registration;

import universityMembers.Student;
import courses.Course;
import grading.OverallResults;

/**
 * 
 * @version 1.2
 * @since 2018/11/12
 * @author Jason
 *
 */
public class CourseRegistrationRecord
{
	private Student registeredStudent;
	private Course registeredCourse;
	private OverallResults overallResults;
	
	public CourseRegistrationRecord(Student student, Course course)
	{
		this.registeredStudent = student;
		this.registeredCourse = course;
		this.overallResults = new OverallResults(course.getAllComponentsWeightage());
	}
	
	public Student getRegisteredStudent()
	{
		return registeredStudent;
	}
	
	public Course getRegisteredCourse()
	{
		return registeredCourse;
	}
	
	public OverallResults getOverallResults()
	{
		return overallResults;
	}
	public void setOverallResults(OverallResults overallResults)
	{
		this.overallResults = overallResults;
	}
	public void setTargetComponentResult(String targetName, int rawMarks)
	{
		this.overallResults.setTargetComponentResult(targetName, rawMarks);
	}
	
	@Override
	public String toString()
	{
		return "CourseRegistrationRecord [student: " + registeredStudent.toString() + ", course: " + 
				registeredCourse.getCourseCode() + ' ' + registeredCourse.getName() + ']';
	}
}
