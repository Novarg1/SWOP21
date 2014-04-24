package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import company.AssemblyLine;
import company.Schedule;
import company.WorkStation;

/**
 * This class collects unit tests for the Workstation class
 * 
 * @author Wonne Joosen
 */
public class TestWorkstation {

	private AssemblyLine assemblyLine;
	private WorkStation workStation1;
	private WorkStation workStation2;
	private WorkStation workStation3;
	private Schedule schedule;
	
	@Before
	public void initialize(){
		schedule = new Schedule();
		assemblyLine = new AssemblyLine(schedule);
		workStation1 = assemblyLine.getWorkstations()[0];
		workStation2 = assemblyLine.getWorkstations()[1];
		workStation3 = assemblyLine.getWorkstations()[2];		
	}
	
	@Test
	public void test(){
		
	}

}
