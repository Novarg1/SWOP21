package vehicle.order;

import java.util.Collections;
import java.util.Set;

import vehicle.assemblytasks.Task;

public abstract class CarModelBuilder extends VehicleOrderBuilder {

	@Override
	protected Set<Task> getModelSpecificTasks() {
		return Collections.emptySet();
	}
}
