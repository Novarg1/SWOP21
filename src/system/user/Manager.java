package system.user;

public class Manager extends User {

	public Manager(String username, String password) {
		super(username, password);
	}
	
	
@Override
	public UserController getController() {
		return new ManagerController(this);
	}

}
