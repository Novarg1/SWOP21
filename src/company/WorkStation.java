package company;

import car.CarInProduction;
import car.CarPart;

public abstract class WorkStation {

	private CarInProduction current = null;
	private boolean ready = true;
	protected final Class[] installableParts;

	protected WorkStation(Class[] installableParts) {
		this.installableParts = installableParts;
	}
	
	public void setCurrentJob(CarInProduction current) {
		this.current = current;
		ready = (current == null) ? true : false;
	}

	public CarInProduction getCurrentJob() {
		return current;
	}


	public boolean isReady() {
		return ready;
	}

	public void install(CarPart part) {
		if(!this.canInstall(part)) {
			throw new IllegalArgumentException();
		}
		current.CAR.install(part);
	}
	
	public boolean isInstalled(CarPart part) {
		return current.CAR.hasPart(part);
	}
	
	private boolean canInstall(CarPart part) {
		for (int i = 0; i < installableParts.length; i++) {
			if(part.getClass() == installableParts[i]) {
				return true;
			}
		}
		return false;
	}
}
