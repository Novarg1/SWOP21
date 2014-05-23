package vehicle.order;

import java.util.HashSet;
import java.util.Set;

import vehicle.assemblytasks.AddCargoProtection;
import vehicle.assemblytasks.InstallToolStorage;
import vehicle.assemblytasks.Task;

public abstract class TruckOrderBuilder extends VehicleOrderBuilder {

	@Override
	protected Set<Task> getModelSpecificTasks() {
		Set<Task> tasks = new HashSet<>();
		tasks.add(new AddCargoProtection());
		tasks.add(new InstallToolStorage());
		return tasks;
	}
}
