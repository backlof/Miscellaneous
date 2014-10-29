
public class Oblig3C
{
	public static void main(String[] argumenter)
	{
		if(argumenter.length == 0)
		{
			System.out.println("\nBruk: \'>java Oblig3C <tekstfil>\' \n");
		}
		else if(argumenter.length > 0)
		{
			OrdAnalyse m = new OrdAnalyse();
			m.analyze(argumenter[0]);
			
			m.printA();
			m.printB();
			m.printC();
		}
	}
}
