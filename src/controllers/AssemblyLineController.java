package controllers;

import company.assemblylines.Assemblyline;
import company.assemblylines.Assemblyline.Status;

/**
 * AssemblyLineController has an assembly line as member and provides
 * functionality around it
 * 
 * @author jonathanlangens
 *
 */
public class AssemblyLineController {
	private Assemblyline assemblyLine;

	/**
	 * Default constructor
	 * 
	 * @param AssemblyLine
	 *            a
	 */
	public AssemblyLineController(Assemblyline a) {
		this.assemblyLine = a;
	}

	/**
	 * @return the assembly lines current status
	 */
	public Status getStatus() {
		return this.assemblyLine.getStatus();
	}

	/**
	 * Tries to set the assembly lines status to s
	 * 
	 * @param s
	 */
	public void setStatus(Status s) {
		this.assemblyLine.setStatus(s);
	}
}
