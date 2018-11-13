package registration;

import universityMembers.Student;
import courses.Course;
import grading.OverallResults;
import java.util.ArrayList;
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
	private ArrayList<String> lessons;
	private OverallResults results;
	
	public CourseRegistrationRecord(Student student, Course course, ArrayList<String> lessons)
	{
		this.registeredStudent = student;
		this.registeredCourse = course;
		this.lessons = lessons;
	}
	
	public Student getRegisteredStudent()
	{
		return registeredStudent;
	}
	
	public Course getRegisteredCourse()
	{
		return registeredCourse;
	}
	public ArrayList<String> getLessonList() {
		return lessons;
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
	public void printStudent() {
		
	}
}
