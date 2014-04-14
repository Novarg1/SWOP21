package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LineReader 
{
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
	
	public static int readInt()
	{
		return Integer.parseInt(readLine());
	}
}
