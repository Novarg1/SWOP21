package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * Helper class to read in input from the console
 * @author jonathanlangens
 *
 */
public class LineReader 
{
	/**
	 * @return the current line that has been typed into the console
	 */
	public static String readLine()
	{		
		InputStreamReader sr =new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(sr);

		String input = "";

		try
		{
			input = br.readLine();
		}catch(Exception e){}

		return input;
	}
	
	/**
	 * @return the current integer being typed into the console
	 */
	public static int readInt()
	{
		return Integer.parseInt(readLine());
	}
}
