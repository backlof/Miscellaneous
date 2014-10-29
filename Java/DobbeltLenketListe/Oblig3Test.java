import java.util.*;

public class Oblig3Test
{
  public static int Oppgave1Test()
  {
    int antallFeil = 0;
    boolean feil1 = true;
    SBinTre<Character> tre = SBinTre.<Character>lagTre();
    try
    {
      tre.min();
    }
    catch (NoSuchElementException e)
    {
      feil1 = false;
    }

    if (feil1)
    {
      antallFeil++;
      System.out.println("min-metoden skal kaste en NoSuchElementException hvis treet er tomt!");
    }

    tre.leggInn('E');

    if (tre.min() != 'E')
    {
      antallFeil++;
      System.out.println("Feil i min-metoden når det er en verdi!");
    }

    tre.leggInn('F');

    if (tre.min() != 'E')
    {
      antallFeil++;
      System.out.println("Feil i min-metoden når rotnoden har den minste verdien!");
    }

    tre.leggInn('C'); tre.leggInn('A'); tre.leggInn('B');

    if (tre.min() != 'A')
    {
      antallFeil++;
      System.out.println("Feil i min-metoden!");
    }

    if (antallFeil == 0)
      System.out.println("Oppgave 1: Metodene først og min ser ok ut!");
    else
      System.out.println("Oppgave 1: Du har minst " + antallFeil + " i først- og min-metoden!");

    return antallFeil;
  }

  public static int Oppgave2Test()
  {
    int antallFeil = 0;
    boolean feil1 = true;
    SBinTre<Character> tre = SBinTre.<Character>lagTre();
    try
    {
      tre.maks();
    }
    catch (NoSuchElementException e)
    {
      feil1 = false;
    }

    if (feil1)
    {
      antallFeil++;
      System.out.println("maks-metoden skal kaste en NoSuchElementException hvis treet er tomt!");
    }

    tre.leggInn('E');

    if (tre.maks() != 'E')
    {
      antallFeil++;
      System.out.println("Feil i maks-metoden når treet har en verdi!");
    }

    tre.leggInn('D');

    if (tre.maks() != 'E')
    {
      antallFeil++;
      System.out.println("Feil i maks-metoden når rotnoden har den minste verdien!");
    }

    tre.leggInn('F'); tre.leggInn('H'); tre.leggInn('G');

    if (tre.maks() != 'H')
    {
      antallFeil++;
      System.out.println("Feil i maks-metoden!");
    }

    if (antallFeil == 0)
      System.out.println("Oppgave 2: Metodene sist og maks ser ok ut!");
    else
      System.out.println("Oppgave 2: Du har minst " + antallFeil + " feil i sist- og maks-metoden!");

    return antallFeil;
  }

  public static int Oppgave3Test()
  {
    int antallFeil = 0;
    SBinTre<Character> tre = SBinTre.<Character>lagTre();

    if (tre.inneholder('A') != 0)
    {
      antallFeil++;
      System.out.println("Feil i inneholder-metoden når treet er tomt!");
    }

    tre.leggInn('E');
    if (tre.inneholder('E') != 1 || tre.inneholder('A') != 0)
    {
      antallFeil++;
      System.out.println("Feil i inneholder-metoden når treet har en verdi!");
    }

    tre.leggInn('I'); tre.leggInn('E'); tre.leggInn('H'); tre.leggInn('E'); tre.leggInn('G'); tre.leggInn('H');
    if (tre.inneholder('E') != 3 || tre.inneholder('A') != 0
        || tre.inneholder('G') != 1 || tre.inneholder('H') != 2 || tre.inneholder('F') != 0)
    {
      antallFeil++;
      System.out.println("Feil i inneholder-metoden!");
    }

    if (antallFeil == 0)
      System.out.println("Oppgave 3: Metoden inneholder ser ok ut!");
    else
      System.out.println("Oppgave 2: Du har minst " + antallFeil + " feil i inneholder-metoden!");

    return antallFeil;
  }

  public static int Oppgave5Test()
  {

    class Test implements Oppgave<Character>
    {
      private StringBuilder s = new StringBuilder();

      public void utførOppgave(Character c)
      {
        s.append(c);
      }

      public String test() { return s.toString(); }
    }

    Test oppgave = new Test();

    int antallFeil = 0;
    SBinTre<Character> tre = SBinTre.<Character>lagTre();

    tre.traverserInorden(oppgave);

    if (oppgave.test().compareTo("") != 0)
    {
      antallFeil++;
      System.out.println("Feil i traverserInorden for et tom tre!");
    }

    tre.leggInn('D'); tre.leggInn('B'); tre.leggInn('J'); tre.leggInn('A'); tre.leggInn('C');
    tre.leggInn('F'); tre.leggInn('K'); tre.leggInn('E'); tre.leggInn('H'); tre.leggInn('G');tre.leggInn('I');
    oppgave = new Test();
    tre.traverserInorden(oppgave);

    if (oppgave.test().compareTo("ABCDEFGHIJK") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metodene neste eller traverserInorden!");
    }

    tre = SBinTre.<Character>lagTre();
    tre.leggInn('E'); tre.leggInn('D'); tre.leggInn('C'); tre.leggInn('B'); tre.leggInn('A');

    oppgave = new Test();
    tre.traverserInorden(oppgave);

    if (oppgave.test().compareTo("ABCDE") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metodene neste eller traverserInorden!");
    }

    if (antallFeil == 0)
      System.out.println("Oppgave 5: Metoden traveserInorden ser ok ut!");
    else
      System.out.println("Oppgave 5: Du har minst " + antallFeil + " feil i traveserInorden!");

    return antallFeil;
  }

  public static int Oppgave6Test()
  {

    class Test implements Oppgave<Character>
    {
      private StringBuilder s = new StringBuilder();

      public void utførOppgave(Character c)
      {
        s.append(c);
      }

      public String test() { return s.toString(); }
    }

    Test oppgave = new Test();

    int antallFeil = 0;
    SBinTre<Character> tre = SBinTre.<Character>lagTre();

    tre.traverserOmvendtInorden(oppgave);

    if (oppgave.test().compareTo("") != 0)
    {
      antallFeil++;
      System.out.println("Feil i traverserInorden for et tom tre!");
    }

    tre.leggInn('D'); tre.leggInn('B'); tre.leggInn('J'); tre.leggInn('A'); tre.leggInn('C');
    tre.leggInn('F'); tre.leggInn('K'); tre.leggInn('E'); tre.leggInn('H'); tre.leggInn('G');tre.leggInn('I');
    oppgave = new Test();
    tre.traverserOmvendtInorden(oppgave);

    if (oppgave.test().compareTo("KJIHGFEDCBA") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metodene neste eller traverserInorden!");
    }

    tre = SBinTre.<Character>lagTre();
    tre.leggInn('A'); tre.leggInn('B'); tre.leggInn('C'); tre.leggInn('D'); tre.leggInn('E');

    oppgave = new Test();
    tre.traverserOmvendtInorden(oppgave);

    if (oppgave.test().compareTo("EDCBA") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metodene neste eller traverserInorden!");
    }

    if (antallFeil == 0)
      System.out.println("Oppgave 6: Metoden traveserOmvendtInorden ser ok ut!");
    else
      System.out.println("Oppgave 6: Du har minst " + antallFeil + " feil i traveseOmvendtInorden!");

    return antallFeil;
  }

  public static int Oppgave7Test()
  {
    int antallFeil = 0;

    SBinTre<Integer> tre = SBinTre.<Integer>lagTre();

    String s = tre.intervallsøk(1, 4).toString();

    if (s.compareTo("[]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden intervallsøk! Burde returnere en tom liste!");
    }

    int[] a = {4,2,10,1,3,6,11,5,8,7,9};
    for (int k : a) tre.leggInn(k);

    s = tre.intervallsøk(0, 15).toString();
    if (s.compareTo("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden intervallsøk!");
    }

    tre.leggInn(4); tre.leggInn(4); tre.leggInn(6); tre.leggInn(3); tre.leggInn(10);  tre.leggInn(13);

    s = tre.intervallsøk(4,8).toString();
    if (s.compareTo("[4, 4, 4, 5, 6, 6, 7]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden intervallsøk!!");
    }

    s = tre.intervallsøk(12,14).toString();
    if (s.compareTo("[13]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden intervallsøk!!");
    }

    if (antallFeil == 0)
      System.out.println("Oppgave 7: Metoden intervallsøk ser ok ut!");
    else
      System.out.println("Oppgave 7: Du har minst " + antallFeil + " feil i intervallsøk!");

    return antallFeil;
  }

  public static int Oppgave8Test()
  {
    int antallFeil = 0;

    SBinTre<Integer> tre = SBinTre.<Integer>lagTre();

    String s = tre.toString();
    String t = tre.omvendtString();

    if (s.compareTo("[]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden toString! Den burde returnere [] !!");
    }

    if (t.compareTo("[]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden toString! Den burde returnere [] !!");
    }

    tre.leggInn(5);
    s = tre.toString();
    t = tre.omvendtString();

    if (s.compareTo("[5]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden toString! Den burde returnere [5] !!");
    }

    if (t.compareTo("[5]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden toString! Den burde returnere [5] !!");
    }

    tre.leggInn(6); tre.leggInn(7); tre.leggInn(8); tre.leggInn(9);tre.leggInn(10);

    s = tre.toString();
    t = tre.omvendtString();

    if (s.compareTo("[5, 6, 7, 8, 9, 10]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden toString! Den burde returnere [5, 6, 7, 8, 9, 10] !!");
    }
    if (t.compareTo("[10, 9, 8, 7, 6, 5]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden toString! Den burde returnere [10, 9, 8, 7, 6, 5] !!");
    }

    tre = SBinTre.<Integer>lagTre();
    int[] a = {2,1,8,4,9,3,6,5,7};
    for (int k : a) tre.leggInn(k);

    s = tre.toString();
    t = tre.omvendtString();

    if (s.compareTo("[1, 2, 3, 4, 5, 6, 7, 8, 9]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden toString! Den burde returnere [1, 2, 3, 4, 5, 6, 7, 8, 9] !!");
    }

    if (t.compareTo("[9, 8, 7, 6, 5, 4, 3, 2, 1]") != 0)
    {
      antallFeil++;
      System.out.println("Feil i metoden toString! Den burde returnere [9, 8, 7, 6, 5, 4, 3, 2, 1] !!");
    }

    for (int i = 1000; i > 0; i--) tre.leggInn(i);

    long tid = System.currentTimeMillis();
    for (int j = 0; j < 1000; j++)
      tre.toString();
    tid = System.currentTimeMillis() - tid;
    if (tid > 1000)
      System.out.println("toString-metoden din er litt treg! Burde vært raskere!");

    if (antallFeil == 0)
      System.out.println("Oppgave 8: Metodene toString og omvendtString ser ok ut!");
    else
      System.out.println("Oppgave 8: Du har minst " + antallFeil + " feil i toString og/eller omvendtString!");

    return antallFeil;
  }

  public static int Oppgave9Test()
  {
    int antallFeil = 0;

    SBinTre<Integer> tre = SBinTre.<Integer>lagTre();

    Iterator<Integer> i = tre.iterator();
    Iterator<Integer> j = tre.omvendtiterator();

    boolean feil1 = true;
    try
    {
      i.next();
    }
    catch (NoSuchElementException e)
    {
      feil1 = false;
    }

    if (feil1)
    {
      antallFeil++;
      System.out.println("Feil i iteratoren! Kaster ikke unntak i next!");
    }

    boolean feil2 = true;
    try
    {
      j.next();
    }
    catch (NoSuchElementException e)
    {
      feil2 = false;
    }

    if (feil2)
    {
      antallFeil++;
      System.out.println("Feil i omvendtiteratoren! Kaster ikke unntak i next!");
    }

    tre.leggInn(2);
    int x = 0;

    for (int k : tre) x = k;

    if (x != 2)
    {
      antallFeil++;
      System.out.println("Feil i iteratoren når treet har kun en node!");
    }

    for (; j.hasNext(); ) x = j.next();

    if (x != 2)
    {
      antallFeil++;
      System.out.println("Feil i omvendtiteratoren når treet har kun en node!");
    }

    int[] a = {1,8,4,9,3,6,5,7};
    for (int k : a) tre.leggInn(k);

    int[] b = new int[a.length + 1];
    int n = 0;
    for (int k : tre) b[n++] = k;

    if (!Arrays.toString(b).equals("[1, 2, 3, 4, 5, 6, 7, 8, 9]"))
    {
      antallFeil++;
      System.out.println("Feil i iteratoren når treet har 9 noder!");
    }

    j = tre.omvendtiterator();
    for (n = 0; j.hasNext(); n++) b[n] = j.next();

    if (!Arrays.toString(b).equals("[9, 8, 7, 6, 5, 4, 3, 2, 1]"))
    {
      antallFeil++;
      System.out.println("Feil i iteratoren når treet har 9 noder!");
    }

    if (antallFeil == 0)
      System.out.println("Oppgave 9: Iteratorene ser ok ut!");
    else
      System.out.println("Oppgave 9: Du har minst " + antallFeil + " feil i iteratorene!");

    return antallFeil;
  }

  public static int Oppgave10Test()
  {
    int antallFeil = 0;

    SBinTre<Integer> tre = SBinTre.<Integer>lagTre();

    int[] a = {2,1,8,4,9,3,6,5,7,10,2,4,6,8,10};
    for (int k : a) tre.leggInn(k);

    for (Iterator<Integer> i = tre.iterator(); i.hasNext();)
    {
      int n = i.next(); if (n % 2 == 0) i.remove();
    }

    if (!tre.toString().equals("[1, 3, 5, 7, 9]"))
    {
      antallFeil++;
      System.out.println("Feil i iteratorens remove-metode!");
    }

    tre = SBinTre.<Integer>lagTre();

    int[] c = {2,1,8,4,9,3,6,5,7,10,2,4,6,8,10};
    for (int k : c) tre.leggInn(k);

    for (Iterator<Integer> i = tre.omvendtiterator(); i.hasNext();)
    {
      int n = i.next(); if (n % 2 == 0) i.remove();
    }

    if (!tre.omvendtString().equals("[9, 7, 5, 3, 1]"))
    {
      antallFeil++;
      System.out.println("Feil i omvendtiteratorens remove-metode!");
    }

    if (antallFeil == 0)
      System.out.println("Oppgave 10: Remove-metodene ser ok ut!");
    else
      System.out.println("Oppgave 10: Du har minst " + antallFeil + " feil i remove-metodene!");

    return antallFeil;
  }

  public static void main(String[] args)
  {
    int antallFeil = 0;
    antallFeil += Oppgave1Test();
    antallFeil += Oppgave2Test();
    antallFeil += Oppgave3Test();
    antallFeil += Oppgave5Test();
    antallFeil += Oppgave6Test();
    antallFeil += Oppgave7Test();
    antallFeil += Oppgave8Test();
    antallFeil += Oppgave9Test();
    antallFeil += Oppgave10Test();

    if (antallFeil == 0)
      System.out.println("\nDette ser bra ut!!");
    else
      System.out.println("\nDu har tilsammen minst " + antallFeil + " feil!!");
  }
} // Oblig3Test