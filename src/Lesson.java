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
	private int vacancy;

	public Lesson(String lessonID, String lessonType, int vacancy)
	{
		this.lessonID = lessonID;
		this.lessonType = lessonType;
		this.vacancy = vacancy;
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
	
	public int getVacancy()
	{
		return vacancy;
	}
	
	public void addStudent(String matricNum)
	{
		
	}
}
