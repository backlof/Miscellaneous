import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Planner
{
	private static final String FILENAME = "trikkogtbaneansi-ver2.txt";	
	private Scanner terminal;
	
	//Lag en metode som skriver ut alle linjene
	
	//Oppslagsliste for stasjoner
	private HashMap<String, Station> stations = new HashMap<String, Station>();
	
	private static final int INITIAL_ARRAY_SIZE = 16;
	private Track[] tracksInSystem;
	private int count = 0;
	
	public Planner()
	{
		terminal = new Scanner(System.in);
		
		tracksInSystem = new Track[INITIAL_ARRAY_SIZE];
		
		readFromFile();
	}
	
	//Beregner ruter
	public void calculateRoute(Station from, Station to)
	{
		System.out.println("Alle alternativer:\n");
		
		boolean needTransition = true;
		
		double bestTime = 100000;
		String bestRoute = "";
		
		//Ingen overgang
		for(int x = 0; x < from.count; x++)
		{
			for(int y = 0; y < to.count; y++)
			{
				if(from.tracksOnStation[x] == to.tracksOnStation[y])
				{
					needTransition = false;
					
					Track onTrack = from.tracksOnStation[x];
					int fromIndex = 0;
					int toIndex = 0;
					
					for(int i = 0; i < onTrack.count; i++)
					{
						if(onTrack.stationsOnTrack[i] == from)
						{
							fromIndex = i;
						}
						if(onTrack.stationsOnTrack[i] == to)
						{
							toIndex = i;
						}
					}
					
					
					double time = (Math.abs(fromIndex - toIndex)) * onTrack.stationTime();
					
					String print = "Ta " + onTrack.getTrack() + " " + from.stationName + " - " + to.stationName + " på " + String.format("%.1f", time) + " minutter.";
					
					if(time < bestTime){
						bestTime = time;
						bestRoute = print;
					}
					
					System.out.println(print);
				}
			}
		}
		
		if(needTransition)
		{
			//1 overgang
			for(int x = 0; x < from.count; x++)
			{
				Track firstTrack = from.tracksOnStation[x];
				
				for(int y = 0; y < firstTrack.count; y++)
				{
					Station transitStation = firstTrack.stationsOnTrack[y];
					
					for(int z = 0; z < transitStation.count; z++)
					{
						Track transitTrack = transitStation.tracksOnStation[z];
						
						for(int i = 0; i < to.count; i++)
						{
							if(transitTrack == to.tracksOnStation[i] && firstTrack != transitTrack && transitStation != from && transitStation != to)
							{							
								int firstFromIndex = 0;
								int firstToIndex = 0;
								
								int lastFromIndex = 0;
								int lastToIndex = 0;
								
								
								
								for(int index = 0; index < firstTrack.count; index++)
								{
									if(from == firstTrack.stationsOnTrack[index])
									{
										firstFromIndex = index;
									}
									if(transitStation == firstTrack.stationsOnTrack[index])
									{
										firstToIndex = index;
									}
								}
								
								for(int index = 0; index < transitTrack.count; index++)
								{
									if(transitStation == transitTrack.stationsOnTrack[index])
									{
										lastFromIndex = index;
									}
									if(to == transitTrack.stationsOnTrack[index])
									{
										lastToIndex = index;
									}
								}
								
								double time = (Math.abs(firstFromIndex - firstToIndex)) * firstTrack.stationTime(); //Første linje
								time += 3; //Gangetid
								time += transitTrack.waitTime(); //Ventetid
								time += (Math.abs(lastFromIndex - lastToIndex)) * transitTrack.stationTime(); //Andre linje
								
								String print = "Ta " + firstTrack.getTrack() + " " + from.stationName + " - " + transitStation.stationName;
								print += " og " + transitTrack.getTrack() + " " + transitStation.stationName + " - " + to.stationName + " på " + String.format("%.1f", time) + " minutter.";
								
								if(time < bestTime){
									bestTime = time;
									bestRoute = print;
								}
								
								System.out.println(print);
							}
						}
					}
				}	
			}
		}
		
		System.out.println("\nBeste tid:\n\n" + bestRoute);
		
	}
	
	//Tar imot brukerinput (stasjoner)
	public void query()
	{
		System.out.println("RUTEPLANLEGGING\n- Finn raske reiser med Trikk og T-bane i Oslo\n");
		
		Station from = null;
		Station to = null;
		
		while(true)
		{
			System.out.print("Fra stasjon: ");
			
			String input = terminal.nextLine().toLowerCase();
			
			if(stations.containsKey(input))
			{
				from = stations.get(input);
				break;
			}
			else if(input.length() > 0)
			{
				suggestions(input);
			}
			else
			{
				System.out.println("\nSkriv 1 bokstav eller mer for forslag.\n");
			}
		}
		
		System.out.println();
		
		while(true)
		{
			System.out.print("Til stasjon: ");
			
			String input = terminal.nextLine().toLowerCase();
			
			if(stations.containsKey(input))
			{
				to = stations.get(input);
				break;
			}
			else if(input.length() > 0)
			{
				suggestions(input);
			}
			else
			{
				System.out.println("\nSkriv 1 bokstav eller mer for forslag.\n");
			}
		}
		
		System.out.println();
		
		calculateRoute(from, to);
	}
	
	//Gir stasjons-forslag til brukeren
	private void suggestions(String searchQuery)
	{
		boolean found = false;
		String result = "";
		
		for(int i = 0; i < count; i++)
		{
			for(int x = 0; x < tracksInSystem[i].count; x++)
			{
				String stationName = tracksInSystem[i].stationsOnTrack[x].stationName;
				
				if(stationName.toLowerCase().contains(searchQuery))
				{					
					if(!result.contains(stationName))
					{
						result += stationName + "\n";
						found = true;
					}
				}
			}
		}
		
		if(found)
		{
			System.out.println("\nForslag:\n");
			System.out.println(result);
		}
		else
		{
			System.out.println("\nIngen forslag..\n");
		}
		
	}
	
	//Legger til en ny toglinje fra fil
	private void addTrack(Track track)
	{
		if(count == tracksInSystem.length)
		{
			extendTracks();
		}
		tracksInSystem[count] = track;
		count++;
		
	}
	
	//Hjelpemetode
	private void extendTracks()
	{
		int newLength = tracksInSystem.length * 2;
		
		Track[] temp = new Track[newLength];
		
		for(int i = 0; i < count; i++)
		{
			temp[i] = tracksInSystem[i];
		}
		
		tracksInSystem = temp;
	}
	
	//Leser inn linjer og stasjoner fra fil, og oppretter arrays, hashmaps og pekere
	private void readFromFile()
	{
		try
		{
			Scanner fileReader = new Scanner(new File(FILENAME));
			
			int trackNumber =-1;
			
			Track track = null;//Deklareres utenfor løkken slik at man ikke trenger å lete den opp for å lage pekere i stasjonene
			
			while(fileReader.hasNextLine())
			{
				String line = fileReader.nextLine();
				
				if(line.contains("*Linje*"))
				{
					trackNumber = Integer.parseInt((line.split(" "))[1]);
					
					track = new Track(trackNumber);
					addTrack(track);
				}
				else if(trackNumber != -1)
				{
					String stationName = line.trim().replace('-', ' ');	
					
					if(stations.containsKey(stationName.toLowerCase()))
					{
						//Legger inn linje på eksisterende stasjon (i hashmapet)
						Station oldStation = stations.get(stationName.toLowerCase());
						oldStation.addTrack(track);
						
						//Legger inn stasjonen i linje
						track.addStation(oldStation);
					}
					else
					{
						//Legger inn ny stasjon i hashmapet
						Station newStation = new Station(track, stationName);
						stations.put(stationName.toLowerCase(), newStation);
						
						//Legger inn stasjonen i linje
						track.addStation(newStation);
					}
				}
			}
			
			fileReader.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
