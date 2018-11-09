/**
 * This entity class holds information for an instance of Faculty.
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
	
	public String getid()
	{
		return id;
	}
	public void setid(String id)
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
