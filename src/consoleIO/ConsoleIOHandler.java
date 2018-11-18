package consoleIO;

import courses.ComponentWeightage;
import courses.Course;
import database.CourseDB;
import database.FacultyMemberDB;
import database.StudentDB;
import registration.Registration;
import universityMembers.FacultyMember;
import universityMembers.Student;

public class ConsoleIOHandler
{
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
	
	public static FacultyMember getFacultyMemberFromDB(FacultyMemberDB facultyDB)
	{
		System.out.println("List of all faculty members: ");
		ConsoleDisplay.displayUniversityMembers(facultyDB.getFacultyAl());

		String courseCoordinatorPrompt = "Enter the ID of the faculty member: ";
		FacultyMember facultyMember;
		do
		{
			String facultyMemberID = ConsoleInputInterface.
					getUserStringInput(courseCoordinatorPrompt, StringFormatType.ALPHA_NUMERIC);
			facultyMember = facultyDB.getFacultyMember(facultyMemberID);
			
			if(facultyMember == null)
				System.out.println("Faculty member not found in database!");
			
		} while(facultyMember == null);
		
		return facultyMember;
	}
	
	public static Student getStudentFromDB(StudentDB studentDB)
	{
		ConsoleDisplay.displayUniversityMembers(studentDB.getStudentAl());
		
		String studentIDPrompt = "Enter the student's ID: ";
		Student student;
		do
		{
			String studentID = ConsoleInputInterface.getUserStringInput(studentIDPrompt, StringFormatType.ALPHA_NUMERIC);
			student = studentDB.getStudent(studentID);
			
			if(student == null)
				System.out.println("Student not found in database!");
			
		} while(student == null);
		
		return student;
	}
	
	public static Registration getCourseRegRecordFromStudent(Student student)
	{
		ConsoleDisplay.displayRegisteredCourses(student.getCourseRegRecordArrayList());
		
		Registration courseRegRecord;
		do
		{
			String courseCodePrompt = "Enter the course code: ";
			String courseCode = ConsoleInputInterface.getUserStringInput(courseCodePrompt, StringFormatType.ALPHA_NUMERIC);
			courseRegRecord = student.getCourseRegistrationRecord(courseCode);
			
			if(courseRegRecord == null)
				System.out.println("Student not registered for this course!");
			
		} while(courseRegRecord == null);
		
		return courseRegRecord;
	}
	
	public static ComponentWeightage getComponentWeightageFromCourse(Course course)
	{
		ConsoleDisplay.displayCourseComponents(course);
		
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
				System.out.println(componentName + " is not a component of " + course.getCourseCode() + ' ' + course.getName());
			
		} while(component == null);
		
		return null;
	}
	
	public static String getLessonTypeFromUser()
	{
		String lessonTypePrompt = "Enter lesson type: ";
		return ConsoleInputInterface.getUserStringInput(lessonTypePrompt, StringFormatType.ALPHABETICAL_AND_SPACE);
	}
	
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
				System.out.println(lessonID + " does not exist in course database");
			
		} while (!lessonExists);
		
		return lessonID;
	}
}
