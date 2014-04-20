package user;

public class Manager extends User 
{

	public Manager(String username) {
		super(username);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return "Manager";
	}

}
