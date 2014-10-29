import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * Subklasse for Boligvindu. Brukes for å vise solgte boliger.
 * Kan vise tidligere og nåværende eier, og endre kontaktinformasjon.
 * Viser salgsdato, registreringsdato og salgsbeløp.
 * Skrevet av Alexander
 * Siste versjon 15. mai
 */
public class VisSolgt extends Boligvindu
{
	private Register r;
	private Lytter lytter;
	private final int xSize = 640;
	private final int ySize = 530;
	private Bolig gammelBolig, nyBolig;
	
	//Konstruktør
	public VisSolgt(Register register, int boligindex)
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
		erSolgt();
		nåværendeEierRadioButton.setSelected(true);
		datoRegTextField.setText(gammelBolig.toStringDato());
		datoSolgtTextField.setText(gammelBolig.toStringSalgsdato());
		solgtForTextField.setText(tusen.format(gammelBolig.getSalgsbeløp()));
		
		
		lagreButton.addActionListener(lytter);
		avbrytButton.addActionListener(lytter);
		nåværendeEierRadioButton.addActionListener(lytter);
		tidligereEierRadioButton.addActionListener(lytter);
		
		setVisible(true);
	}
	
	private void setTidligereEier()
	{
		Person person = gammelBolig.getTidligereEier();
		navnTextField.setText(person.getNavn());
		adresseTextField.setText(person.getAdresse());
		emailTextField.setText(person.getMail());
		telefonTextField.setText("" + person.getTelefon());
	}
	
	private void setNåværendeEier()
	{
		Person person = gammelBolig.getNåværendeEier();
		navnTextField.setText(person.getNavn());
		adresseTextField.setText(person.getAdresse());
		emailTextField.setText(person.getMail());
		telefonTextField.setText("" + person.getTelefon());
	}
	
	private void lagre()
	{
		nyBolig = gammelBolig;
		String adresse = adresseTextField.getText();
		String email = emailTextField.getText();
		email.toLowerCase();
		String telefonString = telefonTextField.getText();
		String beskrivelse = beskrivelseTextArea.getText();
		
		if(erTom(adresse) || erTom(email) || erTom(telefonString))
		{
			JOptionPane.showMessageDialog(null, "Fyll inn adresse, email og telefon. Beskrivelse er frivillig.", "Tomme felter",
											JOptionPane.ERROR_MESSAGE);
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
			
			//Sjekker at telefonnummeret består av tall
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
				if(nåværendeEierRadioButton.isSelected())
				{
					Person nåværendeEier = gammelBolig.getNåværendeEier();
					nåværendeEier.setAdresse(adresse);
					nåværendeEier.setMail(email);
					nåværendeEier.setTelefon(telefon);
					nyBolig.setBeskrivelse(beskrivelse);
					nyBolig.setNåværendeEier(nåværendeEier);
					if(!(r.erstattBolig(gammelBolig, nyBolig)))
						JOptionPane.showMessageDialog(null, "Finner ikke bolig. Den kan ha blitt fjernet eller forandret underveis. Prøv på nytt.",
														"Finner ikke bolig", JOptionPane.ERROR_MESSAGE);
					lukk();
				}
				if(tidligereEierRadioButton.isSelected())
				{
					Person tidligereEier = gammelBolig.getTidligereEier();
					tidligereEier.setAdresse(adresse);
					tidligereEier.setMail(email);
					tidligereEier.setTelefon(telefon);
					nyBolig.setBeskrivelse(beskrivelse);
					nyBolig.setTidligereEier(tidligereEier);
					if(!(r.erstattBolig(gammelBolig, nyBolig)))
						JOptionPane.showMessageDialog(null, "Finner ikke bolig. Den kan ha blitt fjernet eller forandret underveis. Prøv på nytt.",
														"Finner ikke bolig", JOptionPane.ERROR_MESSAGE);
					lukk();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Les pop-up forklaringene og fyll inn feltene som ble visket ut på nytt.",
												"Fyll inn på nytt", JOptionPane.ERROR_MESSAGE);
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
			if(e.getSource() == tidligereEierRadioButton)
				setTidligereEier();
			if(e.getSource() == nåværendeEierRadioButton)
				setNåværendeEier();
		}
	}
}