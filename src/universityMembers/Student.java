package universityMembers;

import registration.Registration;

import java.util.ArrayList;

/**
 * This class holds information of a university student (undergraduate).
 * 
 */
public class Student extends UniversityMember
{
	private ArrayList<Registration> registeredCourses;
	
	/**
	 * Constructor for student that initialises his/her identification number (based on matriculation number)
	 * and full name based on parameters passed in. 
	 * 
	 * @param matricNum
	 * @param fullName
	 */
	public Student(String matricNum, String fullName)
	{
		super(matricNum,fullName);
		registeredCourses = new ArrayList<Registration>();
	}

	/**
	 * Add a registration to university member's registered courses.
	 * @param newRegistration The new registration record.
	 */
	public void addCourseRegistration(Registration newRegistration)
	{
		registeredCourses.add(newRegistration);
	}
	/**
	 * Remove a registration from a student's registered courses.
	 * @param courseRegistrationRecord The course registration record to be removed.
	 */
	public void dropCourseRegistration(Registration courseRegistrationRecord)
	{
		registeredCourses.remove(courseRegistrationRecord);
	}
	/**
	 * Get the Course Registration Record corresponding to the requested course code.
	 * @param courseCode This is course code requested by the user.
	 * @return Course Registration Record corresponding to the requested course code.
	 */
	public Registration getCourseRegistrationRecord(String courseCode)
	{
		for(Registration courseRecord: registeredCourses)
		{
			String existingCourseCode = courseRecord.getRegisteredCourse().getCourseCode();
			
			if(existingCourseCode.equals(courseCode))
				return courseRecord;
		}
			
		return null;
	}
	/**
	 * Getter for courses this student has registered for.
	 * 
	 * @return registeredCourses
	 */
	public ArrayList<Registration> getCourseRegRecordArrayList()
	{
		return registeredCourses;
	}
	
	@Override
	/**
	 * Overrided toString that returns the student.
	 */
	public String toString()
	{
		return "Student[" + super.toString() + ']';
	}
}
