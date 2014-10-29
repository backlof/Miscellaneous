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
	
	//Konstrukt�r
	public EneboligOgRekkehus(String adresse, int bruttoareal, int boareal, int antallRom, int eieform, int verditakst, int l�netakst,
								int bygg�r, String beskrivelse, Person n�v�rendeEier, int etasjer, int tomtSt�rrelse, boolean kjeller,
								boolean eietomt, boolean rekkehus)
	{
		super(adresse, bruttoareal, boareal, antallRom, eieform, verditakst, l�netakst, bygg�r, beskrivelse, n�v�rendeEier);
		floors = etasjer;
		property = tomtSt�rrelse;
		basement = kjeller;
		own = eietomt;
		chain = rekkehus;
	}
	
	public int getEtasjer()
	{
		return floors;
	}
	
	public int getTomtSt�rrelse()
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