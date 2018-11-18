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
 * @author SE1 Group 5
 *
 */
public class SCRAMEApp
{
	private static final int MAX_USER_CHOICE = 10;
	
	public static void main(String args[]) throws IOException
	{
		String studentFilename = "Students.txt";
		String courseFilename = "Courses.txt";
		String facultyFilename = "Faculty.txt";

		FacultyMemberDB facultyMemberDB = new FacultyMemberDB(DatabaseIO.readFacultyDB(facultyFilename));
		CourseDB courseDB = new CourseDB(DatabaseIO.readCourseDB(courseFilename, facultyMemberDB));
		StudentDB studentDB = new StudentDB(DatabaseIO.readStudentDB(studentFilename, courseDB.getCourseAl()));
		
		System.out.println("---Welcome to SCRAME---");

		int userChoice;
		do
		{
			ConsoleDisplay.displayMainMenu();

			String userChoicePrompt = "Enter your choice: ";
			userChoice = ConsoleInputInterface.getUserPositiveIntInput(userChoicePrompt, MAX_USER_CHOICE);

			Student student;
			Course course;
			String lessonType;

			switch (userChoice)
			{
				case 1:
					// Add a student
					student = CreationHandler.createStudent(studentDB);
					studentDB.addStudent(student);
					System.out.println("List of all students:");
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
					// Register student for a course (this include registering for lessons)
					if(studentDB.getStudentAl().isEmpty())
					{
						System.out.println("No students exist in student database.");
					}
					else
					{
						student = ConsoleIOHandler.getStudentFromDB(studentDB);
						CreationHandler.createRegistration(student,courseDB);
					}
					
					break;

				case 4:
					// Check available slot(vacancy) in lessons of a certain type in a course
					if(courseDB.getCourseAl().isEmpty())
					{
						System.out.println("No courses exist in course database.");
					}
					else
					{
						course = ConsoleIOHandler.getCourseFromDB(courseDB);
						ConsoleDisplay.displayCourseLessonTypes(course);
						
						lessonType = ConsoleIOHandler.getLessonTypeFromUser();
						
						System.out.println("All indexes for " + lessonType + ": ");
						course.printLessonsByTypeWithVacancy(lessonType);
					}
					
					break;

				case 5:
					// Print student list by lesson type, or in a specific lesson of a certain type for a course.
					if(courseDB.getCourseAl().isEmpty())
					{
						System.out.println("No courses exist in course database.");
					}
					else
					{
						course = ConsoleIOHandler.getCourseFromDB(courseDB);
						ConsoleDisplay.displayCourseLessonTypes(course);
						
						lessonType = ConsoleIOHandler.getLessonTypeFromUser();
						
						course.printLessonsByType(lessonType);
						
						String promptMessage = "Display options:\n" + 
								"1 - Display students in a specific " + lessonType + "\n" + 
								"2 - Display all " + lessonType + " students\n" + 
								"Select an option: ";
						int printingChoice = ConsoleInputInterface.getUserPositiveIntInput(promptMessage,2);
						
						if(printingChoice == 1)
						{
							int lessonID = ConsoleIOHandler.getLessonIDFromUser(courseDB);
							course.printStudentsInSpecificLesson(lessonID);
						}
						else
							course.printAllStudents(lessonType);
					}

					break;

				case 6:
					// Enter course assessment components weightage for a course
					if(courseDB.getCourseAl().isEmpty())
					{
						System.out.println("No courses exist in course database.");
					}
					else
					{
						course = ConsoleIOHandler.getCourseFromDB(courseDB);
						CreationHandler.createCourseComponents(course);
					}

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
					
					if(courseDB.getCourseAl().isEmpty())
					{
						System.out.println("No courses exist in course database.");
					}
					else
					{
						course = ConsoleIOHandler.getCourseFromDB(courseDB);
						ConsoleDisplay.displayCourseStatistic(course);
					}

					break;

				case 9:
					// Print student transcript
					
					if(studentDB.getStudentAl().isEmpty())
					{
						System.out.println("No students exist in student database.");
					}
					else
					{
						student = ConsoleIOHandler.getStudentFromDB(studentDB);
						ConsoleDisplay.displayStudentTranscript(student);
					}

					break;

				case 10:
					System.out.println("Thank you for using SCRAME\n" + "---Quitting SCRAME---");

					break;

				default:
					System.out.println("Invalid option. Please enter your choice based on the options below: \n");
					break;
			}

		} while (userChoice != MAX_USER_CHOICE);

		ConsoleInputInterface.consoleScanner.close();
	}

}
