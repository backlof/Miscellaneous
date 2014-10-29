import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class OrdAnalyse
{
	private static final int INITIAL_LENGTH = 64; //opprinnelig lengde på array
	
	private static final int COLUMN1_WIDTH = 20;
	private static final int COLUMN2_WIDTH = 10;
	
	private static final String COLUMN1_NAME = "ORD";
	private static final String COLUMN2_NAME = "FOREKOMSTER";
	
	private int countUnique;
	private int countWords;
	private int lastIndex;
	
	private String[] words;
	private int[] count;	
	private int[][] wordPairs;
	
	
	//analyserer inputfilen og henter ut ord for lagring i arrays
	public void analyze(String fileName)
	{
		countUnique = 0;
		countWords = 0;
		
		words = new String[INITIAL_LENGTH];
		count = new int[INITIAL_LENGTH];
		wordPairs = new int[INITIAL_LENGTH][INITIAL_LENGTH];
		
		try
		{	
			//Scanner file = new Scanner(new File(fileName));
			Scanner file = new Scanner(new File("alice.txt"));
			
			String[] line;
			
			while(file.hasNextLine())
			{
				line = file.nextLine().split("[^a-zA-Z']");//valgte denne måten å splitte ord på, fordi det var mest pålitelig i forhold til teksten
				
				for(int i = 0; i < line.length; i++)
				{
					if(!line[i].equals(""))//bare en sjekk for å unngå blanke ord
					{
						addWord(line[i].trim().replaceAll("'s|'S", ""));//legger inn ordene, trimmer ordet og fjerner "'s" fra "Alice's"
					}
				}
			}
			file.close();
			
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Filen finnes ikke!");
		}
	}
	
	//legger inn ord i arrayene
	private void addWord(String newWord)
	{
		countWords++;
		int i;
		boolean found = false;
		
		//sjekker om ordet allerede har blitt lagt inn
		for(i = 0; i < countUnique; i++)
		{
			if(words[i].toLowerCase().equals(newWord.toLowerCase()))
			{
				count[i]++;
				wordPairs[lastIndex][i]++;
				lastIndex = i;
				found = true;
				break;//escaper for-løkken
			}
		}
		//ble ordet funnet?
		if(!found)
		{
			//er arrayen lang nok til å legge inn et nytt ord?
			if(countUnique == words.length - 1)
			{
				expandArrays();
			}
			
			//legger inn ordet i første plass som ikke er opptatt		
			i = countUnique;
			words[i] = newWord;
			count[i] = 1;
			countUnique++;
			//legger inn ordpar
			if(lastIndex != -1){ wordPairs[lastIndex][i]++; }//fordi det første ordet ikke har noe ord før seg
			lastIndex = i;//oppdaterer indeks for siste innlegging
		}
	}
	
	//ekspanderer arrayene når de er fulle
	private void expandArrays()
	{
		int length = words.length;
		int newLength = length * 2;
		
		String[] temp1 = new String[newLength];
		
		for(int i = 0; i < length; i++)
		{
			temp1[i] = words[i];
		}
		
		words = temp1;
		
		
		int[] temp2 = new int[newLength];
		
		for(int i = 0; i < length; i++)
		{
			temp2[i] = count[i];
		}
		
		count = temp2;
		
		int[][] temp3 = new int[newLength][newLength];
		
		for(int i = 0; i < length; i++)
		{
			for(int x = 0; x < length; x++)
			{
				temp3[i][x] = wordPairs[i][x];
			}
		}
		
		wordPairs = temp3;
	}
	
	//skrive ut til fil alle unike ord
	public void printA()
	{
		try
		{
			FileWriter file = new FileWriter("oppsummering.txt", false);
			
			file.write("I filen er det funnet " + countWords + " ord, " + countUnique + " av dem er unike:" + System.getProperty("line.separator"));
			
			file.write(System.getProperty("line.separator"));
			
			file.write(String.format("%-" + COLUMN1_WIDTH + "s %-" + COLUMN2_WIDTH + "s\n", COLUMN1_NAME, COLUMN2_NAME) + System.getProperty("line.separator"));
			
			
			for(int i = 0; i < countUnique; i++)
			{
				file.write(String.format("%-" + COLUMN1_WIDTH + "s %-" + COLUMN2_WIDTH + "s\n", words[i], count[i]) + System.getProperty("line.separator"));
			}

			file.close();
		}
		catch(Exception e)
		{
			System.out.println("\nKan ikke skrive til fil.\n");
		}
	}
	
	//skrive ut de vanligste ordene
	public void printB()
	{
		int largestNumber = 0;
		int index = 0;
		
		//leter etter ordet med flest forekomster
		for(int i = 0; i < countUnique; i++)
		{
			if(count[i] > largestNumber)
			{
				index = i;
				largestNumber = count[i];
			}	
		}
		
		System.out.println("Det vanligste ordet er \"" + words[index] + "\" med " + count[index] + " forekomster\n");
		System.out.println("Her er ordene som forekommer minst 10% så mye:\n");
		
		int lowestNumberAcceptable = largestNumber / 10;
		
		System.out.printf("%-" + COLUMN1_WIDTH + "s %-" + COLUMN2_WIDTH + "s\n", COLUMN1_NAME, COLUMN2_NAME);
		
		for(int i = 0; i < countUnique; i++)
		{
			if(count[i] >= lowestNumberAcceptable)
			{
				System.out.printf("%-" + COLUMN1_WIDTH + "s %-" + COLUMN2_WIDTH + "s\n", words[i], count[i]);
			}
		}
		System.out.println();
	}
	
	//skrive alle ordene som etterfølger alice
	public void printC()
	{
		int i;
		boolean found = false;
		
		//leter etter index for alice
		for(i = 0; i < countUnique; i++)
		{
			if(words[i].toLowerCase().equals("alice"))
			{
				found = true;
				break;
			}
		}
		
		if(found)
		{
			int indexOfAlice = i;
			System.out.println("Her er alle ordene som har etterfulgt Alice:\n");
			System.out.printf("%-" + COLUMN1_WIDTH + "s %-" + COLUMN2_WIDTH + "s\n", COLUMN1_NAME, COLUMN2_NAME);
			
			for(i = 0; i < countUnique; i++)
			{
				if(wordPairs[indexOfAlice][i] > 0)
				{
					System.out.printf("%-" + COLUMN1_WIDTH + "s %-" + COLUMN2_WIDTH + "s\n", words[i], wordPairs[indexOfAlice][i]);
				}
			}
		}
		else{ System.out.println("Alice finnes ikke i teksten."); }
	}
}
