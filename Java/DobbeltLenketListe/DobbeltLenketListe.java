/**
 * Oblig 2 Algoritmer og datastrukturer høst 2010
 * Hans Petter Naumann, 2ac, s163471
 * Alexander Barve, 2aa, s155517
 */

import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T>
{	
	private static final class Node<T>       // en indre nodeklasse
	{
		private T verdi;
		private Node<T> forrige, neste;

		private Node(T verdi, Node<T> forrige, Node<T> neste)   // konstruktør
		{
			this.verdi = verdi;
			this.forrige = forrige;
			this.neste = neste;
		}
	}

	private Node<T> hode;         // peker til den første i listen
	private Node<T> hale;         // peker til den siste i listen
	private int antall;           // antall noder i listen
	private int modAntall;        // antall modifikasjoner i listen
  
	private void indeksKontroll(int indeks)
	{
		if (indeks < 0 )
			throw new IndexOutOfBoundsException("Indeks " + indeks + " er negativ!");
		else if (indeks >= antall)
			throw new IndexOutOfBoundsException("Indeks " + indeks + " >= antall(" + antall + ") noder!");
	}
  
	private Node<T> finnNode(int indeks)
	{
		indeksKontroll(indeks);
		
		if(antall/2 > indeks)
		{
			Node<T> n = hode.neste;
			for(int i = 0; i < indeks; i++)
			{
				n = n.neste;
			}
			return n;
		}
		else
		{
			Node<T> n = hale.forrige;
			for(int i = 1; i < (antall - indeks); i++)
			{
				n = n.forrige;
			}
			return n;
		}
		
	}
  
	private void nullSjekk(T verdi)
	{
		if(verdi == null)
			throw new NullPointerException("Verdien er null");
	}

	public DobbeltLenketListe()  // konstruktør
	{
		hode = hale = null;
		antall = 0;
		modAntall = 0;
	}
  
	public boolean tom()
	{
		return (antall == 0);
	}
  
	public int antall()
	{
		return antall;  
	}
  
	public void nullstill()
	{
		hode = hale = null;
		antall = 0;
		modAntall++;
	}
  
	public boolean inneholder(T verdi)
	{
		if(verdi != null)
		{
			int indeks = indeksTil(verdi);
			if (indeks != -1)
				return true;
		}
		return false;  
	}
  
	public void leggInn(int indeks, T verdi)
	{
		nullSjekk(verdi);
		
		if(indeks < 0 || indeks > antall)
			throw new IndexOutOfBoundsException("Indeksen er ugyldig.");
		
		if(antall == 0)//listen er tom
		{
			Node<T> ny = new Node<T>(verdi, null, null);
			
			hode = new Node<T>(null, null, ny);
			hale = new Node<T>(null, ny, null);
			 	
			antall++;
			modAntall++;
		}
		else if(indeks == 0)//legge inn forrest
		{
			Node<T> ny = new Node<T>(verdi, null, hode.neste);
			hode.neste.forrige = ny;
			hode.neste = ny;
			antall++;
			modAntall++;
		}
		else if(indeks == (antall))
		{
			Node<T> ny = new Node<T>(verdi, hale.forrige, null);
			hale.forrige.neste = ny;
			hale.forrige = ny;
			
			antall++;
			modAntall++;
		}
		else
		{
			Node<T> gammel = finnNode(indeks);
			Node<T> ny = new Node<T>(verdi, gammel.forrige, gammel);
			gammel.forrige.neste = ny;
			gammel.forrige = ny;
			
			antall++;
			modAntall++;
		}
		
	}
  
	public boolean leggInn(T verdi)
	{
		
		nullSjekk(verdi);
		
		
		if(antall == 0) //Listen er tom fra før
		{
			Node<T> ny = new Node<T>(verdi, null, null);
			
			hode = new Node<T>(null, null, ny);
			hale = new Node<T>(null, ny, null);
			 		
			antall++;
			modAntall++;
			return true;
		}
		
		else//Hvis listen ikke er tom fra før
		{
			Node<T> ny = new Node<T>(verdi, hale.forrige, null);
			hale.forrige.neste = ny;
			hale.forrige = ny;
			
			antall++;
			modAntall++;
			return true;
		}
	}
  
	public T hent(int indeks)
	{
		indeksKontroll(indeks);
		Node<T> test = finnNode(indeks);
		return test.verdi;
	}
  
	public int indeksTil(T verdi)
	{
		if(verdi != null)
		{
			Node<T> n = hode;
			int indeks = 0;
			while(n.neste != null)
			{
				if(n.neste.verdi.equals(verdi))
					return indeks;
				
				n = n.neste;
				indeks++;
			}
			
		}
		return -1; // verdi er null eller finnes ikke i listen
	}
  
	public T oppdater(int indeks, T nyverdi)
	{
		
		nullSjekk(nyverdi);
		indeksKontroll(indeks);
		
		Node<T> n = finnNode(indeks);
		T gammel = n.verdi;
		n.verdi = nyverdi;
		modAntall++;
		return (T) gammel;
	}
  
	public T fjern(int indeks)
	{
		indeksKontroll(indeks);
		
		Node<T> n = finnNode(indeks);
		if(indeks == 0)//fjerne første element
		{
			if(antall == 1)
			{
				nullstill();
			}
			else
			{
				hode.neste = hode.neste.neste;	
				hode.neste.forrige = null;
				antall--;
				modAntall++;
			}
		}
		else if(indeks == antall-1) //fjerne siste element
		{
			hale.forrige = hale.forrige.forrige;
			hale.forrige.neste = null;	
			antall--;
			modAntall++;
		}
		else //fjerne et element midt i listen et sted
		{
			n.forrige.neste = n.neste;
			n.neste.forrige = n.forrige;
			antall--;
			modAntall++;
		}
		
		return (T) n.verdi;
  
	}
  
	public boolean fjern(T verdi)
	{
		int i = indeksTil(verdi);
		indeksKontroll(i);
		T n = fjern(i);
		if(n != null)
			return true;
		
					
	    return false;
	}

	private class DobbeltLenketListeIterator implements Iterator<T>
	{
		private Node<T> p;
		private boolean fjernOK;
		private int iteratorModAntall;
		private boolean erHale;

		private DobbeltLenketListeIterator()
		{
			p = hode.neste;         // p starter på den første i listen
			fjernOK = false;  // blir sann når next() kalles
			iteratorModAntall = modAntall;  // teller endringer
			erHale = false; //hvis p er halen
		}
    
		public boolean hasNext()
		{	
			if(p == null || erHale)//er hale eller tom liste
				return false;
			else if(p.neste == null && !erHale)//neste er hale
				return true;
			else
				return true;
		}
    
		public T next()
		{
			if(!(iteratorModAntall == modAntall))
			{
				throw new ConcurrentModificationException("Unntak: Modifikasjonsantall er ulik iteratormodifikasjonsantall.");
			}
			else if(!hasNext())
			{
				throw new NoSuchElementException("Det er ingen neste");
			}
			
			Node<T> mid = p;
			if(p.neste == null && antall != 0)
			{
				p = hale;
				erHale = true;
			}
			else
				p = p.neste;
			fjernOK = true;
			return (T) mid.verdi;
		}
    
		public void remove()
		{
			if(!fjernOK)
			{
				throw new IllegalStateException("Tilstandsfeil. Kunne ikke fjerne et element.");
			}
			else if(!(iteratorModAntall == modAntall))
			{
				throw new ConcurrentModificationException("Unntak: Modifikasjonsantall er ulik iteratormodifikasjonsantall.");
			}
			else
			{
				fjernOK = false;
				if(p == hale && p.forrige.forrige == null)//slette eneste element
				{
					hode.neste = null;
					hale.forrige = null;
				}
				else if(p.forrige.forrige == null)//slette første element
				{
					p.forrige = null;
					hode.neste = p;
				}
				else if(p == hale)//slette siste element
				{
					p.forrige.forrige.neste = null;
					p.forrige = p.forrige.forrige;
				}
				else//vanlig tilfelle
				{
					p.forrige.forrige.neste = p;
					p.forrige = p.forrige.forrige;
				}
				
				
				antall--;
				modAntall++;
				iteratorModAntall++;
			}
				
			
			
		}

	} // class DobbeltLenketListeIterator  

	public Iterator<T> iterator()
	{
		return new DobbeltLenketListeIterator();
	}
  
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append('[');
		if(hode != null && hode.neste != null)
		{
			Node<T> n = hode.neste;
			s.append(n.verdi);
			n = n.neste;
			
			while(n != null)
			{
				s.append(", ");
				s.append(n.verdi);
				n = n.neste;
			}
		}	
		s.append(']');
		return s.toString();	
	}
  
	public String omvendtString()
	{
		StringBuilder s = new StringBuilder();
		s.append('[');
		if(hale != null && hale.forrige != null)
		{
			Node<T> n = hale.forrige;
			s.append(n.verdi);
			n = n.forrige;
			
			while(n != null)
			{
				s.append(',');
				s.append(' ');
				s.append(n.verdi);
				n = n.forrige;
			}
		}	
		s.append(']');
		return s.toString();	
	}
} // class DobbeltLenketListe
