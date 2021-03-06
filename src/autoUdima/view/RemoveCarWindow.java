package autoUdima.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.Document;

public class RemoveCarWindow extends JDialog
{
    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 300; 
    //frame number pattern
    private static final String idPattern = "[0-9]{1}";
    private JTextField searchAndRemoveTextField;
    private JLabel carIdLabel;
    private JButton cancelOperationButton,confirmOperationButton;
    private SpringLayout springLayout;
    private JPanel decorationPanel;
    
    public RemoveCarWindow()
    {
		this.springLayout = new SpringLayout();
		this.setTitle("Borrar Coche");
		this.setModal(true);
		this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		this.setResizable(false);
		initializeComponents();
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void initializeComponents() 
	{
	    Container windowContentPane = this.getContentPane();
	    windowContentPane.setBackground(new Color(228,225,223));
	    windowContentPane.setLayout(springLayout);
	    decorationPanel = new JPanel();
	    decorationPanel.setBackground(new Color(34,35,49));
	    springLayout.putConstraint(SpringLayout.NORTH, decorationPanel,0,SpringLayout.NORTH, windowContentPane);
	    springLayout.putConstraint(SpringLayout.WEST, decorationPanel,0,SpringLayout.WEST, windowContentPane);
	    springLayout.putConstraint(SpringLayout.EAST, decorationPanel,0,SpringLayout.EAST, windowContentPane);
	    springLayout.putConstraint(SpringLayout.SOUTH, decorationPanel,HEIGHT/2,SpringLayout.NORTH, windowContentPane);
	    carIdLabel = new JLabel("Introduce Identificador :");
	    carIdLabel.setForeground(new Color(228,225,223));
	    springLayout.putConstraint(SpringLayout.NORTH, carIdLabel,HEIGHT/4,SpringLayout.NORTH, windowContentPane);
	    springLayout.putConstraint(SpringLayout.WEST, carIdLabel,WIDTH/10,SpringLayout.WEST, windowContentPane);
	    //JTextfield
	    searchAndRemoveTextField = new JTextField(20);
	    springLayout.putConstraint(SpringLayout.NORTH,  searchAndRemoveTextField,HEIGHT/4-4,SpringLayout.NORTH, windowContentPane);
	    springLayout.putConstraint(SpringLayout.WEST,  searchAndRemoveTextField,5,SpringLayout.EAST, carIdLabel);
	    //JButtons
	    confirmOperationButton = new JButton("Borrar");
	    springLayout.putConstraint(SpringLayout.NORTH, confirmOperationButton,10,SpringLayout.SOUTH, decorationPanel);
	    springLayout.putConstraint(SpringLayout.WEST, confirmOperationButton,WIDTH/3,SpringLayout.WEST, windowContentPane);
	    cancelOperationButton = new JButton("Salir");
	    springLayout.putConstraint(SpringLayout.NORTH, cancelOperationButton,10,SpringLayout.SOUTH, decorationPanel);
	    springLayout.putConstraint(SpringLayout.WEST, cancelOperationButton,5,SpringLayout.EAST, confirmOperationButton);
	    
	    this.getContentPane().add(carIdLabel);
	    this.getContentPane().add(searchAndRemoveTextField);
	    this.getContentPane().add(confirmOperationButton);
	    this.getContentPane().add(cancelOperationButton);
	    this.getContentPane().add(decorationPanel);
	}
	
	public void resetSearchAndRemoveTextField() 
	{
		 searchAndRemoveTextField.setText("");
	}

	public JTextField getSearchAndRemoveTextField() 
	{
		return searchAndRemoveTextField;
	}

	public JButton getCancelOperationButton()
	{
		return cancelOperationButton;
	}

	public JButton getConfirmOperationButton() 
	{
		return confirmOperationButton;
	}

	public String getCarId() 
	{
		return  searchAndRemoveTextField.getText();
	}

	public Document getSearchAndRemoveDocument() 
	{
		return searchAndRemoveTextField.getDocument();
	}

	public String getIdPattern()
	{
		return idPattern;
	}

	public void setTextFieldColor(Color color) 
	{
		searchAndRemoveTextField.setBackground(color);
	}
}
