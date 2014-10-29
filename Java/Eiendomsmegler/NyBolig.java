import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
/*
 * Subklasse for Boligvindu. Brukes for å opprette nye boliger.
 * Skrevet av Alexander
 * Siste versjon 12. mai
 */
public class NyBolig extends Boligvindu
{
	private Register r;
	private Lytter lytter;
	private final int xSize = 640;
	private final int ySize = 465;
	
	//Konstruktør
	public NyBolig(Register register)
	{
		super("Oppretter ny bolig");
		lukkevindu();
		erNy();
		
		setSize(xSize, ySize);
		setLocationRelativeTo(null);
		setResizable(false);
		
		r = register;
		lytter = new Lytter();
		
		eneboligRadioButton.addActionListener(lytter);
		rekkehusRadioButton.addActionListener(lytter);
		leilighetRadioButton.addActionListener(lytter);
		avbrytButton.addActionListener(lytter);
		lagreButton.addActionListener(lytter);
		setVisible(true);
	}
	
	private void lagre()
	{
		//Brukes til å sjekke at byggår ikke settes i fremtiden
		int år = Calendar.getInstance().get(Calendar.YEAR);
		String navn = navnTextField.getText();
		String personAdresse = adresseTextField.getText();
		String email = emailTextField.getText();
		email.toLowerCase();
		String telefonString = telefonTextField.getText();
		String personNrString = personNrTextField.getText();
		String beskrivelse = beskrivelseTextArea.getText();
		String boligAdresse = boligAdresseTextField.getText();
		String verditakstString = verditakstTextField.getText();
		String lånetakstString = lånetakstTextField.getText();
		String byggårString = byggårTextField.getText();
		String romString = romTextField.getText();
		String boarealString = boarealTextField.getText();
		String bruttoarealString = bruttoarealTextField.getText();
		String størrelseFellesutgifterString = størrelseFellesutgifterTextField.getText();
		String etasjerEtasjeString = etasjerEtasjeTextField.getText();
		
		//Sjekker om feltene er tomme
		if(erTom(navn) || erTom(personAdresse) || erTom(email) || erTom(telefonString) || erTom(personNrString) ||
			erTom(boligAdresse) ||erTom(verditakstString) || erTom(lånetakstString) || erTom(byggårString) ||
			erTom(romString) ||	erTom(boarealString) || erTom(bruttoarealString) || erTom(størrelseFellesutgifterString) ||
			erTom(etasjerEtasjeString))
		{
			JOptionPane.showMessageDialog(null, "Fyll inn alle felter. Beskrivelse er frivillig.", "Tomme felter", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			boolean gyldig = true;
			int telefon = 0;
			int verditakst = 0;
			int lånetakst = 0;
			int byggår = 0;
			int rom = 0;
			int boareal = 0;
			int bruttoareal = 0;
			int størrelseFellesutgifter = 0;
			int etasjerEtasje = 0;
			long personNr = 1L;
			
			//Sjekker at personnummeret er på 11 tegn
			if(personNrString.length() != 11)
			{
				personNrTextField.setText("");
				gyldig = false;
			}
			
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
			
			//Sjekker at personnummer består av tall
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
			
			//Sjekker at verditakst består av tall
			try
			{
				verditakst = Integer.parseInt(verditakstString);
				if(verditakst < 0)
				{
					gyldig = false;
					verditakstTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				verditakstTextField.setText("");
			}
			
			//Sjekker at lånetakst består av tall
			try
			{
				lånetakst = Integer.parseInt(lånetakstString);
				if(lånetakst < 0)
				{
					gyldig = false;
					lånetakstTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				lånetakstTextField.setText("");
			}
			
			//Sjekker at byggår består av tall og er over 1000, men ikke over nåværende år
			try
			{
				byggår = Integer.parseInt(byggårString);
				if(byggår < 1000 || byggår > år)
				{
					gyldig = false;
					byggårTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				byggårTextField.setText("");
			}
			
			//Sjekker at antall rom består av tall
			try
			{
				rom = Integer.parseInt(romString);
				if(rom < 1)
				{
					gyldig = false;
					romTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				romTextField.setText("");
			}
			
			//Sjekker at boareal består av tall
			try
			{
				boareal = Integer.parseInt(boarealString);
				if(rom < 0)
				{
					gyldig = false;
					boarealTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				boarealTextField.setText("");
			}
			
			//Sjekker at bruttoareal består av tall
			try
			{
				bruttoareal = Integer.parseInt(bruttoarealString);
				if(bruttoareal < 0)
				{
					gyldig = false;
					bruttoarealTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				bruttoarealTextField.setText("");
			}
			
			//Sjekker at tomtestørrelse/fellesutgifter består av tall
			try
			{
				størrelseFellesutgifter = Integer.parseInt(størrelseFellesutgifterString);
				if(størrelseFellesutgifter < 0)
				{
					gyldig = false;
					størrelseFellesutgifterTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				størrelseFellesutgifterTextField.setText("");
			}
			
			//Sjekker at etasjer/i etasje består av tall
			try
			{
				etasjerEtasje = Integer.parseInt(etasjerEtasjeString);
				if(etasjerEtasje < 1)
				{
					gyldig = false;
					etasjerEtasjeTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				etasjerEtasjeTextField.setText("");
			}
			
			if(gyldig)
			{
				int eieform = 0;
				if(aksjeRadioButton.isSelected())
					eieform = 1;
				if(borettslagRadioButton.isSelected())
					eieform = 2;
				
				
				if(leilighetRadioButton.isSelected())
				{
					boolean balkong = true;
					if(kjellerBalkongNeiRadioButton.isSelected())
						balkong = false;
					boolean heis = true;
					if(festetomtHeisNeiRadioButton.isSelected())
						heis = false;
					
					Person nåværendeEier = new Person(navn, personAdresse, email, telefon, personNr);
					Bolig bolig = new Leilighet(boligAdresse, bruttoareal, boareal, rom, eieform, verditakst, lånetakst, byggår,
												beskrivelse, nåværendeEier, etasjerEtasje, størrelseFellesutgifter, heis, balkong);
					r.settInn(bolig);
					r.skrivTilFil();
					lukk();
				}
				else
				{
					boolean kjeller = true;
					if(kjellerBalkongNeiRadioButton.isSelected())
						kjeller = false;
					boolean eietomt = true;
					if(festetomtHeisNeiRadioButton.isSelected())
						eietomt = false;
					boolean rekkehus = false;
					if(rekkehusRadioButton.isSelected())
						rekkehus = true;
					
					Person nåværendeEier = new Person(navn, personAdresse, email, telefon, personNr);
					Bolig bolig = new EneboligOgRekkehus(boligAdresse, bruttoareal, boareal, rom, eieform, verditakst, lånetakst, byggår,
														beskrivelse, nåværendeEier, etasjerEtasje, størrelseFellesutgifter, kjeller,
														eietomt, rekkehus);
					r.settInn(bolig);
					r.skrivTilFil();
					lukk();
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Les pop-up forklaringene og fyll inn feltene som ble visket ut på nytt.",
												"Fyll inn på nytt", JOptionPane.ERROR_MESSAGE);
		}
				
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
	
	private class Lytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == eneboligRadioButton)
				erEneboligOgRekkehus();
			if(e.getSource() == rekkehusRadioButton)
				erEneboligOgRekkehus();
			if(e.getSource() == leilighetRadioButton)
				erLeilighet();
			if(e.getSource() == avbrytButton)
				lukk();
			if(e.getSource() == lagreButton)
				lagre();
		}
	}
}
