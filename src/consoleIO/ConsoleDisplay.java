package consoleIO;

import java.util.ArrayList;

import courses.ComponentWeightage;
import courses.Course;
import grading.ComponentResult;
import grading.Grade;
import lessons.Lesson;
import universityMembers.*;
import registration.Registration;

public class ConsoleDisplay
{
	public static void displayMainMenu()
	{
		System.out.println("Select an option:\n" + 
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
		System.out.println("Printing Student Transcript for " + student + ":");
		
		ArrayList<Registration> courseRegRecordArrayList = student.getCourseRegRecordArrayList();
		
		for (Registration courseRegRecord : courseRegRecordArrayList)
		{
			System.out.println(courseRegRecord.getRegisteredCourse() + " "
					+ courseRegRecord.getOverallResult().getMarks() + " "
					+ courseRegRecord.getOverallResult().computeGrade());
			
			ArrayList<ComponentResult> componentResultList = courseRegRecord.getOverallResult().getComponentResultList();
			for (ComponentResult componentResult : componentResultList)
			{
				System.out.println("       " + componentResult.getName() + " " + componentResult.getMarks());
			}
		}
	}
	
	public static void displayCourseComponents(Course course)
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
			displayCourseComponents(course);
			
			// have to check if the course even has any components first
			ComponentWeightage componentToPrintStatFor = ConsoleIOHandler.getComponentWeightageFromCourse(course);
			String nameOfComponentToPrintStatFor = componentToPrintStatFor.getName();
			
			for (Registration registration : registrations)
			{
				ComponentResult studentResult = registration.getOverallResult().getComponentResult(nameOfComponentToPrintStatFor);
				studentGrade = studentResult.computeGrade();
				
				++courseStat[studentGrade.getValue()];
			}
		}
		
		for (int i = 0; i < Grade.TOTAL_NUMBER_OF_GRADES.getValue(); ++i)
			System.out.printf("%s %.2f %%\n", Grade.valueOf(i), (courseStat[i]/ registrations.size() * 100.0));
	}
	
	public static void displayCourseLessonTypes(Course course)
	{
		System.out.println("All lesson types under this course: ");
		
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
	
	public static void displayStudentsInSpecificLesson(Course course, String lessonID)
	{
		System.out.println("List of students in " + lessonID + ":");
		
		for (Registration registration: course.getRegistrationRecords())
		{
			ArrayList<String> registeredLessons = registration.getLessonArrayList();
			
			for (String registeredLessonID: registeredLessons)
			{
				if (registeredLessonID.equals(lessonID))
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
				String lessonID = lesson.getLessonID();
				displayStudentsInSpecificLesson(course,lessonID);
			}
		}
	}
}
