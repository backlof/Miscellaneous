
public class Track
{
	private int trackNumber;
	
	public Station[] stationsOnTrack;
	
	public int count = 0;
	
	private static final int INITIAL_ARRAY_SIZE = 8;
	
	public Track(int trackNumber)
	{
		this.trackNumber = trackNumber;
		stationsOnTrack = new Station[INITIAL_ARRAY_SIZE];
	}	
	
	//Legger til en stasjon (peker)
	public void addStation(Station newStation)
	{
		if(count == stationsOnTrack.length)
		{
			extendStations();
		}
		
		stationsOnTrack[count] = newStation;
		count++;
	}
	
	//Hjelpemetode
	private void extendStations()
	{
		int newLength = stationsOnTrack.length * 2;
		
		Station[] temp = new Station[newLength];
		
		for(int i = 0; i < count; i++)
		{
			temp[i] = stationsOnTrack[i];
			
		}
		stationsOnTrack = temp;
	}
	
	public String getTrack()
	{
		return getType() + " linje " + trackNumber;
	}
	
	private boolean isSubway()
	{
		return (trackNumber < 10);
	}
	
	private String getType()
	{
		return isSubway() ? "T-bane" : "trikk";
	}
	
	public double waitTime()
	{
		return isSubway() ? 7.5 : 5.0;
	}
	
	public double stationTime()
	{
		return isSubway() ? 1.8 : 1.4;
	}
}
