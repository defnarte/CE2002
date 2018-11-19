package consoleIO;

import courses.ComponentWeightage;
import courses.Course;
import database.CourseDB;
import database.FacultyMemberDB;
import database.StudentDB;
import registration.Registration;
import universityMembers.FacultyMember;
import universityMembers.Student;

/**
 * This control class handles most (by right it should be all, but we screwed up) logic for input/output.
 *
 */
public class ConsoleIOHandler
{
	/**
	 * This method gets a positive integer from the user when the user has to make a choice (that is numbered).
	 * 
	 * @param upperbound
	 * @return positive integer entered by user
	 */
	public static int getPosIntChoiceFromUser(int upperbound)
	{
		String userChoicePrompt = "Enter your choice: ";
		return ConsoleInputInterface.getUserPositiveIntInput(userChoicePrompt, upperbound);
	}
	
	/**
	 * This method gets a course code from the user and return the Course from the Course database
	 * with the corresponding Course code.
	 * 
	 * @param courseDB
	 * @return a valid Course that the user is looking for
	 */
	public static Course getCourseFromDB(CourseDB courseDB)
	{
		ConsoleDisplay.displayCourses(courseDB.getCourseAl());
		
		Course course;
		do
		{
			String coursecodePrompt = "Enter course code: ";
			String coursecode = ConsoleInputInterface
					.getUserStringInput(coursecodePrompt, StringFormatType.ALPHA_NUMERIC);

			course = courseDB.getCourse(coursecode);

			if (course == null)
				System.out.println("Course not found in database!");

		} while (course == null);
		
		return course;
	}
	
	/**
	 * This method gets a Faculty Member ID from the user and returns the Faculty from
	 * FacultyMember database who has the corresponding ID.
	 * 
	 * @param facultyMemberDB
	 * @return a valid FacultyMember whom the user is looking for
	 */
	public static FacultyMember getFacultyMemberFromDB(FacultyMemberDB facultyMemberDB)
	{
		System.out.println("List of all faculty members: ");
		ConsoleDisplay.displayUniversityMembers(facultyMemberDB.getFacultyAl());

		String courseCoordinatorPrompt = "Enter the ID of the faculty member: ";
		FacultyMember facultyMember;
		do
		{
			String facultyMemberID = ConsoleInputInterface.
					getUserStringInput(courseCoordinatorPrompt, StringFormatType.ALPHA_NUMERIC);
			facultyMember = facultyMemberDB.getFacultyMember(facultyMemberID);
			
			if(facultyMember == null)
				System.out.println("Faculty member not found in database!");
			
		} while(facultyMember == null);
		
		return facultyMember;
	}
	
	/**
	 * This method gets a StudentID from the user and returns the Student from Student database
	 * who has the corresponding ID.
	 * 
	 * @param studentDB
	 * @return a valid Student whom the user is looking for
	 */
	public static Student getStudentFromDB(StudentDB studentDB)
	{
		System.out.println("\nList of all students: ");
		ConsoleDisplay.displayUniversityMembers(studentDB.getStudentAl());
		
		String studentIDPrompt = "Enter the student's ID: ";
		Student student;
		do
		{
			String studentID = ConsoleInputInterface.
					getUserStringInput(studentIDPrompt, StringFormatType.ALPHA_NUMERIC);
			student = studentDB.getStudent(studentID);
			
			if(student == null)
				System.out.println("Student not found in database!");
			
		} while(student == null);
		
		return student;
	}
	
	/**
	 * This methods gets a Course code from the user and return the Registration record, from 
	 * a particular Student, which has the corresponding Course code.
	 * 
	 * @param student
	 * @return a valid Registration record that the user is looking for
	 */
	public static Registration getCourseRegRecordFromStudent(Student student)
	{
		ConsoleDisplay.displayRegistrations(student.getCourseRegRecordArrayList());
		
		Registration courseRegRecord;
		do
		{
			String courseCodePrompt = "Enter the course code: ";
			String courseCode = ConsoleInputInterface.
					getUserStringInput(courseCodePrompt, StringFormatType.ALPHA_NUMERIC);
			courseRegRecord = student.getCourseRegistrationRecord(courseCode);
			
			if(courseRegRecord == null)
				System.out.println("Student not registered for this course!");
			
		} while(courseRegRecord == null);
		
		return courseRegRecord;
	}
	
	/**
	 * This method gets a Component name from the user and returns a Component (weightage), from
	 * a particular Course, which has the corresponding name.
	 * 
	 * @param course
	 * @return a valid Component(weightage) the user is looking for.
	 */
	public static ComponentWeightage getComponentWeightageFromCourse(Course course)
	{
		ConsoleDisplay.displayCourseComponentsWithWeightage(course);
		
		ComponentWeightage component = null;
		do
		{
			String componentNamePrompt = "Enter the name of the component: ";
			String componentName = ConsoleInputInterface.
					getUserStringInput(componentNamePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);
			
			for(ComponentWeightage existingComponent: course.getAllComponentsWeightage())
			{
				if(existingComponent.getName().equals(componentName))
					return existingComponent;
			}
			
			if(component == null)
				System.out.println(componentName + " is not a component of " + 
						course.getCourseCode() + ' ' + course.getName());
			
		} while(component == null);
		
		return null;
	}
	
	/**
	 * This method gets and returns a particular Lesson type (as a String) entered by the user.
	 * 
	 * @return Lesson type entered by user
	 */
	public static String getLessonTypeFromUser()
	{
		String lessonTypePrompt = "Enter lesson type: ";
		return ConsoleInputInterface.getUserStringInput(lessonTypePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);
	}
	
	/**
	 * This method gets and returns a Lesson ID (as an int) entered by the user which exists in Course database.
	 * 
	 * @param courseDB
	 * @return a valid Lesson ID the user is looking for
	 */
	public static int getLessonIDFromUser(CourseDB courseDB)
	{
		String lessonIDPrompt = "Enter lesson ID: ";
		int lessonID;
		boolean lessonExists;
		do
		{
			lessonID = ConsoleInputInterface.getUserPositiveIntInput(lessonIDPrompt);
			lessonExists = courseDB.checkLessonExists(lessonID);
			
			if(!lessonExists)
				System.out.println(lessonID + " does not exist in course database.");
			
		} while (!lessonExists);
		
		return lessonID;
	}
}
