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
	 * and full name.
	 * 
	 * @param matricNum
	 * @param fullName
	 */
	public Student(String matricNum, String fullName)
	{
		super(matricNum,fullName);
		registeredCourses = new ArrayList<Registration>();
	}

	// TO-DO: add comments
	public void addCourseRegistration(Registration newCourseRegistrationRecord)
	{
		registeredCourses.add(newCourseRegistrationRecord);
	}
	public void dropCourseRegistration(Registration courseRegistrationRecord)
	{
		registeredCourses.remove(courseRegistrationRecord);
	}
	
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
	public String toString()
	{
		return "Student[" + super.toString() + ']';
	}
//	public int searchRecord(String courseID) 
//	{
//		int index = 0;
//		for (CourseRegistrationRecord registeredCourse:registeredCourses)
//		{
//			if (registeredCourse.getRegisteredCourse().getCourseCode().equals(courseID))
//				return index;
//			index++;
//		}
//		return -1;
//		
//	}
}
