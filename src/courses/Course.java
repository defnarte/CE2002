package courses;

import universityMembers.FacultyMember;
import registration.CourseRegistrationRecord;

import java.util.ArrayList;

import consoleIO.CreationInterface;

/**
 * This class holds information of a course.
 * 
 * @version 1.0
 * @since 2018/11/10
 * @author Jason
 *
 */
public class Course
{
	private String courseCode; // course code
	private String name; // course name
	
	private FacultyMember coordinator; // course coordinator
	private int maxNumOfIntakes; // maximum number of student intakes for this course
	
	private ArrayList<CourseRegistrationRecord> registrations; // registrations for this course
	private ArrayList<ComponentWeightage> componentWeightageList;
	
	/**
	 * Constructor for course that initialises the course's code, name, coordinator and maximum number of student intakes,
	 * as well as the list of registrations for this course with an empty list.
	 * 
	 * @param courseCode
	 * @param courseName
	 * @param courseCoordinator
	 * @param maxNumOfIntakes
	 */
	public Course(String courseCode, String courseName, FacultyMember courseCoordinator, int maxNumOfIntakes)
	{
		this.courseCode = courseCode;
		this.name = courseName;
		this.coordinator = courseCoordinator;
		this.maxNumOfIntakes = maxNumOfIntakes;
		
		registrations = new ArrayList<CourseRegistrationRecord>();
		componentWeightageList = new ArrayList<ComponentWeightage>();
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
	
	public void createComponents()
	{
		this.componentWeightageList = new ArrayList<ComponentWeightage>();
		CreationInterface.createCourseComponents(this);
	}
	
	public void addComponent(ComponentWeightage newComponent)
	{
		componentWeightageList.add(newComponent);
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
	
	@Override
	public String toString()
	{
		String componentString = "";
		
		for(ComponentWeightage component: componentWeightageList)
		{
			componentString += '\t' + component.toString() + '\n';
		}
		
		return "---" + courseCode + ' ' + name + 
				"---\nCourse Coordinator: " + coordinator + 
				"\nmaximum number of intakes: " + maxNumOfIntakes + 
				"\nComponents: \n" + componentString;
	}
}