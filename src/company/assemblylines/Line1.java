package company.assemblylines;

import vehicle.order.ModelC;
import company.workstations.AccessoiresPost;
import company.workstations.BodyPost;
import company.workstations.DriveTrainPost;
import company.workstations.Workstation;

public class Line1 extends Assemblyline {

	public Line1(int day) {
		super(new Workstation[] {
				new BodyPost(), 
				new DriveTrainPost(),
				new AccessoiresPost() 
		}, day);
		super.ignore(ModelC.class);
	}
}
