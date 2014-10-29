import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;
/*
 * Klasse med formål å kunne sammenlikne tidspunkt, lagre tidspunkt og skrive ut datoen.
 * Skrevet av Alexander
 * Siste versjon: 8. mai
 */
public class Dato implements Serializable
{
	private static final long serialVersionUID = 9L;
	private int year, month, dayOfMonth, hourOfDay, minute;
	private Calendar kalenderObjekt;
	
	/*
	 * Brukes for å automatisk finne dag og tidspunkt, uten forandringer.
	 * Salgsdato og innleggingsdato for bolig og dato-sammenlikning for bud bruker denne konstruktøren.
	 */
	public Dato()
	{
		kalenderObjekt = new GregorianCalendar();//brukes for å kunne sammenlikne datoer
		year = Calendar.getInstance().get(Calendar.YEAR);
		month = 1 + Calendar.getInstance().get(Calendar.MONTH);
		dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		minute = Calendar.getInstance().get(Calendar.MINUTE);	
	}
	
	/*
	 * Legger til dager fremover, og tid på den gitte dagen. Brukes av bud.
	 * Kan hoppe videre til neste måned, hvis man overgår antall dager i måneden.
	 */
	public Dato(int dager, int timePåDagen, int minutt)
	{
		Calendar kalendertest = new GregorianCalendar();
		int måned = 1 + Calendar.getInstance().get(Calendar.MONTH);
		int år = Calendar.getInstance().get(Calendar.YEAR);
		int månedsDag = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int månedsDagMax = kalendertest.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		if((månedsDag + dager) > månedsDagMax)
		{
			if(måned == 12)
			{
				kalenderObjekt = new GregorianCalendar(år + 1, 0, (månedsDag + dager) - månedsDagMax, timePåDagen, minutt);
				year = år +1;
				month = 1;
				dayOfMonth = (månedsDag + dager) - månedsDagMax;
				hourOfDay = timePåDagen;
				minute = minutt;
			}
			else
			{
				kalenderObjekt = new GregorianCalendar(år, måned, (månedsDag + dager) - månedsDagMax, timePåDagen, minutt);
				year = år;
				month = måned + 1;
				dayOfMonth = (månedsDag + dager) - månedsDagMax;
				hourOfDay = timePåDagen;
				minute = minutt;
			}
		}
		else
		{
			kalenderObjekt = new GregorianCalendar(år, måned - 1, månedsDag + dager, timePåDagen, minutt);
			year = år;
			month = måned;
			dayOfMonth = månedsDag + dager;
			hourOfDay = timePåDagen;
			minute = minutt;
		}
	}
	
	/*
	public Dato(int år, int måned, int månedsDag, int timePåDagen, int minutt)
	{
		kalenderObjekt = new GregorianCalendar(år, måned - 1, månedsDag, timePåDagen, minutt);
		year = år;
		month = måned;
		dayOfMonth = månedsDag;
		hourOfDay = timePåDagen;
		minute = minutt;
	}
	*/
	
	//Sammenlikner om objektet man bruker metoden på er før objektet man sender i parameteret til metoden
	public boolean før(Dato compareToDato)
	{
		return this.kalenderObjekt.before(compareToDato.kalenderObjekt);
	}
	
	//Returnerer en dato-string inkludert time og minutt. Brukes i budtabellen på hovedvinduet, og ved vising av bud.
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
	
	//Returnerer en dato-string. Brukes i boligtabellen på hovedvinduet, og ved vising av boliger.
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