package creation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import consoleIO.ConsoleIOHandler;
import consoleIO.ConsoleInputInterface;
import courses.AggregateComponentWeightage;
import courses.ComponentWeightage;
import courses.Course;
import database.CourseDB;
import database.StudentDB;
import grading.Markable;
import lessons.Lesson;
import registration.Registration;
import universityMembers.*;

/**
 * This class handles the creation of other Classes.
 *
 */
public class CreationHandler
{
	public static Student createStudent(StudentDB studentDB)
	{
		Student newStudent = CreationInterface.setStudentMetadata(studentDB);
		System.out.println("\nCreated student:\n" + newStudent.toString() + '\n');

		return newStudent;
	}

	public static Course createCourse(FacultyMember courseCoordinator, CourseDB courseDB)
	{
		Course newCourse = CreationInterface.setCourseMetadata(courseDB);
		newCourse.setCoordinator(courseCoordinator);

		createLessons(newCourse,courseDB);

		System.out.println("\nCreated course:\n" + newCourse.toString());

		return newCourse;
	}

	public static void createCourseComponents(Course course)
	{
		String numOfComponentsPrompt = "Enter number of components for " + course.getCourseCode() + ": ";
		int numOfComponents = ConsoleInputInterface.getUserPositiveIntInput(numOfComponentsPrompt);

		int componentsTotalWeightage = Markable.MAX_MARKS;

		for (int componentIndex = 1; componentIndex <= numOfComponents; ++componentIndex)
		{
			ComponentWeightage newComponentWeightage = CreationInterface.setComponentMetadata(componentIndex,
					componentsTotalWeightage);

			componentsTotalWeightage -= newComponentWeightage.getWeightage();

			if (newComponentWeightage instanceof AggregateComponentWeightage)
			{
				AggregateComponentWeightage aggregateComponentWeightage = (AggregateComponentWeightage) newComponentWeightage;

				String numOfSubcomponentsPrompt = "Enter number of subcomponents for " + newComponentWeightage.getName()
						+ ": ";
				int numOfSubcomponents = ConsoleInputInterface.getUserPositiveIntInput(numOfSubcomponentsPrompt);

				int subcomponentsTotalWeightage = Markable.MAX_MARKS;

				for (int subComponentIndex = 1; subComponentIndex <= numOfSubcomponents; ++subComponentIndex)
				{
					ComponentWeightage newSubcomponentWeightage = CreationInterface.setSubcomponentMetadata(
							aggregateComponentWeightage, subComponentIndex, subcomponentsTotalWeightage);

					subcomponentsTotalWeightage -= newSubcomponentWeightage.getWeightage();

					aggregateComponentWeightage.addSubcomponentWeightage(newSubcomponentWeightage);

					if (subcomponentsTotalWeightage == 0)
					{
						// System.out.println(); // just printing newline
						break;
					}

				}

			}

			course.addComponentWeightage(newComponentWeightage);

			if (componentsTotalWeightage == 0)
			{
				// System.out.println(); // just printing newline
				break;
			}
		}

	}

	public static void createLessons(Course course, CourseDB courseDB)
	{
		ArrayList<Lesson> lessons = new ArrayList<Lesson>();
		HashSet<String> lessonTypeTypes = new HashSet<String>();

		boolean userChoice;
		do
		{
			String lessonType = CreationInterface.createLessonTypeForCourse(course, lessonTypeTypes);

			String numOfLessonsPrompt = "Enter the number of " + lessonType + "s to add:";
			int numOfLessons = ConsoleInputInterface.getUserPositiveIntInput(numOfLessonsPrompt);

			for (int lessonID = 1; lessonID <= numOfLessons; ++lessonID)
			{
				Lesson lesson = CreationInterface.createLessonForCourse(courseDB, lessonType, lessonID);
				lessons.add(lesson);
			}

			userChoice = ConsoleInputInterface.getUserBooleanInput("Do you want to add another lesson (Y/N)? ");

		} while (userChoice);

		for (Lesson lesson : lessons)
		{
			course.addLesson(lesson);
		}

		Iterator<String> it = lessonTypeTypes.iterator();
		ArrayList<String> lessonTypeTypeList = new ArrayList<String>();
		while (it.hasNext())
		{
			lessonTypeTypeList.add((String) it.next().toString());
		}

		course.setLessonTypes(lessonTypeTypeList);

	}

	public static void createRegistration(Student student, CourseDB courseDB)
	{
		Course course = ConsoleIOHandler.getCourseFromDB(courseDB);

		if (course.checkStudent(student.getID()))
		{
			System.out.println("Student already registered for " + course);
			return;
		}

		boolean[] isLessonTypeFullArray = new boolean[course.getLessonTypes().size()];
		for (int i = 0; i < isLessonTypeFullArray.length; ++i)
		{
			isLessonTypeFullArray[i] = true;
		}

		int lessonTypeFullArrayIndex = 0;
		
		for(String lessonType: course.getLessonTypes())
		{
			for(Lesson lesson: course.getLessons())
			{
				if(lesson.getLessonType().equals(lessonType) && lesson.getVacancy() > 0)
				{
					isLessonTypeFullArray[lessonTypeFullArrayIndex] = false;
					break;
				}
			}
			
			++lessonTypeFullArrayIndex;
		}
		for (boolean isLessonTypeFull : isLessonTypeFullArray)
		{
			// If any lessonType is full, the student is unable to register for the course.
			if (isLessonTypeFull)
			{
				System.out.println("Course is full.");
				return;
			}
		}

		ArrayList<Integer> registeredLessonArrayList = CreationInterface.createRegisteredLessonArrayList(course);
		
		Registration newRegistration = new Registration(student, course, registeredLessonArrayList);
		
		student.addCourseRegistration(newRegistration);
		course.addStudentRegistration(newRegistration);
		
		System.out.println(student + " is registered for " + course);
	}
}
