package pg13.models;

import java.util.ArrayList;
import java.util.Enumeration;

public enum Category implements Enumeration<Object>
{
	Animals, Biology, Computers, Games, Trivia, Geography, History, Miscellaneous, Politics, Science, Space, Sports;

	@Override
	public boolean hasMoreElements()
	{
		return false;
	}

	@Override
	public Object nextElement()
	{
		return null;
	}

	public static ArrayList<String> valuesAsStrings()
	{
		Category[] vals = Category.values();
		ArrayList<String> strs = new ArrayList<String>();
		for (int i = 0; i < vals.length; i++)
		{
			strs.add(vals[i].toString());
		}
		return strs;
	}
}
