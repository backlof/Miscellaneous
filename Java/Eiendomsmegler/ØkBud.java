import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/*
 * Subklasse for Budvindu. Kan �ke bel�pet til et bud, sette ny akseptfrist og endre kontaktinformasjon.
 * Skrevet av Alexander
 * Siste versjon 14. mai
 */
public class �kBud extends Budvindu
{
	private Register r;
	private final int xSize = 305;
	private final int ySize = 390;
	private Lytter lytter;
	private Bolig gammelBolig, nyBolig;
	private Bud gammeltBud, nyttBud;
	private Person budgiver;
	private Budarkiv budarkiv;
	
	//Konstrukt�r
	public �kBud(Register register, int boligindex, int budindex)
	{
		super("�k bud");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		r = register;
		lytter = new Lytter();
		gammelBolig = r.get(boligindex);
		budarkiv = gammelBolig.getBud();
		gammeltBud = budarkiv.get(budindex);
		
		budgiver = gammeltBud.getBudgiver();
		
		�k(gammeltBud, budgiver);
		
		setSize(xSize, ySize);
		setLocationRelativeTo(null);
		setResizable(false);
		
		avbrytButton.addActionListener(lytter);
		lagreButton.addActionListener(lytter);
		
		setVisible(true);
	}
	
	private void lukk()
	{
		dispose();
	}
	
	private void lagre()
	{
		nyBolig = gammelBolig;
		nyttBud = gammeltBud;
		boolean finansiering = finansieringCheckBox.isSelected();
		String bel�pString = bel�pTextField.getText();
		int dager = (dagerComboBox.getSelectedIndex()) + 1;
		String timerString = timerTextField.getText();
		String minutterString = minutterTextField.getText();
		String adresse = adresseTextField.getText();
		String email = emailTextField.getText();
		email.toLowerCase();
		String telefonString = telefonTextField.getText();
		
		if(erTom(adresse) || erTom(email) || erTom(telefonString))
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
				if(bel�p < (gammeltBud.getBel�p() + 10000))
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
			
			if(gyldig)
			{
				budgiver.setAdresse(adresse);
				budgiver.setMail(email);
				budgiver.setTelefon(telefon);
				nyttBud.setBudgiver(budgiver);
				nyttBud.setFinansiering(finansiering);
				nyttBud.setBel�p(bel�p);
				
				Dato akseptfrist = new Dato(dager, timer, minutter);
				nyttBud.setAkseptfrist(akseptfrist);
				
				if(budarkiv.erstattBud(gammeltBud, nyttBud))
				{
					nyBolig.setBud(budarkiv);
					if(r.erstattBolig(gammelBolig, nyBolig))
					{
						r.skrivTilFil();
						lukk();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Boligen har blitt slettet, kan ikke endre bud.", "Finner ikke bolig",
															JOptionPane.ERROR_MESSAGE);
						lukk();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Budet har blitt fjernet, kan ikke endre bud.", "Finner ikke bud",
													JOptionPane.ERROR_MESSAGE);
					lukk();
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
