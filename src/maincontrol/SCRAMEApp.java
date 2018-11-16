package maincontrol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import consoleIO.*;
import grading.MarksEntryInterface;
import courses.ComponentWeightage;
import courses.Course;
import creation.CreationHandler;
import database.CombinedDB;
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

		CombinedDB database = new CombinedDB();
		CourseDB courses = new CourseDB(database.readCourseDB(courseFilename));
		StudentDB students = new StudentDB(database.readStudentDB(studentFilename, courses.getCourseAl()));

		System.out.println("Welcome to SCRAME");

		int userChoice = 0;
		do
		{
			ConsoleDisplay.printMainMenu();

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
					/**
					 * TO-DO: get an actual faculty member
					 */
					FacultyMember dummyFM = new FacultyMember("S1234567A","Jane Doe");

					Course newCourse = CreationHandler.createCourse(dummyFM);
					courses.addCourse(newCourse);
					break;
				case 3:
					
					System.out.println("Enter the student's ID: ");
					String matriculationNumber2 = ConsoleInputInterface.consoleScanner.next();
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
						String courseCode = ConsoleInputInterface.consoleScanner.next();
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
									String lessonIndex = ConsoleInputInterface.consoleScanner.next();
									while (!courses.getCourseAl().get(courseIndex).getLesson(lessonIndex)
											.enrolStudent())
									{
										System.out.println("Choose another index:");
										lessonIndex = ConsoleInputInterface.consoleScanner.next();
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
					System.out.println("Enter a course code: ");
					String courseCode = ConsoleInputInterface.consoleScanner.next();
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
						String lessonType = ConsoleInputInterface.consoleScanner.next();
						System.out.printf("All indexes for %s:\n", lessonType);
						courses.getCourseAl().get(courseIndex).printLessonList(lessonType);
						System.out.println("Enter an index: ");
						String lessonIndex2 = ConsoleInputInterface.consoleScanner.next();
						int vacancy = courses.getCourseAl().get(courseIndex).getLesson(lessonIndex2).getVacancy();
						int totalSize = courses.getCourseAl().get(courseIndex).getLesson(lessonIndex2).getTotalSize();
						System.out.printf("%s %d/%d\n", lessonIndex2, vacancy, totalSize);
					} else
						System.out.println("Course not found in database!");
					break;
				case 5:
					System.out.println("Enter a course code: ");
					String courseCode3 = ConsoleInputInterface.consoleScanner.next();
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
						String lessonType = ConsoleInputInterface.consoleScanner.next();
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
								String lessonIndex = ConsoleInputInterface.consoleScanner.next();
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
						String coursecodeToEnterWeightage = ConsoleInputInterface.getUserStringInput(courseEnterWeightagePrompt, 2);
						
						courseToEnterWeightage = courses.getCourse(coursecodeToEnterWeightage);
						
						if(courseToEnterWeightage == null)
							System.out.println("Course not found in database!");
						
					} while(courseToEnterWeightage == null);
					
					CreationHandler.createCourseComponents(courseToEnterWeightage);
					
					break;
				case 7:
					System.out.println("Enter a course code: ");
					String courseCode5 = ConsoleInputInterface.consoleScanner.next();
					if (!(checkInput(courseCode5, 2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int courseIndex4 = courses.checkCourse(courseCode5);
					if (courseIndex4 != -1)
					{
						System.out.println("Enter the student's ID: ");
						String matriculationNumber3 = ConsoleInputInterface.consoleScanner.next();
						if (!(checkInput(matriculationNumber3, 2)))
						{
							System.out.println("Error! Please enter only letters and digits.");
							break;
						}
						int studentIndex3 = students.checkStudent(matriculationNumber3);
						if (studentIndex3 != -1)
						{
							// Check the student is registered for the course
							if (courses.getCourseAl().get(courseIndex4).checkStudent(matriculationNumber3))
							{
								ArrayList<ComponentWeightage> components = courses.getCourseAl().get(courseIndex4)
										.getAllComponentsWeightage();
								for (ComponentWeightage component : components)
								{
									if (!component.getName().equals("Exam"))
									{
										int recordIndex = students.getStudentAl().get(studentIndex3)
												.searchRecord(courseCode5);
										{
											MarksEntryInterface.enterMarks(students.getStudentAl().get(studentIndex3)
													.getCoursesRegistered().get(recordIndex));
										}

									}
								}
							} else
								System.out.println("Student not registered for this course!");
						} else
							System.out.println("Student not found in database!");
					} else
						System.out.println("Course not found in database!");
					break;
				case 8:
					System.out.println("Enter a course code: ");
					String courseCode8 = ConsoleInputInterface.consoleScanner.next();
					if (!(checkInput(courseCode8, 2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int courseIndex5 = courses.checkCourse(courseCode8);
					if (courseIndex5 != -1)
					{
						System.out.println("Enter the student's ID: ");
						String matriculationNumber3 = ConsoleInputInterface.consoleScanner.next();
						if (!(checkInput(matriculationNumber3, 2)))
						{
							System.out.println("Error! Please enter only letters and digits.");
							break;
						}
						int studentIndex3 = students.checkStudent(matriculationNumber3);
						if (studentIndex3 != -1)
						{
							// Check the student is registered for the course
							if (courses.getCourseAl().get(courseIndex5).checkStudent(matriculationNumber3))
							{
								ArrayList<ComponentWeightage> components = courses.getCourseAl().get(courseIndex5)
										.getAllComponentsWeightage();
								for (ComponentWeightage component : components)
								{
									if (component.getName().equals("Exam"))
									{
										int recordIndex = students.getStudentAl().get(studentIndex3)
												.searchRecord(courseCode8);
										{
											MarksEntryInterface.enterMarks(students.getStudentAl().get(studentIndex3)
													.getCoursesRegistered().get(recordIndex));
										}

									}
								}
							} else
								System.out.println("Student not registered for this course!");
						} else
							System.out.println("Student not found in database!");
					} else
						System.out.println("Course not found in database!");
					break;
				case 9:
					System.out.println("Enter the course:");
					String coursecode11 = ConsoleInputInterface.consoleScanner.next();
					int courseIndex6 = courses.checkCourse(coursecode11);
					if (courseIndex6 != -1)
					{
						System.out.println("Show grade percentage for:");
						System.out.println("1 - Overall");
						System.out.println("2 - Exam only");
						System.out.println("3 - Coursework only");
						int choice = ConsoleInputInterface.consoleScanner.nextInt();
						double[] courseStat = new double[11];
						ArrayList<CourseRegistrationRecord> registrations = courses.getCourseAl().get(courseIndex6).getRegistrations();
						switch (choice) 
						{
							case 1:
								for (CourseRegistrationRecord registration:registrations)
								{
									courseStat[registration.getOverallResults().computeGrade().getValue()]++;
								}
								break;
							case 2:
								for (CourseRegistrationRecord registration:registrations)
								{
									int index = 0;
									if (registration.getOverallResults().getComponentResultList().get(index).getName().equals("Exam"))
										courseStat[registration.getOverallResults().getComponentResultList().get(index).computeGrade().getValue()]++;
									index++;
								}
								break;
							case 3:
								for (CourseRegistrationRecord registration:registrations)
								{
									int index = 0;
									if (registration.getOverallResults().getComponentResultList().get(index).getName().equals("Exam"))
										courseStat[registration.getOverallResults().getComponentResultList().get(index).computeGrade().getValue()]++;
									index++;
								}
								break;
							default:
								break;
						}
						for (int i=0; i<11; i++)
						{
							courseStat[i] = courseStat[i]/registrations.size() * 100;
							System.out.printf("%s %.2f %%\n",Grade.valueOf(i),courseStat[i]);
						}
					}
					else
						System.out.println("Course not found in database!");
					break;
				case 10:
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
				default:
					break;
			}

		} while (userChoice != 11);

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
