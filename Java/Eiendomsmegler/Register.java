import java.util.ArrayList;
import java.util.Observable;
import java.io.ObjectOutputStream;
import java.io.NotSerializableException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;
/*
 * Inneholder ArrayList med boliger. Fungerer som registeret i programmet
 * Inneholder bolignummer og budnummer som øker for hver gang de blir brukt.
 * Skrevet av Alexander
 * Siste versjon 14. mai
 */
public class Register extends Observable implements Serializable
{
	private static final long serialVersionUID = 2L;
	private ArrayList<Bolig> register;
	private int boligNr;
	private int budNr;
	
	//Konstruktør
	public Register()
	{
		register = new ArrayList<Bolig>(0);
		boligNr = 1;
		budNr = 1;
	}
	
	public int size()
	{
		return register.size();
	}
	
	public void settInn(Bolig bolig)
	{
		bolig.setBoligNr(boligNr);
		register.add(0, bolig);
		boligNr++;
		setChanged();
		notifyObservers(new String(""));
	}
	
	public void fjernBolig(int index)
	{
		register.remove(index);
		setChanged();
		notifyObservers(new String(""));
	}
	
	public int finnIndex(Bolig bolig)
	{
		return register.indexOf(bolig);
	}
	
	public boolean erstattBolig(Bolig gammelBolig, Bolig nyBolig)
	{
		int index = finnIndex(gammelBolig);
		if(index != -1)
		{
			register.set(index, nyBolig);
			setChanged();
			notifyObservers(new String(""));
			return true;
		}
		else
			return false;
	}
	
	public Bolig get(int index)
	{
		return register.get(index);
	}
	
	public boolean settInnBud(Bolig gammelBolig, Bud bud)
	{
		Bolig nyBolig = gammelBolig;
		bud.setBudNr(budNr);
		int index = finnIndex(gammelBolig);
		if(index != -1)
		{
			Budarkiv budarkiv = nyBolig.getBud();
			budarkiv.settInn(bud);
			if(this.erstattBolig(gammelBolig, nyBolig))
			{
				budNr++;
				setChanged();
				notifyObservers(new String(""));
				return true;
			}
		}
		return false;
	}
	
	public void godtaBud(int boligindex, int budindex)
	{
		Bolig bolig = register.get(boligindex);
		Budarkiv budarkiv = bolig.getBud();
		Bud godtattBud = budarkiv.get(budindex);
		if(godtattBud.getFinansiering())
		{
			if(godtattBud.harUtgått())
			{
				JOptionPane.showMessageDialog(null, "Kan ikke godta bud som har utgått akseptfristen.", "Utgått bud",
												JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				Person budgiver = godtattBud.getBudgiver();
				bolig.selg(budgiver, godtattBud);
				register.set(boligindex, bolig);
				setChanged();
				notifyObservers(new String(""));
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Kan ikke godta bud som ikke har godkjent finansiering.", "Ikke godkjent finansiering",
											JOptionPane.ERROR_MESSAGE);
	}
	
	public void slettUtgåtteBud()
	{
		for(int i = 0; i < size(); i++)
		{
			Bolig bolig = get(i);
			Budarkiv budarkiv = bolig.getBud();
			budarkiv.slettUtgåtte();
			setChanged();
			notifyObservers(new String(""));
		}
	}
	
	public boolean finnesPersonNr(long personNr)
	{
		for(int i = 0; i < size(); i++)
		{
			Bolig bolig = get(i);
			Budarkiv budarkiv = bolig.getBud();
			for(int x = 0; x < budarkiv.size(); x++)
			{
				Bud bud = budarkiv.get(x);
				if(bud.harPersonNr(personNr))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void skrivTilFil()
	{
		try
		{
			ObjectOutputStream utfil = new ObjectOutputStream(new FileOutputStream("register.dta"));
			utfil.writeObject(this);
		    utfil.flush();
		    utfil.close();
		}
		catch(NotSerializableException nse)
		{
			JOptionPane.showMessageDialog(null, "Kan ikke skrive fil." + "\n" + "Feilmelding:" + "\n" + nse.toString(),
											"NotSerializableException", JOptionPane.ERROR_MESSAGE);
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Kan ikke skrive fil." + "\n" + "Feilmelding:" + "\n" + ioe.toString(),
											"IOException", JOptionPane.ERROR_MESSAGE);
		}
	}
}