package system.user;

public class GarageHolder extends User {

	public GarageHolder(String username, String password) {
		super(username, password);
	}

	@Override
	public UserController getController() {
		return new GarageHolderController(this);
	}
}
