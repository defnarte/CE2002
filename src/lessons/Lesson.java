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

	/**
	 * Constructor for lesson (used when loading an existing lesson)
	 * @param lessonID This is the ID of the lesson.
	 * @param lessonType This is the type of the lesson.
	 * @param totalSize This is the total size of the lesson.
	 * @param vacancy This is the vacancy of the lesson.
	 */
	public Lesson(int lessonID, String lessonType, int totalSize, int vacancy)
	{
		this.lessonID = lessonID;
		this.lessonType = lessonType;
		this.totalSize = totalSize;
		this.vacancy = vacancy;
	}
	/**
	 * Constructor for lesson (used when creating a new lesson)
	 * @param lessonID This is the ID of the lesson.
	 * @param lessonType This is the type of the lesson.
	 * @param totalSize This is the total size of the lesson.
	 */
	public Lesson(int lessonID, String lessonType, int totalSize)
	{
		this.lessonID = lessonID;
		this.lessonType = lessonType;
		this.totalSize = totalSize;
		this.vacancy = totalSize;
	}
	/**
	 * Get the ID of the lesson.
	 * @return ID of the lesson
	 */
	public int getLessonID()
	{
		return lessonID;
	}
	/**
	 * Get the type of the lesson.
	 * @return Type of the lesson
	 */
	public String getLessonType()
	{
		return lessonType;
	}
	/**
	 * Get the total size of the lesson.
	 * @return Total size of the lesson.
	 */
	public int getTotalSize()
	{
		return totalSize;
	}
	/**
	 * Get the vacancy of the lesson.
	 * @return Vacancy of the lesson.
	 */
	public int getVacancy()
	{
		return vacancy;
	}
	/**
	 * Enrol a student in the lesson
	 * @return Boolean whether the student was successfully registered.
	 */
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
	
	/**
	 * Overrided toString() which returns the lessonType and lessonID;
	 */
	@Override
	public String toString()
	{
		return lessonType + ' ' + lessonID;
	}
}
