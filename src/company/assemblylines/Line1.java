package company.assemblylines;

import vehicle.order.ModelC;
import company.workstations.AccessoiresPost;
import company.workstations.BodyPost;
import company.workstations.DriveTrainPost;
import company.workstations.Workstation;

public class Line1 extends Assemblyline {

	public Line1() {
		super(new Workstation[] {
				new BodyPost(), 
				new DriveTrainPost(),
				new AccessoiresPost() 
		});
		super.ignore(ModelC.class);
	}
}
