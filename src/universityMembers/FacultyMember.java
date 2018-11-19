package universityMembers;

/**
 * This class holds information of a university faculty member.
 *
 */
public class FacultyMember extends UniversityMember
{
	/**
	 * Constructor for faculty member that initialises his/her identification number (based on his/her faculty ID)
	 *  and full name.
	 * 
	 * @param facultyID faculty member ID
	 * @param fullName faculty member full name
	 */
	public FacultyMember(String facultyID, String fullName)
	{
		super(facultyID,fullName);
	}
	
	@Override
	/**
	 * Overrided toString that returns "Faculty Member[id fullName]"
	 * 
	 */
	public String toString()
	{
		return "Faculty Member[" + super.toString() + ']';
	}
}
