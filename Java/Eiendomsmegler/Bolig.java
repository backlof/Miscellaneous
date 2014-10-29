import java.io.Serializable;
/*
 * Abstrakt-klasse som definerer det alle boliger har til felles
 * Skrevet av Alexander
 * Siste versjon: 7. mai
 */
public abstract class Bolig implements Serializable
{
	private static final long serialVersionUID = 4L;
	private int nummer;
	private String adr, description;
	private int xAreal, yAreal, rom, xTakst, yTakst, year, salgsbeløp, eierskap;
	private Person nåværendeEier, tidligereEier;
	private Dato registrert, salgsdato;
	private Budarkiv bud;
	private boolean solgt;
	
	//Konstruktør
	protected Bolig(String adresse, int bruttoareal, int boareal, int antallRom, int eieform, int verditakst, int lånetakst, int byggår, String beskrivelse, Person selger)
	{
		registrert = new Dato();
		adr = adresse;
		xAreal = bruttoareal;
		yAreal = boareal;
		rom = antallRom;
		eierskap = eieform; // 0 = Selveier, 1 = Aksje, 2 = Borettslag
		xTakst = verditakst;
		yTakst = lånetakst;
		year = byggår;
		description = beskrivelse;
		nåværendeEier = selger;
		tidligereEier = null;
		bud = new Budarkiv();
		solgt = false;
		salgsdato = null;
	}
	
	//Brukes av Register før man setter boligen inn i registeret.
	protected void setBoligNr(int boligNr)
	{
		nummer = boligNr;
	}
	
	protected int getBoligNr()
	{
		return nummer;
	}
	
	protected Budarkiv getBud()
	{
		return bud;
	}
	
	protected void setBud(Budarkiv budarkiv)
	{
		bud = budarkiv;
	}
	
	public void setBeskrivelse(String beskrivelse)
	{
		description = beskrivelse;
	}
	
	protected String getBeskrivelse()
	{
		return description;
	}
	
	protected String getAdresse()
	{
		return adr;
	}
	
	protected int getBruttoareal()
	{
		return xAreal;
	}
	
	protected int getBoareal()
	{
		return yAreal;
	}
	
	protected int getAntallRom()
	{
		return rom;
	}
	
	protected int getVerditakst()
	{
		return xTakst;
	}
	
	protected int getLånetakst()
	{
		return yTakst;
	}
	
	protected int getByggår()
	{
		return year;
	}
	
	protected int getSalgsbeløp()
	{
		return salgsbeløp;
	}
	
	protected int getEieform()
	{
		return eierskap;
	}
	
	protected String toStringEieform()
	{
		if(eierskap == 0)
			return "Selveier";
		if(eierskap == 1)
			return "Aksje";
		if(eierskap == 2)
			return "Borettslag";
		else
			return "//Feil i kode";
	}
	
	protected Person getNåværendeEier()
	{
		return nåværendeEier;
	}
	
	protected void setNåværendeEier(Person person)
	{
		nåværendeEier = person;
	}
	
	//Metode som brukes ved salg av bolig
	protected void selg(Person budgiver, Bud godtattBud)
	{
		solgt = true;
		tidligereEier = nåværendeEier;
		nåværendeEier = budgiver;
		salgsdato = new Dato();
		salgsbeløp = godtattBud.getBeløp();
		bud.fjernAlleBud();
	}
	
	protected void setTidligereEier(Person selger)
	{
		tidligereEier = selger;
	}
	
	protected Person getTidligereEier()
	{
		return tidligereEier;
	}
	
	protected String toStringDato()
	{
		return registrert.toDatoString();
	}
	
	protected String toStringSalgsdato()
	{
		if(solgt)
			return salgsdato.toDatoString();
		else
			return "";
	}
	
	protected Boolean getSolgt()
	{
		return solgt;
	}
	
	protected String toStringSolgt()
	{
		if(solgt)
			return "Solgt";
		else
			return "Ikke solgt";
	}
	
	protected String getSelgerNavn()
	{
		if(solgt)
			return tidligereEier.getNavn();
		else
			return nåværendeEier.getNavn();
	}
}