package registration;

import universityMembers.Student;
import courses.Course;

/**
 * 
 * @version 1.1
 * @since 2018/11/12
 * @author Jason
 *
 */
//enum RegistrationOutcome
//{
//	ALREADY_REGISTERED, // registration failed as student is already registered for this course this semester
//	NO_VACANCY, // registration failed as there is no vacancy in this course
//	REGISTRATION_SUCCESS,
//}
//enum DeregistrationOutcome
//{
//	NOT_REGISTERED, // de-registration failed as the student is not registered for this course
//	DEREGISTRATION_SUCCESS,
//}

public class CourseRegistrationHandler
{
	public static CourseRegistrationRecord register(Student student, Course course)
	{
//		if(course.getVacancy() == 0)
//			return null; // RegistrationOutcome.NO_VACANCY;
		
		/**
		 * Checks if student is already registered for this course this semester.
		 */
		for(CourseRegistrationRecord registration: course.getRegistrationRecords())
		{
			if(registration.getRegisteredStudent() == student)
				return null; // RegistrationOutcome.ALREADY_REGISTERED;
		}
		
		CourseRegistrationRecord newRegistration = new CourseRegistrationRecord(student,course,null);
		student.addCourseRegistration(newRegistration);
		course.addStudentRegistration(newRegistration);
		
		return newRegistration; // RegistrationOutcome.REGISTRATION_SUCCESS;
	}
	
	public static boolean deregister(Student student, Course course)
	{
		/**
		 * Checks if student is registered for this course this semester.
		 * If the student is registered for this course this semester, 
		 * de-register the student from the course.
		 * 
		 * Should NOT check from student's end as the student may have taken the course before,
		 * but failed the course and have to re-take it.
		 */
		for(CourseRegistrationRecord registration: course.getRegistrationRecords())
		{
			if(registration.getRegisteredStudent() == student)
			{
				student.dropCourseRegistration(registration);
				course.dropStudentRegistration(registration);
				
				return true; // DeregistrationOutcome.DEREGISTRATION_SUCCESS;
			}
				
		}
		
		return false; // DeregistrationOutcome.NOT_REGISTERED;
	}
}
