package company.assemblylines;

import company.workstations.AccessoriesPost;
import company.workstations.BodyPost;
import company.workstations.DriveTrainPost;
import company.workstations.Workstation;

public class Line2 extends Assemblyline {

	public Line2(int day) {
		super(new Workstation[] {
				new BodyPost(), 
				new DriveTrainPost(),
				new AccessoriesPost() 
		}, day);
	}
}
