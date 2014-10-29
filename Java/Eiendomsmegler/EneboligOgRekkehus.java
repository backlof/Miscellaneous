import java.io.Serializable;
/*
 * Subklasse for Bolig.
 * Skrevet av Peter
 * Siste versjon 7. mai
 */
public class EneboligOgRekkehus extends Bolig implements Serializable
{
	private static final long serialVersionUID = 5L;
	private int floors, property;
	private boolean basement, own, chain;
	
	//Konstruktør
	public EneboligOgRekkehus(String adresse, int bruttoareal, int boareal, int antallRom, int eieform, int verditakst, int lånetakst,
								int byggår, String beskrivelse, Person nåværendeEier, int etasjer, int tomtStørrelse, boolean kjeller,
								boolean eietomt, boolean rekkehus)
	{
		super(adresse, bruttoareal, boareal, antallRom, eieform, verditakst, lånetakst, byggår, beskrivelse, nåværendeEier);
		floors = etasjer;
		property = tomtStørrelse;
		basement = kjeller;
		own = eietomt;
		chain = rekkehus;
	}
	
	public int getEtasjer()
	{
		return floors;
	}
	
	public int getTomtStørrelse()
	{
		return property;
	}
	
	public boolean harKjeller()
	{
		return basement;
	}
	
	public boolean erEietomt()
	{
		return own;
	}
	
	public boolean erRekkehus()
	{
		return chain;
	}
}