package registration;

import universityMembers.Student;
import consoleIO.MarksEntryInterface;
import courses.Course;
import grading.OverallResults;

/**
 * 
 * @version 1.3
 * @since 2018/11/13
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
//	public void setOverallResults(OverallResults overallResults)
//	{
//		this.overallResults = overallResults;
//	}
	public boolean setTargetComponentResult(String targetName, int rawMarks)
	{
		return this.overallResults.setTargetComponentResult(targetName, rawMarks);
	}
	public void enterMarks()
	{
		MarksEntryInterface.enterMarks(this);
	}
	
	@Override
	public String toString()
	{
		return "CourseRegistrationRecord [student: " + registeredStudent.toString() + ", course: " + 
				registeredCourse.getCourseCode() + ' ' + registeredCourse.getName() + ']';
	}
}
