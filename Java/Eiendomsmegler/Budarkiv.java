import java.util.ArrayList;
import java.io.Serializable;
/*
 * Klasse som lagrer flere bud. Alle boliger inneholder et eget objekt av denne klassen.
 * Skrevet av Peter
 * Siste versjon: 14. mai
 */
public class Budarkiv implements Serializable
{
	private static final long serialVersionUID = 3L;
	private ArrayList<Bud> budarkiv;
	
	//Konstruktør
	public Budarkiv()
	{
		budarkiv = new ArrayList<Bud>(0);
	}
	
	//Returnerer størrelsen på ArrayList
	public int size()
	{
		return budarkiv.size();
	}
	
	//Setter bud inn i ArrayList
	public void settInn(Bud bud)
	{
		budarkiv.add(0, bud);
	}
	
	//Finner index til et bud
	public int finnIndex(Bud gammeltBud)
	{
		return budarkiv.indexOf(gammeltBud);
	}
	
	//Blir brukt for å fjerne alle budene etter salg.
	public void fjernAlleBud()
	{
		budarkiv.clear();
	}
	
	//Finner det eldre budet og erstatter det med det nye i parameteret
	public boolean erstattBud(Bud gammeltBud, Bud nyttBud)
	{
		int index = finnIndex(gammeltBud);
		if(index != -1)
		{
			budarkiv.set(index, nyttBud);
			return true;
		}
		else
			return false;
	}
	
	//Returnerer bud i index
	public Bud get(int index)
	{
		return budarkiv.get(index);
	}
	
	//Fjerner alle budene til en bolig som har utgått.
	public void slettUtgåtte()
	{
		int x = size();
		int i = 0;
		if(x != 0)
		{
			do
			{
				Bud b = budarkiv.get(i);
				if(b.harUtgått())
				{
					budarkiv.remove(i);
					x--;
				}
				else
				{
					i++;
				}	
			}
			while(x != 0 && i < x);
		}
	}
}
