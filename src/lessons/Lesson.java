package lessons;
//enum LessonType

//{
//	LECTURE,
//	TUTORIAL,
//	LAB,
//}

/**
 * This entity class holds information regarding an instance of Lesson in an
 * instance of Course
 * 
 * @version 1.1
 * @since 2018/11/09
 * @author Jason
 *
 */
public class Lesson
{
	private int lessonID;
	private String lessonType;
	private int totalSize;
	private int vacancy;

	// Used when loading an existing database
	public Lesson(int lessonID, String lessonType, int totalSize, int vacancy)
	{
		this.lessonID = lessonID;
		this.lessonType = lessonType;
		this.totalSize = totalSize;
		this.vacancy = vacancy;
	}

	// Used when creating a new lesson
	public Lesson(int lessonID, String lessonType, int totalSize)
	{
		this.lessonID = lessonID;
		this.lessonType = lessonType;
		this.totalSize = totalSize;
		this.vacancy = totalSize;
	}

	public int getLessonID()
	{
		return lessonID;
	}

	public String getLessonType()
	{
		return lessonType;
	}

	public int getTotalSize()
	{
		return totalSize;
	}

	public int getVacancy()
	{
		return vacancy;
	}

	public boolean decrementVacancy()
	{
		if (vacancy > 0)
		{
			// System.out.println("Student successfully registered.");
			vacancy--;
			return true;
		} else
		{
			// System.out.println("Lesson is full!");
			return false;
		}
	}

//	public boolean deregisterStudent()
//	{
//		if (vacancy <= 0)
//		{
//			// System.out.println("Lesson has no students!");
//			return false;
//		} else
//		{
//			// System.out.println("Student successfully deregistered.");
//			vacancy++;
//			return true;
//		}
//	}
	
	@Override
	public String toString()
	{
		return lessonType + ' ' + lessonID;
	}
}
