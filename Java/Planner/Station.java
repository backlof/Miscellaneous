
public class Station
{
	public String stationName;
	
	public Track[] tracksOnStation;//Pekere til linjene stasjonen er på
	
	private static final int INITIAL_ARRAY_SIZE = 4;
	
	public int count = 0;
	
	public Station(Track track, String stationName)
	{
		this.stationName = stationName;
		tracksOnStation = new Track[INITIAL_ARRAY_SIZE];
		addTrack(track);
	}
	
	//Legger til peker for linje
	public void addTrack(Track track)
	{
		if(count == tracksOnStation.length)
		{
			extendTracks();
		}
		
		tracksOnStation[count] = track;
		count++;
	}
	
	//Hjelpemetode
	private void extendTracks()
	{
		int newLength = tracksOnStation.length * 2;
		
		Track[] temp = new Track[newLength];
		
		for(int i = 0; i < count; i++)
		{
			temp[i] = tracksOnStation[i];
		}
		
		tracksOnStation = temp;
	}	
}
