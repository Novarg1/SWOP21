package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import company.AssemblyLine;
import company.Company;
import company.WorkStation;

public class TestAssemblyLine {

	Company company;

	@Before
	public void initialize() {
		company = new Company();
	}

	@Test
	public void testWorkStations() {
		for (WorkStation ws : company.getAssemblyLine().getWorkstations()) {
			//TODO
		}
	}
}
