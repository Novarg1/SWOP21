package company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import car.CarInProduction;
import car.CarPart;

public abstract class WorkStation {

	private CarInProduction current = null;
	private boolean ready = true;
	protected final Class<?>[] installableParts;

	protected WorkStation(Class<?>[] installableParts) {
		this.installableParts = installableParts;
	}
	
	public void setCurrentJob(CarInProduction current) {
		this.current = current;
		ready = (current == null);
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
		ready = getPendingTasks().isEmpty();
	}
	
	public boolean isInstalled(CarPart part) {
		return current.CAR.hasPart(part);
	}
	
	public List<CarPart> getPendingTasks() {
		if(current == null) {
			return Collections.emptyList();
		}
		List<CarPart> list = new ArrayList<CarPart>(5);
		for (Class<?> partClass : installableParts) {
			CarPart part = current.ORDER.SPECIFICATION.get(partClass);
			if(!isInstalled(part)) {
				list.add(part);
			}
		}
		return list;
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
