package system.user;

public abstract class User {

	public final String USERNAME;
	public final String PASSWORD;

	public User(String username, String password) {
		this.USERNAME = username;
		this.PASSWORD = password;
	}

	public boolean equals(User other) {
		return this.USERNAME.equals(other.USERNAME);
	}
}
