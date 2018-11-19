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

/**
 * This boundary class displays content that is passed as argument into its methods on the console.
 *
 */
public class ConsoleDisplay
{
	/**
	 * This method displays the main menu of SCRAME on the console. 
	 * The main menu is a list of functions the user can use SCRAME for.
	 * 
	 */
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
	
	/**
	 * This method takes in an ArrayList of generic type (assumed to be UniversityMember)
	 * and displays each item in the ArrayList on the console.
	 * 
	 * @param uniMemArrayList
	 */
	public static <T> void displayUniversityMembers(ArrayList<T> uniMemArrayList)
	{
		for(T uniMember: uniMemArrayList)
			System.out.println(uniMember);
	}
	
	/**
	 * This method takes in an ArrayList of Course and displays each Course in the ArrayList on the console.
	 * 
	 * @param courseArrayList
	 */
	public static void displayCourses(ArrayList<Course> courseArrayList)
	{
		System.out.println("\nList of courses: ");
		
		for (Course course : courseArrayList)
		{
			System.out.println(course);
		}
	}
	
	/**
	 * This method takes in an ArrayList of Course and displays each Course, 
	 * along with its respective course coordinator, on the console.
	 * 
	 * @param courseArrayList
	 */
	public static void displayCoursesWithCoordinators(ArrayList<Course> courseArrayList)
	{
		System.out.println("\nList of courses (with their respective course coordinators): ");
		
		for (Course course : courseArrayList)
		{
			System.out.println(course  + " - course coordinator: " + course.getCoordinator());
		}
	}
	
	/**
	 * This method takes in an ArrayList of Registration and displays each Registration on the console.
	 * 
	 * @param registrationArrayList
	 */
	public static void displayRegistrations(ArrayList<Registration> registrationArrayList)
	{
		for(Registration registration: registrationArrayList)
			System.out.println(registration);
	}
	
	/**
	 * This method takes in a Student and display the result 
	 * for each Course the Student is registered for on the console.
	 * 
	 * @param student
	 */
	public static void displayStudentTranscript(Student student)
	{
		System.out.println("\n" + student + "'s transcript:\n" + "---------------------------------");
		
		ArrayList<Registration> courseRegRecordArrayList = student.getCourseRegRecordArrayList();
		
		for (Registration courseRegRecord : courseRegRecordArrayList)
		{
			System.out.println(courseRegRecord.getRegisteredCourse() + "\n" + courseRegRecord.getOverallResult());
			
			ArrayList<ComponentResult> componentResultList = courseRegRecord.
					getOverallResult().getComponentResultList();
			
			displayComponentResult(componentResultList);
			
			System.out.println("---------------------------------");
		}
	}
	/**
	 * This method takes in an ArrayList of ComponentResult and display each ComponentResult on the console.
	 * 
	 * @param componentResultList
	 */
	private static void displayComponentResult(ArrayList<ComponentResult> componentResultList)
	{
		for (ComponentResult componentResult : componentResultList)
		{
			if(componentResult instanceof AggregateComponentResult)
				System.out.println((AggregateComponentResult)componentResult);
			else
				System.out.println(componentResult);
		}
	}
	
	/**
	 * This method takes in a Course and display its Components, 
	 * along with their respective weightages, on the console.
	 * 
	 * @param course
	 */
	public static void displayCourseComponentsWithWeightage(Course course)
	{
		System.out.println("\n" + course + "'s components (with weightage): ");
		
		for(ComponentWeightage componentWeightage: course.getAllComponentsWeightage())
			System.out.println(componentWeightage);
	}
	
	/**
	 * This method takes a Course and display the percentage of students who obtain a particular grade,
	 * for each possible grade, for either the Course itself, or one of its Components, on the console.
	 * 
	 * Note that most of the logic in this method should be shifted to a method in another class,
	 * but there's not enough time to make the change.
	 * 
	 * @param course
	 */
	public static void displayCourseStatistic(Course course)
	{
		System.out.println("\nShow grade percentage for: \n1 - Overall \n2 - A particular component");
		String userChoicePrompt = "Enter your choice: ";
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
		
		// the actual displaying of statistics
		System.out.println("\nGrade\t| Percentage of students\n" + "--------------------------------");
		for (int i = Grade.TOTAL_NUMBER_OF_GRADES.getValue() -1; i >= 0; --i)
			System.out.printf("%s\t| %.2f %%\t\t\n", Grade.valueOf(i), 
					(courseStat[i]/ registrations.size() * 100.0));
		System.out.println("--------------------------------");
	}
	
	/**
	 * This methods takes in a Course and displays the lesson type the Course has on the console.
	 * 
	 * @param course
	 */
	public static void displayCourseLessonTypes(Course course)
	{
		System.out.println("\nAll lesson types under this course: ");
		
		for(String lessonType: course.getLessonTypes())
			System.out.println(lessonType);
	}
	
	/**
	 * This method takes in a Course and a Lesson type and 
	 * display all Lessons in the Course of that Lesson type on the console.
	 * 
	 * @param course
	 * @param lessonType
	 */
	public static void displayCourseLessonByType(Course course, String lessonType)
	{
		for (Lesson lesson:course.getLessons())
		{
			if (lesson.getLessonType().equals(lessonType) )
					System.out.println(lesson);
		}
	}
	
	/**
	 * This method takes in a Course and a Lesson type and display all Lessons, 
	 * along with their respective vacancies, in the Course of that Lesson type on the console.
	 * 
	 * @param course
	 * @param lessonType
	 */
	public static void displayCourseLessonByTypeWithVacancy(Course course, String lessonType)
	{
		for (Lesson lesson:course.getLessons())
		{
			if (lesson.getLessonType().equals(lessonType) )
					System.out.println(lesson + " [" + lesson.getVacancy() + '/' + lesson.getTotalSize() + ']');
		}
	}
	
	/**
	 * This methods takes in a Course and Lesson ID, and displays all students registered
	 * for a specific Lesson ID in a Course on the console.
	 * 
	 * @param course
	 * @param lessonID
	 */
	public static void displayStudentsInSpecificLesson(Course course, int lessonID)
	{
		System.out.println("\nList of students in " + lessonID + ":");
		
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
	
	/**
	 * The methods takes in a Course and Lesson type, and displays all students registered
	 * for the Course, grouped by the Lesson type, on the console.
	 * 
	 * @param course
	 * @param lessonType
	 */
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
