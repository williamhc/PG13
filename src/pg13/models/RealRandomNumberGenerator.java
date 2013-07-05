package pg13.models;

public class RealRandomNumberGenerator implements IRandomNumberGenerator
{
	public RealRandomNumberGenerator()
	{
		
	}
	
	@Override
	public int random(int max) 
	{
		return (int) (Math.random() * (max));
	}

}
