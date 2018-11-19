package courses;

import java.util.ArrayList;

import consoleIO.ConsoleDisplay;
import creation.CreationHandler;
import universityMembers.FacultyMember;
import registration.Registration;
import lessons.Lesson;

/**
 * This entity class holds information of a course.
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
	 * Constructor for course that initialises the course's code and name based on the arguments passed in,
	 * and initialises lessons, lessonTypes, registrations and componentWeightageList to new ArrayLists.
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
	/**
	 * Constructor for course that initialises the course's code, name and coordinator based on the arguments 
	 * passed in, and initialises lessons, lessonTypes, registrations and componentWeightageList to new ArrayLists.
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param courseCoordinator
	 */
	public Course(String courseCode, String courseName, FacultyMember courseCoordinator)
	{
		this(courseCode,courseName);
		this.coordinator = courseCoordinator;
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

	/**
	 * This method adds a new ComponentWeightage passed in as argument to componentWeightageList.
	 * 
	 * @param newComponent
	 */
	public void addComponentWeightage(ComponentWeightage newComponent)
	{
		componentWeightageList.add(newComponent);
	}

	/**
	 * This method sets componentWeightageList to an empty ArrayList.
	 */
	public void clearComponentsWeightage()
	{
		componentWeightageList = new ArrayList<ComponentWeightage>();
	}

	/**
	 * Getter for componentWeightageList.
	 * 
	 * @return ArrayList of ComponentWeightage
	 */
	public ArrayList<ComponentWeightage> getAllComponentsWeightage()
	{
		return componentWeightageList;
	}

	/**
	 * Getter for registrations for this course.
	 * 
	 * @return registrations
	 */
	public ArrayList<Registration> getRegistrations()
	{
		return registrations;
	}

	/**
	 * This method adds a registration passed in as argument to registrations (ArrayList of Registration).
	 * 
	 * @param newRegistration
	 */
	public void addStudentRegistration(Registration newRegistration)
	{
		registrations.add(newRegistration);
	}
	/**
	 * This method removes the target registration from registrations (ArrayList of Registration).
	 * 
	 * @param Registration
	 */
	public void dropStudentRegistration(Registration Registration)
	{
		registrations.remove(Registration);
	}

	/**
	 * This method returns a Lesson based on the target Lesson ID passed in as argument.
	 * Returns null if none of the Lesson's ID matches the target Lesson ID.
	 * 
	 * @param lessonID
	 * @return Lesson with corresponding ID, or null if Lesson cannot be found
	 */
	public Lesson getLesson(int lessonID)
	{
		for (Lesson lesson : lessons)
		{
			if (lesson.getLessonID() == lessonID)
				return lesson;
		}

		return null;
	}

	/**
	 * Getter for Lessons (ArrayList of Lesson)
	 * 
	 * @return lessons
	 */
	public ArrayList<Lesson> getLessons()
	{
		return lessons;
	}

	/**
	 * This method adds a Lesson passed in as argument to lessons (ArrayList of Lesson).
	 * 
	 * @param lesson
	 */
	public void addLesson(Lesson lesson)
	{
		lessons.add(lesson);
	}
	
	/**
	 * This method makes use of the ConsoleDisplay class to display this Course's Lessons on the console,
	 * grouped by Lesson type.
	 * 
	 * @param lessonType
	 */
	public void printLessonsByType(String lessonType)
	{
		ConsoleDisplay.displayCourseLessonByType(this, lessonType);
	}
	/**
	 * This method makes use of the ConsoleDisplay class to display this Course's Lessons, along with
	 * their respective vacancies, on the console, grouped by Lesson type.
	 * 
	 * @param lessonType
	 */
	public void printLessonsByTypeWithVacancy(String lessonType)
	{
		ConsoleDisplay.displayCourseLessonByTypeWithVacancy(this, lessonType);
	}
	
	/**
	 * This method makes use of the ConsoleDisplay class to display Students registered for a Lesson
	 * of a particular Lesson ID, that is passed in as argument.
	 * 
	 * @param lessonID
	 */
	public void printStudentsInSpecificLesson(int lessonID)
	{
		ConsoleDisplay.displayStudentsInSpecificLesson(this, lessonID);
	}
	
	/**
	 * This method makes use of the ConsoleDisplay class to display all Students registered for this
	 * Course, grouped by the Lesson type passed in as argument.
	 * 
	 * @param lessonType
	 */
	public void printAllStudents(String lessonType)
	{
		ConsoleDisplay.displayAllStudentsInCourse(this, lessonType);
	}
	
	/**
	 * This method checks if a student is registered for this Course, by checking if the the Student ID
	 * passed in matches any of the Student ID's registered for this Course.
	 * 
	 * @param studentID
	 * @return true if Student with target ID is registered for this Course, and false otherwise
	 */
	public boolean checkStudentRegistered(String studentID) 
	{
		for (Registration registeredStudent:registrations) 
		{
			if (registeredStudent.getRegisteredStudent().getID().equals(studentID))
				return true;
		}
		return false;
	}

	/**
	 * This method checks if this Course has a particular Lesson type, passed in
	 * as parameter (as A String)
	 * 
	 * @param lessonType
	 * @return true if Lesson type matches a Lesson type in Course, and false otherwise
	 */
	public boolean checkLessonTypeExists(String lessonType)
	{
		for (String uniqueLessonType:lessonTypes)
		{
			if (lessonType.equals(uniqueLessonType))
				return true;
		}
		return false;
	}
	
	@Override
	/**
	 * Overrided toString method that returns Course code and name.
	 * 
	 * @return Course code and name
	 */
	public String toString()
	{
		return "Course[" + courseCode + ' ' + name + ']';
	}
}