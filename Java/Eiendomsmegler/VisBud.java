import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/*
 * Subklasse for Budvindu. Kan endre kontaktinformasjon og sette finansieringen godkjent.
 * Skrevet av Peter
 * Siste versjon 14. mai
 */
public class VisBud extends Budvindu
{
	private Register r;
	private final int xSize = 305;
	private final int ySize = 390;
	private Lytter lytter;
	private Bolig gammelBolig, nyBolig;
	private Bud gammeltBud, nyttBud;
	private Person budgiver;
	private Budarkiv budarkiv;
	
	//Konstruktør
	public VisBud(Register register, int boligindex, int budindex)
	{
		super("Vis bud");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		r = register;
		lytter = new Lytter();
		gammelBolig = r.get(boligindex);
		budarkiv = gammelBolig.getBud();
		gammeltBud = budarkiv.get(budindex);
		
		budgiver = gammeltBud.getBudgiver();
		
		vis(gammeltBud, budgiver);
		
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
		/*
		String beløpString = beløpTextField.getText();
		int dager = (dagerComboBox.getSelectedIndex()) + 1;
		String timerString = timerTextField.getText();
		String minutterString = minutterTextField.getText();
		*/
		String adresse = adresseTextField.getText();
		String email = emailTextField.getText();
		email.toLowerCase();
		String telefonString = telefonTextField.getText();
		
		//Sjekker om feltene er tomme
		if(erTom(adresse) || erTom(email) || erTom(telefonString))
		{
			JOptionPane.showMessageDialog(null, "Fyll inn alle felter.", "Tomme felter", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			boolean gyldig = true;
			int telefon = 0;
			
			//Sjekker at emailen er skrevet på gyldig format
			if(!(erEmail(email)))
			{
				emailTextField.setText("");
				gyldig = false;
			}
			
			//Sjekker at telefonnummeret er på 8 tegn
			if(telefonString.length() != 8)
			{
				telefonTextField.setText("");
				gyldig = false;
			}
			
			//Sjekker om telefonnummeret består av BARE tall
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
				JOptionPane.showMessageDialog(null, "Les pop-up forklaringene og fyll inn feltene som ble visket ut på nytt.",
												"Fyll inn på nytt", JOptionPane.ERROR_MESSAGE);
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
