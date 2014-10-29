import java.util.Random;

public class Randnum
{
	private static Random rand = new Random();
	
	//private integers for running the main method
	private static int count = 0;
	private static int runs = 1;
	
	//public integers for generating random numbers
	public static int from = 1;
	public static int to = 10;

	public static void main(String[] args)
	{
		if(args.length > 0)
		{
			try
			{		
				switch (args.length)
				{
					case 1:
						to = Integer.parseInt(args[0]);
						break;
					case 2:
						from = Integer.parseInt(args[0]);
						to = Integer.parseInt(args[1]);
						break;
					default:
						from = Integer.parseInt(args[0]);
						to = Integer.parseInt(args[1]);
						runs = Integer.parseInt(args[2]);
						break;
				}
				
				calculate();
			}
			catch(Exception e)
			{
				help();
				System.out.println("The parameters need to be integers!\n");
			}
		}
		else
		{
			help();
		}
	}
	
	private static void help()
	{
		System.out.println("Examples of uses: \n");
		System.out.println("java Randnum <numbers>");
		System.out.println("java Randnum <from> <to>");
		System.out.println("java Randnum <from> <to> <runs>\n");
	}
	
	private static void calculate()
	{
		System.out.println(generate());
		count++;
		
		if(count != runs)
		{
			calculate();
		}
	}
	
	//method for use outside of this class
	public static int generate(int fromNumber, int toNumber)
	{
		return rand.nextInt(toNumber - fromNumber + 1) + fromNumber;
	}
	
	public static int generate()
	{
		return rand.nextInt(to - from + 1) + from;
	}
}
