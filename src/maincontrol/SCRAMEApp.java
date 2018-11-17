package maincontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import consoleIO.*;
import courses.Course;
import creation.CreationHandler;
import database.DatabaseIO;
import database.FacultyDB;
import database.CourseDB;
import database.StudentDB;
import grading.ComponentResult;
import grading.Grade;
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

		FacultyDB faculty = new FacultyDB(DatabaseIO.readFacultyDB(facultyFilename));
		CourseDB courses = new CourseDB(DatabaseIO.readCourseDB(courseFilename, faculty));
		StudentDB students = new StudentDB(DatabaseIO.readStudentDB(studentFilename, courses.getCourseAl()));

		System.out.println("Welcome to SCRAME");

		int userChoice = 0;
		do
		{
			ConsoleDisplay.displayMainMenu();

			String userChoicePrompt = "Enter your choice:";
			userChoice = ConsoleInputInterface.getUserPositiveIntInput(userChoicePrompt, 11);

			switch (userChoice)
			{
				case 1:
					// Add a student
					Student student = CreationHandler.createStudent();
					students.addStudent(student);
					// students.saveStudents("Students.txt");

					System.out.println("List of all students:");
					students.printStudentList();
					break;
				case 2:
					// Add a course
					System.out.println("List of all faculty members: ");
					ConsoleDisplay.displayFacultyMembers(faculty.getAllFacultyMembers());

					FacultyMember courseCoordinator = ConsoleIO.getFacultyMemberFromDB(faculty);
					Course newCourse = CreationHandler.createCourse(courseCoordinator);
					CreationHandler.createLessons(newCourse);
					courses.addCourse(newCourse);
					break;
				case 3:
					// Register student for a course (this include registering for Tutorial/Lab
					// classes)
					System.out.println("Enter the student's ID: ");
					String matriculationNumber2 = ConsoleInputInterface.consoleScanner.nextLine();
					if (!(checkInput(matriculationNumber2, 2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int studentIndex = students.checkStudent(matriculationNumber2);
					if (studentIndex != -1)
					{
						System.out.println("List of all courses:");
						courses.printCourseList();
						System.out.println("Enter a course code: ");
						String courseCode = ConsoleInputInterface.consoleScanner.nextLine();
						if (!(checkInput(courseCode, 2)))
						{
							System.out.println("Error! Please enter only letters and digits.");
							break;
						}
						int courseIndex = courses.checkCourse(courseCode);
						if (courseIndex != -1)
						{
							if (!courses.getCourseAl().get(courseIndex).checkStudent(matriculationNumber2))
							{
								System.out.printf("Registering %s into %s...\n", matriculationNumber2, courseCode);
								// Code for adding a student into lessons
								ArrayList<Lesson> lessons = courses.getCourseAl().get(courseIndex).getLessons();
								ArrayList<String> lessonListType = new ArrayList<String>();
								// Find all unique lesson types
								for (int j = 0; j < lessons.size(); j++)
								{
									lessonListType.add(lessons.get(j).getLessonType());
								}
								Set<String> uniqueLessonType = new HashSet<String>(lessonListType);
								Iterator<String> it = uniqueLessonType.iterator();
								ArrayList<String> uniqueLessonListType = new ArrayList<String>();
								while (it.hasNext())
								{
									uniqueLessonListType.add((String) it.next().toString());
								}
								System.out.println(uniqueLessonListType);
								for (String uniqueLesson : uniqueLessonListType)
								{
									System.out.println("Register for " + uniqueLesson);
									System.out.println("List of indexes:");
									courses.getCourseAl().get(courseIndex).printLessonList(uniqueLesson);

									System.out.println("Select an index to register for:");
									String lessonIndex = ConsoleInputInterface.consoleScanner.nextLine();
									while (!courses.getCourseAl().get(courseIndex).getLesson(lessonIndex)
											.enrolStudent())
									{
										System.out.println("Choose another index:");
										lessonIndex = ConsoleInputInterface.consoleScanner.nextLine();
									}
								}
							} else
								System.out.println("Student already registered in this course.");
						} else
							System.out.println("Course not found in database!");
					} else
						System.out.println("Student not found in database!");
					break;

				case 4:
					// Check available slot in a class (vacancy in a class)
					System.out.println("Enter a course code: ");
					String courseCode = ConsoleInputInterface.consoleScanner.nextLine();
					if (!(checkInput(courseCode, 2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int courseIndex = courses.checkCourse(courseCode);
					if (courseIndex != -1)
					{
						System.out.println("All lesson types under this course: ");
						ArrayList<Lesson> lessons = courses.getCourseAl().get(courseIndex).getLessons();
						ArrayList<String> lessonListType = new ArrayList<String>();
						for (int j = 0; j < lessons.size(); j++)
						{
							lessonListType.add(lessons.get(j).getLessonType());
						}
						// Find all unique lesson types
						Set<String> uniqueLessonType = new HashSet<String>(lessonListType);
						Iterator<String> it = uniqueLessonType.iterator();
						while (it.hasNext())
						{
							System.out.println(it.next());
						}
						System.out.println("Enter a lesson type to print by: ");
						String lessonType = ConsoleInputInterface.consoleScanner.nextLine();
						System.out.printf("All indexes for %s:\n", lessonType);
						courses.getCourseAl().get(courseIndex).printLessonList(lessonType);
						System.out.println("Enter an index: ");
						String lessonIndex2 = ConsoleInputInterface.consoleScanner.nextLine();
						int vacancy = courses.getCourseAl().get(courseIndex).getLesson(lessonIndex2).getVacancy();
						int totalSize = courses.getCourseAl().get(courseIndex).getLesson(lessonIndex2).getTotalSize();
						System.out.printf("%s %d/%d\n", lessonIndex2, vacancy, totalSize);
					} else
						System.out.println("Course not found in database!");
					break;

				case 5:
					// Print student list by lecture, tutorial or laboratory session for a course.
					System.out.println("Enter a course code: ");
					String courseCode3 = ConsoleInputInterface.consoleScanner.nextLine();
					if (!(checkInput(courseCode3, 2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int courseIndex2 = courses.checkCourse(courseCode3);
					if (courseIndex2 != -1)
					{
						System.out.println("All lesson types under this course: ");
						ArrayList<Lesson> lessons = courses.getCourseAl().get(courseIndex2).getLessons();
						ArrayList<String> lessonListType = new ArrayList<String>();
						for (int j = 0; j < lessons.size(); j++)
						{
							lessonListType.add(lessons.get(j).getLessonType());
						}
						// Find all unique lesson types
						Set<String> uniqueLessonType = new HashSet<String>(lessonListType);
						Iterator<String> it = uniqueLessonType.iterator();
						while (it.hasNext())
						{
							System.out.println(it.next());
						}
						System.out.println("Enter a lesson type to check vacancy: ");
						String lessonType = ConsoleInputInterface.consoleScanner.nextLine();
						System.out.printf("All indexes for %s:\n", lessonType);
						courses.getCourseAl().get(courseIndex2).printLessonList(lessonType);
						System.out.println("Select an option:");
						System.out.println("1 - Print student list by index");
						System.out.println("2 - Print all students");
						int userChoice2 = ConsoleInputInterface.consoleScanner.nextInt();
						switch (userChoice2)
						{
							case 1:
								System.out.println("Enter an index: ");
								String lessonIndex = ConsoleInputInterface.consoleScanner.nextLine();
								if (!(checkInput(lessonIndex, 2)))
								{
									System.out.println("Error! Please enter only letters and digits.");
									break;
								}

								System.out.println(courses.getCourseAl().get(courseIndex2));
								courses.getCourseAl().get(courseIndex2).printSomeStudents(lessonIndex);
								break;
							case 2:
								courses.getCourseAl().get(courseIndex2).printAllStudents(lessonType);
								break;
							default:
								System.out.println("Invalid option.");
								break;
						}
					} else
						System.out.println("Course not found in database!");
					break;

				case 6:
					// Enter course assessment components weightage for a course
					Course courseToEnterWeightage;
					do
					{
						String courseEnterWeightagePrompt = "Enter course code: ";
						String coursecodeToEnterWeightage = ConsoleInputInterface
								.getUserStringInput(courseEnterWeightagePrompt, StringFormatType.ALPHA_NUMERIC);

						courseToEnterWeightage = courses.getCourse(coursecodeToEnterWeightage);

						if (courseToEnterWeightage == null)
							System.out.println("Course not found in database!");

					} while (courseToEnterWeightage == null);

					CreationHandler.createCourseComponents(courseToEnterWeightage);

					break;

				case 7:
					// Enter coursework mark inclusive of its components.
					Student studentToEnterMarksFor = ConsoleIO.getStudentFromDB(students);
					
					// have to check if student is currently taking any course at all
					
					System.out.println("Which course do you want to enter marks in?");
					ConsoleDisplay.displayRegisteredCourses(studentToEnterMarksFor.getCoursesRegistered());
					
					CourseRegistrationRecord courseToEnterMarksFor = ConsoleIO.getCourseRegRecordFromStudent(studentToEnterMarksFor);
					
					courseToEnterMarksFor.enterMarks();

					break;
					
//				case 8:
//					System.out.println("Enter a course code: ");
//					String courseCode8 = ConsoleInputInterface.consoleScanner.nextLine();
//					if (!(checkInput(courseCode8, 2)))
//					{
//						System.out.println("Error! Please enter only letters and digits.");
//						break;
//					}
//					int courseIndex5 = courses.checkCourse(courseCode8);
//					if (courseIndex5 != -1)
//					{
//						System.out.println("Enter the student's ID: ");
//						String matriculationNumber3 = ConsoleInputInterface.consoleScanner.nextLine();
//						if (!(checkInput(matriculationNumber3, 2)))
//						{
//							System.out.println("Error! Please enter only letters and digits.");
//							break;
//						}
//						int studentIndex3 = students.checkStudent(matriculationNumber3);
//						if (studentIndex3 != -1)
//						{
//							// Check the student is registered for the course
//							if (courses.getCourseAl().get(courseIndex5).checkStudent(matriculationNumber3))
//							{
//								ArrayList<ComponentWeightage> components = courses.getCourseAl().get(courseIndex5)
//										.getAllComponentsWeightage();
//								for (ComponentWeightage component : components)
//								{
//									if (component.getName().equals("Exam"))
//									{
//										int recordIndex = students.getStudentAl().get(studentIndex3)
//												.searchRecord(courseCode8);
//										{
//											MarksEntryInterface.enterMarks(students.getStudentAl().get(studentIndex3)
//													.getCoursesRegistered().get(recordIndex));
//										}
//
//									}
//								}
//							} else
//								System.out.println("Student not registered for this course!");
//						} else
//							System.out.println("Student not found in database!");
//					} else
//						System.out.println("Course not found in database!");
//					
//					break;
					
				case 8: //case 9:
					System.out.println("Enter the course:");
					String coursecode11 = ConsoleInputInterface.consoleScanner.nextLine();
					int courseIndex6 = courses.checkCourse(coursecode11);
					if (courseIndex6 != -1)
					{
						System.out.println("Show grade percentage for:");
						System.out.println("1 - Overall");
						System.out.println("2 - Exam only");
						System.out.println("3 - Coursework only");
						int choice = ConsoleInputInterface.consoleScanner.nextInt();
						double[] courseStat = new double[11];
						ArrayList<CourseRegistrationRecord> registrations = courses.getCourseAl().get(courseIndex6)
								.getRegistrations();
						switch (choice)
						{
							case 1:
								for (CourseRegistrationRecord registration : registrations)
								{
									courseStat[registration.getOverallResults().computeGrade().getValue()]++;
								}
								break;
							case 2:
								for (CourseRegistrationRecord registration : registrations)
								{
									int index = 0;
									if (registration.getOverallResults().getComponentResultList().get(index).getName()
											.equals("Exam"))
										courseStat[registration.getOverallResults().getComponentResultList().get(index)
												.computeGrade().getValue()]++;
									index++;
								}
								break;
							case 3:
								for (CourseRegistrationRecord registration : registrations)
								{
									int index = 0;
									if (registration.getOverallResults().getComponentResultList().get(index).getName()
											.equals("Exam"))
										courseStat[registration.getOverallResults().getComponentResultList().get(index)
												.computeGrade().getValue()]++;
									index++;
								}
								break;
							default:
								break;
						}
						for (int i = 0; i < 11; i++)
						{
							courseStat[i] = courseStat[i] / registrations.size() * 100;
							System.out.printf("%s %.2f %%\n", Grade.valueOf(i), courseStat[i]);
						}
					} else
						System.out.println("Course not found in database!");
					
					break;
					
				case 9: //case 10:
					System.out.println("Enter the student's ID: ");
					String studentID = ConsoleInputInterface.consoleScanner.next();
					if (!(checkInput(studentID, 2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int studentIndex2 = students.checkStudent(studentID);
					if (studentIndex2 != -1)
					{
						System.out.println(
								"Printing Student Transcript for " + students.getStudentAl().get(studentIndex2).getID()
										+ ", " + students.getStudentAl().get(studentIndex2).getfullName() + ":");
						ArrayList<CourseRegistrationRecord> coursesRegistered = students.getStudentAl()
								.get(studentIndex2).getCoursesRegistered();
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
								System.out.println(
										"       " + componentResult.getName() + " " + componentResult.getMarks());
							}
						}
					}
					break;
					
				case 10: // case 11:
					System.out.println("Thank you for using SCRAME\n" + "---Quitting SCRAME---");
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
