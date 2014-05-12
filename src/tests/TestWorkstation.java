package tests;

import org.junit.Before;
import org.junit.Test;

import company.AssemblyLine;
import company.schedule.Scheduler;
import company.workstations.Workstation;

/**
 * This class collects unit tests for the Workstation class
 * 
 * @author Wonne Joosen
 */
public class TestWorkstation {

	private AssemblyLine assemblyLine;
	private Workstation workStation1;
	private Workstation workStation2;
	private Workstation workStation3;
	private Scheduler schedule;
	
	@Before
	public void initialize(){
		schedule = new Scheduler();
		assemblyLine = new AssemblyLine(schedule);
		workStation1 = assemblyLine.getWorkstations()[0];
		workStation2 = assemblyLine.getWorkstations()[1];
		workStation3 = assemblyLine.getWorkstations()[2];		
	}
	
	@Test
	public void test(){
		
	}

}
