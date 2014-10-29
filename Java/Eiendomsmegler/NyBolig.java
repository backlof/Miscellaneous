import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
/*
 * Subklasse for Boligvindu. Brukes for � opprette nye boliger.
 * Skrevet av Alexander
 * Siste versjon 12. mai
 */
public class NyBolig extends Boligvindu
{
	private Register r;
	private Lytter lytter;
	private final int xSize = 640;
	private final int ySize = 465;
	
	//Konstrukt�r
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
		//Brukes til � sjekke at bygg�r ikke settes i fremtiden
		int �r = Calendar.getInstance().get(Calendar.YEAR);
		String navn = navnTextField.getText();
		String personAdresse = adresseTextField.getText();
		String email = emailTextField.getText();
		email.toLowerCase();
		String telefonString = telefonTextField.getText();
		String personNrString = personNrTextField.getText();
		String beskrivelse = beskrivelseTextArea.getText();
		String boligAdresse = boligAdresseTextField.getText();
		String verditakstString = verditakstTextField.getText();
		String l�netakstString = l�netakstTextField.getText();
		String bygg�rString = bygg�rTextField.getText();
		String romString = romTextField.getText();
		String boarealString = boarealTextField.getText();
		String bruttoarealString = bruttoarealTextField.getText();
		String st�rrelseFellesutgifterString = st�rrelseFellesutgifterTextField.getText();
		String etasjerEtasjeString = etasjerEtasjeTextField.getText();
		
		//Sjekker om feltene er tomme
		if(erTom(navn) || erTom(personAdresse) || erTom(email) || erTom(telefonString) || erTom(personNrString) ||
			erTom(boligAdresse) ||erTom(verditakstString) || erTom(l�netakstString) || erTom(bygg�rString) ||
			erTom(romString) ||	erTom(boarealString) || erTom(bruttoarealString) || erTom(st�rrelseFellesutgifterString) ||
			erTom(etasjerEtasjeString))
		{
			JOptionPane.showMessageDialog(null, "Fyll inn alle felter. Beskrivelse er frivillig.", "Tomme felter", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			boolean gyldig = true;
			int telefon = 0;
			int verditakst = 0;
			int l�netakst = 0;
			int bygg�r = 0;
			int rom = 0;
			int boareal = 0;
			int bruttoareal = 0;
			int st�rrelseFellesutgifter = 0;
			int etasjerEtasje = 0;
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
			
			//Sjekker at verditakst best�r av tall
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
			
			//Sjekker at l�netakst best�r av tall
			try
			{
				l�netakst = Integer.parseInt(l�netakstString);
				if(l�netakst < 0)
				{
					gyldig = false;
					l�netakstTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				l�netakstTextField.setText("");
			}
			
			//Sjekker at bygg�r best�r av tall og er over 1000, men ikke over n�v�rende �r
			try
			{
				bygg�r = Integer.parseInt(bygg�rString);
				if(bygg�r < 1000 || bygg�r > �r)
				{
					gyldig = false;
					bygg�rTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				bygg�rTextField.setText("");
			}
			
			//Sjekker at antall rom best�r av tall
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
			
			//Sjekker at boareal best�r av tall
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
			
			//Sjekker at bruttoareal best�r av tall
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
			
			//Sjekker at tomtest�rrelse/fellesutgifter best�r av tall
			try
			{
				st�rrelseFellesutgifter = Integer.parseInt(st�rrelseFellesutgifterString);
				if(st�rrelseFellesutgifter < 0)
				{
					gyldig = false;
					st�rrelseFellesutgifterTextField.setText("");
				}
			}
			catch(NumberFormatException nfe)
			{
				gyldig = false;
				st�rrelseFellesutgifterTextField.setText("");
			}
			
			//Sjekker at etasjer/i etasje best�r av tall
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
					
					Person n�v�rendeEier = new Person(navn, personAdresse, email, telefon, personNr);
					Bolig bolig = new Leilighet(boligAdresse, bruttoareal, boareal, rom, eieform, verditakst, l�netakst, bygg�r,
												beskrivelse, n�v�rendeEier, etasjerEtasje, st�rrelseFellesutgifter, heis, balkong);
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
					
					Person n�v�rendeEier = new Person(navn, personAdresse, email, telefon, personNr);
					Bolig bolig = new EneboligOgRekkehus(boligAdresse, bruttoareal, boareal, rom, eieform, verditakst, l�netakst, bygg�r,
														beskrivelse, n�v�rendeEier, etasjerEtasje, st�rrelseFellesutgifter, kjeller,
														eietomt, rekkehus);
					r.settInn(bolig);
					r.skrivTilFil();
					lukk();
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Les pop-up forklaringene og fyll inn feltene som ble visket ut p� nytt.",
												"Fyll inn p� nytt", JOptionPane.ERROR_MESSAGE);
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
