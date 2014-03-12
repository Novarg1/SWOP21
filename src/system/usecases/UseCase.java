package system.usecases;

import system.SystemController;

/** UseCase interface
 * A class implementing this interface has only to know what the
 * next input is at any given point. This can be used to test usecases effectively
 */
public interface UseCase 
{
	public String nextInput();
	public boolean passTest(SystemController c);
}
