//enum LessonType
//{
//	LECTURE,
//	TUTORIAL,
//	LAB,
//}

/**
 * This entity class holds information regarding an instance of Lesson in an instance of Course
 * 
 * last-updated: 2018-1-09
 * @author Jason
 *
 */
public class Lesson
{
	private String lessonID;
	private String lessonType;
	private int totalSize;
	private int vacancy;

	// Used when loading an existing database
	public Lesson(String lessonID, String lessonType,int totalSize, int vacancy) {
		this.lessonID = lessonID;
		this.lessonType = lessonType;
		this.totalSize = totalSize;
		this.vacancy = vacancy;
	}
	// Used when creating a new lesson
	public Lesson(String lessonID, String lessonType,int totalSize) {
		this.lessonID = lessonID;
		this.lessonType = lessonType;
		this.totalSize = totalSize;
		this.vacancy = totalSize;
	}

	public String getLessonID()
	{
		return lessonID;
	}
	public String getLessonType()
	{
		return lessonType;
	}

//	public LessonType getLessonType()
//	{
//		return lessonType;
//	}
	public int getTotalSize() {
		return totalSize;
	}
	
	public int getVacancy()
	{
		return vacancy;
	}
	
}
