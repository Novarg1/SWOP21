package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import vehicle.assemblytasks.Task;
import company.CMCSystem;
import company.assemblylines.Assemblyline;
import company.workstations.Workstation;
import dao.OrderDAOImpl;

public class ScenarioAdvanceAssemblyLine {
	/**
	 * variables that may change during the tests
	 */
	
	/**
	 * variable that must remain immutable during tests
	 */
	private CMCSystem system = new CMCSystem(new OrderDAOImpl());
	
	/**
	 * a function that resets all mutable variables to a standard configuration
	 */
	@Before
	public void setUp() throws Exception {
		// log in the garage holder
		system.logInUser(1);
	}
	
	private void performAllTasksOn(Workstation station)
	{
		Set<Task> tasks = station.getPendingTasks();
		for(Task t : tasks)
		{
			t.perform();
		}
	}
	
	private void performAllTasksOn(Assemblyline line)
	{		
		for(Workstation w : line.getWorkstations())
		{
			performAllTasksOn(w);
		}
	}
	
	@Test
	public void test() {
		// get the first assemblyLine
		Assemblyline line = system.getAssemblyLine(0);
		
		// get the start time
		int time = line.getCurrentTime().getTime();
		
		// perform all tasks on all workstations
		performAllTasksOn(line);
		
		// get the new time
		int ntime = line.getCurrentTime().getTime();
		
		assertEquals(ntime, time);
	}

}
