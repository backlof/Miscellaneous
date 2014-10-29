import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
/*
 * Abstrakt-klasse som definerer utseendet på budvinduene; øk bud, vis bud og nytt bud.
 * Inneholder metoder som redefinerer visse deler av innholdet for subklassene.
 * Skrevet av Alexander
 * Siste versjon: 14. mai
 */
public abstract class Budvindu extends JFrame
{
	protected JPanel southPanel, centerPanel, northPanel;
	protected JButton lagreButton, avbrytButton;
	protected JPanel budgiverGridPanel, navnPanel, adressePanel, emailPanel, telefonPanel, personNrPanel;
	protected JPanel navnLabelPanel, navnTextFieldPanel, adresseLabelPanel, adresseTextFieldPanel;
	protected JPanel emailLabelPanel, emailTextFieldPanel, telefonLabelPanel, telefonTextFieldPanel;
	protected JPanel personNrLabelPanel, personNrTextFieldPanel;
	protected JLabel navnLabel, adresseLabel, emailLabel, telefonLabel, personNrLabel;
	protected JTextField navnTextField, adresseTextField, emailTextField, telefonTextField, personNrTextField;
	protected JPanel budPanel, finansieringPanel, beløpPanel, akseptfristPanel, akseptfristBorderPanel;
	protected JPanel akseptfristWestPanel, akseptfristCenterPanel, dagerComboBoxPanel, dagerLabelPanel;
	protected JPanel timerPanel, timerLabelPanel, minutterPanel;
	protected JLabel dagerLabel, timerLabel;
	protected JCheckBox finansieringCheckBox;
	protected JTextField beløpTextField, timerTextField, minutterTextField, datoTextField;
	protected JComboBox dagerComboBox;
	protected Container container;
	private final DecimalFormat tusen = new DecimalFormat(",###");//Brukes i vis bud for å gjøre store tall enklere å lese.
	protected Border centerBorder, akseptfristBorder, beløpBorder, finansieringBorder, northBorder;
	
	//Konstruktør
	protected Budvindu(String tittel)
	{
		super(tittel);
		container = getContentPane();
		container.setLayout(new BorderLayout(0,0));
		
		setKomponenter();
		
		//Lager en ComboBox for å velge antall dager budet varer
		String[] boligsøk = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
		dagerComboBox = new JComboBox(boligsøk);
		dagerComboBoxPanel.add(dagerComboBox);
		
		adresseTextField.setToolTipText("Fyll inn gate og nummer. Bruk mellomrom.");
		emailTextField.setToolTipText("Fyll inn email på format: brukernavn@leverandør.landskode");
		telefonTextField.setToolTipText("Fyll inn telefonnummer på 8 tall.");
		finansieringCheckBox.setToolTipText("Sjekk denne boksen hvis budgiver har meddelt at finansieringen er i orden");
	}
	
	//Fyller inn komponentene i vinduet til Container.
	private void setKomponenter()
	{
		Border etched = BorderFactory.createEtchedBorder();
		
		southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
		container.add(southPanel, BorderLayout.SOUTH);
			lagreButton = new JButton("Lagre");
			southPanel.add(lagreButton);
			avbrytButton = new JButton("Avbryt");
			southPanel.add(avbrytButton);
		centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
		centerBorder = BorderFactory.createTitledBorder(etched, "Budgiver");
		centerPanel.setBorder(centerBorder);
		container.add(centerPanel, BorderLayout.CENTER);
			budgiverGridPanel = new JPanel(new GridLayout(5,1,0,8));
			centerPanel.add(budgiverGridPanel);
				navnPanel = new JPanel(new BorderLayout(10,0));
				budgiverGridPanel.add(navnPanel);
					navnLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
					navnPanel.add(navnLabelPanel, BorderLayout.WEST);
						navnLabel = new JLabel("Navn:");
						navnLabelPanel.add(navnLabel);
					navnTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
					navnPanel.add(navnTextFieldPanel, BorderLayout.CENTER);
						navnTextField = new JTextField(15);
						navnTextFieldPanel.add(navnTextField);
				adressePanel = new JPanel(new BorderLayout(10,0));
				budgiverGridPanel.add(adressePanel);
					adresseLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
					adressePanel.add(adresseLabelPanel, BorderLayout.WEST);
						adresseLabel = new JLabel("Adresse:");
						adresseLabelPanel.add(adresseLabel);
					adresseTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
					adressePanel.add(adresseTextFieldPanel, BorderLayout.CENTER);
						adresseTextField = new JTextField(15);
						adresseTextFieldPanel.add(adresseTextField);
				emailPanel = new JPanel(new BorderLayout(10,0));
				budgiverGridPanel.add(emailPanel);
					emailLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
					emailPanel.add(emailLabelPanel, BorderLayout.WEST);
						emailLabel = new JLabel("E-mail:");
						emailLabelPanel.add(emailLabel);
					emailTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
					emailPanel.add(emailTextFieldPanel, BorderLayout.CENTER);
						emailTextField = new JTextField(15);
						emailTextFieldPanel.add(emailTextField);
				telefonPanel = new JPanel(new BorderLayout(10,0));
				budgiverGridPanel.add(telefonPanel);
					telefonLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
					telefonPanel.add(telefonLabelPanel, BorderLayout.WEST);
						telefonLabel = new JLabel("Telefon:");
						telefonLabelPanel.add(telefonLabel);
					telefonTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
					telefonPanel.add(telefonTextFieldPanel, BorderLayout.CENTER);
						telefonTextField = new JTextField(15);
						telefonTextFieldPanel.add(telefonTextField);
				personNrPanel = new JPanel(new BorderLayout(10,0));
				budgiverGridPanel.add(personNrPanel);
					personNrLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
					personNrPanel.add(personNrLabelPanel, BorderLayout.WEST);
						personNrLabel = new JLabel("Personnr.:");
						personNrLabelPanel.add(personNrLabel);
					personNrTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
					personNrPanel.add(personNrTextFieldPanel, BorderLayout.CENTER);
						personNrTextField = new JTextField(15);
						personNrTextFieldPanel.add(personNrTextField);
			northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
			northBorder = BorderFactory.createTitledBorder(etched, "Bud");
			northPanel.setBorder(northBorder);
			container.add(northPanel, BorderLayout.NORTH);
				budPanel = new JPanel(new BorderLayout(0,0));
				northPanel.add(budPanel);
					finansieringPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
					finansieringBorder = BorderFactory.createTitledBorder(etched, "Finansiering");
					finansieringPanel.setBorder(finansieringBorder);
					budPanel.add(finansieringPanel, BorderLayout.CENTER);
						finansieringCheckBox = new JCheckBox("Bekreftet finansiering");
						finansieringPanel.add(finansieringCheckBox);
					beløpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
					beløpBorder = BorderFactory.createTitledBorder(etched, "Beløp");
					beløpPanel.setBorder(beløpBorder);
					budPanel.add(beløpPanel, BorderLayout.EAST);
						beløpTextField = new JTextField(8);
						beløpPanel.add(beløpTextField);
					akseptfristPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
					akseptfristBorder = BorderFactory.createTitledBorder(etched, "Akseptfrist");
					akseptfristPanel.setBorder(akseptfristBorder);
					budPanel.add(akseptfristPanel, BorderLayout.SOUTH);
						akseptfristBorderPanel = new JPanel(new BorderLayout(5,0));
						//Legg til akseptfristBorderPanel under øk/nytt bud, og en JTextField i vis bud for vising av akseptfrist.
							akseptfristWestPanel = new JPanel(new BorderLayout(5,0));
							akseptfristBorderPanel.add(akseptfristWestPanel, BorderLayout.WEST);
								dagerComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
								akseptfristWestPanel.add(dagerComboBoxPanel, BorderLayout.WEST);
									//Initialiser i konstruktøren (med objekter)
									//Add til dagerComboBoxPanel
								dagerLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,3));
								akseptfristWestPanel.add(dagerLabelPanel, BorderLayout.EAST);
									dagerLabel = new JLabel("dag(er), klokken");
									dagerLabelPanel.add(dagerLabel);
							akseptfristCenterPanel = new JPanel(new BorderLayout(5,0));
							akseptfristBorderPanel.add(akseptfristCenterPanel, BorderLayout.CENTER);
								timerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
								akseptfristCenterPanel.add(timerPanel, BorderLayout.WEST);
									timerTextField = new JTextField(2);
									timerPanel.add(timerTextField);
								timerLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,2));
								akseptfristCenterPanel.add(timerLabelPanel, BorderLayout.CENTER);
									timerLabel = new JLabel(":");
									timerLabelPanel.add(timerLabel);
								minutterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
								akseptfristCenterPanel.add(minutterPanel, BorderLayout.EAST);
									minutterTextField = new JTextField(2);
									minutterPanel.add(minutterTextField);
	}
	
	//Fellesinnhold for øk bud og nytt bud.
	private void ikkeVis()
	{
		akseptfristPanel.add(akseptfristBorderPanel);
		dagerComboBox.setToolTipText("Velg hvor mange dager akseptfristen er på.");
		timerTextField.setToolTipText("Akseptfristen kan ikke være tidligere på dagen enn 10(fra og med 10 til 24). Bruk tall.");
		minutterTextField.setToolTipText("Fyll inn minutt på gitt time(fra og med 0 til 60");
	}
	
	//Egendefinering for nytt bud vindu.
	protected void ny()
	{
		ikkeVis();
		beløpTextField.setToolTipText("Fyll inn beløp på budet. Tar bare imot tall");
		navnTextField.setToolTipText("Fyll inn navn på selger. Bruk store bokstaver.");
		personNrTextField.setToolTipText("Fyll inn personnummer på 11 tall. Sjekk at personnummeret er gyldig før registrering");
	}
	
	//Egendefinering for øk bud vindu.
	protected void øk(Bud gammeltBud, Person gammelBudgiver)
	{
		ikkeVis();
		setFelter(gammeltBud, gammelBudgiver);
		beløpTextField.setToolTipText("Fyll inn tall. Budet må øke med minst 10 000");
	}
	
	//Egendefinering for vis bud vindu.
	protected void vis(Bud gammeltBud, Person budgiver)
	{
		datoTextField = new JTextField(10);
		datoTextField.setText(gammeltBud.getAkseptfrist());
		datoTextField.setEditable(false);
		akseptfristPanel.add(datoTextField);
		setFelter(gammeltBud, budgiver);
		beløpTextField.setText("" + tusen.format(gammeltBud.getBeløp()));
		beløpTextField.setEditable(false);
		harFinansiering(gammeltBud.getFinansiering());
	}
	
	//Fyller inn innholdet til et bud i feltene.
	private void setFelter(Bud gammeltBud, Person budgiver)
	{
		beløpTextField.setText("" + gammeltBud.getBeløp());
		navnTextField.setText(budgiver.getNavn());
		adresseTextField.setText(budgiver.getAdresse());
		emailTextField.setText(budgiver.getMail());
		telefonTextField.setText("" + budgiver.getTelefon());
		personNrTextField.setEditable(false);
		navnTextField.setEditable(false);
	}
	
	//Disabler finansiering checkbox hvis den har vært krysset av tidligere. Blir brukt i vis bud vinduet.
	private void harFinansiering(Boolean finansiering)
	{
		if(finansiering)
		{
			finansieringCheckBox.setSelected(true);
			finansieringCheckBox.setEnabled(false);
		}
	}
	
	//Hjelpemetode for å sjekke om textfeltene er tomme før lagring.
	protected boolean erTom(String stringToCheck)
	{
		if(stringToCheck.equals(""))
			return true;
		else
			return false;
	}
	
	//Hjelpemetode for å sjekke om e-mail er skrevet inn på riktig format.
	protected boolean erEmail(String stringToCheck)
	{
		if(stringToCheck.matches("[A-Za-zæøåÆØÅ0-9._-]+@[A-Za-zæøåÆØÅ0-9.-]+.[A-Za-z]{2,4}"))
			return true;
		else
			return false;
	}
}
