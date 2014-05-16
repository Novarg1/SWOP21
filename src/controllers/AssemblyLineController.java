package controllers;

import company.AssemblyLine;
import company.AssemblyLine.Status;

public class AssemblyLineController {
	private AssemblyLine assemblyLine;
	
	public AssemblyLineController(AssemblyLine a)
	{
		this.assemblyLine = a;
	}
	
	public Status getStatus()
	{
		return this.assemblyLine.getStatus();
	}

	public void setStatus(Status s)
	{
		this.assemblyLine.setStatus(s);
	}
}
