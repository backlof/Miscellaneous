/**
 * Oblig 3 Algoritmer og datastrukturer høsten 2010
 * Vegard Nyeng, 2IA, s163302
 * Hans Petter Naumann, 2AC, s163471
 * Alexander Barve, 2AA, s155517
 * Peter Marcinkowski, 2AB, s154390
 */
import java.util.*;

public class SBinTre<T> implements Iterable<T>
{
	private static final class Node<T> //en indre nodeklasse
	{
		private T verdi;          //nodens verdi
		private Node<T> venstre;  //venstre barn
		private Node<T> høyre;    //høyre barn

		private Node(T verdi, Node<T> v, Node<T> h)  //konstruktør
		{
			this.verdi = verdi;
			venstre = v;
			høyre = h;
		}

		private Node(T verdi)  //konstruktør
		{
			this(verdi, null, null);
		}

	} // class Node

	private Node<T> rot;       //peker til rotnoden
	private int antall;        //antall noder
	private int modAntall;     //antall endringer
	private final Comparator<? super T> comp;   //komparator


	//hjelpemetoder

	private static <T> Node<T> først(Node<T> p)
	{

		while(p.venstre != null)
		{
			p = p.venstre;
		}
		return p;
	}

	private static <T> Node<T> sist(Node<T> p)
	{
	  		while(p.høyre != null)
	  		{
	  			p = p.høyre;
	  		}
	  		return p;
	}

	private Node<T> neste(Node<T> denne)
	{
		if(denne.høyre != null)
			return først(denne.høyre);
		
		Node<T> p = rot, q = null;
		while ( sist(p) != denne ) 
		{
			int cmp = comp.compare (denne.verdi, p.verdi);
		 
			if ( cmp < 0) 
			{
				q = p;
				p = p.venstre;
			}
			else	
				p = p.høyre;
		}
		return q;
	}
	
	/* VEGARD
	private Node<T> forrige(Node<T> denne)
	{
		Node<T> sjekk = først(rot);
		
		if(sjekk == denne)
			return null;
		
		while(sjekk != null)
		{
			if(neste(sjekk) == denne)
				return sjekk;
			sjekk = neste(sjekk);
		}
		return null;
	}
	*/
	
	private Node<T> forrige(Node<T> p)
	{
		if(p.venstre != null)
			return sist(p.venstre);
	  
		Node<T> q = rot;
		Node<T> temp = null;
		int cmp = 0;

		while(først(q) != p) // nullPointerException?
		{
			cmp = comp.compare(p.verdi, q.verdi);
			if(cmp < 0)
				q = q.venstre;
			else
			{
				temp = q;
				q = q.høyre;
			}
		}
		return temp; // ferdig+testet kode
	}

	private Node<T> finnNode(T verdi)
	{
		if(tom()) return null;

		Node<T> q = null;

		for(Node<T> p = rot; p != null;)
		{
			int cmp = comp.compare(verdi,p.verdi);       //sammenligner
			if(cmp < 0)
			{
				q = p;
				p = p.venstre;                  //går til venstre
			}
			else if(cmp > 0)
			{
				p = p.høyre;
			}
			else return p;
		}
		return q;
	}


	//konstruktører m.m.

	public SBinTre(Comparator<? super T> c)
	{
		if(c == null)
			throw new NullPointerException("Komparatoren c er null!");

		rot = null;
		antall = 0;
		modAntall = 0;
		comp = c;
	}

	//klassen Komparator må være tilgjengelig
	public static <T extends Comparable<? super T>> SBinTre<T> lagTre()
	{
		return new SBinTre<T>(Komparator.<T> naturlig());
	}

	public static <T> SBinTre<T> lagTre(Comparator<? super T> c)
	{
		if(c == null)
			throw new NullPointerException("Komparatoren c er null!");

		return new SBinTre<T>(c);
	}


	//offentlige metoder

	public void leggInn(T verdi)
	{
		if(verdi == null)
			throw new IllegalArgumentException("Ikke tillatt med null-verdier!");

		Node<T> p = rot, q = null;               //p starter i roten
		int cmp = 0;                             //hjelpevariabel

		while(p != null)       //fortsetter til p er "ute av" treet
		{
			q = p;                                 //q forelder til p

			cmp = comp.compare(verdi,p.verdi);     //bruker komparatoren
			p = cmp < 0 ? p.venstre : p.høyre;     //flytter p
		}

		p = new Node<T>(verdi);                  //oppretter en ny node

		if(q == null)
			rot = p;                  //rotnoden
		else if(cmp < 0)
			q.venstre = p;         //til venstre for q
		else
			q.høyre = p;                        //til høyre for q

		antall++;                                //én verdi mer i treet
		modAntall++;
	}

	public int inneholder(T verdi)
	{
		int antall = 0;
		for(Node<T> p = rot; p != null; )             //starter i roten
	    {
			int cmp = comp.compare(verdi,p.verdi);       //sammenligner
			if(cmp < 0)
				p = p.venstre;                  //går til venstre
			else if(cmp > 0)
				p = p.høyre;               //går til høyre
			else
			{
				antall++;
				p = p.høyre;
			}
	    }
	    return antall;
	}

	public void traverserInorden(Oppgave<? super T> oppgave)
	{
		if(rot != null)
		{
			Node<T> mid = først(rot);
			while(mid != null)
			{
				oppgave.utførOppgave(mid.verdi);
				mid = neste(mid);
			}
		}	
	}

	public void traverserOmvendtInorden(Oppgave<? super T> oppgave)
	{
		if (rot != null)
		{
			Node<T> p = sist (rot);
		   
			while (p != null) 
			{
				oppgave.utførOppgave(p.verdi);
			    p = forrige(p);
			}
		}  
	}

	public void traverserPreorden(Oppgave<? super T> oppgave)
	{
		if(rot != null)
			traverserPreorden(rot,oppgave);
	}

	private static <T> void traverserPreorden(Node<T> p, Oppgave<? super T> oppgave)
	{
		oppgave.utførOppgave(p.verdi);
		if(p.venstre != null)
			traverserPreorden(p.venstre, oppgave);
		if(p.høyre != null)
			traverserPreorden(p.høyre, oppgave);
	}

	public T min()
	{
		if(tom())
			throw new NoSuchElementException("Treet er tomt");

		Node<T> min = først(rot);
		return min.verdi;
	}

	public T maks()
	{
		if(tom())
			throw new NoSuchElementException("Treet er tomt");

		Node<T> max = sist(rot);
		return max.verdi;
	}

	public int antall()
	{
		return antall;
	}

	public boolean tom()
	{
		return antall == 0;
	}

	public boolean slett(T verdi)
	{
		if(verdi == null)
			return false;

		Node<T> p = rot, q = null;  //q skal være forelder til p

		while(p != null)     //leter etter verdi
		{
			int cmp = comp.compare(verdi,p.verdi);    //sammenligner
			if(cmp < 0)
			{
				q = p;
				p = p.venstre;
			}
			else if(cmp > 0)
			{
				q = p;
				p = p.høyre;
			}
			else break;    //den søkte verdien ligger i p
		}
		if(p == null)
			return false;   //fant ikke verdi

		if(p.venstre != null && p.høyre != null)  //p har to barn
		{
			Node<T> r = p, s = p.høyre;   //finner neste i inorden
			while(s.venstre != null)
			{
				r = s;    //r er forelder til s
				s = s.venstre;
			}
			p.verdi = s.verdi;   //kopierer fra s til p
			if(r == p)
				r.høyre = s.høyre;
			else
				r.venstre = s.høyre;
		}
		else  //p har ett eller ingen barn
		{
			Node<T> x = p.høyre == null ? p.venstre : p.høyre;
			if(p == rot)
				rot = x;
			else if(p == q.venstre)
				q.venstre = x;
			else
				q.høyre = x;
		}
		antall--;   //det er nå én node mindre i treet
		modAntall++;

		return true;
	}
	
	public Liste<T> intervallsøk(T fraverdi, T tilverdi)
	{
		int cmp = comp.compare(fraverdi, tilverdi);
		if(cmp > 0)
			throw new IndexOutOfBoundsException("fraverdi (" +fraverdi +") må være mindre enn eller lik tilverdi (" +tilverdi +").");
	  
		DobbeltLenketListe<T> liste = new DobbeltLenketListe<T>();
		Node<T> q = finnNode(fraverdi);
		Node<T> r = finnNode(tilverdi);
	  
		if(fraverdi == tilverdi)
		{
			cmp = 0;
			while(neste(q) != null && cmp == 0)
			{
				liste.leggInn(q.verdi);
				q = neste(q);
				cmp = comp.compare(fraverdi, q.verdi);
			}
		}
		else
		{
			while(q != r)
			{
				liste.leggInn(q.verdi);
				q = neste(q);
			}
		}
		return liste;
	}

	public String toString()
	{
		StringBuilder s = new StringBuilder();
		if(rot != null)
		{
			Node<T> q = sist(rot);
			rekursivtoString(rot, q, s);		   
		}   
		return "[" +s.toString() +"]";
	}
	
	private void rekursivtoString(Node<T> p, Node<T> q, StringBuilder s)
	{
		if(p.venstre != null)
			rekursivtoString(p.venstre, q, s);
		
		s.append(p.verdi);
		if(q == p)
			return;
		s.append(',');
		s.append(' ');
	   
		if(p.høyre != null) 
			rekursivtoString(p.høyre, q, s);
	}
	
	public String omvendtString()
	{
		StringBuilder s = new StringBuilder();
		if(rot != null)
		{
			Node<T> q = først(rot);
			rekursivOmvendtString(rot, q, s);
		}   
		return "[" +s.toString() +"]";
	}
	
	private void rekursivOmvendtString(Node<T> p, Node<T> q, StringBuilder s)
	{
		if(p.høyre !=null) 
			rekursivOmvendtString(p.høyre, q, s);
			      
		s.append(p.verdi);
		if(q == p)
			return;
		s.append(',');
		s.append(' ');
			  
		if(p.venstre != null) 
			rekursivOmvendtString(p.venstre, q, s);
	}


	//Iterator-klasser
	public Iterator<T> iterator()
	{
		return new InordenIterator();
	}

	 private final class InordenIterator implements Iterator<T>
	  {
	    private Node<T> denne;
	    private int iteratorModAntall;
	    private boolean fjernOK;

	    private InordenIterator()  // konstruktør
	    {
	    	iteratorModAntall = modAntall;
	    	fjernOK = false;
	    	if(rot != null)
	    		denne = først(rot);
	    	else denne = null;
	    }

	    public boolean hasNext()
	    {
	    	return denne != null;
	    }

	    public T next()
	    {
	    	if(!hasNext())
	    		throw new NoSuchElementException("Ikke flere igjen");
	    	if (modAntall != iteratorModAntall)
	    		throw new ConcurrentModificationException("modAntall og iteratorModAntall er ulike!");
	    	fjernOK = true;
	    	T temp = denne.verdi; 
	    	denne = neste(denne);
	    	return temp;  
	    }
	    public void remove(){  throw new UnsupportedOperationException("\nUnsupported Operation Exception"); }
	  }

	public Iterator<T> omvendtiterator()
	{
		return new OmvendtInordenIterator();
	}

	  private final class OmvendtInordenIterator implements Iterator<T>
	  {
	    private Node<T> denne;
	    private int iteratorModAntall;
	    private boolean fjernOK;

	    private OmvendtInordenIterator()
	    {
	        iteratorModAntall = modAntall;
	        fjernOK = false;
	        if(rot != null)
	        	denne = sist(rot);
	        else
	        	denne = null;
	    }

	    public boolean hasNext()
	    {
	    	return denne!= null;
	    }

	    public T next()
	    {
	    	if(!hasNext())
	    		throw new NoSuchElementException("Ikke flere igjen");
	        if(modAntall != iteratorModAntall)
	        	throw new ConcurrentModificationException("modAntall og iteratormodifikasjonsantallet er ikke like!");
	        fjernOK = true; 
	        T verdi = denne.verdi; 	
	        denne = forrige(denne);    	
	        return verdi; 
	    }

	    public void remove()
	    {
	    	throw new UnsupportedOperationException();
	    }
	  }
} //class SBinTre
