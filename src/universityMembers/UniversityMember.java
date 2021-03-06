package universityMembers;

/**
 * This class holds information of a member of the university.
 *
 */
public class UniversityMember
{
	private String id; // identification number of this member
	private String fullName; // member's full name
	
	/**
	 * Constructor for university member that initialises identification number and full name.
	 * 
	 * @param id
	 * @param fullName
	 */
	public UniversityMember(String id, String fullName)
	{
		this.id = id;
		this.fullName = fullName;
	}
	
	/**
	 * Getter for identification number.
	 * 
	 * @return id
	 */
	public String getID()
	{
		return id;
	}
	// No Setter for identification number; 
	// Every record is tied to its identification number, permanently
	
	/**
	 * Getter for member's full name.
	 * 
	 * @return fullName
	 */
	public String getfullName()
	{
		return fullName;
	}
	/**
	 * Setter for member's full name, in case there is a change of name.
	 * 
	 * @param fullName
	 */
	public void setfullName(String fullName)
	{
		this.fullName = fullName;
	}
	
	@Override
	/**
	 * Overrided toString that returns identification number and full name of university member.
	 * 
	 */
	public String toString()
	{
		return id + ' ' + fullName;
	}
}
