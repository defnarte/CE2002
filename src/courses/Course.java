package courses;

import java.util.ArrayList;
import java.util.HashSet;

import consoleIO.ConsoleDisplay;
import creation.CreationHandler;
import universityMembers.FacultyMember;
import registration.Registration;
import lessons.Lesson;

/**
 * This class holds information of a course.
 * 
 * @version 1.2
 * @since 2018/11/12
 * @author Jason
 *
 */
public class Course
{
	private String courseCode; // course code
	private String name; // course name

	private FacultyMember coordinator; // course coordinator

	private ArrayList<String> lessonTypes;
	private ArrayList<Lesson> lessons;
	private ArrayList<ComponentWeightage> componentWeightageList;
	private ArrayList<Registration> registrations; // registrations for this course

	/**
	 * Constructor for course that initialises the course's code, name, coordinator
	 * and maximum number of student intakes, as well as the list of registrations
	 * for this course with an empty list.
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param courseCoordinator
	 * @param maxNumOfIntakes
	 */
	public Course(String courseCode, String courseName)
	{
		this.courseCode = courseCode;
		this.name = courseName;
		
		lessons = new ArrayList<Lesson>();
		lessonTypes = new ArrayList<String>();
		registrations = new ArrayList<Registration>();
		componentWeightageList = new ArrayList<ComponentWeightage>();
	}
	public Course(String courseCode, String courseName, FacultyMember courseCoordinator)
	{
		this.courseCode = courseCode;
		this.name = courseName;
		this.coordinator = courseCoordinator;

		lessons = new ArrayList<Lesson>();
		lessonTypes = new ArrayList<String>();
		componentWeightageList = new ArrayList<ComponentWeightage>();
		registrations = new ArrayList<Registration>();
	}

	/**
	 * Getter for course code.
	 * 
	 * @return courseCode
	 */
	public String getCourseCode()
	{
		return courseCode;
	}

	/**
	 * Setter for course code.
	 * 
	 */
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}

	/**
	 * Getter for course name.
	 * 
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Setter for course name.
	 * 
	 */
	public void setName(String courseName)
	{
		this.name = courseName;
	}
	
	/**
	 * Getter for course coordinator.
	 * 
	 * @return coordinator
	 */
	public FacultyMember getCoordinator()
	{
		return coordinator;
	}
	/**
	 * Setter for course coordinator.
	 * 
	 */
	public void setCoordinator(FacultyMember courseCoordinator)
	{
		this.coordinator = courseCoordinator;
	}
	
	public ArrayList<String> getLessonTypes()
	{
		return lessonTypes;
	}
	/**
	 * Setter for lesson types.
	 * @param uniqueLessonTypes
	 */
	public void setLessonTypes(ArrayList<String> uniqueLessonTypes)
	{
		this.lessonTypes = uniqueLessonTypes;
	}

	public void addComponentWeightage(ComponentWeightage newComponent)
	{
		componentWeightageList.add(newComponent);
	}

	public void setAllComponentsWeightage()
	{
		componentWeightageList = new ArrayList<ComponentWeightage>();

		CreationHandler.createCourseComponents(this);
	}

	public ArrayList<ComponentWeightage> getAllComponentsWeightage()
	{
		return componentWeightageList;
	}

	/**
	 * Getter for registrations for this course.
	 * 
	 * @return registrations
	 */
	public ArrayList<Registration> getRegistrationRecords()
	{
		return registrations;
	}

	// TO-DO: add comments
	public void addStudentRegistration(Registration newCourseRegistrationRecord)
	{
		registrations.add(newCourseRegistrationRecord);
	}

	public void dropStudentRegistration(Registration courseRegistrationRecord)
	{
		registrations.remove(courseRegistrationRecord);
	}

	public Lesson getLesson(String lessonID)
	{
		for (Lesson lesson : lessons)
		{
			if (lesson.getLessonID().equals(lessonID))
				return lesson;
		}
		System.out.println("LessonID not found.");
		return null;
	}

	public ArrayList<Lesson> getLessons()
	{
		return lessons;
	}

	public void addLesson(Lesson lesson)
	{
		lessons.add(lesson);
	}
	
	public void printLessonsByType(String lessonType)
	{
		ConsoleDisplay.displayCourseLessonByType(this, lessonType);
	}
	
	public void printLessonsByTypeWithVacancy(String lessonType)
	{
		ConsoleDisplay.displayCourseLessonByTypeWithVacancy(this, lessonType);
	}
	
	public void printStudentsInSpecificLesson(String lessonID)
	{
		ConsoleDisplay.displayStudentsInSpecificLesson(this, lessonID);
	}
	
	public void printAllStudents(String lessonType)
	{
		ConsoleDisplay.displayAllStudentsInCourse(this, lessonType);
	}
	
	public boolean checkStudent(String studentID) 
	{
		for (Registration registeredStudent:registrations) 
		{
			if (registeredStudent.getRegisteredStudent().getID().equals(studentID))
				return true;
		}
		return false;
	}

	public boolean checkLessonType(String lessonType)
	{
		for (String uniqueLessonType:lessonTypes)
		{
			if (lessonType.equals(uniqueLessonType))
				return true;
		}
		return false;
	}
	@Override
	public String toString()
	{
		return "Course[" + courseCode + ' ' + name + ']';
	}
}