package consoleIO;

import java.util.ArrayList;

import courses.ComponentWeightage;
import courses.Course;
import grading.AggregateComponentResult;
import grading.ComponentResult;
import grading.Grade;
import lessons.Lesson;
import universityMembers.*;
import registration.Registration;

public class ConsoleDisplay
{
	public static void displayMainMenu()
	{
		System.out.println("\nSelect an option:\n" + 
				"1.  Add a student to student database\n" + 
				"2.  Add a course to course database\n" + 
				"3.  Register student for a course (includes registering lessons)\n" + 
				"4.  Check available slot (vacancy) by lesson type in a course\n" + 
				"5.  Print student list by lesson type for a course.\n" + 
				"6.  Enter course assessment components weightage for a course\n" + 
				"7.  Enter marks for a course taken a by student\n" + 
				"8.  Print course statistics\n" + 
				"9.  Print a student transcript.\n" + 
				"10. Quit");
	}
	
	public static <T> void displayUniversityMembers(ArrayList<T> uniMemArrayList)
	{
		for(T uniMember: uniMemArrayList)
			System.out.println(uniMember);
	}
	
	public static void displayCourses(ArrayList<Course> courseArrayList)
	{
		System.out.println("\nList of courses: ");
		
		for (Course course : courseArrayList)
		{
			System.out.println(course);
		}
	}
	
	public static void displayCoursesWithCoordinators(ArrayList<Course> courseArrayList)
	{
		System.out.println("\nList of courses (with their respective course coordinators): ");
		
		for (Course course : courseArrayList)
		{
			System.out.println(course  + " - course coordinator: " + course.getCoordinator());
		}
	}
	
	public static void displayRegisteredCourses(ArrayList<Registration> courseRegRecordArrayList)
	{
		for(Registration courseRegRecord: courseRegRecordArrayList)
			System.out.println(courseRegRecord);
	}
	
	public static void displayStudentTranscript(Student student)
	{
		System.out.println("\n" + student + "'s transcript:\n" + "---------------------------------");
		
		ArrayList<Registration> courseRegRecordArrayList = student.getCourseRegRecordArrayList();
		
		for (Registration courseRegRecord : courseRegRecordArrayList)
		{
			System.out.println(courseRegRecord.getRegisteredCourse() + "\n" + "Overall result: "
					+ courseRegRecord.getOverallResult().getMarks() + "% Grade: "
					+ courseRegRecord.getOverallResult().computeGrade());
			
			ArrayList<ComponentResult> componentResultList = courseRegRecord.getOverallResult().getComponentResultList();
			
			for (ComponentResult componentResult : componentResultList)
			{
				System.out.println("\t" + componentResult.getName() + ": " + componentResult.getMarks() + '%');
				
				if(componentResult instanceof AggregateComponentResult)
				{
					AggregateComponentResult aggregateComponentResult = (AggregateComponentResult)componentResult;
				}
			}
			
			System.out.println("---------------------------------");
		}
	}
	private static void displayComponentResult(ArrayList<ComponentResult> componentResultList)
	{
		
	}
	
	public static void displayCourseComponentsWithWeightage(Course course)
	{
		System.out.println(course + "'s components (with weightage): ");
		
		for(ComponentWeightage componentWeightage: course.getAllComponentsWeightage())
			System.out.println(componentWeightage);
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
		double[] courseStat = new double[Grade.TOTAL_NUMBER_OF_GRADES.getValue()];
		
		ArrayList<Registration> registrations = course.getRegistrationRecords();
		
		Grade studentGrade;
		
		if(userChoice == 1)
		{
			for (Registration registration : registrations)
			{
				studentGrade = registration.getOverallResult().computeGrade();
				// the statement below increment the number of students who got a particular grade 
				// at the corresponding index of courseStat
				++courseStat[studentGrade.getValue()];
			}
		}
		else
		{
			displayCourseComponentsWithWeightage(course);
			
			// have to check if the course even has any components first
			ComponentWeightage componentToPrintStatFor = ConsoleIOHandler.getComponentWeightageFromCourse(course);
			String nameOfComponentToPrintStatFor = componentToPrintStatFor.getName();
			
			for (Registration registration : registrations)
			{
				ComponentResult studentResult = registration.getOverallResult().
						getComponentResult(nameOfComponentToPrintStatFor);
				studentGrade = studentResult.computeGrade();
				
				++courseStat[studentGrade.getValue()];
			}
		}
		
		System.out.println("\nGrade\t| Percentage of students\n" + "--------------------------------");
		for (int i = Grade.TOTAL_NUMBER_OF_GRADES.getValue() -1; i >= 0; --i)
			System.out.printf("%s\t| %.2f %%\t\t\n", Grade.valueOf(i), (courseStat[i]/ registrations.size() * 100.0));
		System.out.println("--------------------------------");
	}
	
	public static void displayCourseLessonTypes(Course course)
	{
		System.out.println("\nAll lesson types under this course: ");
		
		for(String lessonType: course.getLessonTypes())
			System.out.println(lessonType);
	}
	
	public static void displayCourseLessonByType(Course course, String lessonType)
	{
		for (Lesson lesson:course.getLessons())
		{
			if (lesson.getLessonType().equals(lessonType) )
					System.out.println(lesson);
		}
	}
	
	public static void displayCourseLessonByTypeWithVacancy(Course course, String lessonType)
	{
		for (Lesson lesson:course.getLessons())
		{
			if (lesson.getLessonType().equals(lessonType) )
					System.out.println(lesson + " [" + lesson.getVacancy() + '/' + lesson.getTotalSize() + ']');
		}
	}
	
	public static void displayStudentsInSpecificLesson(Course course, int lessonID)
	{
		System.out.println("List of students in " + lessonID + ":");
		
		for (Registration registration: course.getRegistrationRecords())
		{
			ArrayList<Integer> registeredLessonIDArrayList = registration.getLessonArrayList();
			
			for (Integer registeredLessonID: registeredLessonIDArrayList)
			{
				if (registeredLessonID == lessonID)
					System.out.println(registration.getRegisteredStudent());
			}
		}
	}
	
	public static void displayAllStudentsInCourse(Course course, String lessonType)
	{
		for (Lesson lesson: course.getLessons())
		{
			if (lesson.getLessonType().equals(lessonType))
			{
				int lessonID = lesson.getLessonID();
				displayStudentsInSpecificLesson(course,lessonID);
			}
		}
	}
}
