package system.userinterface;

public class UserInterfaceDemo extends UserInterfaceUseCaseTester {
	public UserInterfaceDemo()
	{
		super();
		// login in user 2 - the garage holder and order a car
		this.addInput("user2");
		this.addInput(" ");
			
		// tell the system you want to order a car
		this.addInput("1");
		this.addInput("1");
			
		// specifiy the car
		this.addInput("1");
		this.addInput("1");
		this.addInput("2");
		this.addInput("1");
		this.addInput("2");
		this.addInput("2");
		this.addInput("yes");
			
		// log in as the manager - user 1
		this.addInput("user1");
		this.addInput(" ");
				
		// tell the system you want to advance the al
		this.addInput("1");
				
		// how much time has passed since the last...
		this.addInput("60");
				
		// go a head and advance the al
		this.addInput("yes");
			
		// login in user 2
		this.addInput("user2");
		this.addInput(" ");
				
		// login in user 3
		this.addInput("user3");
		this.addInput(" ");		
	}
}
