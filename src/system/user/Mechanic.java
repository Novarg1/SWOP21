package system.user;

import company.Company;

public class Mechanic extends User {

	public Mechanic(String username, String password) {
		super(username, password);
	}
	

	@Override
	public UserController getController(Company company) {
		return new MechanicController(this, company);
	}
}
