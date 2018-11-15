package universityMembers;

/**
 * This class holds information of a university faculty member.
 * 
 * @version 1.1
 * @since 2018/11/12
 * @author Jason
 *
 */
public class FacultyMember extends UniversityMember
{
	/**
	 * Constructor for faculty member that initialises his/her identification number (based on his/her faculty ID)
	 *  and full name.
	 * 
	 * @param facultyID
	 * @param fullName
	 */
	public FacultyMember(String facultyID, String fullName)
	{
		super(facultyID,fullName);
	}
	
	@Override
	public String toString()
	{
		return "Faculty Member[" + super.toString() + ']';
	}
}
