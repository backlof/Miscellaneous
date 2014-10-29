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
	private final DecimalFormat tusen = new DecimalFormat(",###");//Brukes for � skrive ut store tall oversiktlig.
	private Container container;
	private JPanel hovedPanel, boligPanel, boligS�kPanel, boligTablePanel, boligButtonPanel, budPanel, budS�kPanel;
	private JPanel budTablePanel, budButtonPanel;
	private JComboBox boligS�kComboBox, budS�kComboBox;
	private JTextField boligS�kTextField, budS�kTextField;
	private JButton[] boligButtons, budButtons;
	private Vector<String> boligKolonner, budKolonner;
	private Vector<Vector<String>> boligRadData, budRadData;
	private JTable boligTable, budTable;
	private JScrollPane boligScrollPane, budScrollPane;
	private MyTableModel boligTableModel, budTableModel;
	private Lytter lytter;
	private RegisterObserver registerObs;
	private EnterListener enterListener;
	
	//Konstrukt�r
	public Hovedvindu()
	{
		super("BoligAdmin");
		lesFraFil();
		lytter = new Lytter();
		registerObs = new RegisterObserver();//private klasse i bunnen av filen
		enterListener = new EnterListener();//private klasse i bunnen av filen
		
		container = this.getContentPane();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Setter st�rrelse p� vinduet n�r det ikke er maksimert.
		setSize(xSize, ySize);
		
		this.setLocationRelativeTo(null);
		
		//Initialiserer JPanels.
		hovedPanel = new JPanel(new GridLayout(2,1));
		boligPanel = new JPanel(new BorderLayout());
		boligS�kPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		boligTablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		boligButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		budPanel = new JPanel(new BorderLayout());
		budS�kPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
		
		//Tilf�yer at JTable'ene skal fylle JScrollPane.
		boligTable.setFillsViewportHeight(true);
		budTable.setFillsViewportHeight(true);
		
		//Setter st�rrelse p� scrollefelt.
		boligTable.setPreferredScrollableViewportSize(new Dimension(xSize - 130,ySize - 545));
		budTable.setPreferredScrollableViewportSize(new Dimension(xSize - 130,ySize - 545));
		
		//Initialiserer comboboxer med alternativ.
		String[] boligs�k = {"Bolignummer", "Selger", "Gate"};
		boligS�kComboBox = new JComboBox(boligs�k);
		String[] buds�k = {"Budnummer", "Budgiver", "Gate"};
		budS�kComboBox = new JComboBox(buds�k);
		
		//Initialiserer tekstfelt
		boligS�kTextField = new JTextField(30);
		budS�kTextField = new JTextField(30);
		
		//S�kefeltene over tabellene.
		boligS�kPanel.add(boligS�kComboBox);
		boligS�kPanel.add(boligS�kTextField);
		boligPanel.add(boligS�kPanel, BorderLayout.NORTH);
		budS�kPanel.add(budS�kComboBox);
		budS�kPanel.add(budS�kTextField);
		budPanel.add(budS�kPanel, BorderLayout.NORTH);
		
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
		String[] budButtonsNavn = {"Vis bud", "�k bud", "Godta bud", "Slett utg�tte bud", "Vis alle bud"};
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
		
		boligS�kTextField.addKeyListener(enterListener);//blir forklart over den private klassen lenger nede
		budS�kTextField.addKeyListener(enterListener);//blir forklart over den private klassen lenger nede
		
		//Tar i bruk metoden for � sette forklaringer p� forskjellige komponenter.
		setToolTips();
		
		//Setter max-st�rrelse p� hovedpanelet og adder hovedpanelet i containeren
		Dimension panelSt�rrelse = new Dimension((xSize - 50), (ySize - 50));
		hovedPanel.setPreferredSize(panelSt�rrelse);
		hovedPanel.add(boligPanel);
		hovedPanel.add(budPanel);
		container.add(hovedPanel);		
		
		//Gj�r vinduet synlig
		this.setVisible(true);
	}
	
	public void skrivTilFil()
	{
		register.skrivTilFil();
	}
	
	//Legger til forklaringer ved knapper og s�kefelt
	private void setToolTips()
	{
		boligS�kTextField.setToolTipText("Velg hva du skal s�ke p�, skriv inn s�kekriterie og trykk enter.");
		budS�kTextField.setToolTipText("Velg hva du skal s�ke p�, skriv inn s�kekriterie og trykk enter.");
		boligButtons[0].setToolTipText("�pner nytt vindu for � opprette ny bolig.");
		boligButtons[1].setToolTipText("Marker en bolig i tabellen og trykk for � vise bolig i nytt vindu.");
		boligButtons[2].setToolTipText("Fjerner markert bolig fra registeret.");
		boligButtons[3].setToolTipText("Marker en bolig og trykk for � legge inn nytt bud p� boligen.");
		boligButtons[4].setToolTipText("Marker en bolig og trykk for � vise alle budene i aktive bud.");
		boligButtons[5].setToolTipText("Nullstiller tabellen med boliger.");
		budButtons[0].setToolTipText("Marker et bud og trykk for � �pne et nytt vindu som viser budet");
		budButtons[1].setToolTipText("Marker et bud og trykk for � �ke bel�pet i budet, og sette ny frist.");
		budButtons[2].setToolTipText("Marker et bud og trykk for � godta budet og selge boligen.");
		budButtons[3].setToolTipText("Trykk for � fjerne budene som har utg�tt sin akseptfrist fra registeret.");
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
		boligKolonner.addElement("Lagt ut (dd/mm/����)");
	}
	
	//Gir kolonnenavn til budtabellen
	private void fyllInnBudKolonner()
	{
		budKolonner.addElement("Budnr.");
		budKolonner.addElement("Adresse");
		budKolonner.addElement("Bel�p");
		budKolonner.addElement("Budgiver");
		budKolonner.addElement("Akseptfrist (dd/mm/����)");
	}
	
	//Lager et vectorobjekt for EN bolig. Blir brukt i nullstill tabell, og s�kefunksjonene.
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
	
	//Lager et vectorobjekt for ET bud. Blir brukt i nullstill tabell, og s�kefunksjonene.
	private Vector<String> budVector(Bud bud, Bolig bolig, int boligindex, int budindex)
	{
		Vector<String> vector = new Vector<String>();
		vector.addElement("" + bud.getBudNr());
		vector.addElement(bolig.getAdresse());
		vector.addElement(new String(tusen.format(bud.getBel�p())));
		vector.addElement(bud.getBudgiverNavn());
		vector.addElement(bud.getAkseptfrist());
		vector.addElement("" + boligindex);
		vector.addElement("" + budindex);
		return vector;
	}
	
	//G�r igjennom arkivet og fyller inn boligtabellen. Blir brukt ved oppstart og nullstilling av boligtabellen.
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
	
	//G�r igjennom arkivet og fyller inn budtabellen. Blir brukt ved oppstart og nullstilling av budtabellen.
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
			//"Ny Bolig"-knapp. �pner nytt vindu for oppretting av bolig.
			if(e.getSource() == boligButtons[0])
			{
				final NyBolig nyBolig = new NyBolig(register);
			}
			
			//"Detaljer"-knapp. �pner nytt vindu for � vise og redigere solgt eller usolgt bolig.
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
			
			//"Fjern"-knapp. Kan fjerne en bolig. Funksjonen sp�r kontrollsp�rsm�l f�rst.
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
			
			//"Nytt Bud"-knapp. �pner nytt vindu for � legge til bud p� en bolig.
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
			
			//"Vis bud"-knapp. �pner et nytt vindu for � vise det markerte budet. Kan redigere kontaktinformasjon.
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
			
			//"�k bud"-knapp. �pner nytt vindu for � �ke det markerte budet, og sette ny akseptfrist.
			if(e.getSource() == budButtons[1])
			{
				int rad = budTable.getSelectedRow();
				if(rad != -1)
				{
					Vector<String> vector = budRadData.get(rad);
					int boligindex = Integer.parseInt(vector.get(5));
					int budindex = Integer.parseInt(vector.get(6));
					Budvindu �kBud = new �kBud(register, boligindex, budindex);
				}
			}
			
			//"Godta bud"-knapp. Funksjon som godtar et bud og alt det medf�rer. Kontroll-sp�rsm�l blir spurt f�rst.
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
			
			//"Slett utg�tte bud"-knapp. G�r igjennom arkivet og fjerner bud som har utg�tt akseptfristen.
			if(e.getSource() == budButtons[3])
			{
				register.slettUtg�tteBud();
			}
			
			//"Vis alle bud"-knapp. Nullstiller budtabellen.
			if(e.getSource() == budButtons[4])
			{
				nullstillBudTable();
			}
		}
	}
	
	/*
	 * Lytter etter bruk av noen spesifikke metoder i Register, p� register-objektet.
	 * Nullstiller tabellen n�r endringer blir gjort p� registeret for � forsikre om at indexene i tabellene er riktige.
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
	 * Lytter etter entertrykking n�r man er i tekstfeltene over tabellene.
	 * Inneholder funksjonaliteten for s�kingen.
	 */
	private class EnterListener implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode(); 
			if(key == KeyEvent.VK_ENTER)
			{
				//N�r enterknappen blir trykket i boligs�kefeltet.
				if(e.getSource() == boligS�kTextField)
				{
					//S�ker p� bolignummer.
					if(boligS�kComboBox.getSelectedIndex() == 0)
					{
						try
						{
							int nummer = Integer.parseInt(boligS�kTextField.getText());
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
					
					//S�ker p� navnet til selgeren.
					if(boligS�kComboBox.getSelectedIndex() == 1)
					{
						boligRadData.removeAllElements();
						String s�k = boligS�kTextField.getText();
						for(int i = 0; i < register.size(); i++)
						{
							Bolig bolig = register.get(i);
							String selger = bolig.getSelgerNavn();
							selger = selger.toLowerCase();
							s�k = s�k.toLowerCase();
							if(selger.matches(".*" + s�k + ".*"))
							{
								Vector<String> vector = new Vector<String>();
								vector = boligVector(bolig, i);
								boligRadData.addElement(vector);
							}
						}
						boligTableModel.setRows(boligRadData);
						boligTableModel.fireTableDataChanged();
					}
					
					//S�ker p� boligadressen.
					if(boligS�kComboBox.getSelectedIndex() == 2)
					{
						boligRadData.removeAllElements();
						String s�k = boligS�kTextField.getText();
						for(int i = 0; i < register.size(); i++)
						{
							Bolig bolig = register.get(i);
							String adresse = bolig.getAdresse();
							adresse = adresse.toLowerCase();
							s�k = s�k.toLowerCase();
							if(adresse.matches(".*" + s�k + ".*"))
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
				
				//N�r enterknappen blir trykket i boligs�kefeltet.
				if(e.getSource() == budS�kTextField)
				{
					//S�ker p� b.
					if(budS�kComboBox.getSelectedIndex() == 0)
					{
						try
						{
							int nummer = Integer.parseInt(budS�kTextField.getText());
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
					
					//S�ker p� budgiver.
					if(budS�kComboBox.getSelectedIndex() == 1)
					{
						String s�k = budS�kTextField.getText();
						budRadData.removeAllElements();
						for(int i = 0; i < register.size(); i++)
						{
							Bolig bolig = register.get(i);
							Budarkiv budarkiv = bolig.getBud();
							for(int x = 0; x < budarkiv.size(); x++)
							{
								Bud bud = budarkiv.get(x);
								String budgiver = bud.getBudgiverNavn();
								s�k = s�k.toLowerCase();
								budgiver = budgiver.toLowerCase();
								if(budgiver.matches(".*" + s�k + ".*"))
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
				//S�ker p� boligadresse.
				if(budS�kComboBox.getSelectedIndex() == 2)
				{
					String s�k = budS�kTextField.getText();
					budRadData.removeAllElements();
					for(int i = 0; i < register.size(); i++)
					{
						Bolig bolig = register.get(i);
						String adresse = bolig.getAdresse();
						s�k = s�k.toLowerCase();
						adresse = adresse.toLowerCase();
						if(adresse.matches(".*" + s�k + ".*"))
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
		
		//Abstrakt-metode man er n�dt til � implementere.
		public void keyReleased(KeyEvent e)
		{
		}
		
		//Abstrakt-metode man er n�dt til � implementere.
		public void keyTyped(KeyEvent e)
		{
		}
	}
}