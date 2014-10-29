import java.io.Serializable;
/*
 * Klasse som definerer bud-objekt med metoder som gj�r det mulig � endre kontaktinformasjon p� budgiver og �ke bud.
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
	
	//Konstrukt�r
	public Bud(Person budgiver, int bel�p, Dato akseptfrist, boolean finansiering)
	{
		person = budgiver;
		size = bel�p;
		dato = akseptfrist;
		status = finansiering;
	}
	
	//Brukes f�r man setter objektet inn i registeret
	public void setBudNr(int budNr)
	{
		nummer = budNr;
	}

	public int getBudNr()
	{
		return nummer;
	}
	
	//Sjekker om et bud er utg�tt
	public Boolean harUtg�tt()
	{
		Dato n� = new Dato();
		return this.dato.f�r(n�);
	}
	
	public int getBel�p()
	{
		return size;
	}
	
	public void setBel�p(int bel�p)
	{
		size = bel�p;
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