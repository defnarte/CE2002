package registration;

import universityMembers.Student;
import courses.Course;
import grading.OverallResults;

/**
 * 
 * @version 1.0
 * @since 2018/11/12
 * @author Jason
 *
 */
public class CourseRegistrationRecord
{
	private Student registeredStudent;
	private Course registeredCourse;
	private OverallResults results;
	
	public CourseRegistrationRecord(Student student, Course course)
	{
		this.registeredStudent = student;
		this.registeredCourse = course;
	}
	
	public Student getRegisteredStudent()
	{
		return registeredStudent;
	}
	
	public Course getRegisteredCourse()
	{
		return registeredCourse;
	}
	
	public OverallResults getResults()
	{
		return results;
	}
	public void setResults(OverallResults results)
	{
		this.results = results;
	}
	
	@Override
	public String toString()
	{
		return "CourseRegistrationRecord [" + registeredStudent.toString() + " is registered for " + 
				registeredCourse.getCourseCode() + ' ' + registeredCourse.getName() + ']';
	}
}
