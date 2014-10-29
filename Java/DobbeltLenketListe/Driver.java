public class Driver 
{
	public static void main(String[] args)
	{
		SBinTre<Integer> test = SBinTre.<Integer>lagTre();
		test.leggInn(2);
		test.leggInn(1);
		test.leggInn(4);
		test.leggInn(2);
		
		System.out.println(test.inneholder(2));
	}
}