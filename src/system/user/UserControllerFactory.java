package system.user;

public final class UserControllerFactory {
	public static UserController getUserController(UserRole r) throws Exception
	{
		switch(r)
		{
		case USERROLE_MANAGER:return new ManagerController();
		case USERROLE_GARAGEHOLDER:return new GarageHolderController();
		case USERROLE_MECHANIC:return new MechanicController();
		default: throw new Exception();
		}
	}
}
