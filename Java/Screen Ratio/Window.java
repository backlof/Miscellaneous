import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Window class
public class Window extends JFrame
{
	private JPanel mainPanel, northPanel, southPanel, centerPanel;
	private JPanel sizePanel, sizeJTextFieldPanel, sizeJComboBoxPanel;
	private JPanel ratioPanel, ratioWidthJTextFieldPanel, ratioJLabelPanel, ratioHeightJTextFieldPanel;
	private JTextField sizeJTextField, ratioWidthJTextField, ratioHeightJTextField, results;
	private JLabel ratioJLabel;
	private JComboBox sizeJComboBox;
	private JButton calculateJButton;
	private final String[] COMBO = {"Width", "Height"};
	private final Border etched = BorderFactory.createEtchedBorder();
	private Listener listener;
	private final int windowWidth = 256;
	private final int windowHeight = 190;
	
	public Window()
	{
		super("resCalc v1.2");
		listener = new Listener();
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));
		initializeComponents();
		setBorders();
		c.add(mainPanel);
		calculateJButton.addActionListener(listener);
		
		setSize(windowWidth, windowHeight);
		setLocationRelativeTo(null);
		setResizable(false);
		sizeJComboBox.setSelectedIndex(1);
		sizeJTextField.setText("1080");
		setVisible(true);
	}
	
	private void initializeComponents()
	{
		mainPanel = new JPanel(new BorderLayout(5,5));
			northPanel = new JPanel(new BorderLayout(0,5));
			mainPanel.add(northPanel, BorderLayout.NORTH);
				sizePanel = new JPanel(new BorderLayout(0,0));
				northPanel.add(sizePanel, BorderLayout.WEST);
					sizeJTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
					sizePanel.add(sizeJTextFieldPanel, BorderLayout.WEST);
						sizeJTextField = new JTextField(4);
						sizeJTextField.setHorizontalAlignment(JTextField.CENTER);
						sizeJTextFieldPanel.add(sizeJTextField);
					sizeJComboBoxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
					sizePanel.add(sizeJComboBoxPanel, BorderLayout.CENTER);
						sizeJComboBox = new JComboBox(COMBO);
						sizeJComboBoxPanel.add(sizeJComboBox);
				ratioPanel = new JPanel(new BorderLayout(0,0));
				northPanel.add(ratioPanel, BorderLayout.CENTER);
					ratioWidthJTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
					ratioPanel.add(ratioWidthJTextFieldPanel, BorderLayout.WEST);
						ratioWidthJTextField = new JTextField(4);
						ratioWidthJTextField.setHorizontalAlignment(JTextField.CENTER);
						ratioWidthJTextFieldPanel.add(ratioWidthJTextField);
					ratioJLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,2,9));
					ratioPanel.add(ratioJLabelPanel, BorderLayout.CENTER);
						ratioJLabel = new JLabel("/");
						ratioJLabelPanel.add(ratioJLabel);
					ratioHeightJTextFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
					ratioPanel.add(ratioHeightJTextFieldPanel, BorderLayout.EAST);
						ratioHeightJTextField = new JTextField(4);
						ratioHeightJTextField.setHorizontalAlignment(JTextField.CENTER);
						ratioHeightJTextFieldPanel.add(ratioHeightJTextField);
			centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
			mainPanel.add(centerPanel, BorderLayout.CENTER);
				results = new JTextField(13);
				results.setHorizontalAlignment(JTextField.CENTER);
				results.setEditable(false);
				centerPanel.add(results);
			southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
			mainPanel.add(southPanel, BorderLayout.SOUTH);
				calculateJButton = new JButton("Calculate");
				southPanel.add(calculateJButton);
	}
	
	private void setBorders()
	{
		Border sizeBorder = BorderFactory.createTitledBorder(etched, "Size");
		sizePanel.setBorder(sizeBorder);
		Border ratioBorder = BorderFactory.createTitledBorder(etched, "Aspect ratio");
		ratioPanel.setBorder(ratioBorder);
		Border resultsBorder = BorderFactory.createTitledBorder(etched, "Resolution");
		centerPanel.setBorder(resultsBorder);
	}
	
	//What the program does when you click the calculate button
	private void calculate()
	{
		Screensize screensize;
		int size = 0;
		double ratioWidth = 0;
		double ratioHeight = 0;
		boolean correctlyFilledIn = true;//this remains true if everything is filled out correctly
		
		/*
		 * Tries to convert the text from size-field to numbers, but will throw numberformat exceptions
		 * If you write a letter, it will that way avoid an exception which would shut the program down
		 */
		try
		{
			size = Integer.parseInt(sizeJTextField.getText());
			//Checks if size = 0
			if(size == 0)
			{
				sizeJTextField.setText("");
				correctlyFilledIn = false;
			}
		}
		catch(NumberFormatException nfe)
		{
			sizeJTextField.setText("");//It will blank out the field to let the user know it was incorrectly filled in
			correctlyFilledIn = false;
		}
		
		try
		{
			ratioWidth = Double.parseDouble(ratioWidthJTextField.getText());
			if(ratioWidth == 0)
			{
				ratioWidthJTextField.setText("");
				correctlyFilledIn = false;
			}
		}
		catch(NumberFormatException nfe)
		{
			ratioWidthJTextField.setText("");
			correctlyFilledIn = false;
		}
		
		try
		{
			ratioHeight = Double.parseDouble(ratioHeightJTextField.getText());
			if(ratioWidth == 0)
			{
				ratioHeightJTextField.setText("");
				correctlyFilledIn = false;
			}
		}
		catch(NumberFormatException nfe)
		{
			ratioHeightJTextField.setText("");
			correctlyFilledIn = false;
		}
		
		//Can continue if everything was filled in correctly
		if(correctlyFilledIn)
		{
			if(sizeJComboBox.getSelectedIndex() == 0)
				screensize = new Screensize(size, 0, ratioWidth, ratioHeight);
			else
				screensize = new Screensize(size, 1, ratioWidth, ratioHeight);
			
			results.setText(screensize.toString());
		}
	}
	
	//Private class that listens after a click on calculateJButton
	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == calculateJButton)
			{
				calculate();
			}
		}
	}
}
