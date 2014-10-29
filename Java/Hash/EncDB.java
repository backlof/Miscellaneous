import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class EncDB implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, String[]> db = new HashMap<String, String[]>();
	
	private static final String[] ENC_METHODS = {"MD2","MD5","SHA1","SHA-256","SHA-384","SHA-512"};
	
	public int count = 0;
	
	public EncDB()
	{
	}
	
	public void getHash(String input)
	{
		System.out.println();
		for(int x = 0; x < ENC_METHODS.length; x++)
		{
			String output = "";
			
			try
			{
				String hex = "";
				
				MessageDigest md = MessageDigest.getInstance(ENC_METHODS[x]);
				
				md.reset();
				md.update(input.getBytes("UTF-8"));
				
				byte[] digest = md.digest();
				
				
				
				for(int i = 0; i < digest.length; i++)
				{
					output +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
				}
				
				addHash(output, input);
			}
			catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
			{
				output = "No such method.";
			}
			
			System.out.printf("%-10s" + output + "\n", ENC_METHODS[x]);
		}
		System.out.println("\nSaved in database.\n");
	}
	
	public void addHash(String hash, String password)
	{
		if(db.containsKey(hash))
		{
			String[] temp = db.get(hash);
			boolean found = false;
			
			for(int i = 0; i < temp.length; i++)
			{
				if(temp[i].equals(password))
				{
					found = true;
				}
			}
			
			if(!found)
			{
				expandArray(temp);
				temp[temp.length - 1] = password;
				count++;
			}
		}
		else
		{
			String[] newArray = {password};
			db.put(hash, newArray);
			count++;
		}
	}
	
	private void expandArray(String[] array)
	{
		String[] temp = new String[array.length + 1];
		
		for(int i = 0; i < array.length; i++)
		{
			temp[i] = array[i];
		}
		
		array = temp;
	}
	
	public void write()
	{
		//må gjøre output penere (kutte av lengden)
		for(Map.Entry<String, String[]> entry : db.entrySet())
		{
			System.out.println(String.format("%-40s ", entry.getKey()) + Arrays.toString((String[])entry.getValue()) );  
		}
	}
}
