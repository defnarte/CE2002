package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

import consoleIO.ConsoleInputInterface;
import courses.*;
import grading.ComponentResult;
import grading.OverallResult;
import lessons.Lesson;
import registration.CourseRegistrationRecord;
import universityMembers.FacultyMember;
import universityMembers.Student;

/**
 * This class is an entity, control and boundary class...
 * 
 * @version 1.0
 * @since 2018/11/12
 * @author Isaac
 *
 */
public class DatabaseIO
{
	public static final String SEPARATOR = "|";

	public static ArrayList<String> read(String filename) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new FileInputStream(filename));
		ArrayList<String> data = new ArrayList<String>();
		try
		{
			while (sc.hasNextLine())
			{
				data.add(sc.nextLine());
			}
		} finally
		{
			sc.close();
		}
		return data;
	}

	public static ArrayList<Student> readStudentDB(String studentFilename, ArrayList<Course> courses) throws IOException
	{
		ArrayList<String> studentStringArray = read(studentFilename);
		ArrayList<Student> students = new ArrayList<Student>();
		for (int i = 0; i < studentStringArray.size(); i++)
		{
			String st = (String) studentStringArray.get(i);
			StringTokenizer studentStar = new StringTokenizer(st, SEPARATOR);

			String name = studentStar.nextToken().trim();
			String matriculationNumber = studentStar.nextToken().trim();
			Student student = new Student(matriculationNumber, name);

			ArrayList<String> coursesEnrolled = new ArrayList<String>(
			Arrays.asList(studentStar.nextToken().trim().split("\\s*,\\s*")));
			ArrayList<ArrayList<String>> lessonsRegistered = stringsplit(studentStar.nextToken().trim(), "_");
			ArrayList<ArrayList<String>> marksObtained = stringsplit(studentStar.nextToken().trim(), "_");

			// For each of the courses for a particular student in the student text file
			for (int k = 0; k < coursesEnrolled.size(); k++)
			{	
				// Check each of the courses in the courseDB
				for (int j = 0; j < courses.size(); j++)
				{
					// If a course code in the courseDB matches the course code in the student text
					// file, create a CourseRegistrationRecord object.
					if (courses.get(j).getCourseCode().equals(coursesEnrolled.get(k)))
					{
						// Include the lessons the student registered for
						ArrayList<String> lessonsRegisteredCourse = lessonsRegistered.get(k);
						CourseRegistrationRecord courseRecord = new CourseRegistrationRecord(student, courses.get(j),
								lessonsRegisteredCourse);
						// Change the vacancy of the lessons enrolled accordingly
						for (String lesson : lessonsRegisteredCourse)
						{
							PrintStream originalStream = System.out;
							PrintStream dummyStream = new PrintStream(new OutputStream(){
							    public void write(int b) {
							    }
							});
							System.setOut(dummyStream);
							courses.get(j).getLesson(lesson).enrolStudent();
							System.setOut(originalStream);
							
						}
						// Additionally, add the marks scored by the student for this course
						ArrayList<String> marksObtainedCourse = marksObtained.get(k);
						ArrayList<ComponentResult> componentResultList = new ArrayList<ComponentResult>();
						ArrayList<ComponentWeightage> components = courses.get(j).getAllComponentsWeightage();
						for (int l = 0; l < marksObtainedCourse.size(); l++)
						{
							ComponentWeightage weightage = components.get(l);
							int marks = Integer.parseInt(marksObtainedCourse.get(l));
							ComponentResult compResult = new ComponentResult(weightage, marks);
							componentResultList.add(compResult);
						}
						OverallResult results = new OverallResult();
						results.setOverallResults(componentResultList);
						courseRecord.setOverallResults(results);

						student.addCourse(courseRecord);
						courses.get(j).addRegistration(courseRecord);
					}

				}
			}
			students.add(student);
		}
		return students;
	}

	public static ArrayList<Course> readCourseDB(String courseFilename, FacultyMemberDB facultyDB) throws IOException
	{
		ArrayList<String> courseStringArray = read(courseFilename);
		ArrayList<Course> courses = new ArrayList<Course>();
		for (int i = 0; i < courseStringArray.size(); i++)
		{
			String st = (String) courseStringArray.get(i);
			StringTokenizer courseStar = new StringTokenizer(st, SEPARATOR);

			String courseCode = courseStar.nextToken().trim();
			String courseName = courseStar.nextToken().trim();
			String studentID = courseStar.nextToken().trim();
			FacultyMember studentber = facultyDB.getFacultyMember(studentID);
			Course course = new Course(courseCode, courseName, studentber);

			ArrayList<String> weightageString = new ArrayList<String>(
					Arrays.asList(courseStar.nextToken().trim().split("\\s*,\\s*")));
			for (int j = 0; j < weightageString.size(); j++)
			{
				String componentName = weightageString.get(j);
				int componentWeightage = Integer.parseInt(weightageString.get(j + 1));
				ComponentWeightage component = new ComponentWeightage(componentName, componentWeightage);
				course.addComponentWeightage(component);
				j++;
			}

			HashSet<String> uniqueLessonType = new HashSet<String>();
			ArrayList<ArrayList<String>> lessonList = stringsplit(courseStar.nextToken().trim(), "_");
			for (int k = 0; k < lessonList.size(); k++)
			{
				ArrayList<String> lessonString = lessonList.get(k);
				String lessonType = lessonString.get(0);
				uniqueLessonType.add(lessonType);
				String lessonID = lessonString.get(1);
				int totalSize = Integer.parseInt(lessonString.get(2));
				Lesson lesson = new Lesson(lessonID, lessonType, totalSize);
				course.addLesson(lesson);
			}
			Iterator<String> it = uniqueLessonType.iterator();
			ArrayList<String> uniqueLessonTypeList = new ArrayList<String>();
			while (it.hasNext())
			{
				uniqueLessonTypeList.add((String) it.next().toString());
			}
			course.setLessonTypes(uniqueLessonTypeList);
			courses.add(course);
		}
		return courses;
	}
	
	public static ArrayList<FacultyMember> readFacultyDB(String facultyFilename) throws FileNotFoundException 
	{
		ArrayList<String> facultyList = read(facultyFilename);
		ArrayList<FacultyMember> faculty = new ArrayList<FacultyMember>();
		for (int i=0; i< facultyList.size(); i++)
		{
			String st = (String) facultyList.get(i);
			StringTokenizer facultyStar = new StringTokenizer(st, SEPARATOR);
			String name = facultyStar.nextToken().trim();
			String id = facultyStar.nextToken().trim();
			FacultyMember facultymem = new FacultyMember(id,name);
			faculty.add(facultymem);
		}
		return faculty;
	}

	private static ArrayList<ArrayList<String>> stringsplit(String st, String SEP)
	{
		StringTokenizer star = new StringTokenizer(st, SEP);
		ArrayList<ArrayList<String>> stringList = new ArrayList<ArrayList<String>>();
		int n = star.countTokens();
		for (int j = 0; j < n; j++)
		{
			ArrayList<String> arrlist = new ArrayList<String>(
					Arrays.asList(star.nextToken().trim().split("\\s*,\\s*")));
			stringList.add(arrlist);
		}
		return stringList;
	}
}
