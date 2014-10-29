import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Housing
{
	/* Jeg valgte å gjøre alle metoder og variabler static i denne klassen fordi den alltid leser fra samme fil
	 * Da gir det ikke noen mening å kunne ha flere instanser
	 */
	
	private static final String REGISTRY_FILENAME = "hybeldata.txt";
	private static final String[] OPTIONS = {
		"Skriv oversikt",
		"Registrer ny leietager",
		"Registrer betaling fra leietager",
		"Registrer frivillig utflytting",
		"Månedskjøring av husleie",
		"Kast ut leietagere",
		"Øk husleien",
		"Avslutt"
	};
	
	public static final int FLOORS[] = {1,2,3};
	private static final char ROOMS[] = {'A','B','C','D','E','F'};
	
	private static final int[] COLUMN_WIDTH = { 6, 23, 10 };
	private static final String[] COLUMN_NAMES = { "Hybel", "Leietager", "Saldo" };
	public static final String TABLE_FORMAT = "%-" + COLUMN_WIDTH[0] +"s %-" + COLUMN_WIDTH[1] + "s %-" + COLUMN_WIDTH[2] + "s\n";
	
	private static Studio[] studios;
	
	private static int year = 2013;
	private static int month = 11;
	
	public static int earnings = 0;
	private static int monthsRunning = 0;
	
	private static final int expencePerRoom = 1200;
	private static final int expencesPerFloor = 1700;
	
	private static Scanner terminal = new Scanner(System.in);
	
	private static int studiosInHousing()
	{
		return (FLOORS.length * ROOMS.length);
	}
	
	public static void menu()
	{
		System.out.println();
		for(int i = 0; i < OPTIONS.length; i++)
		{
			System.out.println(" " + (i+1) + ". " + OPTIONS[i]);
		}
		System.out.println();
		
		String option;
		boolean valid;
		
		do
		{
			valid = true;
			System.out.print("Alternativ: ");
			option = terminal.nextLine().trim();
			
			switch(option)
			{
				case "1": printSummary(); break;
				case "2": addNewTenant(); break;
				case "3": addPayment(); break;
				case "4": moveOut(); break;
				case "5": newMonth(); break;
				case "6": evictTenants(); break;
				case "7": increaseRent(); break;
				case "8": System.out.println("\nSystemet avslutter. Lagrer til fil...\n"); writeToFile(); break;
				default: valid = false; System.out.println("\nSkriv et tall mellom 1 og 8.\n"); break;
			}
			
		}
		while(!valid);
		
		if(!option.equals("8")){ menu(); }
	}
	
	private static boolean studioExist(String name)
	{
		for(int i = 0; i < studios.length; i++)
		{
			if(name.toLowerCase().equals(studios[i].getName().toLowerCase()))
			{
				return true;
			}
		}
		return false;
	}
	
	private static Studio findStudio(String name)
	{
		for(int i = 0; i < studios.length; i++)
		{
			if(name.toLowerCase().equals(studios[i].getName().toLowerCase()))
			{
				return studios[i];
			}
		}
		
		throw new NoSuchElementException("Denne hybelen finnes ikke.");
	}
	
	private static String lineToWrite()
	{
		return month + ";" + year + ";" + earnings + ";" + monthsRunning + ";" + Studio.rentNormal + ";" + Studio.rentTopFloor + ";";
	}
	
	private static void writeToFile()
	{
		try
		{
			FileWriter fileWriter = new FileWriter(REGISTRY_FILENAME, false);
			
			fileWriter.write(lineToWrite() + System.getProperty("line.separator"));
			
			for(int i = 0; i < studios.length; i++)
			{
				fileWriter.write(studios[i].lineToWrite() + System.getProperty("line.separator"));
			}
			
			fileWriter.close();
		}
		catch (IOException e)
		{
			System.out.println("\nKan ikke skriv til fil. Feilmelding:\n\n");
			e.printStackTrace();
		}
	}

	public static void readFromFile()
	{
		try
		{
			Scanner fileScanner = new Scanner(new File(REGISTRY_FILENAME));
			
			//Les fra fil
			String[] line = fileScanner.nextLine().split(";");
			
			month = Integer.parseInt(line[0]);
			year = Integer.parseInt(line[1]);
			earnings = Integer.parseInt(line[2]);
			monthsRunning = Integer.parseInt(line[3]);
			Studio.rentNormal = Integer.parseInt(line[4]);
			Studio.rentTopFloor =  Integer.parseInt(line[5]);
			
			studios = new Studio[studiosInHousing()];
			for(int i = 0; i < studios.length; i++)
			{
				studios[i] = new Studio(line = fileScanner.nextLine().split(";"));
			}

			fileScanner.close();
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("\nSystemet har aldri blitt kjørt før. Lager nytt register...");
			createNewRegistry();
		}
	}

	private static void createNewRegistry()
	{
		studios = new Studio[studiosInHousing()];
		
		int index = 0;
		for(int x = 0; x < FLOORS.length; x++)
		{
			for(int y = 0; y < ROOMS.length; y++)
			{
				studios[index] = new Studio(FLOORS[x], ROOMS[y]);
				index++;
			}
		}
	}
	
	private static void printHeader()
	{
		System.out.println();
		System.out.printf(TABLE_FORMAT, COLUMN_NAMES[0], COLUMN_NAMES[1], COLUMN_NAMES[2]);
	}
	
	private static void printAllStudios()
	{
		printHeader();
		
		for(int i = 0; i < studios.length; i++)
		{
			studios[i].print();
		}
	}
	
	private static void printSummary()
	{
		printAllStudios();
		
		String format = "%-18s %-5s \n";
		
		System.out.println();
		
		System.out.printf(format, "Måned/år:", month + "/" + year);
		System.out.printf(format, "Måneder i drift:", monthsRunning);
		System.out.printf(format, "Totalfortjeneste:", earnings);
		
		stallMenu();
	}
	
	private static void stallMenu()
	{
		System.out.print("\nKlikk <ENTER> for å gå videre: ");
		terminal.nextLine();
	}
	
	private static void addNewTenant()
	{
		boolean avaliableStudios = false;
		
		for(int i = 0; i < studios.length; i++)
		{
			if(studios[i].isAvaliable())
			{
				if(avaliableStudios == false){ printHeader(); }
				studios[i].print();
				avaliableStudios = true;
			}
		}
		
		if(avaliableStudios)
		{			
			boolean foundStudio = false;
			
			while(!foundStudio)
			{
				System.out.print("\nVelg hybel <1C>: ");
				String studioName = terminal.nextLine().trim();
				
				if(studioExist(studioName))
				{
					//peker
					Studio studioToEdit = findStudio(studioName);
					
					if(studioToEdit.isAvaliable())
					{
						//en ledig leilighet
						foundStudio = true;
						
						System.out.print("\nFyll inn navn på leietaker:");
						studioToEdit.addTenant(terminal.nextLine().trim());
						
						System.out.println("\nLeietager har blitt lagt inn:");
						
						printHeader();
						studioToEdit.print();
						
						stallMenu();
					}
					else
					{
						System.out.println("\nLeiligheten du valgte er ikke ledig.");
					}
				}
				else{ System.out.println("\nFinner ingen slik leilighet."); }
			}
			
		}
		else
		{
			System.out.print("\nDet er ingen ledige hybler.\n");
			stallMenu();
		}
	}
	
	private static void addPayment()
	{
		boolean tenantsInStudios = false;
		
		for(int i = 0; i < studios.length; i++)
		{
			if(!studios[i].isAvaliable())
			{
				if(tenantsInStudios == false){ printHeader(); }
				studios[i].print();
				tenantsInStudios = true;
			}
		}
		
		if(tenantsInStudios)
		{			
			boolean foundStudio = false;
			
			while(!foundStudio)
			{
				System.out.print("\nVelg hybel <1C>: ");
				String studioName = terminal.nextLine().trim();
				
				if(studioExist(studioName))
				{
					//peker
					Studio studioToEdit = findStudio(studioName);
					
					if(studioToEdit.isAvaliable())
					{
						System.out.println("\nLeligheten har ingen leietager.");
					}
					else
					{
						foundStudio = true;
						boolean isNumber = false;
						
						while(!isNumber)
						{
							System.out.print("\nHvor mye: ");
							
							try
							{
								int payment = Integer.parseInt(terminal.nextLine().trim());
								
								isNumber = true;
								
								studioToEdit.addPayment(payment);
								
								System.out.println("\nBeløp har blitt lagt inn:");
								
								printHeader();
								studioToEdit.print();
								
								stallMenu();
							}
							catch(NumberFormatException e)
							{
								System.out.println("\nKan ikke konvertere til tall. Prøv igjen.");
							}
						}
					}
				}
				else{ System.out.println("\nFinner ingen slik leilighet."); }
			}
			
		}
		else
		{
			System.out.print("\nDet er ingen leietagere for øyeblikket.\n");
			stallMenu();
		}
		
		
		
		
		
	}
	
	private static void moveOut()
	{
		System.out.print("\nSkriv inn navn på studenten som skal flytte ut: ");
		String studentName = terminal.nextLine().trim();
		
		boolean studentFound = false;
		
		for(int i = 0; i < studios.length; i++)
		{
			if(studios[i].isTenant(studentName))
			{
				studentFound = true;
				
				if(studios[i].canMoveOut())
				{
					studios[i].moveOut();
				}
				else{
					System.out.println("\nStudenten kan ikke flytte ut fordi saldo ikke er nok til månedsleie.\n");
				}
			}
		}
		if(!studentFound)
		{
			System.out.println("\nKunne ikke finne student.\n");
		}
		
	}
	
	private static void newMonth()
	{		
		if(month == 12)
		{
			System.out.print("Ønsker du å utføre månedskjøring for 1/" + (year + 1) + " <j/n>:");
		}
		else
		{
			System.out.print("Ønsker du å utføre månedskjøring for " + (month + 1) + "/" + year + " <j/n>:");
		}
		
		if(terminal.nextLine().trim().equals("j"))
		{
			int lastMonth = earnings;
			
			for(int i = 0; i < studios.length; i++)
			{
				studios[i].payRent();
				earnings -= expencePerRoom;
			}
			
			earnings -= (FLOORS.length * expencesPerFloor);
			
			int monthlyEarning = earnings - lastMonth; 
			
			if(month == 12){ year++; month = 1; }
			else{ month++; }
			monthsRunning++;
			
			
			String format = "%-22s %-5s \n";
			
			System.out.println();
			
			System.out.printf(format, "Måned/år:", month + "/" + year);
			System.out.printf(format, "Måneder i drift:", monthsRunning);
			System.out.printf(format, "Husleiesatser:", Studio.rentNormal + ", " + Studio.rentTopFloor);
			System.out.printf(format, "Månedlig fortjeneste:", monthlyEarning);
			System.out.printf(format, "Totalfortjeneste:", earnings);
			System.out.printf(format, "Gjennomsnittlig:", (earnings / monthsRunning));
			
			
			stallMenu();			
		}
	}
	
	private static void evictTenants()
	{
		for(int i = 0; i < studios.length; i++)
		{
			studios[i].checkForEviction();
		}
	}
	
	private static void increaseRent()
	{
		boolean valid = false;
		
		while(!valid)
		{
			System.out.print("Ny leie (vanlig):");
			
			try
			{
				int newRent = Integer.parseInt(terminal.nextLine().trim());
				
				if(newRent <= 0)
				{
					System.out.println("\nUgyldig tall.\n7");
				}
				else
				{
					Studio.rentNormal = newRent;
					valid = true;
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("\nKan ikke konvertere til tall. Prøv igjen.\n");
			}
		}
		
		valid = false;
		
		while(!valid)
		{
			System.out.print("Ny leie (topp-etasjer):");
			
			try
			{
				int newRent = Integer.parseInt(terminal.nextLine().trim());
				
				if(newRent <= Studio.rentNormal)
				{
					System.out.println("\nLeien må være større for hybler i topp-etasjen.\n");
				}
				else
				{
					Studio.rentTopFloor = newRent;
					valid = true;
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("\nKan ikke konvertere til tall. Prøv igjen.\n");
			}
		}
	}
	
}
