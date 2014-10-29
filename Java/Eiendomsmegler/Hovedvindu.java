import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.util.Vector;
import java.util.Observable;
import java.util.Observer;
import java.text.DecimalFormat;
/*
 * Hovedvinduet for programmet. Klassen knytter all funksjonalitet i programmet sammen.
 * Skrevet av Alexander og Peter
 * Siste versjon: 16. mai
 */
public class Hovedvindu extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Register register;
	private final int xSize = 1024 - 50;
	private final int ySize = 768;
	private final DecimalFormat tusen = new DecimalFormat(",###");//Brukes for å skrive ut store tall oversiktlig.
	private Container container;
	private JPanel hovedPanel, boligPanel, boligSøkPanel, boligTablePanel, boligButtonPanel, budPanel, budSøkPanel;
	private JPanel budTablePanel, budButtonPanel;
	private JComboBox boligSøkComboBox, budSøkComboBox;
	private JTextField boligSøkTextField, budSøkTextField;
	private JButton[] boligButtons, budButtons;
	private Vector<String> boligKolonner, budKolonner;
	private Vector<Vector<String>> boligRadData, budRadData;
	private JTable boligTable, budTable;
	private JScrollPane boligScrollPane, budScrollPane;
	private MyTableModel boligTableModel, budTableModel;
	private Lytter lytter;
	private RegisterObserver registerObs;
	private EnterListener enterListener;
	
	//Konstruktør
	public Hovedvindu()
	{
		super("BoligAdmin");
		lesFraFil();
		lytter = new Lytter();
		registerObs = new RegisterObserver();//private klasse i bunnen av filen
		enterListener = new EnterListener();//private klasse i bunnen av filen
		
		container = this.getContentPane();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Setter størrelse på vinduet når det ikke er maksimert.
		setSize(xSize, ySize);
		
		this.setLocationRelativeTo(null);
		
		//Initialiserer JPanels.
		hovedPanel = new JPanel(new GridLayout(2,1));
		boligPanel = new JPanel(new BorderLayout());
		boligSøkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		boligTablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		boligButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		budPanel = new JPanel(new BorderLayout());
		budSøkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		budTablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		budButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		
		//Initialiserer vectorene som inneholder tabelldata.
		boligKolonner = new Vector<String>();
		boligRadData = new Vector<Vector<String>>();
		budKolonner = new Vector<String>();
		budRadData = new Vector<Vector<String>>();
		
		//Legger inn kolonnenavn i kolonnevectorene.
		fyllInnBoligKolonner();
		fyllInnBudKolonner();
		
		//Initialiserer MyTableModel objektene med vectorer som parameter.
		boligTableModel = new MyTableModel(boligKolonner);
		budTableModel = new MyTableModel(budKolonner);
		
		//Legger inn raddata i radvectorene.
		nullstillBoligTable();
		nullstillBudTable();
		
		//Initialiserer JTable objektene, med MyTableModel som parameter.
		boligTable = new JTable(boligTableModel);
		budTable = new JTable(budTableModel);
		
		//Setter tabellene i scrolling.
		boligScrollPane = new JScrollPane(boligTable);
		budScrollPane = new JScrollPane(budTable);
		
		//Tilføyer at JTable'ene skal fylle JScrollPane.
		boligTable.setFillsViewportHeight(true);
		budTable.setFillsViewportHeight(true);
		
		//Setter størrelse på scrollefelt.
		boligTable.setPreferredScrollableViewportSize(new Dimension(xSize - 130,ySize - 545));
		budTable.setPreferredScrollableViewportSize(new Dimension(xSize - 130,ySize - 545));
		
		//Initialiserer comboboxer med alternativ.
		String[] boligsøk = {"Bolignummer", "Selger", "Gate"};
		boligSøkComboBox = new JComboBox(boligsøk);
		String[] budsøk = {"Budnummer", "Budgiver", "Gate"};
		budSøkComboBox = new JComboBox(budsøk);
		
		//Initialiserer tekstfelt
		boligSøkTextField = new JTextField(30);
		budSøkTextField = new JTextField(30);
		
		//Søkefeltene over tabellene.
		boligSøkPanel.add(boligSøkComboBox);
		boligSøkPanel.add(boligSøkTextField);
		boligPanel.add(boligSøkPanel, BorderLayout.NORTH);
		budSøkPanel.add(budSøkComboBox);
		budSøkPanel.add(budSøkTextField);
		budPanel.add(budSøkPanel, BorderLayout.NORTH);
		
		//Initialiserer flere JButtons som blir plasser under boligtabellen
		String[] boligButtonsNavn = {"Ny bolig", "Detaljer", "Fjern", "Nytt bud", "Vis bud", "Vis alle boliger"};
		boligButtons = new JButton[boligButtonsNavn.length];
		for(int i = 0; i < boligButtons.length; i++)
		{
			boligButtons[i] = new JButton("" + boligButtonsNavn[i]);
			boligButtonPanel.add(boligButtons[i]);
		}
		boligPanel.add(boligButtonPanel, BorderLayout.SOUTH);
		
		//Initialiserer flere JButtons som blir plasser under budtabellen
		String[] budButtonsNavn = {"Vis bud", "Øk bud", "Godta bud", "Slett utgåtte bud", "Vis alle bud"};
		budButtons = new JButton[budButtonsNavn.length];
		for(int i = 0; i < budButtons.length; i++)
		{
			budButtons[i] = new JButton("" + budButtonsNavn[i]);
			budButtonPanel.add(budButtons[i]);
		}
		budPanel.add(budButtonPanel, BorderLayout.SOUTH);
		
		//Legger border med tittel rundt boligfeltet og rundt budfeltet
		Border etched = BorderFactory.createEtchedBorder();
		Border boligBorder = BorderFactory.createTitledBorder(etched, "Boliger");
		boligPanel.setBorder(boligBorder);
		Border budBorder = BorderFactory.createTitledBorder(etched, "Aktive bud");
		budPanel.setBorder(budBorder);
		
		//Gir tabellene scrollefunksjon
		boligTablePanel.add(boligScrollPane);
		boligPanel.add(boligTablePanel, BorderLayout.CENTER);
		budTablePanel.add(budScrollPane);
		budPanel.add(budTablePanel, BorderLayout.CENTER);
		
		
		
		//Lyttere til knappene
		boligButtons[0].addActionListener(lytter);
		boligButtons[1].addActionListener(lytter);
		boligButtons[2].addActionListener(lytter);
		boligButtons[3].addActionListener(lytter);
		boligButtons[4].addActionListener(lytter);
		boligButtons[5].addActionListener(lytter);
		budButtons[0].addActionListener(lytter);
		budButtons[1].addActionListener(lytter);
		budButtons[2].addActionListener(lytter);
		budButtons[3].addActionListener(lytter);
		budButtons[4].addActionListener(lytter);
		
		register.addObserver(registerObs);//blir forklart over den private klassen lenger nede
		
		boligSøkTextField.addKeyListener(enterListener);//blir forklart over den private klassen lenger nede
		budSøkTextField.addKeyListener(enterListener);//blir forklart over den private klassen lenger nede
		
		//Tar i bruk metoden for å sette forklaringer på forskjellige komponenter.
		setToolTips();
		
		//Setter max-størrelse på hovedpanelet og adder hovedpanelet i containeren
		Dimension panelStørrelse = new Dimension((xSize - 50), (ySize - 50));
		hovedPanel.setPreferredSize(panelStørrelse);
		hovedPanel.add(boligPanel);
		hovedPanel.add(budPanel);
		container.add(hovedPanel);		
		
		//Gjør vinduet synlig
		this.setVisible(true);
	}
	
	public void skrivTilFil()
	{
		register.skrivTilFil();
	}
	
	//Legger til forklaringer ved knapper og søkefelt
	private void setToolTips()
	{
		boligSøkTextField.setToolTipText("Velg hva du skal søke på, skriv inn søkekriterie og trykk enter.");
		budSøkTextField.setToolTipText("Velg hva du skal søke på, skriv inn søkekriterie og trykk enter.");
		boligButtons[0].setToolTipText("Åpner nytt vindu for å opprette ny bolig.");
		boligButtons[1].setToolTipText("Marker en bolig i tabellen og trykk for å vise bolig i nytt vindu.");
		boligButtons[2].setToolTipText("Fjerner markert bolig fra registeret.");
		boligButtons[3].setToolTipText("Marker en bolig og trykk for å legge inn nytt bud på boligen.");
		boligButtons[4].setToolTipText("Marker en bolig og trykk for å vise alle budene i aktive bud.");
		boligButtons[5].setToolTipText("Nullstiller tabellen med boliger.");
		budButtons[0].setToolTipText("Marker et bud og trykk for å åpne et nytt vindu som viser budet");
		budButtons[1].setToolTipText("Marker et bud og trykk for å øke beløpet i budet, og sette ny frist.");
		budButtons[2].setToolTipText("Marker et bud og trykk for å godta budet og selge boligen.");
		budButtons[3].setToolTipText("Trykk for å fjerne budene som har utgått sin akseptfrist fra registeret.");
		budButtons[4].setToolTipText("Nullstiller tabellen med bud.");
	}
	
	//Gir kolonnenavn til boligtabellen
	private void fyllInnBoligKolonner()
	{
		boligKolonner.addElement("Bolignr.");
		boligKolonner.addElement("Status");
		boligKolonner.addElement("Type");
		boligKolonner.addElement("Adresse");
		boligKolonner.addElement("Selger");
		boligKolonner.addElement("Verditakst");
		boligKolonner.addElement("Lagt ut (dd/mm/åååå)");
	}
	
	//Gir kolonnenavn til budtabellen
	private void fyllInnBudKolonner()
	{
		budKolonner.addElement("Budnr.");
		budKolonner.addElement("Adresse");
		budKolonner.addElement("Beløp");
		budKolonner.addElement("Budgiver");
		budKolonner.addElement("Akseptfrist (dd/mm/åååå)");
	}
	
	//Lager et vectorobjekt for EN bolig. Blir brukt i nullstill tabell, og søkefunksjonene.
	public Vector<String> boligVector(Bolig bolig, int index)
	{
		Vector<String> vector = new Vector<String>();
		vector.addElement("" + bolig.getBoligNr());
		vector.addElement(bolig.toStringSolgt());
		if(bolig instanceof Leilighet)
			vector.addElement("Leilighet");
		else
		{
			if(((EneboligOgRekkehus) bolig).erRekkehus())
				vector.addElement("Rekkehus");
			else
				vector.addElement("Enebolig");
		}
		vector.addElement(bolig.getAdresse());
		vector.addElement(bolig.getSelgerNavn());
		vector.addElement(new String(tusen.format(bolig.getVerditakst())));
		vector.addElement(bolig.toStringDato());
		vector.addElement("" + index);
		return vector;
	}
	
	//Lager et vectorobjekt for ET bud. Blir brukt i nullstill tabell, og søkefunksjonene.
	private Vector<String> budVector(Bud bud, Bolig bolig, int boligindex, int budindex)
	{
		Vector<String> vector = new Vector<String>();
		vector.addElement("" + bud.getBudNr());
		vector.addElement(bolig.getAdresse());
		vector.addElement(new String(tusen.format(bud.getBeløp())));
		vector.addElement(bud.getBudgiverNavn());
		vector.addElement(bud.getAkseptfrist());
		vector.addElement("" + boligindex);
		vector.addElement("" + budindex);
		return vector;
	}
	
	//Går igjennom arkivet og fyller inn boligtabellen. Blir brukt ved oppstart og nullstilling av boligtabellen.
	private void nullstillBoligTable()
	{
		boligRadData.removeAllElements();
		for(int i = 0; i < register.size(); i++)
		{
			Vector<String> vector = new Vector<String>();
			Bolig bolig = register.get(i);
			vector = boligVector(bolig, i);
			boligRadData.addElement(vector);
		}
		boligTableModel.setRows(boligRadData);
		boligTableModel.fireTableDataChanged();
	}
	
	//Går igjennom arkivet og fyller inn budtabellen. Blir brukt ved oppstart og nullstilling av budtabellen.
	private void nullstillBudTable()
	{
		budRadData.removeAllElements();
		for(int i = 0; i < register.size(); i++)
		{
			Bolig bolig = register.get(i);
			Budarkiv budarkiv = bolig.getBud();
			for(int x = 0; x < budarkiv.size(); x++)
			{
				Vector<String> vector = new Vector<String>();
				Bud bud = budarkiv.get(x);
				vector = budVector(bud, bolig, i, x);
				budRadData.addElement(vector);
			}
		}
		budTableModel.setRows(budRadData);
		budTableModel.fireTableDataChanged();
	}
	
	//Leser registeret fra fil. Blir bare brukt ved oppstart
	private void lesFraFil()
	{
		try
		{
			ObjectInputStream innfil = new ObjectInputStream(new FileInputStream("register.dta"));
			register = (Register)innfil.readObject();
			innfil.close();
		}
		catch(ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null, "Finner ikke klasse. Oppretter et nytt boligregister.", "ClassNotFoundException",
											JOptionPane.ERROR_MESSAGE);
			register = new Register();
		}
		catch(FileNotFoundException fnfe)
		{
			JOptionPane.showMessageDialog(null, "Finner ikke lagringsfil. Oppretter et nytt boligregister.", "FileNotFoundException",
											JOptionPane.ERROR_MESSAGE);
			register = new Register();
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Feil ved innlesing. Oppretter et nytt boligregister.", "IOException",
											JOptionPane.ERROR_MESSAGE);
			register = new Register();
		}
	}
	
	//Funksjonaliteten til JButtons'ene under tabellene
	private class Lytter implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//"Ny Bolig"-knapp. Åpner nytt vindu for oppretting av bolig.
			if(e.getSource() == boligButtons[0])
			{
				final NyBolig nyBolig = new NyBolig(register);
			}
			
			//"Detaljer"-knapp. Åpner nytt vindu for å vise og redigere solgt eller usolgt bolig.
			if(e.getSource() == boligButtons[1])
			{
				int rad = boligTable.getSelectedRow();
				if(rad != -1)
				{
					Vector<String> vector = boligRadData.get(rad);
					int boligindex = Integer.parseInt(vector.get(7));
					Bolig bolig = register.get(boligindex);
					if(bolig.getSolgt())
					{
						Boligvindu visBolig = new VisSolgt(register, boligindex);
					}
					else
					{
						Boligvindu visBolig = new VisUsolgt(register, boligindex);
					}
				}
			}
			
			//"Fjern"-knapp. Kan fjerne en bolig. Funksjonen spør kontrollspørsmål først.
			if(e.getSource() == boligButtons[2])
			{
				int rad = boligTable.getSelectedRow();
				if(rad != -1)
				{
					int svar = JOptionPane.showConfirmDialog(null, "Er du sikker?", "Fjerne bolig", JOptionPane.YES_NO_OPTION);
					if(svar == JOptionPane.YES_OPTION)
					{
						Vector<String> vector = boligRadData.get(rad);
						int index = Integer.parseInt(vector.get(7));
						Bolig bolig = register.get(index);
						if(bolig.getSolgt())
						{
							JOptionPane.showMessageDialog(null, "Kan ikke fjerne solgt bolig.", "Kan ikke fjernes",
															JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							register.fjernBolig(index);
						}
					}
				}
			}
			
			//"Nytt Bud"-knapp. Åpner nytt vindu for å legge til bud på en bolig.
			if(e.getSource() == boligButtons[3])
			{
				int rad = boligTable.getSelectedRow();
				if(rad != -1)
				{
					Vector<String> vector = boligRadData.get(rad);
					int boligindex = Integer.parseInt(vector.get(7));
					Bolig bolig = register.get(boligindex);
					if(bolig.getSolgt())
					{
						JOptionPane.showMessageDialog(null, "Boligen er solgt. Kan ikke legge inn bud.", "Kan ikke legge inn bud",
														JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						Budvindu nyttBud = new NyttBud(register, boligindex);
					}
				}
			}
			
			//"Vis bud"-knapp under boligtabell. Funksjon som viser BARE budene til den markerte boligen i budtabellen.
			if(e.getSource() == boligButtons[4])
			{
				int rad = boligTable.getSelectedRow();
				if(rad != -1)
				{
					Vector<String> boligvector = boligRadData.get(rad);
					int boligindex = Integer.parseInt(boligvector.get(7));
					budRadData.removeAllElements();
					Bolig bolig = register.get(boligindex);
					Budarkiv budarkiv = bolig.getBud();
					for(int x = 0; x < budarkiv.size(); x++)
					{
						Vector<String> vector = new Vector<String>();
						Bud bud = budarkiv.get(x);
						vector = budVector(bud, bolig, boligindex, x);
						budRadData.addElement(vector);
					}
					budTableModel.setRows(budRadData);
					budTableModel.fireTableDataChanged();
				}
			}
			
			//"Vis alle boliger"-knapp. Nullstiller tabell.
			if(e.getSource() == boligButtons[5])
			{
				nullstillBoligTable();
			}
			
			//"Vis bud"-knapp. Åpner et nytt vindu for å vise det markerte budet. Kan redigere kontaktinformasjon.
			if(e.getSource() == budButtons[0])
			{
				int rad = budTable.getSelectedRow();
				if(rad != -1)
				{
					Vector<String> vector = budRadData.get(rad);
					int boligindex = Integer.parseInt(vector.get(5));
					int budindex = Integer.parseInt(vector.get(6));
					Budvindu visBud = new VisBud(register, boligindex, budindex);
				}
			}
			
			//"Øk bud"-knapp. Åpner nytt vindu for å øke det markerte budet, og sette ny akseptfrist.
			if(e.getSource() == budButtons[1])
			{
				int rad = budTable.getSelectedRow();
				if(rad != -1)
				{
					Vector<String> vector = budRadData.get(rad);
					int boligindex = Integer.parseInt(vector.get(5));
					int budindex = Integer.parseInt(vector.get(6));
					Budvindu økBud = new ØkBud(register, boligindex, budindex);
				}
			}
			
			//"Godta bud"-knapp. Funksjon som godtar et bud og alt det medfører. Kontroll-spørsmål blir spurt først.
			if(e.getSource() == budButtons[2])
			{
				int rad = budTable.getSelectedRow();
				if(rad != -1)
				{
					Vector<String> vector = budRadData.get(rad);
					int boligindex = Integer.parseInt(vector.get(5));
					int budindex = Integer.parseInt(vector.get(6));
					int svar = JOptionPane.showConfirmDialog(null, "Er du sikker?", "Godta bud", JOptionPane.YES_NO_OPTION);
					if(svar == JOptionPane.YES_OPTION)
					{
						register.godtaBud(boligindex, budindex);
					}
				}
			}
			
			//"Slett utgåtte bud"-knapp. Går igjennom arkivet og fjerner bud som har utgått akseptfristen.
			if(e.getSource() == budButtons[3])
			{
				register.slettUtgåtteBud();
			}
			
			//"Vis alle bud"-knapp. Nullstiller budtabellen.
			if(e.getSource() == budButtons[4])
			{
				nullstillBudTable();
			}
		}
	}
	
	/*
	 * Lytter etter bruk av noen spesifikke metoder i Register, på register-objektet.
	 * Nullstiller tabellen når endringer blir gjort på registeret for å forsikre om at indexene i tabellene er riktige.
	 */
	private class RegisterObserver implements Observer
	{
		public void update(Observable obj, Object arg)
		{
			nullstillBudTable();
			nullstillBoligTable();
		}
	}
	
	/*
	 * Lytter etter entertrykking når man er i tekstfeltene over tabellene.
	 * Inneholder funksjonaliteten for søkingen.
	 */
	private class EnterListener implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode(); 
			if(key == KeyEvent.VK_ENTER)
			{
				//Når enterknappen blir trykket i boligsøkefeltet.
				if(e.getSource() == boligSøkTextField)
				{
					//Søker på bolignummer.
					if(boligSøkComboBox.getSelectedIndex() == 0)
					{
						try
						{
							int nummer = Integer.parseInt(boligSøkTextField.getText());
							boligRadData.removeAllElements();
							for(int i = 0; i < register.size(); i++)
							{
								Bolig bolig = register.get(i);
								if(nummer == bolig.getBoligNr())
								{
									Vector<String> vector = new Vector<String>();
									vector = boligVector(bolig, i);
									boligRadData.addElement(vector);
								}
							}
							boligTableModel.setRows(boligRadData);
							boligTableModel.fireTableDataChanged();
						}
						catch(NumberFormatException nfe)
						{
							JOptionPane.showMessageDialog(null, "Funksjonen tar bare imot tall.", "NumberFormatException",
															JOptionPane.ERROR_MESSAGE);
						}
					}
					
					//Søker på navnet til selgeren.
					if(boligSøkComboBox.getSelectedIndex() == 1)
					{
						boligRadData.removeAllElements();
						String søk = boligSøkTextField.getText();
						for(int i = 0; i < register.size(); i++)
						{
							Bolig bolig = register.get(i);
							String selger = bolig.getSelgerNavn();
							selger = selger.toLowerCase();
							søk = søk.toLowerCase();
							if(selger.matches(".*" + søk + ".*"))
							{
								Vector<String> vector = new Vector<String>();
								vector = boligVector(bolig, i);
								boligRadData.addElement(vector);
							}
						}
						boligTableModel.setRows(boligRadData);
						boligTableModel.fireTableDataChanged();
					}
					
					//Søker på boligadressen.
					if(boligSøkComboBox.getSelectedIndex() == 2)
					{
						boligRadData.removeAllElements();
						String søk = boligSøkTextField.getText();
						for(int i = 0; i < register.size(); i++)
						{
							Bolig bolig = register.get(i);
							String adresse = bolig.getAdresse();
							adresse = adresse.toLowerCase();
							søk = søk.toLowerCase();
							if(adresse.matches(".*" + søk + ".*"))
							{
								Vector<String> vector = new Vector<String>();
								vector = boligVector(bolig, i);
								boligRadData.addElement(vector);
							}
						}
						boligTableModel.setRows(boligRadData);
						boligTableModel.fireTableDataChanged();
					}
				}
				
				//Når enterknappen blir trykket i boligsøkefeltet.
				if(e.getSource() == budSøkTextField)
				{
					//Søker på b.
					if(budSøkComboBox.getSelectedIndex() == 0)
					{
						try
						{
							int nummer = Integer.parseInt(budSøkTextField.getText());
							budRadData.removeAllElements();
							for(int i = 0; i < register.size(); i++)
							{
								Bolig bolig = register.get(i);
								Budarkiv budarkiv = bolig.getBud();
								for(int x = 0; x < budarkiv.size(); x++)
								{	
									Bud bud = budarkiv.get(x);
									if(nummer == bud.getBudNr())
									{
										Vector<String> vector = new Vector<String>();
										vector = budVector(bud, bolig, i, x);
										budRadData.addElement(vector);
									}
								}
							}
							budTableModel.setRows(budRadData);
							budTableModel.fireTableDataChanged();
						}
						catch(NumberFormatException nfe)
						{
							JOptionPane.showMessageDialog(null, "Funksjonen tar bare imot tall.", "NumberFormatException",
									JOptionPane.ERROR_MESSAGE);
						}
					}
					
					//Søker på budgiver.
					if(budSøkComboBox.getSelectedIndex() == 1)
					{
						String søk = budSøkTextField.getText();
						budRadData.removeAllElements();
						for(int i = 0; i < register.size(); i++)
						{
							Bolig bolig = register.get(i);
							Budarkiv budarkiv = bolig.getBud();
							for(int x = 0; x < budarkiv.size(); x++)
							{
								Bud bud = budarkiv.get(x);
								String budgiver = bud.getBudgiverNavn();
								søk = søk.toLowerCase();
								budgiver = budgiver.toLowerCase();
								if(budgiver.matches(".*" + søk + ".*"))
								{
									Vector<String> vector = new Vector<String>();
									vector = budVector(bud, bolig, i, x);
									budRadData.addElement(vector);
								}
							}
						}
						budTableModel.setRows(budRadData);
						budTableModel.fireTableDataChanged();
					}
				}
				//Søker på boligadresse.
				if(budSøkComboBox.getSelectedIndex() == 2)
				{
					String søk = budSøkTextField.getText();
					budRadData.removeAllElements();
					for(int i = 0; i < register.size(); i++)
					{
						Bolig bolig = register.get(i);
						String adresse = bolig.getAdresse();
						søk = søk.toLowerCase();
						adresse = adresse.toLowerCase();
						if(adresse.matches(".*" + søk + ".*"))
						{
							Budarkiv budarkiv = bolig.getBud();
							for(int x = 0; x < budarkiv.size(); x++)
							{
								Bud bud = budarkiv.get(x);
								Vector<String> vector = new Vector<String>();
								vector = budVector(bud, bolig, i, x);
								budRadData.addElement(vector);
							}
						}
					}
					budTableModel.setRows(budRadData);
					budTableModel.fireTableDataChanged();
				}
			}
		}
		
		//Abstrakt-metode man er nødt til å implementere.
		public void keyReleased(KeyEvent e)
		{
		}
		
		//Abstrakt-metode man er nødt til å implementere.
		public void keyTyped(KeyEvent e)
		{
		}
	}
}