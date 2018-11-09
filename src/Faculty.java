/**
 * This entity class holds information for an instance of Faculty.
 * 
 * @author Jason
 *
 */
public class Faculty
{
	private String facultyID;
	private String facultyName;
	
	public Faculty(String facultyID, String facultyName)
	{
		this.facultyID = facultyID;
		this.facultyName = facultyName;
	}
	
	public String getFacultyID()
	{
		return facultyID;
	}
	public void setFacultyID(String facultyID)
	{
		this.facultyID = facultyID;
	}
	
	public String getFacultyName()
	{
		return facultyName;
	}
	public void setFacultyName(String facultyName)
	{
		this.facultyName = facultyName;
	}
}
