package system.user;

import company.Company;

public class Manager extends User {

	public Manager(String username, String password) {
		super(username, password);
	}
	
	
@Override
	public UserController getController(Company company) {
		return new ManagerController(this, company);
	}

}
