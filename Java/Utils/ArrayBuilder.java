import java.util.NoSuchElementException;

/**
 * @author Alexander
 * @version 0.1
 *
 */
public class ArrayBuilder
{
	private static final int INITIAL_SIZE = 64;
	
	public static final int APPEND_AT_THE_END = 0;
	public static final int APPEND_AT_THE_BEGINNING = 1;
	
	private int insertedObjects = 0;
	
	private Object[] array;
	
	public ArrayBuilder()
	{
		array = null;
	}
	
	private int lastIndex()
	{
		return (insertedObjects - 1);
	}
	
	private boolean isInitialized()
	{
		return array != null;
	}
	
	public void append(Object objectToInsert, int whereToAppend)
	{
		if(!isInitialized())
		{
			array = new Object[INITIAL_SIZE];
		}
		else if(insertedObjects == array.length)
		{
			expandArray();
		}
		if(whereToAppend == APPEND_AT_THE_BEGINNING)
		{
			for(int i = lastIndex(); i > 0; i--)
			{
				array[i + 1] = array[i];
			}
			array[0] = objectToInsert;
			insertedObjects++;
		}
		else
		{
			array[lastIndex() + 1] = objectToInsert;
			insertedObjects++;
		}
	}
	
	public boolean contains(Object objectToFind)
	{
		if(isInitialized())
		{
			for(int i = 0; i < insertedObjects; i++)
			{
				if(objectToFind.equals(array[i])){ return true; }
			}
		}
		
		return false;
	}
	
	public int findIndex(Object objectToFind)
	{
		if(isInitialized())
		{
			for(int i = 0; i < insertedObjects; i++)
			{
				if(objectToFind.equals(array[i])){ return i; }
			}
		}
		throw new NoSuchElementException("There is no such element in the array.");
	}
	
	private void expandArray()
	{
		Object[] temporary = new Object[(array.length * 2)];
		
		for(int i = 0; i < insertedObjects; i++)
		{
			temporary[i] = array[i];
		}
		
		array = temporary;
	}
	
	public Object[] toArray()
	{
		if(isInitialized())
		{
			Object[] temporary = new Object[insertedObjects];
			
			for(int i = 0; i < insertedObjects; i++)
			{
				temporary[i] = array[i];
			}
			
			return temporary;
		}
		else
		{
			throw new NoSuchElementException("Array has not been initialized or given a type.");
		}
	}
}
