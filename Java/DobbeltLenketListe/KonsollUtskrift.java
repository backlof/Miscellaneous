public class KonsollUtskrift<T> implements Oppgave<T>
{
    public void utførOppgave(T t)
    {
      System.out.print(t + " ");
    }
}