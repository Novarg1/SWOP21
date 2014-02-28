package session;

public enum UserRole {
	USERROLE_MANAGER,
	USERROLE_GARAGEHOLDER,
	USERROLE_MECHANIC;
	
	@Override
	public String toString() {
		switch(this) {
		case USERROLE_MANAGER: return "manager";
		case USERROLE_GARAGEHOLDER: return "garage holder";
		case USERROLE_MECHANIC: return "mechanic";
		default: throw new IllegalArgumentException();
		}
	}

}
