import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
/*
 * Abstrakt-klasse som definerer utseendet p� vinduene; ny bolig og vis bolig (solgt og usolgt).
 * Inneholder metoder som gj�r forandringer p� utseendet av vinduet (brukes av subklassene).
 * Skrevet av Alexander og Peter
 * Siste versjon: 12. mai
 */
public abstract class Boligvindu extends JFrame
{
	protected final DecimalFormat tusen = new DecimalFormat(",###");//Brukes for � skrive ut store tall oversiktlig.
	protected JPanel hovedPanel, southPanel, westPanel, centerPanel;
	protected JButton lagreButton, avbrytButton;
	protected JPanel typePanel, eierPanel, beskrivelsePanel, beskrivelseFlowPanel;
	protected JRadioButton eneboligRadioButton, rekkehusRadioButton, leilighetRadioButton;
	protected JPanel eierBorderPanel, eierGridPanel, velgEierPanel;
	protected JRadioButton n�v�rendeEierRadioButton, tidligereEierRadioButton;
	protected JPanel navnPanel, adressePanel, emailPanel, telefonPanel, personNrPanel;
	protected JPanel navnLabelPanel, navnTextFieldPanel, adresseLabelPanel, adresseTextFieldPanel, emailLabelPanel;
	protected JPanel emailTextFieldPanel, telefonLabelPanel, telefonTextFieldPanel, personNrLabelPanel, personNrTextFieldPanel, TextFieldPanel;
	protected JLabel navnLabel, adresseLabel, emailLabel, telefonLabel, personNrLabel;
	protected JTextField navnTextField, adresseTextField, emailTextField, telefonTextField, personNrTextField;
	protected JTextArea beskrivelseTextArea;
	protected JPanel eieformPanel, boligPanel, tilleggPanel;
	protected JRadioButton selveieRadioButton, aksjeRadioButton, borettslagRadioButton;
	protected JPanel boligBorderPanel, boligTextFieldPanel, boligBoxPanel, solgtPanel;
	protected JPanel boligGridTextFieldPanel, boligAdressePanel, verditakstPanel, l�netakstPanel;
	protected JPanel boligAdresseLabelPanel, boligAdresseTextFieldPanel;
	protected JPanel verditakstLabelPanel, verditakstTextFieldPanel;
	protected JPanel l�netakstLabelPanel, l�netakstTextFieldPanel;
	protected JTextField boligAdresseTextField, verditakstTextField, l�netakstTextField;
	protected JLabel boligAdresseLabel, verditakstLabel, l�netakstLabel;
	protected JPanel boligBoxGridPanel, bygg�rPanel, romPanel, boarealPanel, bruttoarealPanel;
	protected JTextField bygg�rTextField, romTextField, boarealTextField, bruttoarealTextField;
	protected JPanel solgtBorderPanel, datoRegPanel, datoSolgtPanel, solgtForPanel;
	protected JTextField datoRegTextField, datoSolgtTextField, solgtForTextField;
	protected JPanel kjellerBalkongPanel, tomtHeisPanel, tilleggGridPanel, st�rrelseFellesutgifterPanel, etasjerEtasjePanel;
	protected JRadioButton kjellerBalkongJaRadioButton, kjellerBalkongNeiRadioButton;
	protected JRadioButton eietomtHeisJaRadioButton, festetomtHeisNeiRadioButton;
	protected JTextField st�rrelseFellesutgifterTextField, etasjerEtasjeTextField;
	protected JScrollPane beskrivelseScrollPane;
	protected ButtonGroup typeButtonGroup, eierButtonGroup, eieformButtonGroup, kjellerBalkongButtonGroup, tomtHeisButtonGroup;
	
	//Konstrukt�r
	protected Boligvindu(String tittel)
	{
		super(tittel);
		Container container = getContentPane();
		container.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		setKomponenter();
		container.add(hovedPanel);
	}
	
	//Legger inn vinduets komponenter i Container
	protected void setKomponenter()
	{
		Border etched = BorderFactory.createEtchedBorder();
		
		hovedPanel = new JPanel(new BorderLayout(0,0));
			southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
			hovedPanel.add(southPanel, BorderLayout.SOUTH);
				lagreButton = new JButton("Lagre");
				southPanel.add(lagreButton);
				avbrytButton = new JButton("Avbryt");
				southPanel.add(avbrytButton);
			westPanel = new JPanel(new BorderLayout(0,0));
			hovedPanel.add(westPanel, BorderLayout.WEST);
				typePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
				Border typeBorder = BorderFactory.createTitledBorder(etched, "Type");
				typePanel.setBorder(typeBorder);
				westPanel.add(typePanel, BorderLayout.NORTH);
					eneboligRadioButton = new JRadioButton("Enebolig");
					typePanel.add(eneboligRadioButton);
					rekkehusRadioButton = new JRadioButton("Rekkehus");
					typePanel.add(rekkehusRadioButton);
					leilighetRadioButton = new JRadioButton("Leilighet");
					typePanel.add(leilighetRadioButton);
				eierPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
				Border eierBorder = BorderFactory.createTitledBorder(etched, "Eier");
				eierPanel.setBorder(eierBorder);
				westPanel.add(eierPanel, BorderLayout.CENTER);
					eierBorderPanel = new JPanel(new BorderLayout(0,0));
					eierPanel.add(eierBorderPanel);
						eierGridPanel = new JPanel(new GridLayout(5,1,0,8));
						eierBorderPanel.add(eierGridPanel, BorderLayout.CENTER);
							navnPanel = new JPanel(new BorderLayout(10,0));
							eierGridPanel.add(navnPanel);
								navnLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
								navnPanel.add(navnLabelPanel, BorderLayout.WEST);
									navnLabel = new JLabel("Navn:");
									navnLabelPanel.add(navnLabel);
								navnTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
								navnPanel.add(navnTextFieldPanel, BorderLayout.CENTER);
									navnTextField = new JTextField(15);
									navnTextFieldPanel.add(navnTextField);
							adressePanel = new JPanel(new BorderLayout(10,0));
							eierGridPanel.add(adressePanel);
								adresseLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
								adressePanel.add(adresseLabelPanel, BorderLayout.WEST);
									adresseLabel = new JLabel("Adresse:");
									adresseLabelPanel.add(adresseLabel);
								adresseTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
								adressePanel.add(adresseTextFieldPanel, BorderLayout.CENTER);
									adresseTextField = new JTextField(15);
									adresseTextFieldPanel.add(adresseTextField);
							emailPanel = new JPanel(new BorderLayout(10,0));
							eierGridPanel.add(emailPanel);
								emailLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
								emailPanel.add(emailLabelPanel, BorderLayout.WEST);
									emailLabel = new JLabel("E-mail:");
									emailLabelPanel.add(emailLabel);
								emailTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
								emailPanel.add(emailTextFieldPanel, BorderLayout.CENTER);
									emailTextField = new JTextField(15);
									emailTextFieldPanel.add(emailTextField);
							telefonPanel = new JPanel(new BorderLayout(10,0));
							eierGridPanel.add(telefonPanel);
								telefonLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
								telefonPanel.add(telefonLabelPanel, BorderLayout.WEST);
									telefonLabel = new JLabel("Telefon:");
									telefonLabelPanel.add(telefonLabel);
								telefonTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
								telefonPanel.add(telefonTextFieldPanel, BorderLayout.CENTER);
									telefonTextField = new JTextField(15);
									telefonTextFieldPanel.add(telefonTextField);
							personNrPanel = new JPanel(new BorderLayout(10,0));
							eierGridPanel.add(personNrPanel);
								personNrLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
								personNrPanel.add(personNrLabelPanel, BorderLayout.WEST);
									personNrLabel = new JLabel("Personnr.:");
									personNrLabelPanel.add(personNrLabel);
								personNrTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
								personNrPanel.add(personNrTextFieldPanel, BorderLayout.CENTER);
									personNrTextField = new JTextField(15);
									personNrTextFieldPanel.add(personNrTextField);
						velgEierPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,0));
						Border velgEierBorder = BorderFactory.createTitledBorder(etched, "Velg eier");
						velgEierPanel.setBorder(velgEierBorder);
							n�v�rendeEierRadioButton = new JRadioButton("N�v�rende eier");
							velgEierPanel.add(n�v�rendeEierRadioButton);
							tidligereEierRadioButton = new JRadioButton("Tidligere eier");
							velgEierPanel.add(tidligereEierRadioButton);
				beskrivelsePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
				Border beskrivelseBorder = BorderFactory.createTitledBorder(etched, "Beskrivelse");
				beskrivelsePanel.setBorder(beskrivelseBorder);
				westPanel.add(beskrivelsePanel, BorderLayout.SOUTH);
					beskrivelseTextArea = new JTextArea(8,21);
					beskrivelseTextArea.setLineWrap(true);
					beskrivelseTextArea.setWrapStyleWord(true);
					beskrivelseScrollPane = new JScrollPane(beskrivelseTextArea);
					beskrivelsePanel.add(beskrivelseScrollPane);
			centerPanel = new JPanel(new BorderLayout(0,0));
			hovedPanel.add(centerPanel, BorderLayout.CENTER);
				eieformPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
				Border eieformBorder = BorderFactory.createTitledBorder(etched, "Eieform");
				eieformPanel.setBorder(eieformBorder);
				centerPanel.add(eieformPanel, BorderLayout.NORTH);
					selveieRadioButton = new JRadioButton("Selveie");
					eieformPanel.add(selveieRadioButton);
					aksjeRadioButton = new JRadioButton("Aksje");
					eieformPanel.add(aksjeRadioButton);
					borettslagRadioButton = new JRadioButton("Borettslag");
					eieformPanel.add(borettslagRadioButton);
				boligPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
				Border boligBorder = BorderFactory.createTitledBorder(etched, "Bolig");
				boligPanel.setBorder(boligBorder);
				centerPanel.add(boligPanel, BorderLayout.CENTER);
					boligBorderPanel = new JPanel(new BorderLayout(0,0));
					boligPanel.add(boligBorderPanel);
						boligTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
						boligBorderPanel.add(boligTextFieldPanel, BorderLayout.NORTH);
							boligGridTextFieldPanel = new JPanel(new GridLayout(3,1,0,10));
							boligTextFieldPanel.add(boligGridTextFieldPanel);
								boligAdressePanel = new JPanel(new BorderLayout(0,0));
								boligGridTextFieldPanel.add(boligAdressePanel);
									boligAdresseLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
									boligAdressePanel.add(boligAdresseLabelPanel, BorderLayout.WEST);
										boligAdresseLabel = new JLabel("Adresse:");
										boligAdresseLabelPanel.add(boligAdresseLabel);
									boligAdresseTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
									boligAdressePanel.add(boligAdresseTextFieldPanel, BorderLayout.CENTER);
										boligAdresseTextField = new JTextField(15);
										boligAdresseTextFieldPanel.add(boligAdresseTextField);
								verditakstPanel = new JPanel(new BorderLayout(0,0));
								boligGridTextFieldPanel.add(verditakstPanel);
									verditakstLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
									verditakstPanel.add(verditakstLabelPanel, BorderLayout.WEST);
										verditakstLabel = new JLabel("Verditakst:");
										verditakstLabelPanel.add(verditakstLabel);
									verditakstTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
									verditakstPanel.add(verditakstTextFieldPanel, BorderLayout.CENTER);
										verditakstTextField = new JTextField(15);
										verditakstTextFieldPanel.add(verditakstTextField);
								l�netakstPanel = new JPanel(new BorderLayout(0,0));
								boligGridTextFieldPanel.add(l�netakstPanel);
									l�netakstLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
									l�netakstPanel.add(l�netakstLabelPanel, BorderLayout.WEST);
										l�netakstLabel = new JLabel("L�netakst:");
										l�netakstLabelPanel.add(l�netakstLabel);
									l�netakstTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
									l�netakstPanel.add(l�netakstTextFieldPanel, BorderLayout.CENTER);
										l�netakstTextField = new JTextField(15);
										l�netakstTextFieldPanel.add(l�netakstTextField);
						boligBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
						boligBorderPanel.add(boligBoxPanel, BorderLayout.CENTER);
							boligBoxGridPanel = new JPanel(new GridLayout(1,4));
							boligBoxPanel.add(boligBoxGridPanel);
								bygg�rPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
								Border bygg�rBorder = BorderFactory.createTitledBorder(etched, "Bygg�r");
								bygg�rPanel.setBorder(bygg�rBorder);
								boligBoxGridPanel.add(bygg�rPanel);
									bygg�rTextField = new JTextField(5);
									bygg�rPanel.add(bygg�rTextField);
								romPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
								Border romBorder = BorderFactory.createTitledBorder(etched, "Rom");
								romPanel.setBorder(romBorder);
								boligBoxGridPanel.add(romPanel);
									romTextField = new JTextField(3);
									romPanel.add(romTextField);
								boarealPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
								Border boarealBorder = BorderFactory.createTitledBorder(etched, "Boareal");
								boarealPanel.setBorder(boarealBorder);
								boligBoxGridPanel.add(boarealPanel);
									boarealTextField = new JTextField(3);
									boarealPanel.add(boarealTextField);
								bruttoarealPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
								Border bruttoarealBorder = BorderFactory.createTitledBorder(etched, "Bruttoareal");
								bruttoarealPanel.setBorder(bruttoarealBorder);
								boligBoxGridPanel.add(bruttoarealPanel);
									bruttoarealTextField = new JTextField(3);
									bruttoarealPanel.add(bruttoarealTextField);
						solgtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
							solgtBorderPanel = new JPanel(new BorderLayout(0,0));
							solgtPanel.add(solgtBorderPanel);
								datoRegPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
								Border datoRegBorder = BorderFactory.createTitledBorder(etched, "Dato lagt ut");
								datoRegPanel.setBorder(datoRegBorder);
								solgtBorderPanel.add(datoRegPanel, BorderLayout.WEST);
									datoRegTextField = new JTextField(7);
									datoRegPanel.add(datoRegTextField);
								datoSolgtPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
								Border datoSolgtBorder = BorderFactory.createTitledBorder(etched, "Dato solgt");
								datoSolgtPanel.setBorder(datoSolgtBorder);
								solgtBorderPanel.add(datoSolgtPanel, BorderLayout.CENTER);
									datoSolgtTextField = new JTextField(7);
									datoSolgtPanel.add(datoSolgtTextField);
								solgtForPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
								Border solgtForBorder = BorderFactory.createTitledBorder(etched, "Solgt for");
								solgtForPanel.setBorder(solgtForBorder);
								solgtBorderPanel.add(solgtForPanel, BorderLayout.EAST);
									solgtForTextField = new JTextField(7);
									solgtForPanel.add(solgtForTextField);
				tilleggPanel = new JPanel(new BorderLayout(0,0));
				Border tilleggBorder = BorderFactory.createTitledBorder(etched, "Enebolig og Rekkehus");
				tilleggPanel.setBorder(tilleggBorder);
				centerPanel.add(tilleggPanel, BorderLayout.SOUTH);
					kjellerBalkongPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
					Border kjellerBalkongBorder = BorderFactory.createTitledBorder(etched, "Kjeller");
					kjellerBalkongPanel.setBorder(kjellerBalkongBorder);
					tilleggPanel.add(kjellerBalkongPanel, BorderLayout.WEST);
						kjellerBalkongJaRadioButton = new JRadioButton("Ja");
						kjellerBalkongPanel.add(kjellerBalkongJaRadioButton);
						kjellerBalkongNeiRadioButton = new JRadioButton("Nei");
						kjellerBalkongPanel.add(kjellerBalkongNeiRadioButton);
					tomtHeisPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
					Border tomtHeisBorder = BorderFactory.createTitledBorder(etched, "Tomt");
					tomtHeisPanel.setBorder(tomtHeisBorder);
					tilleggPanel.add(tomtHeisPanel, BorderLayout.CENTER);
						eietomtHeisJaRadioButton = new JRadioButton("Eietomt");
						tomtHeisPanel.add(eietomtHeisJaRadioButton);
						festetomtHeisNeiRadioButton = new JRadioButton("Festetomt");
						tomtHeisPanel.add(festetomtHeisNeiRadioButton);
					tilleggGridPanel = new JPanel(new GridLayout(1,2));
					tilleggPanel.add(tilleggGridPanel, BorderLayout.SOUTH);
						st�rrelseFellesutgifterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
						Border st�rrelseFellesutgifterBorder = BorderFactory.createTitledBorder(etched, "St�rrelse p� tomt");
						st�rrelseFellesutgifterPanel.setBorder(st�rrelseFellesutgifterBorder);
						tilleggGridPanel.add(st�rrelseFellesutgifterPanel);
							st�rrelseFellesutgifterTextField = new JTextField(5);
							st�rrelseFellesutgifterTextField.setToolTipText("Fyll inn st�rrelse p� tomt i kvadratmeter. Tar bare imot tall.");
							st�rrelseFellesutgifterPanel.add(st�rrelseFellesutgifterTextField);
						etasjerEtasjePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
						Border etasjerEtasjeBorder = BorderFactory.createTitledBorder(etched, "Etasjer");
						etasjerEtasjePanel.setBorder(etasjerEtasjeBorder);
						tilleggGridPanel.add(etasjerEtasjePanel);
							etasjerEtasjeTextField = new JTextField(5);
							etasjerEtasjeTextField.setToolTipText("Fyll inn antall etasjer boligen har. Tar bare imot tall.");
							etasjerEtasjePanel.add(etasjerEtasjeTextField);
	}
	
	//Gj�r innholdet i vinduet optimalisert for enebolig og rekkehus.
	protected void erEneboligOgRekkehus()
	{
		Border etched = BorderFactory.createEtchedBorder();
		Border tilleggBorder = BorderFactory.createTitledBorder(etched, "Enebolig og Rekkehus");
		tilleggPanel.setBorder(tilleggBorder);
		Border kjellerBalkongBorder = BorderFactory.createTitledBorder(etched, "Kjeller");
		kjellerBalkongPanel.setBorder(kjellerBalkongBorder);
		Border tomtHeisBorder = BorderFactory.createTitledBorder(etched, "Tomt");
		tomtHeisPanel.setBorder(tomtHeisBorder);
		Border st�rrelseFellesutgifterBorder = BorderFactory.createTitledBorder(etched, "St�rrelse p� tomt");
		st�rrelseFellesutgifterPanel.setBorder(st�rrelseFellesutgifterBorder);
		Border etasjerEtasjeBorder = BorderFactory.createTitledBorder(etched, "Etasjer");
		etasjerEtasjePanel.setBorder(etasjerEtasjeBorder);
		
		kjellerBalkongJaRadioButton.setText("Ja");
		kjellerBalkongNeiRadioButton.setText("Nei");
		
		eietomtHeisJaRadioButton.setText("Eietomt");
		festetomtHeisNeiRadioButton.setText("Festetomt");
		
		st�rrelseFellesutgifterTextField.setToolTipText("Fyll inn st�rrelse p� tomt i kvadratmeter. Tar bare imot tall.");
		etasjerEtasjeTextField.setToolTipText("Fyll inn antall etasjer boligen har. Tar bare imot tall.");
	}
	
	//Gj�r innholdet i vinduet optimalisert for leilighet.
	protected void erLeilighet()
	{
		Border etched = BorderFactory.createEtchedBorder();
		
		Border tilleggBorder = BorderFactory.createTitledBorder(etched, "Leilighet");
		tilleggPanel.setBorder(tilleggBorder);
		Border kjellerBalkongBorder = BorderFactory.createTitledBorder(etched, "Balkong");
		kjellerBalkongPanel.setBorder(kjellerBalkongBorder);
		Border tomtHeisBorder = BorderFactory.createTitledBorder(etched, "Heis");
		tomtHeisPanel.setBorder(tomtHeisBorder);
		Border st�rrelseFellesutgifterBorder = BorderFactory.createTitledBorder(etched, "Fellesutgifter");
		st�rrelseFellesutgifterPanel.setBorder(st�rrelseFellesutgifterBorder);
		Border etasjerEtasjeBorder = BorderFactory.createTitledBorder(etched, "I etasje");
		etasjerEtasjePanel.setBorder(etasjerEtasjeBorder);
		
		kjellerBalkongJaRadioButton.setText("Ja");
		kjellerBalkongNeiRadioButton.setText("Nei");
		
		eietomtHeisJaRadioButton.setText("Ja");
		festetomtHeisNeiRadioButton.setText("Nei");
		
		st�rrelseFellesutgifterTextField.setToolTipText("Fyll inn m�nedlige fellesutgifter for leiligheten. Tar bare imot tall.");
		etasjerEtasjeTextField.setToolTipText("Fyll inn hvilken etasje boligen ligger i. Tar bare imot tall.");
	}
	
	//Egendefinerte innstillinger for boliger som ikke er solgt.
	protected void erSolgt()
	{
		boligBorderPanel.add(solgtPanel, BorderLayout.SOUTH);
		eierBorderPanel.add(velgEierPanel, BorderLayout.SOUTH);
		eierButtonGroup = new ButtonGroup();
		eierButtonGroup.add(n�v�rendeEierRadioButton);
		eierButtonGroup.add(tidligereEierRadioButton);
		n�v�rendeEierRadioButton.setToolTipText("Velg hvilken eier du vil vise/endre kontaktinformasjon p�.");
		tidligereEierRadioButton.setToolTipText("Velg hvilken eier du vil vise/endre kontaktinformasjon p�.");
	}
	
	//Egendefinerte innstillinger for vising av bolig, ikke oppretting.
	protected void erIkkeNy()
	{
		//Disabler textfield
		navnTextField.setEditable(false);
		personNrTextField.setEditable(false);
		boligAdresseTextField.setEditable(false);
		verditakstTextField.setEditable(false);
		l�netakstTextField.setEditable(false);
		bygg�rTextField.setEditable(false);
		romTextField.setEditable(false);
		boarealTextField.setEditable(false);
		bruttoarealTextField.setEditable(false);
		datoRegTextField.setEditable(false);
		datoSolgtTextField.setEditable(false);
		solgtForTextField.setEditable(false);
		st�rrelseFellesutgifterTextField.setEditable(false);
		etasjerEtasjeTextField.setEditable(false);
		
		//Disabler radiobuttons
		eneboligRadioButton.setEnabled(false);
		rekkehusRadioButton.setEnabled(false);
		leilighetRadioButton.setEnabled(false);
		selveieRadioButton.setEnabled(false);
		aksjeRadioButton.setEnabled(false);
		borettslagRadioButton.setEnabled(false);
		kjellerBalkongJaRadioButton.setEnabled(false);
		kjellerBalkongNeiRadioButton.setEnabled(false);
		eietomtHeisJaRadioButton.setEnabled(false);
		festetomtHeisNeiRadioButton.setEnabled(false);
		
		adresseTextField.setToolTipText("Fyll inn gate og nummer. Bruk mellomrom.");
		emailTextField.setToolTipText("Fyll inn email p� format: brukernavn@leverand�r.landskode");
		telefonTextField.setToolTipText("Fyll inn telefonnummer p� 8 tall.");
	}
	
	//Egendefinerte innstillinger for oppretting av ny bolig.
	protected void erNy()
	{
		typeButtonGroup = new ButtonGroup();
		eieformButtonGroup = new ButtonGroup();
		kjellerBalkongButtonGroup = new ButtonGroup();
		tomtHeisButtonGroup = new ButtonGroup();
		
		typeButtonGroup.add(eneboligRadioButton);
		typeButtonGroup.add(rekkehusRadioButton);
		typeButtonGroup.add(leilighetRadioButton);
		
		eieformButtonGroup.add(selveieRadioButton);
		eieformButtonGroup.add(aksjeRadioButton);
		eieformButtonGroup.add(borettslagRadioButton);
		
		kjellerBalkongButtonGroup.add(kjellerBalkongJaRadioButton);
		kjellerBalkongButtonGroup.add(kjellerBalkongNeiRadioButton);
		
		tomtHeisButtonGroup.add(eietomtHeisJaRadioButton);
		tomtHeisButtonGroup.add(festetomtHeisNeiRadioButton);
		
		selveieRadioButton.setSelected(true);
		kjellerBalkongJaRadioButton.setSelected(true);
		eietomtHeisJaRadioButton.setSelected(true);
		eneboligRadioButton.setSelected(true);
		
		navnTextField.setToolTipText("Fyll inn navn p� selger. Bruk store bokstaver.");
		adresseTextField.setToolTipText("Fyll inn gate og nummer. Bruk mellomrom.");
		emailTextField.setToolTipText("Fyll inn email p� format: brukernavn@leverand�r.landskode");
		telefonTextField.setToolTipText("Fyll inn telefonnummer p� 8 tall.");
		personNrTextField.setToolTipText("Fyll inn personnummer p� 11 tall. Sjekk at personnummeret er gyldig f�r registrering");
		beskrivelseTextArea.setToolTipText("Fyll inn en beskrivelse om boligen. Kan redigeres siden.");
		boligAdresseTextField.setToolTipText("Fyll inn adressen til boligen som blir solgt med gate og nummer. Bruk mellomrom.");
		verditakstTextField.setToolTipText("Fyll inn ansl�tt verditakst. Kan ikke endres siden. Tar bare imot tall.");
		l�netakstTextField.setToolTipText("Fyll inn l�netaksten til boligen. Kan ikke endres siden. Tar bare imot tall.");
		bygg�rTextField.setToolTipText("Fyll inn �ret boligen ble bygd. Tar bare imot tall.");
		romTextField.setToolTipText("Fyll inn antall rom ekskludert bad og kj�kken. Tar bare imot tall.");
		boarealTextField.setToolTipText("Fyll inn boarealet for boligen. Tar bare imot tall.");
		bruttoarealTextField.setToolTipText("Fyll inn bruttoarealet for boligen. Tar bare imot tall.");
	}
	
	//Hjelpemetode for � fylle inn info fra tidligere registrert bolig.
	protected void setInfo(Bolig gammelBolig)
	{
		Person n�v�rendeEier = gammelBolig.getN�v�rendeEier();
		navnTextField.setText(n�v�rendeEier.getNavn());
		adresseTextField.setText(n�v�rendeEier.getAdresse());
		emailTextField.setText(n�v�rendeEier.getMail());
		telefonTextField.setText("" + n�v�rendeEier.getTelefon());
		beskrivelseTextArea.setText(gammelBolig.getBeskrivelse());
		
		int eieform = gammelBolig.getEieform();
		if(eieform == 0)
			selveieRadioButton.setSelected(true);
		if(eieform == 1)
			aksjeRadioButton.setSelected(true);
		if(eieform == 2)
			borettslagRadioButton.setSelected(true);
		
		boligAdresseTextField.setText(gammelBolig.getAdresse());
		verditakstTextField.setText("" + tusen.format(gammelBolig.getVerditakst()));
		l�netakstTextField.setText("" + tusen.format(gammelBolig.getL�netakst()));
		bygg�rTextField.setText("" + gammelBolig.getBygg�r());
		romTextField.setText("" + gammelBolig.getAntallRom());
		boarealTextField.setText("" + gammelBolig.getBoareal());
		bruttoarealTextField.setText("" + gammelBolig.getBruttoareal());
		
		if(gammelBolig instanceof EneboligOgRekkehus)
		{
			erEneboligOgRekkehus();
			if(((EneboligOgRekkehus)gammelBolig).erRekkehus())
				rekkehusRadioButton.setSelected(true);
			else
				eneboligRadioButton.setSelected(true);
			if(((EneboligOgRekkehus)gammelBolig).harKjeller())
				kjellerBalkongJaRadioButton.setSelected(true);
			else
				kjellerBalkongNeiRadioButton.setSelected(true);
			if(((EneboligOgRekkehus)gammelBolig).erEietomt())
				eietomtHeisJaRadioButton.setSelected(true);
			else
				festetomtHeisNeiRadioButton.setSelected(true);
			
			st�rrelseFellesutgifterTextField.setText("" + tusen.format(((EneboligOgRekkehus)gammelBolig).getTomtSt�rrelse()));
			etasjerEtasjeTextField.setText("" + ((EneboligOgRekkehus)gammelBolig).getEtasjer());
		}
		if(gammelBolig instanceof Leilighet)
		{
			erLeilighet();
			leilighetRadioButton.setSelected(true);
			if(((Leilighet)gammelBolig).harBalkong())
				kjellerBalkongJaRadioButton.setSelected(true);
			else
				kjellerBalkongNeiRadioButton.setSelected(true);
			
			if(((Leilighet)gammelBolig).harHeis())
				eietomtHeisJaRadioButton.setSelected(true);
			else
				festetomtHeisNeiRadioButton.setSelected(true);
			
			st�rrelseFellesutgifterTextField.setText("" + tusen.format(((Leilighet)gammelBolig).getFellesutgifer()));
			etasjerEtasjeTextField.setText(""+ ((Leilighet)gammelBolig).getEtasje());
		}
	}
	
	//Hjelpemetode for � sjekke om textfelter er tomme.
	protected boolean erTom(String stringToCheck)
	{
		if(stringToCheck.equals(""))
			return true;
		else
			return false;
	}
	
	//Hjelpemetode for � sjekke om e-mail er skrevet p� riktig format.
	protected boolean erEmail(String stringToCheck)
	{
		if(stringToCheck.matches("[A-Za-z������0-9._-]+@[A-Za-z������0-9.-]+.[A-Za-z]{2,4}"))
			return true;
		else
			return false;
	}
}
