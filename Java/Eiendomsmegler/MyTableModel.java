import javax.swing.table.AbstractTableModel;
import java.util.Vector;
/*
 * TableModel for JTable'ene i hovedvinduet.
 * Skrevet av Alexander
 * Siste versjon 9. mai
 */
public class MyTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 10L;
	private Vector<String> columns;
	private Vector<Vector<String>> rows;
	
	//Konstruktør.
	public MyTableModel(Vector<String> kolonneNavn)
	{
		columns = kolonneNavn;
	}
	
	public int getColumnCount()
	{
		return columns.size();
	}
	
	public String getColumnName(int kolonne)
	{
		return columns.get(kolonne);
	}
	
	//Metode for å forandre innholdet i radene.
	public void setRows(Vector<Vector<String>> radData)
	{
		rows = radData;
	}
	
	public int getRowCount()
	{
		return rows.size();
	}
	
	public String getValueAt(int rad, int kolonne)
	{
		Vector<String> vector = rows.get(rad);
		return vector.get(kolonne);
	}
}
