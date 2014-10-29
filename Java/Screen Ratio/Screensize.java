//Calculating class
public class Screensize
{
	public final static int WIDTH = 0;
	public final static int HEIGHT = 1;
	private int width, height;
	
	public Screensize(int size, int constant, double ratioWidth, double ratioHeight)
	{
		if(constant == WIDTH)
		{
			width = size;
			double temp = (width/ratioWidth) * ratioHeight;
			height = (int)temp;
		}
		if(constant == HEIGHT)
		{
			height = size;
			double temp = (height/ratioHeight) * ratioWidth;
			width = (int)temp;
		}
	}
	
	public String toString()
	{
		return "" + width + " x " + height;
	}
}
