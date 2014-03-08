package system.user;

import company.Company;

public class GarageHolder extends User {

	public GarageHolder(String username, String password) {
		super(username, password);
	}

	@Override
	public UserController getController(Company company) {
		return new GarageHolderController(this, company);
	}
}
