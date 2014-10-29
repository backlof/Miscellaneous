import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;
/*
 * Klasse som inneholder main-metoden til programmet.
 * Starter hovedvinduet(som er limet i programmet).
 * Skrevet av Alexander og Peter
 * Siste versjon 16. mai
 */
public class Driver
{
	//Main-metode
	public static void main(String[] args)
	{
		try
		{
			 UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch(UnsupportedLookAndFeelException e)
		{
			JOptionPane.showMessageDialog(null, "Ingen støtte for utseende. Bruker operativsystemets forhåndsinnstillte utseende.",
											"UnsupportedLookAndFeelException", JOptionPane.ERROR_MESSAGE);
		}
		catch(ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Finner ikke klasse. Bruker operativsystemets forhåndsinnstillte utseende.",
											"ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
		}
		catch(InstantiationException e)
		{
			JOptionPane.showMessageDialog(null, "Bruker operativsystemets forhåndsinnstillte utseende.",
											"InstantiationException", JOptionPane.ERROR_MESSAGE);
		}
		catch(IllegalAccessException e)
		{
			JOptionPane.showMessageDialog(null, "Bruker operativsystemets forhåndsinnstillte utseende.",
											"IllegalAccessException", JOptionPane.ERROR_MESSAGE);
		}

		final Hovedvindu vindu = new Hovedvindu();
		vindu.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				vindu.skrivTilFil();
				System.exit(0);
			}
		});
	}
}