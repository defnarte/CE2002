import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import courses.Course;
import database.CombinedDB;
import database.CourseDB;
import database.StudentDB;
import grading.ComponentResult;
import lessons.Lesson;
import registration.CourseRegistrationRecord;
import universityMembers.Student;

public class SCRAMEApp
{
	public static void main(String args[]) throws IOException
	{
		String studentFilename = "Students.txt";
		String courseFilename = "Courses.txt";
		
		CombinedDB database = new CombinedDB();
		CourseDB courses = new CourseDB(database.readCourseDB(courseFilename));
		StudentDB students = new StudentDB(database.readStudentDB(studentFilename,courses.getCourseAl()));
				
		System.out.println("Welcome to SCRAME");
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		do
		{
			printMenu();
			
			// TO-DO: place this do-while loop in a method
			do
			{
				System.out.println("Select an option:");
				
				if(sc.hasNextInt())
					choice = sc.nextInt();
			} while(choice < 1 || choice > 11);
			
			switch (choice)
			{
				case 1:
					// Error check
					System.out.println("Enter the name of the student: ");
					String name = sc.next();
					if (!(checkInput(name,1)))
					{
						System.out.println("Error! Please enter only letters and spaces.");
						break;
					}
					// Error check
					System.out.println("Enter the student's ID: ");
					String matriculationNumber = sc.next();
					if (!(checkInput(matriculationNumber,2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					Student student = new Student(matriculationNumber,name);
					students.addStudent(student);
					//students.saveStudents("Students.txt");

					System.out.println("List of all students:");
					students.printStudentList();
					break;
				case 2:
					System.out.println("Enter the name of the course: ");
					String name2 = sc.next();
					if (!(checkInput(name2,1)))
					{
						System.out.println("Error! Please enter only letters and spaces.");
						break;
					}
					System.out.println("Enter the course code: ");
					String courseCode2 = sc.next();
					if (!(checkInput(courseCode2,2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					break;
				case 3:
					System.out.println("Enter the student's ID: ");
					String matriculationNumber2 = sc.next();
					if (!(checkInput(matriculationNumber2,2)))
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
						String courseCode = sc.next();
						if (!(checkInput(courseCode,2)))
						{
							System.out.println("Error! Please enter only letters and digits.");
							break;
						}
						int courseIndex = courses.checkCourse(courseCode);
						if (courseIndex != -1) 
						{
							System.out.printf("Registering %s into %s...\n", matriculationNumber2,courseCode);
							// Code for adding a student into lessons
							ArrayList<Lesson> lessons = courses.getCourseAl().get(courseIndex).getLessons();
							for (Lesson lesson:lessons)
							{
								// Find all unique lesson types
								ArrayList<String> lessonListType = new ArrayList<String>();
								for (int j = 0; j<lessons.size(); j++) 
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
								for (String uniqueLesson:uniqueLessonListType)
								{
									System.out.println("Register for " + uniqueLesson);
									System.out.println("List of indexes:");
									courses.getCourseAl().get(courseIndex).printLessonList(uniqueLesson);
									
									String lessonIndex = sc.next();	
								}
							}
						}
						else
							System.out.println("Course not found in database!");
					}
					else
						System.out.println("Student not found in database!");
					break;
				case 4:
					System.out.println("Enter a course code: ");
					String courseCode = sc.next();
					if (!(checkInput(courseCode,2)))
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
						for (int j = 0; j<lessons.size(); j++) 
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
						String lessonType = sc.next();
						System.out.printf("All indexes for %s:\n",lessonType);
						courses.getCourseAl().get(courseIndex).printLessonList(lessonType);
						System.out.println("Enter an index: ");
						String lessonIndex2 = sc.next();
						int vacancy = courses.getCourseAl().get(courseIndex).getLesson(lessonIndex2).getVacancy();
						int totalSize = courses.getCourseAl().get(courseIndex).getLesson(lessonIndex2).getTotalSize();
						System.out.printf("%s %d/%d\n",lessonIndex2,vacancy,totalSize);
					}
					else
						System.out.println("Course not found in database!");
					break;
				case 5:
					System.out.println("Enter a course code: ");
					String courseCode3 = sc.next();
					if (!(checkInput(courseCode3,2)))
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
						for (int j = 0; j<lessons.size(); j++) 
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
						String lessonType = sc.next();
						System.out.printf("All indexes for %s:\n",lessonType);
						courses.getCourseAl().get(courseIndex2).printLessonList(lessonType);
						System.out.println("Select an option:");
						System.out.println("1 - Print student list by index");
						System.out.println("2 - Print all students");
						int choice2 = sc.nextInt();
						switch (choice2) 
						{
							case 1:
								System.out.println("Enter an index: ");
								String lessonIndex = sc.next();
								if (!(checkInput(lessonIndex,2))) 
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
					}
					else
						System.out.println("Course not found in database!");
				case 6:
					System.out.println("Enter a course code: ");
					String courseCode4 = sc.next();
					if (!(checkInput(courseCode4,2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int courseIndex3 = courses.checkCourse(courseCode4);
					if (courseIndex3 != -1) 
					{
						
					}
					else
						System.out.println("Course not found in database!");
					break;
				case 7:
					System.out.println("Enter a course code: ");
					String courseCode5 = sc.next();
					if (!(checkInput(courseCode5,2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int courseIndex4 = courses.checkCourse(courseCode5);
					if (courseIndex4 != -1) 
					{
						
					}
					else
						System.out.println("Course not found in database!");
					break;
				case 8:
					System.out.println("Enter a course code: ");
					String courseCode6 = sc.next();
					if (!(checkInput(courseCode6,2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int courseIndex5 = courses.checkCourse(courseCode6);
					if (courseIndex5 != -1) 
					{
						
					}
					break;
				case 9:
					break;
				case 10:
					System.out.println("Enter the student's ID: ");
					String studentID = sc.next();
					if (!(checkInput(studentID,2)))
					{
						System.out.println("Error! Please enter only letters and digits.");
						break;
					}
					int studentIndex2 = students.checkStudent(studentID);
					if (studentIndex2 != -1)
					{
							System.out.println("Printing Student Transcript for " + students.getStudentAl().get(studentIndex2).getID() + ", " + students.getStudentAl().get(studentIndex2).getfullName() + ":");
							ArrayList<CourseRegistrationRecord> coursesRegistered = students.getStudentAl().get(studentIndex2).getCoursesRegistered();
							for (CourseRegistrationRecord courseRegistered:coursesRegistered) 
							{
								System.out.println(courseRegistered.getRegisteredCourse().getCourseCode() + " " 
										+ courseRegistered.getRegisteredCourse().getName() + " "
										+ courseRegistered.getOverallResults().getOverallMarks() + " "
										+ courseRegistered.getOverallResults().computeGrade());
								ArrayList<ComponentResult> componentResultList = courseRegistered.getOverallResults().getComponentResultList();
								for (ComponentResult componentResult:componentResultList) {
									System.out.println("       " + componentResult.getName() + " " + componentResult.getMarks());
								}
							}
					}
					break;
				default:
					break;
			}
			

		} while (choice != 11);
		
		sc.close();
	}
	
	public static void printMenu()
	{
		System.out.println(	"Select an option:\n" +
							"1.  Add a student\n" +
							"2.  Add a course\n" + 
							"3.  Register student for a course (this include registering for Tutorial/Lab classes)\n" + 
							"4.  Check available slot in a class (vacancy in a class)\n" + 
							"5.  Print student list by lecture, tutorial or laboratory session for a course.\n" + 
							"6.  Enter course assessment components weightage\n" + 
							"7.  Enter coursework mark � inclusive of its components.\n" + 
							"8.  Enter exam mark\n" + 
							"9.  Print course statistics\n" + 
							"10. Print student transcript.\n" +
							"11. Quit");
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
