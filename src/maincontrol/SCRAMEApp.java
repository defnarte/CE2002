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
import registration.Registration;
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
			String lessonType;

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
					ConsoleDisplay.displayCourseLessonTypes(course);
					
					lessonType = ConsoleIOHandler.getLessonTypeFromUser();
					
					System.out.println("All indexes for " + lessonType + ": ");
					course.printLessonsByTypeWithVacancy(lessonType);

					break;

				case 5:
					// Print student list by lecture, tutorial or laboratory session for a course.
					course = ConsoleIOHandler.getCourseFromDB(courseDB);
					ConsoleDisplay.displayCourseLessonTypes(course);
					
					lessonType = ConsoleIOHandler.getLessonTypeFromUser();
					
					course.printLessonsByType(lessonType); // false
					
					String promptMessage = "Select an option:\n"
					+ "1 - Print student list for a specific " + lessonType + "\n"
					+ "2 - Print all " + lessonType + " students";
					int printingChoice = ConsoleInputInterface.getUserPositiveIntInput(promptMessage,2);
					
					if(printingChoice == 1)
					{
						String lessonID = ConsoleIOHandler.getLessonIDFromUser(courseDB);
						course.printStudentsInSpecificLesson(lessonID);
					}
					else
						course.printAllStudentsOfALessonType(lessonType);

					break;

				case 6:
					// Enter course assessment components weightage for a course
					course = ConsoleIOHandler.getCourseFromDB(courseDB);

					CreationHandler.createCourseComponents(course);

					break;

				case 7:
					// Enter course component marks.
					student = ConsoleIOHandler.getStudentFromDB(studentDB);

					// check if student is currently taking any course at all
					if(student.getCourseRegRecordArrayList().isEmpty())
						System.out.println(student + " is not registered for any course currently.");
					else
					{
						System.out.println("Which course do you want to enter marks in?");
						ConsoleDisplay.displayRegisteredCourses(student.getCourseRegRecordArrayList());

						Registration courseRegRecord = ConsoleIOHandler.getCourseRegRecordFromStudent(student);

						courseRegRecord.enterMarks();
					}

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

}
