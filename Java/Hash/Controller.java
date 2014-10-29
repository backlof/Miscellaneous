import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Controller
{
	private static final String DATA_FILENAME = "EncDTA.ser";
	
	private Scanner terminal = new Scanner(System.in);
	private EncDB lib;
	
	public Controller()
	{
		readLibrary();
	}
	
	public void menu()
	{
		System.out.print("Passord: ");
		lib.getHash(terminal.nextLine());
		
		System.out.println(lib.count + " passwords in db.");
		
		
		saveLibrary();
		
	}
	
	public void saveLibrary()
	{
		try
		{
			ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(DATA_FILENAME));
			writer.writeObject(lib);
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void readLibrary()
	{
		try
		{
			ObjectInputStream reader = new ObjectInputStream(new FileInputStream(DATA_FILENAME));
			lib = (EncDB)reader.readObject();
			reader.close();
		}
		catch(Exception e)
		{
			lib = new EncDB();
		}	
	}
}
