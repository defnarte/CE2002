package consoleIO;

import courses.Course;
import database.FacultyDB;
import database.StudentDB;
import registration.CourseRegistrationRecord;
import universityMembers.FacultyMember;
import universityMembers.Student;

public class ConsoleIO
{
	public static FacultyMember getFacultyMemberFromDB(FacultyDB faculty)
	{
		FacultyMember facultyMember;
		
		do
		{
			String courseCoordinatorPrompt = "Enter the ID of the course coordinator";
			String facultyMemberID = ConsoleInputInterface.getUserStringInput(courseCoordinatorPrompt, StringFormatType.ALPHA_NUMERIC);
			facultyMember = faculty.getFacultyMember(facultyMemberID);
			
			if(facultyMember == null)
				System.out.println("Faculty member not found in database!");
			
		} while(facultyMember == null);
		
		return facultyMember;
	}
	
	public static Student getStudentFromDB(StudentDB studentDB)
	{
		Student student;
		
		do
		{
			String studentIDPrompt = "Enter the student's ID: ";
			String studentID = ConsoleInputInterface.getUserStringInput(studentIDPrompt, StringFormatType.ALPHA_NUMERIC);
			student = studentDB.getStudent(studentID);
			
			if(student == null)
				System.out.println("Student not found in database!");
			
		} while(student == null);
		
		return student;
	}
	
	public static CourseRegistrationRecord getCourseRegRecordFromStudent(Student student)
	{
		CourseRegistrationRecord courseRegRecord;
		
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
}
