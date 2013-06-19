package pg13.models;

import java.util.Enumeration;

public enum Category implements Enumeration<Object>
{
	Animals,
	Biology,
	Computers,
	Games,
	Trivia,
	Geography,
	History,
	Miscellaneous,
	Politics,
	Science,
	Space,
	Sports;

	@Override
	public boolean hasMoreElements() {
		return false;
	}

	@Override
	public Object nextElement() {
		return null;
	}
}
