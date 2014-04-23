package view;

import controllers.SystemController;

public abstract class View 
{
	protected SystemController systemController;
	protected View(SystemController c)
	{
		this.systemController = c;
	}
	
	public abstract boolean show();
}
