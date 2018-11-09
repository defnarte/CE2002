/**
 * This entity class holds information for a faculty member.
 * A faculty member may be the course coordinator of a particular Course.
 * 
 * last-updated: 2018-11-09
 * @author Jason
 *
 */
public class Faculty
{
	private String id;
	private String name;
	
	public Faculty(String id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public String getID()
	{
		return id;
	}
	public void setID(String id)
	{
		this.id = id;
	}
	
	public String getname()
	{
		return name;
	}
	public void setname(String name)
	{
		this.name = name;
	}
}
