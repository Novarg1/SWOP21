package company.assemblylines;

import company.workstations.AccessoriesPost;
import company.workstations.BodyPost;
import company.workstations.CargoPost;
import company.workstations.CertificationPost;
import company.workstations.DriveTrainPost;
import company.workstations.Workstation;

public class Line3 extends Assemblyline {

	public Line3(int day) {
		super(new Workstation[]{
				new BodyPost(),
				new DriveTrainPost(),
				new CargoPost(),
				new AccessoriesPost(),
				new CertificationPost()
		}, day);
	}
}
