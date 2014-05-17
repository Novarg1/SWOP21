package company.assemblylines;

import company.workstations.AccessoiresPost;
import company.workstations.BodyPost;
import company.workstations.CargoPost;
import company.workstations.CertificationPost;
import company.workstations.DriveTrainPost;
import company.workstations.Workstation;

public class Line3 extends Assemblyline {

	public Line3() {
		super(new Workstation[]{
				new BodyPost(),
				new DriveTrainPost(),
				new CargoPost(),
				new AccessoiresPost(),
				new CertificationPost()
		});
	}
}
