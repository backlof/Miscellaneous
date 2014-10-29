import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.servicetag.UnauthorizedAccessException;


public class Studio
{
	private static final String NO_TENANT = "(LEDIG)";
	private static final int DEPOSIT = 15000;
	private static final int EVICTION_FEE = 3000;
	
	private static final String TORPEDO_FILENAME = "torpedo.txt";
	
	public static int rentNormal = 7500;
	public static int rentTopFloor = 9000;
	
	private int floor;
	private char room;
	
	private String tenant = NO_TENANT;
	private int balance = 0;
	
	public Studio(int floor, char room)
	{
		this.floor = floor;
		this.room = room;
	}

	public String getName()
	{
		return "" + floor + room;
	}
	
	public boolean isAvaliable()
	{
		return (tenant.equals(NO_TENANT));
	}
	
	public int rent()
	{
		if(floor == 3){	return rentTopFloor; }
		else{ return rentNormal; }
	}
	
	public String lineToWrite()
	{
		return floor + ";" + room + ";" + balance + ";" + tenant + ";";
	}
	
	public Studio(String[] lineFromFile)
	{
		floor = Integer.parseInt(lineFromFile[0]);
		room = lineFromFile[1].charAt(0);
		balance = Integer.parseInt(lineFromFile[2]);
		tenant = lineFromFile[3];
	}
	
	public void print()
	{
		System.out.printf(Housing.TABLE_FORMAT, getName(), tenant, "" + balance);
	}
	
	public void addTenant(String tenantName)
	{
		if(isAvaliable())
		{
			tenant = tenantName;
			balance = DEPOSIT;
			payRent();
		}
		else
		{
			throw new UnauthorizedAccessException("Denne hybelen har allerede en leietaker");
		}	
	}
	
	public void payRent()
	{
		if(!tenant.equals(NO_TENANT))
		{
			if(balance < 0)
			{
				balance -= rent();
			}
			else if(balance < rent())
			{
				Housing.earnings += rent() - (rent() - balance);
				balance -= rent();
			}
			else
			{
				balance -= rent();
				Housing.earnings += rent();
			}
		}
	}
	
	public void addPayment(int payment)
	{
		balance += payment;
	}
	
	public boolean isTenant(String name)
	{
		return (name.toLowerCase().equals(tenant.toLowerCase()));
	}
	
	public boolean canMoveOut()
	{
		return (balance >= rent());
	}
	
	public void moveOut()
	{
		payRent();
		
		System.out.println("/n" + tenant + " har flyttet ut, og fått igjen resterende saldo: " + balance + "\n");
		balance = 0;
		
		tenant = NO_TENANT;
	}
	
	public int claim()
	{
		if(balance < (0 - rent()))
		{
			return (0 - balance) + EVICTION_FEE;
		}
		else{ return 0; }
	}

	public void checkForEviction()
	{
		if(balance < (0 - rent()))
		{
			try
			{
				FileWriter writeToFile = new FileWriter(new File(TORPEDO_FILENAME), true);
				writeToFile.write(floor + ";" + room + ";" + tenant + ";" + claim() + ";" + System.getProperty("line.separator"));
				writeToFile.close();
				
				Housing.earnings += claim() - (EVICTION_FEE/2);
				
				System.out.println("\n" + tenant + " har blitt kastet ut fra " + getName() + ", betalt leie for " + (claim() - 3000) + " (+gebyr)." );
				
				tenant = NO_TENANT;
				balance = 0;
			}
			catch(IOException e)
			{
				System.out.println("\nKan ikke skrive til fil:\n");
				e.printStackTrace();
			}
		}
	}
}
