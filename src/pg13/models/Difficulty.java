package pg13.models;

import java.util.Enumeration;

public enum Difficulty implements Enumeration<Object>
{
	Easy, Medium, Hard;

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
}
