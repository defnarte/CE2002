package maincontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import consoleIO.*;
import courses.Course;
import creation.CreationHandler;
import database.DatabaseIO;
import database.FacultyMemberDB;
import database.CourseDB;
import database.StudentDB;
import lessons.Lesson;
import registration.CourseRegistrationRecord;
import universityMembers.FacultyMember;
import universityMembers.Student;

/**
 * This class is the main program that runs everything.
 * 
 * 
 * @author SE1 Group 5
 *
 */
public class SCRAMEApp
{
	public static void main(String args[]) throws IOException
	{
		String studentFilename = "Students.txt";
		String courseFilename = "Courses.txt";
		String facultyFilename = "Faculty.txt";

		FacultyMemberDB facultyMemberDB = new FacultyMemberDB(DatabaseIO.readFacultyDB(facultyFilename));
		CourseDB courseDB = new CourseDB(DatabaseIO.readCourseDB(courseFilename, facultyMemberDB));
		StudentDB studentDB = new StudentDB(DatabaseIO.readStudentDB(studentFilename, courseDB.getCourseAl()));

		System.out.println("Welcome to SCRAME");

		int userChoice = 0;
		do
		{
			ConsoleDisplay.displayMainMenu();

			String userChoicePrompt = "Enter your choice: ";
			userChoice = ConsoleInputInterface.getUserPositiveIntInput(userChoicePrompt, 11);

			Student student;
			Course course;

			switch (userChoice)
			{
				case 1:
					// Add a student
					student = CreationHandler.createStudent(studentDB);
					studentDB.addStudent(student);
					System.out.println("List of all studentDB:");
					studentDB.printStudentList();
					break;
				case 2:
					// Add a course
					System.out.println("List of all faculty members: ");
					ConsoleDisplay.displayUniversityMembers(facultyMemberDB.getFacultyAl());

					FacultyMember courseCoordinator = ConsoleIOHandler.getFacultyMemberFromDB(facultyMemberDB);
					course = CreationHandler.createCourse(courseCoordinator,courseDB);
					CreationHandler.createLessons(course,courseDB);
					courseDB.addCourse(course);
					courseDB.printCourseList();
					break;
				case 3:
					// Register student for a course (this include registering for Tutorial/Lab
					// classes)
					// have to check if studentDB is empty
					student = ConsoleIOHandler.getStudentFromDB(studentDB);
					CreationHandler.createRegistration(student,courseDB);
					break;

				case 4:
					// Check available slot in a class (vacancy in a class)
					course = ConsoleIOHandler.getCourseFromDB(courseDB);

					System.out.println("All lesson types under this course: ");
					ArrayList<Lesson> lessons = course.getLessons();
					ArrayList<String> lessonListType = new ArrayList<String>();
					for (int j = 0; j < lessons.size(); j++)
					{
						lessonListType.add(lessons.get(j).getLessonType());
					}
					// Find all unique lesson types
					HashSet<String> uniqueLessonType = new HashSet<String>(lessonListType);
					Iterator<String> it = uniqueLessonType.iterator();
					while (it.hasNext())
					{
						System.out.println(it.next());
					}
					System.out.println("Enter a lesson type to print by: ");
					String lessonType = ConsoleInputInterface.consoleScanner.nextLine();
					System.out.printf("All indexes for %s:\n", lessonType);
					course.printLessonList(lessonType,false);
					System.out.println("Enter an index: ");
					String lessonIndex2 = ConsoleInputInterface.consoleScanner.nextLine();
					int vacancy = course.getLesson(lessonIndex2).getVacancy();
					int totalSize = course.getLesson(lessonIndex2).getTotalSize();
					System.out.printf("%s %d/%d\n", lessonIndex2, vacancy, totalSize);

					break;

				case 5:
					// Print student list by lecture, tutorial or laboratory session for a course.
					course = ConsoleIOHandler.getCourseFromDB(courseDB);

					System.out.println("All lesson types under this course: ");
					ArrayList<Lesson> lessons2 = course.getLessons();
					ArrayList<String> lessonListType2 = new ArrayList<String>();
					for (int j = 0; j < lessons2.size(); j++)
					{
						lessonListType2.add(lessons2.get(j).getLessonType());
					}
					// Find all unique lesson types
					HashSet<String> uniqueLessonType2 = new HashSet<String>(lessonListType2);
					Iterator<String> it2 = uniqueLessonType2.iterator();
					while (it2.hasNext())
					{
						System.out.println(it2.next());
					}
					String lessonType2 = ConsoleInputInterface.getUserStringInput("Enter a lesson type to check vacancy: ", StringFormatType.ALPHA_NUMERIC);
					while (!course.checkLessonType(lessonType2))
					{
						lessonType2 = ConsoleInputInterface.getUserStringInput("Invalid lesson type. Enter a lesson type to check vacancy: ", StringFormatType.ALPHA_NUMERIC);
					}
					System.out.printf("All indexes for %s:\n", lessonType2);
					course.printLessonList(lessonType2,false);
					String promptMessage = "Select an option:\n"
					+ "1 - Print student list by index\n"
					+ "2 - Print all studentDB\n";
					int userChoice2 = ConsoleInputInterface.getUserPositiveIntInput(promptMessage,2);
					switch (userChoice2)
					{
						case 1:
							String lessonID = ConsoleInputInterface.getUserStringInput("Enter an index: ",StringFormatType.ALPHA_NUMERIC);
							while (!courseDB.checkLesson(lessonID))
							{
								lessonID = ConsoleInputInterface.getUserStringInput("Invalid index. Enter an index: ",StringFormatType.ALPHA_NUMERIC);
							}
							course.printSomeStudents(lessonID);
							break;
						case 2:
							course.printAllStudents(lessonType2);
							break;
						default:
							System.out.println("Invalid option.");
							break;
					}

					break;

				case 6:
					// Enter course assessment components weightage for a course
					course = ConsoleIOHandler.getCourseFromDB(courseDB);

					CreationHandler.createCourseComponents(course);

					break;

				case 7: // incomplete
					// Enter course component marks.
					student = ConsoleIOHandler.getStudentFromDB(studentDB);

					// have to check if student is currently taking any course at all

					System.out.println("Which course do you want to enter marks in?");
					ConsoleDisplay.displayRegisteredCourses(student.getCourseRegRecordArrayList());

					CourseRegistrationRecord courseRegRecord = ConsoleIOHandler.getCourseRegRecordFromStudent(student);

					courseRegRecord.enterMarks();

					break;

				case 8:
					// Print course statistics
					course = ConsoleIOHandler.getCourseFromDB(courseDB);
					ConsoleDisplay.displayCourseStatistic(course);

					break;

				case 9:
					// Print student transcript
					student = ConsoleIOHandler.getStudentFromDB(studentDB);
					ConsoleDisplay.displayStudentTranscript(student);

					break;

				case 10:
					System.out.println("Thank you for using SCRAME\n" + "---Quitting SCRAME---");

					break;

				default:
					System.out.println("Invalid option. Please enter your choice based on the options below: \n");
					break;
			}

		} while (userChoice != 10);

		ConsoleInputInterface.consoleScanner.close();
	}

	public static boolean checkInput(String input, int type)
	{
		boolean b = false;
		// Check letters and whitespace
		if (type == 1)
		{
			Pattern p = Pattern.compile("^[ A-Za-z]+$");
			Matcher m = p.matcher(input);
			b = m.matches();
		}
		// Check letters and digits
		else if (type == 2)
		{
			Pattern p2 = Pattern.compile("^[A-Za-z0-9]+$");
			Matcher m2 = p2.matcher(input);
			b = m2.matches();
		}
		return b;
	}
}
