import java.io.Serializable;
/*
 * Definerer personer og har kontaktinformasjon.
 * Skrevet av Alexander
 * Siste versjon 7. mai
 */
public class Person implements Serializable
{
	private static final long serialVersionUID = 8L;
	private long nummer;
	private int tlf;
	private String name, mail, adr;
	
	//Konstruktør
	public Person(String navn, String adresse, String eMail, int telefon, long personNr)
	{
		name = navn;
		adr = adresse;
		mail = eMail;
		tlf = telefon;
		nummer = personNr;
	}
	
	/*
	 * Hjelpemetode som benyttes for å undersøke om samme person allerede har bud liggende.
	 * Denne metoden erstatter en "get"-metode, for å sikre personnummer.
	 */
	public boolean sammePersonNr(long personNr)
	{
		if(nummer == personNr)
			return true;
		else
			return false;
	}
	
	public int getTelefon()
	{
		return tlf;
	}
	
	public void setTelefon(int telefon)
	{
		tlf = telefon;
	}
	
	public String getNavn()
	{
		return name;
	}
	
	public String getMail()
	{
		return mail;
	}
	
	public void setMail(String eMail)
	{
		mail = eMail;
	}
	
	public String getAdresse()
	{
		return adr;
	}
	
	public void setAdresse(String adresse)
	{
		adr = adresse;
	}
}
