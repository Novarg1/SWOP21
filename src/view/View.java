package view;

import controllers.SystemController;

/**
 * Abstract class that provides a ui
 * 
 * View has a pointer to the system controller, each subclass should
 * call View's default constructor and override the abstract show method
 * @author jonathanlangens
 *
 */
public abstract class View 
{
	protected SystemController systemController;
	protected View(SystemController c)
	{
		this.systemController = c;
	}
	
	public abstract boolean show();
}
