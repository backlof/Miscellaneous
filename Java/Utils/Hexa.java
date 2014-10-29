import java.lang.StringBuilder;

public class Hexa
{
	public static final int ALL_CAPS = 1;
	public static final int LOWER_CASE = 0; //enum? constant?

	//public static enum Caps { ALL, NONE}
	//public static Caps cap = Caps.NONE;
	//send heller i metodene

	private int value;

	public Hexa(){ value = 0; }

	public Hexa(int input){ set(input); }

	public Hexa(String input){ set(input); }

	public void set(String input)
	{
		if(input == null || input == "")
		{
			throw new NumberFormatException();
		}
		else
		{
			int sum = 0;
			int digit;
			int increment = 0;

			for(int index = (input.length() - 1); index >= 0; index--)
			{
				digit = convertDigit(input.charAt(index));
				sum += digit * (Math.pow(16, increment));
				increment++;
			}
			value = sum;
		}
	}

	public void set(int input){ value = input; }

	private int convertDigit(char digit)
	{
		switch(digit)
		{
			case '0': return 0;
			case '1': return 1;
			case '2': return 2;
			case '3': return 3;
			case '4': return 4;
			case '5': return 5;
			case '6': return 6;
			case '7': return 7;
			case '8': return 8;
			case '9': return 9;
			case 'A': case 'a': return 10;
			case 'B': case 'b': return 11;
			case 'C': case 'c': return 12;
			case 'D': case 'd': return 13;
			case 'E': case 'e': return 14;
			case 'F': case 'f': return 15;
			default: throw new NumberFormatException();
		}
	}
	
	private char convertNumber(int number)
	{
		switch(number)
		{
			case 10: return 'A';
			case 11: return 'B';
			case 12: return 'C';
			case 13: return 'D';
			case 14: return 'E';
			case 15: return 'F';
		}
		return ("" + number).charAt(0);
	}

	public String get()
	{
		if(value < 10)
		{
			return "" + value;
		}
		else if(value < 16)
		{
			return "" + Character.toString(convertNumber(value));
		}
		else
		{
			boolean test = true;
			StringBuilder sb = new StringBuilder();
			int workingNumber = value;

			while(test)
			{
				if(workingNumber >= 16)
				{
					sb.append(convertNumber(workingNumber % 16));
					workingNumber = workingNumber / 16;
				}
				else
				{
					sb.append(convertNumber(workingNumber));
					test = false;
				}
			}
			return sb.reverse().toString();
		}
	}

	public int getInteger()
	{
		return value;
	}
}