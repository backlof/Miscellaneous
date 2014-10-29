import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//Main-method class
public class Main
{
	public static void main(String[] args)
	{
		try
		{
			 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(UnsupportedLookAndFeelException ulafe)
		{
		}
		catch(ClassNotFoundException cnfe)
		{
		}
		catch(InstantiationException ie)
		{
		}
		catch(IllegalAccessException iae)
		{
		}

		Window window = new Window();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
