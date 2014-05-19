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
	
	/**
	 * default constructor, takes a system controller as parameter
	 * @param c
	 */
	protected View(SystemController c)
	{
		this.systemController = c;
	}
	
	/**
	 * every view has to override show(). The user of the view can use 
	 * this function to notify the view that it must show itself
	 * @return
	 */
	public abstract boolean show();
}
