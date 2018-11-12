package registration;

import universityMembers.Student;
import courses.Course;
import grading.Results;

public class CourseRegistrationRecord
{
	private Student registeredStudent;
	private Course registeredCourse;
	private Results results;
	
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
	
	public Results getResults()
	{
		return results;
	}
	public void setResults(Results results)
	{
		this.results = results;
	}
}
