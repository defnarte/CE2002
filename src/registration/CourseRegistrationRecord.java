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
public class CourseRegistrationRecord
{
	private Student registeredStudent;
	private Course registeredCourse;
	private ArrayList<String> lessons;
	private OverallResult overallResults;
	
	public CourseRegistrationRecord(Student student, Course course, ArrayList<String> lessons)
	{
		this.registeredStudent = student;
		this.registeredCourse = course;
		this.lessons = lessons;
		this.overallResults = new OverallResult(course.getAllComponentsWeightage());
	}
	
	public Student getRegisteredStudent()
	{
		return registeredStudent;
	}
	
	public Course getRegisteredCourse()
	{
		return registeredCourse;
	}
	public ArrayList<String> getLessonList() 
	{
		return lessons;
	}
	
	public OverallResult getOverallResults()
	{
		return overallResults;
	}
	public void setOverallResults(OverallResult overallResults)
	{
		this.overallResults = overallResults;
	}
	public boolean setTargetComponentResult(String targetName, int rawMarks)
	{
		return this.overallResults.setTargetComponentResult(targetName, rawMarks);
	}
	public void enterMarks()
	{
		MarksEntryInterface.enterMarksForCourse(this);
	}
	
	@Override
	public String toString()
	{
		return "CourseRegistrationRecord [student: " + registeredStudent.toString() + ", course: " + 
				registeredCourse.getCourseCode() + ' ' + registeredCourse.getName() + ']';
	}
	public void printStudent() {
		
	}
}
