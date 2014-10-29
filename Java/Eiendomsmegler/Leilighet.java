import java.io.Serializable;
/*
 * Subklasse for Bolig. Definerer leiligheter.
 * Skrevet av Alexander
 * Siste versjon 7. mai
 */
public class Leilighet extends Bolig implements Serializable
{
	private static final long serialVersionUID = 6L;
	private int floor, commons;
	private boolean elevator, balcony;
	
	//Konstrukt�r.
	public Leilighet(String adresse, int bruttoareal, int boareal, int antallRom, int eieform, int verditakst, int l�netakst,
						int bygg�r, String beskrivelse, Person n�v�rendeEier, int etasje, int fellesutgifter, boolean heis,
						boolean balkong)
	{
		super(adresse, bruttoareal, boareal, antallRom, eieform, verditakst, l�netakst, bygg�r, beskrivelse, n�v�rendeEier);
		floor = etasje;
		commons = fellesutgifter;
		elevator = heis;
		balcony = balkong;
	}
	
	public int getEtasje()
	{
		return floor;
	}
	
	public int getFellesutgifer()
	{
		return commons;
	}
	
	public boolean harHeis()
	{
		return elevator;
	}
	
	public boolean harBalkong()
	{
		return balcony;
	}
}
