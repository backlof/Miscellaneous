import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * Subklasse for Boligvindu. Viser usolgte boliger og kan endre kontaktinformasjon og beskrivelse.
 * Skrevet av Alexander
 * Siste versjon 13. mai
 */
public class VisUsolgt extends Boligvindu
{
	private Register r;
	private Lytter lytter;
	private final int xSize = 640;
	private final int ySize = 465;
	private Bolig gammelBolig, nyBolig;
	
	//Konstrukt�r
	public VisUsolgt(Register register, int boligindex)
	{
		super("Detaljer");
		
		setSize(xSize, ySize);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		r = register;
		lytter = new Lytter();
		gammelBolig = r.get(boligindex);
		setInfo(gammelBolig);
		erIkkeNy();
		
		
		lagreButton.addActionListener(lytter);
		avbrytButton.addActionListener(lytter);
		
		setVisible(true);
	}
	
	private void lagre()
	{
		nyBolig = gammelBolig;
		String adresse = adresseTextField.getText();
		String email = emailTextField.getText();
		email.toLowerCase();
		String telefonString = telefonTextField.getText();
		String beskrivelse = beskrivelseTextArea.getText();
		
		//Sjekker om felter er tomme
		if(erTom(adresse) || erTom(email) || erTom(telefonString))
		{
			JOptionPane.showMessageDialog(null, "Fyll inn adresse, email og telefon. Beskrivelse er frivillig.", "Tomme felter",
											JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			boolean gyldig = true;
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
			
			//Sjekker at telefonnummeret best�r av tall
			try
			{
				telefon = Integer.parseInt(telefonString);
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				telefonTextField.setText("");
			}
			if(gyldig)
			{
				Person selger = gammelBolig.getN�v�rendeEier();
				selger.setAdresse(adresse);
				selger.setMail(email);
				selger.setTelefon(telefon);
				nyBolig.setBeskrivelse(beskrivelse);
				nyBolig.setN�v�rendeEier(selger);
				if(!(r.erstattBolig(gammelBolig, nyBolig)))
					JOptionPane.showMessageDialog(null, "Finner ikke bolig. Den kan ha blitt fjernet eller forandret underveis. Pr�v p� nytt.",
													"Finner ikke bolig", JOptionPane.ERROR_MESSAGE);
				lukk();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Les pop-up forklaringene og fyll inn feltene som ble visket ut p� nytt.",
												"Fyll inn p� nytt", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	
	private void lukk()
	{
		dispose();
	}
	
	private class Lytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == avbrytButton)
				lukk();
			if(e.getSource() == lagreButton)
				lagre();
		}
	}
}
