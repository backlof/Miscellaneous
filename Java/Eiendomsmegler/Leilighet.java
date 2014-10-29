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
	
	//Konstruktør.
	public Leilighet(String adresse, int bruttoareal, int boareal, int antallRom, int eieform, int verditakst, int lånetakst,
						int byggår, String beskrivelse, Person nåværendeEier, int etasje, int fellesutgifter, boolean heis,
						boolean balkong)
	{
		super(adresse, bruttoareal, boareal, antallRom, eieform, verditakst, lånetakst, byggår, beskrivelse, nåværendeEier);
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
