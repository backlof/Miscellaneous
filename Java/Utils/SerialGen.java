import java.util.Random;

public class SerialGen
{
	public static void main(String[] args)
	{
		for(int i = 0; i < 5; i++)
		{
			System.out.println(generateHex(5,10));
		}
	}

	private static String generateHex(int grouplength, int groups)
	{
		String bin = "";
		int unit;
		Random ran = new Random();

		int length = groups * grouplength;

		for(int i=1; i <= length; i++)
		{
			
			unit = ran.nextInt(16);
			bin += getHex(unit);
			if(i != 1 && i != length && (i % grouplength) == 0)
			{
				bin += "-";
			}
		}
		return bin;
	}

	private static String getHex(int number)
	{
		switch(number)
		{
			case 10: return "A";
			case 11: return "B";
			case 12: return "C";
			case 13: return "D";
			case 14: return "E";
			case 15: return "F";
			default: return "" + number;
		}
	}
}