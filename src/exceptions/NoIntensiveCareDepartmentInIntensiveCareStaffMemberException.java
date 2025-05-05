package exceptions;

public class NoIntensiveCareDepartmentInIntensiveCareStaffMemberException extends Exception {
	public NoIntensiveCareDepartmentInIntensiveCareStaffMemberException()
	{
		super ("There is no Intensive Care Department on this Staff Member's List!");
	}

}
