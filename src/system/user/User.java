package system.user;

import company.Company;

public abstract class User {

	public final String USERNAME;
	public final String PASSWORD;

	public User(String username, String password) {
		this.USERNAME = username;
		this.PASSWORD = password;
	}

	public abstract UserController getController(Company company);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((PASSWORD == null) ? 0 : PASSWORD.hashCode());
		result = prime * result
				+ ((USERNAME == null) ? 0 : USERNAME.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (PASSWORD == null) {
			if (other.PASSWORD != null)
				return false;
		} else if (!PASSWORD.equals(other.PASSWORD))
			return false;
		if (USERNAME == null) {
			if (other.USERNAME != null)
				return false;
		} else if (!USERNAME.equals(other.USERNAME))
			return false;
		return true;
	}
}
