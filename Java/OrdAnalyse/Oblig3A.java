
public class Oblig3A
{
	public static void main(String[] argumenter)
	{
		if(argumenter.length == 0)
		{
			System.out.println("\nBruk: \'>java Oblig3A <tekstfil>\' \n");
		}
		else if(argumenter.length > 0)
		{
			OrdAnalyse m = new OrdAnalyse();
			m.analyze(argumenter[0]);
			
			m.printA();
		}
	}
}
