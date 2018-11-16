package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import courses.*;
import grading.ComponentResult;
import grading.OverallResults;
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
public class CombinedDB
{
	private ArrayList<Student> students;
	private ArrayList<Course> courses;
	public static final String SEPARATOR = "|";

	public CombinedDB()
	{
		this.students = new ArrayList<Student>();
		this.courses = new ArrayList<Course>();
	}

	public void printStudentList()
	{
		for (Student student : students)
		{
			System.out.println(student.getfullName() + student.getID());
		}
	}

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

	public ArrayList<Student> readStudentDB(String studentFilename, ArrayList<Course> courses) throws IOException
	{
		ArrayList<String> studentStringArray = read(studentFilename);

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
							courses.get(j).getLesson(lesson).enrolStudent();
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
						/**
						 * THis should not done in the DB class
						 */
						OverallResults results = new OverallResults();
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

	public ArrayList<Course> readCourseDB(String courseFilename) throws IOException
	{
		ArrayList<String> courseStringArray = read(courseFilename);

		for (int i = 0; i < courseStringArray.size(); i++)
		{
			String st = (String) courseStringArray.get(i);
			StringTokenizer courseStar = new StringTokenizer(st, SEPARATOR);

			String courseCode = courseStar.nextToken().trim();
			String courseName = courseStar.nextToken().trim();
			int maxNumOfIntakes = Integer.parseInt(courseStar.nextToken().trim());
			
			FacultyMember dummy = new FacultyMember("S1234567A","dummy");
			Course course = new Course(courseCode, courseName, dummy, maxNumOfIntakes);

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

			ArrayList<ArrayList<String>> lessonList = stringsplit(courseStar.nextToken().trim(), "_");
			for (int k = 0; k < lessonList.size(); k++)
			{
				ArrayList<String> lessonString = lessonList.get(k);
				String lessonType = lessonString.get(0);
				String lessonID = lessonString.get(1);
				int totalSize = Integer.parseInt(lessonString.get(2));
				// int vacancy = Integer.parseInt(lessonString.get(3));
				Lesson lesson = new Lesson(lessonID, lessonType, totalSize);
				course.addLesson(lesson);
			}
			courses.add(course);
		}
		return courses;
	}

	public static ArrayList<ArrayList<String>> stringsplit(String st, String SEP)
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
