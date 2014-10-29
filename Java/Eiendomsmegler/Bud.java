import java.io.Serializable;
/*
 * Klasse som definerer bud-objekt med metoder som gjør det mulig å endre kontaktinformasjon på budgiver og øke bud.
 * Skrevet av Peter
 * Siste versjon: 7. mai
 */
public class Bud implements Serializable
{
	private static final long serialVersionUID = 7L;
	private Person person;
	private int nummer;
	private int size;
	private Dato dato;
	private boolean status;
	
	//Konstruktør
	public Bud(Person budgiver, int beløp, Dato akseptfrist, boolean finansiering)
	{
		person = budgiver;
		size = beløp;
		dato = akseptfrist;
		status = finansiering;
	}
	
	//Brukes før man setter objektet inn i registeret
	public void setBudNr(int budNr)
	{
		nummer = budNr;
	}

	public int getBudNr()
	{
		return nummer;
	}
	
	//Sjekker om et bud er utgått
	public Boolean harUtgått()
	{
		Dato nå = new Dato();
		return this.dato.før(nå);
	}
	
	public int getBeløp()
	{
		return size;
	}
	
	public void setBeløp(int beløp)
	{
		size = beløp;
	}
	
	public String getAkseptfrist()
	{
		return dato.toBudString();
	}
	
	public void setAkseptfrist(Dato akseptfrist)
	{
		dato = akseptfrist;
	}
	
	public Person getBudgiver()
	{
		return person;
	}
	
	public void setBudgiver(Person nyBudgiver)
	{
		person = nyBudgiver;
	}
	
	public String getBudgiverNavn()
	{
		return person.getNavn();
	}
	
	public void setFinansiering(Boolean finansiering)
	{
		status = finansiering;
	}
	
	public boolean getFinansiering()
	{
		return status;
	}
	
	public boolean harPersonNr(long personNr)
	{
		if(person.sammePersonNr(personNr))
			return true;
		else
			return false;
	}
}