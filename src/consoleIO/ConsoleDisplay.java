package consoleIO;

import java.util.ArrayList;

import courses.ComponentWeightage;
import courses.Course;
import grading.ComponentResult;
import grading.Grade;
import universityMembers.FacultyMember;
import universityMembers.Student;
import registration.CourseRegistrationRecord;

public class ConsoleDisplay
{
	public static void displayMainMenu()
	{
		System.out.println("Select an option:\n" + "1.  Add a student\n" + "2.  Add a course\n"
				+ "3.  Register student for a course (this include registering for Tutorial/Lab classes)\n"
				+ "4.  Check available slot in a class (vacancy in a class)\n"
				+ "5.  Print student list by lecture, tutorial or laboratory session for a course.\n"
				+ "6.  Enter course assessment components weightage\n"
				+ "7.  Enter marks for course taken by student\n"
				+ "8.  Print course statistics\n" + "9. Print student transcript.\n" + "10. Quit");
//				+ "7.  Enter coursework mark inclusive of its components.\n" + "8.  Enter exam mark\n"
//				+ "9.  Print course statistics\n" + "10. Print student transcript.\n" + "11. Quit");
	}
	
	public static void displayFacultyMembers(ArrayList<FacultyMember> faculty)
	{
		for(FacultyMember facultyMem: faculty)
			System.out.println(facultyMem);
	}
	
	public static void displayRegisteredCourses(ArrayList<CourseRegistrationRecord> registeredCourses)
	{
		for(CourseRegistrationRecord registeredCourse: registeredCourses)
			System.out.println(registeredCourse);
	}
	
	public static void displayStudentTranscript(Student student)
	{
		System.out.println("Printing Student Transcript for " + student + ":");
		
		ArrayList<CourseRegistrationRecord> coursesRegistered = student.getCoursesRegistered();
		
		for (CourseRegistrationRecord courseRegistered : coursesRegistered)
		{
			System.out.println(courseRegistered.getRegisteredCourse().getCourseCode() + " "
					+ courseRegistered.getRegisteredCourse().getName() + " "
					+ courseRegistered.getOverallResults().getMarks() + " "
					+ courseRegistered.getOverallResults().computeGrade());
			ArrayList<ComponentResult> componentResultList = courseRegistered.getOverallResults()
					.getComponentResultList();
			
			for (ComponentResult componentResult : componentResultList)
			{
				System.out.println("       " + componentResult.getName() + " " + componentResult.getMarks());
			}
		}
	}
	
	public static void displayCourseComponent(Course course)
	{
		System.out.println(course.getCourseCode() + ' ' + course.getName() + " components: ");
		
		for(ComponentWeightage component: course.getAllComponentsWeightage())
			System.out.println(component);
	}
	
	public static void displayCourseStatistic(Course course)
	{
		String userChoicePrompt = "Show grade percentage for: \n1 - Overall \n2 - A particular component";
		int userChoice = ConsoleInputInterface.getUserPositiveIntInput(userChoicePrompt, 2);

		/**
		 * The array courseStat holds the number of student for each Grade for this Course.
		 * Each index of courseStat correspond to a Grade (e.g. courseStat[0] -> Grade.F(0)).
		 * For instance, courseStat[0] having a value of 2 means 2 Students got a Grade of F for this Course.
		 */
		int[] courseStat = new int[Grade.TOTAL_NUMBER_OF_GRADES.getValue()];
		
		ArrayList<CourseRegistrationRecord> registrations = course.getRegistrations();
		
		Grade studentGrade;
		
		if(userChoice == 1)
		{
			for (CourseRegistrationRecord registration : registrations)
			{
				studentGrade = registration.getOverallResults().computeGrade();
				
				// the statement below increment the number of students who got a particular grade 
				// at the corresponding index of courseStat
				++courseStat[studentGrade.getValue()];
			}
		}
		else
		{
			displayCourseComponent(course);
			
			// have to check if the course even has any components first
			ComponentWeightage componentToPrintStatFor = ConsoleIO.getComponentWeightageFromCourse(course);
			String nameOfComponentToPrintStatFor = componentToPrintStatFor.getName();
			
			for (CourseRegistrationRecord registration : registrations)
			{
				ComponentResult studentResult = registration.getOverallResults().getComponentResult(nameOfComponentToPrintStatFor);
				studentGrade = studentResult.computeGrade();
				
				++courseStat[studentGrade.getValue()];
			}
		}
		
		for (int i = 0; i < Grade.TOTAL_NUMBER_OF_GRADES.getValue(); ++i)
			System.out.printf("%s %.2f %%\n", Grade.valueOf(i), (courseStat[i]/ registrations.size() * 100.0));
	}
}
