import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/*
 * Subklasse for Budvindu. Brukes for � legge inn bud p� boliger.
 * Skrevet av Alexander
 * Siste versjon 14. mai
 */
public class NyttBud extends Budvindu
{
	private Register r;
	private final int xSize = 305;
	private final int ySize = 390;
	private Lytter lytter;
	private Bolig gammelBolig;
	
	//Konstrukt�r
	public NyttBud(Register register, int boligindex)
	{
		super("Oppretter nytt bud");
		ny();
		lukkevindu();
		r = register;
		lytter = new Lytter();
		
		gammelBolig = r.get(boligindex);
		
		setSize(xSize, ySize);
		this.setLocationRelativeTo(null);
		setResizable(false);
		
		avbrytButton.addActionListener(lytter);
		lagreButton.addActionListener(lytter);
		
		setVisible(true);
	}
	
	private void lukk()
	{
		dispose();
	}
	
	private void lukkevindu()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int svar = JOptionPane.showConfirmDialog(null, "Du har ikke lagret. Vil du fortsatt lukke vinduet?", "Er du sikker?",
														JOptionPane.YES_NO_OPTION);
				if(svar == JOptionPane.YES_OPTION)
					lukk();
					
			}
		});
	}
	
	private void lagre()
	{
		boolean finansiering = finansieringCheckBox.isSelected();
		String bel�pString = bel�pTextField.getText();
		int dager = (dagerComboBox.getSelectedIndex()) + 1;
		String timerString = timerTextField.getText();
		String minutterString = minutterTextField.getText();
		String navn = navnTextField.getText();
		String adresse = adresseTextField.getText();
		String email = emailTextField.getText();
		email.toLowerCase();
		String telefonString = telefonTextField.getText();
		String personNrString = personNrTextField.getText();
		
		//Sjekker om feltene er tomme
		if(erTom(timerString) || erTom(minutterString) || erTom(navn) ||erTom(adresse) || erTom(email) ||
				erTom(telefonString) || erTom(personNrString))
		{
			JOptionPane.showMessageDialog(null, "Fyll inn alle felter.", "Tomme felter", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			boolean gyldig = true;
			int bel�p = 0;
			int timer = 0;
			int minutter = 0;
			int telefon = 0;
			long personNr = 1L;
			
			//Sjekker at personnummeret er p� 11 tegn
			if(personNrString.length() != 11)
			{
				personNrTextField.setText("");
				gyldig = false;
			}
			
			//Sjekker at emailen er skrevet p� gyldig format
			if(!(erEmail(email)))
			{
				emailTextField.setText("");
				gyldig = false;
			}
			
			//Sjekker at telefonnummeret er p� 8 tegn
			if(telefonString.length() != 8)
			{
				telefonTextField.setText("");
				gyldig = false;
			}
			
			//Sjekker at bel�pet best�r av tall
			try
			{
				bel�p = Integer.parseInt(bel�pString);
				if(bel�p < 0)
				{
					gyldig = false;
					bel�pTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				bel�pTextField.setText("");
			}
			
			//Sjekker at timer best�r av tall
			try
			{
				timer = Integer.parseInt(timerString);
				if(timer < 10 || timer > 23)
				{
					gyldig = false;
					timerTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				timerTextField.setText("");
			}
			
			//Sjekker at minutter best�r av tall
			try
			{
				minutter = Integer.parseInt(minutterString);
				if(minutter < 0 || minutter > 59)
				{
					gyldig = false;
					minutterTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				minutterTextField.setText("");
			}
			
			//Sjekker at telefonnummeret best�r av tall
			try
			{
				telefon = Integer.parseInt(telefonString);
				if(telefon < 0)
				{
					gyldig = false;
					telefonTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				telefonTextField.setText("");
			}
			
			//Sjekker at personnummer best�r av tall
			try
			{
				personNr = Long.parseLong(personNrString);
				if(personNr < 0)
				{
					gyldig = false;
					personNrTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				personNrTextField.setText("");
			}
			
			if(gyldig)
			{
				if(r.finnesPersonNr(personNr))
				{
					JOptionPane.showMessageDialog(null, "Personen har et aktivt bud. Pr�v � fjerne utg�tte bud.",
													"Budgiver har allerede aktivt bud", JOptionPane.ERROR_MESSAGE);
					lukk();
				}
				else
				{
					Person budgiver = new Person(navn, adresse, email, telefon, personNr);
					Dato akseptfrist = new Dato(dager, timer, minutter);
					Bud nyttBud = new Bud(budgiver, bel�p, akseptfrist, finansiering);
					if(r.settInnBud(gammelBolig, nyttBud))
					{
						r.skrivTilFil();
						lukk();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Boligen har blitt slettet, kan ikke legge inn bud.", "Fyll inn p� nytt",
														JOptionPane.ERROR_MESSAGE);
						lukk();
					}
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Les pop-up forklaringene og fyll inn feltene som ble visket ut p� nytt.",
												"Fyll inn p� nytt", JOptionPane.ERROR_MESSAGE);
		}
				
	}
	
	private class Lytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == avbrytButton)
			{
				lukk();
			}
			if(e.getSource() == lagreButton)
			{
				lagre();
			}
		}
	}
}
