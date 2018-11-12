package universityMembers;

import registration.CourseRegistrationRecord;

import java.util.ArrayList;

/**
 * This class holds information of a university student (undergraduate).
 * 
 * @version 1.0
 * @since 2018/11/10
 * @author Jason
 * 
 */
public class Student extends UniversityMember
{
	private ArrayList<CourseRegistrationRecord> registeredCourses;
	
	/**
	 * Constructor for student that initialises his/her identification number (based on matriculation number)
	 * and full name.
	 * 
	 * @param matricNum
	 * @param fullName
	 */
	public Student(String matricNum, String fullName)
	{
		super(matricNum,fullName);
		registeredCourses = new ArrayList<CourseRegistrationRecord>();
	}
	
	/**
	 * Getter for courses this student has registered for.
	 * 
	 * @return registeredCourses
	 */
	public ArrayList<CourseRegistrationRecord> getCoursesRegistered()
	{
		return registeredCourses;
	}
	
	// TO-DO: add comments
	public void addCourse(CourseRegistrationRecord newCourseRegistrationRecord)
	{
		registeredCourses.add(newCourseRegistrationRecord);
	}
	public void dropCourse(CourseRegistrationRecord courseRegistrationRecord)
	{
		registeredCourses.remove(courseRegistrationRecord);
	}
	
}
