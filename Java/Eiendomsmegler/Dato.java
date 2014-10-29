import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;
/*
 * Klasse med form�l � kunne sammenlikne tidspunkt, lagre tidspunkt og skrive ut datoen.
 * Skrevet av Alexander
 * Siste versjon: 8. mai
 */
public class Dato implements Serializable
{
	private static final long serialVersionUID = 9L;
	private int year, month, dayOfMonth, hourOfDay, minute;
	private Calendar kalenderObjekt;
	
	/*
	 * Brukes for � automatisk finne dag og tidspunkt, uten forandringer.
	 * Salgsdato og innleggingsdato for bolig og dato-sammenlikning for bud bruker denne konstrukt�ren.
	 */
	public Dato()
	{
		kalenderObjekt = new GregorianCalendar();//brukes for � kunne sammenlikne datoer
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = 1 + Calendar.getInstance().get(Calendar.MONTH);
		dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		minute = Calendar.getInstance().get(Calendar.MINUTE);	
	}
	
	/*
	 * Legger til dager fremover, og tid p� den gitte dagen. Brukes av bud.
	 * Kan hoppe videre til neste m�ned, hvis man overg�r antall dager i m�neden.
	 */
	public Dato(int dager, int timeP�Dagen, int minutt)
	{
		Calendar kalendertest = new GregorianCalendar();
		int m�ned = 1 + Calendar.getInstance().get(Calendar.MONTH);
		int �r = Calendar.getInstance().get(Calendar.YEAR);
		int m�nedsDag = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int m�nedsDagMax = kalendertest.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		if((m�nedsDag + dager) > m�nedsDagMax)
		{
			if(m�ned == 12)
			{
				kalenderObjekt = new GregorianCalendar(�r + 1, 0, (m�nedsDag + dager) - m�nedsDagMax, timeP�Dagen, minutt);
				year = �r +1;
				month = 1;
				dayOfMonth = (m�nedsDag + dager) - m�nedsDagMax;
				hourOfDay = timeP�Dagen;
				minute = minutt;
			}
			else
			{
				kalenderObjekt = new GregorianCalendar(�r, m�ned, (m�nedsDag + dager) - m�nedsDagMax, timeP�Dagen, minutt);
				year = �r;
				month = m�ned + 1;
				dayOfMonth = (m�nedsDag + dager) - m�nedsDagMax;
				hourOfDay = timeP�Dagen;
				minute = minutt;
			}
		}
		else
		{
			kalenderObjekt = new GregorianCalendar(�r, m�ned - 1, m�nedsDag + dager, timeP�Dagen, minutt);
			year = �r;
			month = m�ned;
			dayOfMonth = m�nedsDag + dager;
			hourOfDay = timeP�Dagen;
			minute = minutt;
		}
	}
	
	/*
	public Dato(int �r, int m�ned, int m�nedsDag, int timeP�Dagen, int minutt)
	{
		kalenderObjekt = new GregorianCalendar(�r, m�ned - 1, m�nedsDag, timeP�Dagen, minutt);
		year = �r;
		month = m�ned;
		dayOfMonth = m�nedsDag;
		hourOfDay = timeP�Dagen;
		minute = minutt;
	}
	*/
	
	//Sammenlikner om objektet man bruker metoden p� er f�r objektet man sender i parameteret til metoden
	public boolean f�r(Dato compareToDato)
	{
		return this.kalenderObjekt.before(compareToDato.kalenderObjekt);
	}
	
	//Returnerer en dato-string inkludert time og minutt. Brukes i budtabellen p� hovedvinduet, og ved vising av bud.
	public String toBudString()
	{
		String x, y;
		
		if(hourOfDay < 10)
			x = "0" + hourOfDay;
		else
			x = "" + hourOfDay;
		
		if(minute < 10)
			y = "0" + minute;
		else
			y = "" + minute;
		return toDatoString() + "  " + x + ":" + y;
	}
	
	//Returnerer en dato-string. Brukes i boligtabellen p� hovedvinduet, og ved vising av boliger.
	public String toDatoString()
	{
		String x, y;
		
		if(dayOfMonth < 10)
			x = "0" + dayOfMonth;
		else
			x = "" + dayOfMonth;
		
		if(month < 10)
			y = "0" + month;
		else
			y = "" + month;
		return x + "." +  y + "." + year;
	}
}