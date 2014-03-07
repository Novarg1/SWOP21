package system.user;

public class Mechanic extends User {

	public Mechanic(String username, String password) {
		super(username, password);
	}
	

	@Override
	public UserController getController() {
		return new MechanicController(this);
	}
}
