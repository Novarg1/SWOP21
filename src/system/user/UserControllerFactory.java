package system.user;
/* Factory Pattern
 * 
 * returns a fully instantiated usercontroller
 */
public final class UserControllerFactory {
	public static UserController getUserController(String userName,UserRole r) throws Exception
	{
		switch(r)
		{
		/*case USERROLE_MANAGER:return new ManagerController(userName);
		case USERROLE_GARAGEHOLDER:return new GarageHolderController(userName);
		case USERROLE_MECHANIC:return new MechanicController(userName);*/
		default: throw new Exception();
		}
	}
}
