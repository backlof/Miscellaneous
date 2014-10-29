import java.lang.StringBuilder;
import java.util.Random;

public class TimeExec
{
	private static long start;
	private static final int TIMER_IN_SECONDS = 2;
	private static StringBuilder stringToBuild;

	public static void main(String[] args)
	{
		stringToBuild = new StringBuilder();
		start = System.currentTimeMillis();
		
		job(0);
		
		System.out.println(stringToBuild.toString());
	}

	public static void job(int n)
	{
		
		
		
		if(n != 0)
		{
			
			
			
			
			
			
			
			
			
		}
		
		
		
		//Arbeidsoppgave under (må være litt lengre oppgaver for å unngå crash)
		
		
		Random random = new Random();

		stringToBuild.append("" + random.nextInt(2));
		//-------------------

		if((System.currentTimeMillis() - start) < (TIMER_IN_SECONDS * 1000))
		{
			job(n++);
		}
	}
}