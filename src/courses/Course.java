package courses;

import java.util.ArrayList;
import java.util.HashSet;

import creation.CreationHandler;
import universityMembers.FacultyMember;
import registration.CourseRegistrationRecord;
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
	private ArrayList<CourseRegistrationRecord> registrations; // registrations for this course

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
		registrations = new ArrayList<CourseRegistrationRecord>();
		componentWeightageList = new ArrayList<ComponentWeightage>();
	}
	public Course(String courseCode, String courseName, FacultyMember courseCoordinator)
	{
		this.courseCode = courseCode;
		this.name = courseName;
		this.coordinator = courseCoordinator;

		lessons = new ArrayList<Lesson>();
		componentWeightageList = new ArrayList<ComponentWeightage>();
		registrations = new ArrayList<CourseRegistrationRecord>();
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
	 * Setter for lesson types.
	 * @param uniqueLessonTypes
	 */
	public void setLessonTypes(ArrayList<String> uniqueLessonTypes)
	{
		this.lessonTypes = uniqueLessonTypes;
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
	
	public ArrayList<String> getLessonTypes()
	{
		return lessonTypes;
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
	
	public void printLessonList(String lessonType,boolean full)
	{
		for (Lesson lesson:lessons)
		{
			if (lesson.getLessonType().equals(lessonType) )
			{
				if (full == true)
					System.out.println(lesson.getLessonID() + " " + lesson.getVacancy() + "/" + lesson.getTotalSize());
				else
					System.out.println(lesson.getLessonID());
			}
		}
	}
	// TO-DO: move this method to a display class
	public void printSomeStudents(String lessonID)
	{
		System.out.println("List of students:");
		for (int i = 0; i < registrations.size(); i++)
		{
			ArrayList<String> studentLessons = registrations.get(i).getLessonList();
			for (int j = 0; j < studentLessons.size(); j++)
			{
				if (studentLessons.get(j).equals(lessonID))
				{
					System.out.println(registrations.get(i).getRegisteredStudent().getfullName());
				}
			}
		}
	}
	
	// TO-DO: move this method to a display class
	public void printAllStudents(String lessonType)
	{
		for (Lesson lesson : lessons)
		{
			if (lesson.getLessonType().equals(lessonType))
			{
				String lessonID = lesson.getLessonID();
				printSomeStudents(lessonID);
			}
		}
	}
	
	public boolean checkStudent(String studentID) 
	{
		for (CourseRegistrationRecord registeredStudent:registrations) 
		{
			if (registeredStudent.getRegisteredStudent().getID().equals(studentID))
				return true;
		}
		return false;
	}

	@Override
	public String toString()
	{
		String componentString = "";

		for (ComponentWeightage component : componentWeightageList)
		{
			componentString += component.toString();
		}

		return "---" + courseCode + ' ' + name + "---\nCourse Coordinator: " + coordinator
				 + "\nComponents: \n" + componentString;
	}
}