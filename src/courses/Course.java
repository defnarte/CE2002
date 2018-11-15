package courses;

import universityMembers.FacultyMember;
import registration.CourseRegistrationRecord;

import java.util.ArrayList;

import control.CreationHandler;
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
	private int maxNumOfIntakes; // maximum number of student intakes for this course
	
	private ArrayList<Lesson> lessons;
	private ArrayList<CourseRegistrationRecord> registrations; // registrations for this course
	private ArrayList<ComponentWeightage> components;
	
	/**
	 * Constructor for course that initialises the course's code, name, coordinator and maximum number of student intakes,
	 * as well as the list of registrations for this course with an empty list.
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param courseCoordinator
	 * @param maxNumOfIntakes
	 */
	public Course(String courseCode, String courseName, int maxNumOfIntakes)
	{
		this.courseCode = courseCode;
		this.name = courseName;
		this.maxNumOfIntakes = maxNumOfIntakes;
		
		lessons = new ArrayList<Lesson>();
		registrations = new ArrayList<CourseRegistrationRecord>();
		components = new ArrayList<ComponentWeightage>();
	}
	public Course(String courseCode, String courseName, FacultyMember courseCoordinator, int maxNumOfIntakes)
	{
		this.courseCode = courseCode;
		this.name = courseName;
		this.coordinator = courseCoordinator;
		this.maxNumOfIntakes = maxNumOfIntakes;
		
		lessons = new ArrayList<Lesson>();
		registrations = new ArrayList<CourseRegistrationRecord>();
		components = new ArrayList<ComponentWeightage>();
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
	
	/**
	 * This method gets the number of vacancy (remaining slots for student intakes) for this course.
	 * Number of vacancy is calculated through maximum number of student intakes minus current number of 
	 * student intakes (given by number of registrations for this course).
	 * 
	 * @return vacancy
	 */
	public int getVacancy()
	{
		return maxNumOfIntakes - registrations.size();
	}
	/**
	 * Setter for maximum number of intakes.
	 * Returns true if maximum number of intakes is set successfully.
	 * Returns false if maximum number of intakes is NOT set, 
	 * as the number of students registered for this course is more than the new maximum.
	 * 
	 */
	public boolean setMaxNumOfIntakes(int maxNumOfIntakes)
	{
		if(maxNumOfIntakes > registrations.size())
		{
			this.maxNumOfIntakes = maxNumOfIntakes;
			return true;
		}
		else
			return false;
	}
	
	public void addComponentWeightage(ComponentWeightage newComponent)
	{
		components.add(newComponent);
	}
	public ArrayList<ComponentWeightage> getComponents() 
	{
		return components;
	}
	public void setAllComponentsWeightage()
	{
		components = new ArrayList<ComponentWeightage>();
		CreationHandler.createCourseComponents(this);
	}
	
	/**
	 * Getter for registrations for this course.
	 * 
	 * @return registrations
	 */
	public ArrayList<CourseRegistrationRecord> getRegistrations()
	{
		return registrations;
	}
	
	// TO-DO: add comments
	public void addRegistration(CourseRegistrationRecord newCourseRegistrationRecord)
	{
		registrations.add(newCourseRegistrationRecord);
	}
	public void dropRegistration(CourseRegistrationRecord courseRegistrationRecord)
	{
		registrations.remove(courseRegistrationRecord);
	}
	public Lesson getLesson(String lessonID) {
		for (Lesson lesson:lessons) {
			if (lesson.getLessonID().equals(lessonID)) 
				return lesson;
		}
		System.out.println("LessonID not found.");
		return null;
	}
	public ArrayList<Lesson> getLessons() {
		return lessons;
	}
	public void addLesson(Lesson lesson) {
		lessons.add(lesson);
	}
	public void printSomeStudents(String lessonID) {
		System.out.println("List of students:");
		for (int i=0; i<registrations.size(); i++) {
			ArrayList<String> studentLessons = registrations.get(i).getLessonList();
			for (int j=0; j < studentLessons.size(); j++) {
				if (studentLessons.get(j).equals(lessonID)) {
					System.out.println(registrations.get(i).getRegisteredStudent().getfullName());
				}
			}
		}
	}
	public void printAllStudents(String lessonType) {
		for (Lesson lesson : lessons) {
			if(lesson.getLessonType().equals(lessonType)) {
				String lessonID = lesson.getLessonID();
				printSomeStudents(lessonID);
			}
		}
	}
	@Override
	public String toString()
	{
		String componentString = "";
		
		for(ComponentWeightage component: components)
		{
			componentString += '\t' + component.toString() + '\n';
		}
		
		return "---" + courseCode + ' ' + name + 
				"---\nCourse Coordinator: " + coordinator + 
				"\nmaximum number of intakes: " + maxNumOfIntakes + 
				"\nComponents: \n" + componentString;
	}
}