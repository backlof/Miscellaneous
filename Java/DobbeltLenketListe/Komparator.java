import java.util.*;

public class Komparator
{
  private Komparator() {};    // hindrer instansiering

  private static class         // en naturlig komparator
  Naturlig<T extends Comparable<? super T>> implements Comparator<T>
  {
    public int compare(T t1, T t2) { return t1.compareTo(t2); }

  } // class Naturlig

  public static     // en fabrikasjonsmetode
  <T extends Comparable<? super T>> Comparator<T> naturlig()
  {
    return new Naturlig<T>();
  }

  private static class   // en naturlig omvendtkomparator
  Omvendt1<T extends Comparable<? super T>> implements Comparator<T>
  {
    public int compare(T t1, T t2)
    {
      return t2.compareTo(t1);  // snur sammenligningen
    }

  } // class Omvendt1

  public static     // en fabrikasjonsmetode
  <T extends Comparable<? super T>> Comparator<T> omvendt()
  {
    return new Omvendt1<T>();
  }

  private static class Omvendt2<T> implements Comparator<T>
  {
    private Comparator<? super T> c;  // instansvariabel

    public Omvendt2(Comparator<? super T> c) // konstruktÃ¸r
    {
      this.c = c;
    }

    public int compare(T t1, T t2)
    {
      return c.compare(t2,t1);  // snur sammenligningen
    }

  } // class Omvendt2

  public static <T> Comparator<T> omvendt(Comparator<? super T> c)
  {
    return new Omvendt2<T>(c);
  }

  private static class Sammensatt1<T> implements Comparator<T>
  {
    private Comparator<? super T> c1, c2;     // to komparatorer for T

    public Sammensatt1(Comparator<? super T> c1, Comparator<? super T> c2)
    {
      this.c1 = c1; this.c2 = c2;
    }

    public int compare(T t1, T t2)
    {
      int cmp = c1.compare(t1,t2);
      return cmp != 0 ? cmp : c2.compare(t1,t2);
    }
  }  // class Sammensatt1

  public static <T> Comparator<T> // en fabrikasjonsmetode
  sammensatt(Comparator<? super T> c1, Comparator<? super T> c2)
  {
    return new Sammensatt1<T>(c1,c2);
  }

  private static class
  Sammensatt2<T extends Comparable<? super T>> implements Comparator<T>
  {
    private Comparator<? super T> c;

    public Sammensatt2(Comparator<? super T> c)
    {
      this.c = c;
    }

    public int compare(T t1, T t2)
    {
      int cmp = c.compare(t1,t2);
      return cmp != 0 ? cmp : t1.compareTo(t2);
    }
  } // class Sammensatt2

  public static  // en fabrikasjonsmetode
  <T extends Comparable<? super T>> Comparator<T> sammensatt(Comparator<? super T> c)
  {
    return new Sammensatt2<T>(c);
  }

} // slutt på class Komparator