package autoUdima.view;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.Document;
import com.toedter.calendar.JDateChooser;

public class NewCarWindow extends JDialog 
{
	private static final long serialVersionUID = 2L;
        private static final int NUM_OF_COLUMNS = 15;
	private static final int MIN_WIDTH=500;
	private static final int MIN_HEIGHT=400;
	private static final int NUM_OF_TEXT_FIELD_ELEMENTS=3;
	//Fields East position
	private static final int EAST_SPACE = 500/2 + 200;
	//Text Field index
	private static final int  DESCRIPTION_TEXT_FIELD = 0;
	private static final int FRAME_TEXT_FIELD = 1;
	private static final int  BASE_PRICE_TEXT_FIELD = 2;
	//pattern to match bastidor number
	public static final String shortFramePattern = "[0-9]{1}";
        public static final String largeFramePatter = "[0-9]{3}-[0-9]{2}-[0-9]{3}";
	private Font textFont;
	JPanel decoration;
	JTextField[] textFieldsArray;
	JDateChooser licensePlateDateChooser;
	JLabel descriptionLabel;
	JLabel typologyLabel;
	JLabel stateLabel;
	JLabel frameLabel;//bastidor
	JLabel numberPlateLabel;//matricula
	JLabel basePriceLabel;
	JButton createCarButton;
	JButton cancelButton;
	String[] typologyStringOptions;
	String[] carStateStringOptions;
	JComboBox<String> typologyComboBox;
	JComboBox<String> carStateComboBox;

	public NewCarWindow() 
	{
		this.setTitle("Nuevo Coche");
		this.setModal(true);
		initializeComponents();
		this.setResizable(false);
		this.setMinimumSize(new Dimension(MIN_WIDTH,MIN_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void initializeComponents() 
	{
		Container window = this.getContentPane();
		window.setBackground(new Color(34,35,49));
		SpringLayout mainLayout = new SpringLayout();
		window.setLayout(mainLayout);
		textFont = new Font("verdana",Font.BOLD,12);	
		textFieldsArray= new JTextField[NUM_OF_TEXT_FIELD_ELEMENTS];
		
		for(int index = 0; index < textFieldsArray.length;++index) 
		{
			textFieldsArray[index] = new JTextField(NUM_OF_COLUMNS);
			textFieldsArray[index].setFont(new Font("verdana",Font.BOLD,12));
			textFieldsArray[index].setPreferredSize(new Dimension(100,25));
			textFieldsArray[index].setBackground(Color.lightGray);
		}
		//tooltips for the textFields
		textFieldsArray[DESCRIPTION_TEXT_FIELD].setToolTipText("Maximo 10 letras");
		textFieldsArray[FRAME_TEXT_FIELD].setToolTipText("ej: 444-44-444");	
		textFieldsArray[BASE_PRICE_TEXT_FIELD].setToolTipText("ej: 10000");
		//description
		descriptionLabel = new JLabel("Introduce descripcion:");
		descriptionLabel.setForeground(Color.WHITE);
		mainLayout.putConstraint(SpringLayout.WEST, descriptionLabel, 5, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.NORTH, descriptionLabel, 40, SpringLayout.NORTH, window);
		mainLayout.putConstraint(SpringLayout.EAST, textFieldsArray[DESCRIPTION_TEXT_FIELD] , EAST_SPACE, SpringLayout.WEST,window );
		mainLayout.putConstraint(SpringLayout.WEST, textFieldsArray[DESCRIPTION_TEXT_FIELD],MIN_WIDTH/2, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.NORTH, textFieldsArray[DESCRIPTION_TEXT_FIELD], 40, SpringLayout.NORTH, window);

		//typology
		typologyLabel = new JLabel("Introduce tipologia:");
		typologyLabel.setForeground(Color.WHITE);
		typologyStringOptions = new String[] {"Deportivo","Familiar","Furgoneta"};
		typologyComboBox = new JComboBox<String>(typologyStringOptions);
                typologyComboBox.setFont(textFont);
       
		mainLayout.putConstraint(SpringLayout.NORTH,typologyLabel,20,SpringLayout.SOUTH,descriptionLabel);
		mainLayout.putConstraint(SpringLayout.NORTH,typologyComboBox, 5,SpringLayout.SOUTH, textFieldsArray[DESCRIPTION_TEXT_FIELD]);
		mainLayout.putConstraint(SpringLayout.EAST, typologyComboBox, EAST_SPACE, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.WEST, typologyLabel, 5, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.WEST,typologyComboBox,MIN_WIDTH/2 , SpringLayout.WEST, window);
		//state
		stateLabel = new JLabel("Introduce estado:");
		stateLabel.setForeground(Color.WHITE);
		carStateStringOptions = new String[] {"Nuevo","Seminuevo"};
		carStateComboBox = new JComboBox<String>(carStateStringOptions);
                carStateComboBox.setFont(textFont); 	
		mainLayout.putConstraint(SpringLayout.NORTH,stateLabel,20,SpringLayout.SOUTH,typologyLabel);
		mainLayout.putConstraint(SpringLayout.NORTH,carStateComboBox, 10,SpringLayout.SOUTH,typologyComboBox);
		mainLayout.putConstraint(SpringLayout.EAST, carStateComboBox , EAST_SPACE, SpringLayout.WEST,window );
		mainLayout.putConstraint(SpringLayout.WEST, stateLabel, 5, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.WEST,carStateComboBox,MIN_WIDTH/2, SpringLayout.WEST, window);
		//Frame number (bastidor)
		frameLabel = new JLabel("Introduce numero de bastidor:");
		frameLabel.setForeground(Color.WHITE);

		mainLayout.putConstraint(SpringLayout.NORTH,frameLabel,20,SpringLayout.SOUTH,stateLabel);
		mainLayout.putConstraint(SpringLayout.NORTH,textFieldsArray[FRAME_TEXT_FIELD], 10,SpringLayout.SOUTH,carStateComboBox);
		mainLayout.putConstraint(SpringLayout.EAST, textFieldsArray[FRAME_TEXT_FIELD] , EAST_SPACE, SpringLayout.WEST,window );
		mainLayout.putConstraint(SpringLayout.WEST, frameLabel, 5, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.WEST,textFieldsArray[FRAME_TEXT_FIELD],MIN_WIDTH/2, SpringLayout.WEST, window);
		//matriculation date
		licensePlateDateChooser = new JDateChooser("dd/MM/yyyy","##/##/####",'_');
		licensePlateDateChooser.setDateFormatString("dd/MM/yyyy");
		licensePlateDateChooser.setFont(textFont);
		DateFormat format = new SimpleDateFormat("dd/MM/yyy");

		try 
		{  
			Date minimumSelectableDate = format.parse("01/01/1950");
			licensePlateDateChooser.setMinSelectableDate(minimumSelectableDate);
		} 
		catch (ParseException e) 
		{
			System.out.println("ha habido un error, al inicializar la fecha minima selectiva posible: linea 176 clase:NewCarWindow.java");
		}
		numberPlateLabel = new JLabel("Primera fecha de matriculación:");
		numberPlateLabel.setForeground(Color.WHITE);

		mainLayout.putConstraint(SpringLayout.NORTH,numberPlateLabel,20,SpringLayout.SOUTH,frameLabel);
		mainLayout.putConstraint(SpringLayout.NORTH,licensePlateDateChooser , 10,SpringLayout.SOUTH,textFieldsArray[FRAME_TEXT_FIELD]);
		mainLayout.putConstraint(SpringLayout.EAST,licensePlateDateChooser, EAST_SPACE, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.WEST, numberPlateLabel, 5, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.WEST,licensePlateDateChooser ,MIN_WIDTH/2, SpringLayout.WEST, window);
		//base price
		basePriceLabel = new JLabel("Introduce precio base:");
		basePriceLabel.setForeground(Color.WHITE);

		mainLayout.putConstraint(SpringLayout.NORTH,basePriceLabel,20,SpringLayout.SOUTH,numberPlateLabel);
		mainLayout.putConstraint(SpringLayout.NORTH,textFieldsArray[BASE_PRICE_TEXT_FIELD], 10,SpringLayout.SOUTH,licensePlateDateChooser);
		mainLayout.putConstraint(SpringLayout.EAST, textFieldsArray[BASE_PRICE_TEXT_FIELD], EAST_SPACE, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.WEST, basePriceLabel, 5, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.WEST,textFieldsArray[BASE_PRICE_TEXT_FIELD],MIN_WIDTH/2, SpringLayout.WEST, window);
		//Button create
		createCarButton = new JButton("OK");
		mainLayout.putConstraint(SpringLayout.NORTH,createCarButton,20,SpringLayout.SOUTH,textFieldsArray[BASE_PRICE_TEXT_FIELD]);
		mainLayout.putConstraint(SpringLayout.WEST,createCarButton,(MIN_WIDTH/4) +(MIN_WIDTH/4)/4,SpringLayout.WEST,window);
		//Button cancel
		cancelButton = new JButton("Cancel");
		mainLayout.putConstraint(SpringLayout.NORTH,cancelButton,20,SpringLayout.SOUTH,textFieldsArray[BASE_PRICE_TEXT_FIELD]);
		mainLayout.putConstraint(SpringLayout.WEST,cancelButton,(MIN_WIDTH/2) ,SpringLayout.WEST,window);
		//decoration
		JPanel decoration = new JPanel();
		decoration.setSize(new Dimension(400,400));
		decoration.setBackground(new Color(228,225,223));

		mainLayout.putConstraint(SpringLayout.NORTH, decoration, 20, SpringLayout.SOUTH, basePriceLabel);
		mainLayout.putConstraint(SpringLayout.WEST, decoration, 0, SpringLayout.WEST, window);
		mainLayout.putConstraint(SpringLayout.EAST, decoration, 0, SpringLayout.EAST, window);
		mainLayout.putConstraint(SpringLayout.SOUTH, decoration, 0, SpringLayout.SOUTH, window);

		//Add to the contentPane the elements
		getContentPane().add(textFieldsArray[DESCRIPTION_TEXT_FIELD]);
		getContentPane().add(descriptionLabel);
		getContentPane().add(typologyLabel);
		getContentPane().add(typologyComboBox);
		getContentPane().add(stateLabel);
		getContentPane().add(carStateComboBox);
		getContentPane().add(frameLabel);
		getContentPane().add(textFieldsArray[FRAME_TEXT_FIELD]);
		getContentPane().add(numberPlateLabel);
		getContentPane().add(licensePlateDateChooser);
		getContentPane().add(basePriceLabel);
		getContentPane().add(textFieldsArray[BASE_PRICE_TEXT_FIELD]);
		getContentPane().add(createCarButton);
		getContentPane().add(cancelButton);
		getContentPane().add(decoration);
	}

	public String getTypology() 
	{
		return typologyComboBox.getSelectedItem().toString();
	}
	
	public String getCarState()
	{
		return carStateComboBox.getSelectedItem().toString();
	}

	public void resetTextFields() 
	{
		for(JTextField i : textFieldsArray)
		{
			if(!(i.getText().isEmpty()))
			{
				i.setText("");
			}
		}
	}
	
	public void resetDescriptionTextfield() 
	{
		textFieldsArray[DESCRIPTION_TEXT_FIELD].setText("");
	}

	public void resetFrameTextfield() 
	{
		textFieldsArray[FRAME_TEXT_FIELD].setText("");
	}

	public void resetBasePriceTextField() 
	{
		textFieldsArray[BASE_PRICE_TEXT_FIELD].setText("");
	}

	public Document getDescriptionDocument()
	{
		return textFieldsArray[DESCRIPTION_TEXT_FIELD].getDocument();
	}

	public Document getFrameNumberDocument() 
	{
		return textFieldsArray[FRAME_TEXT_FIELD].getDocument();
	}

	public Document getBasePriceDocument() 
	{
		return textFieldsArray[BASE_PRICE_TEXT_FIELD].getDocument();
	}

	public JTextField getDescriptionTextField() 
	{
		return textFieldsArray[DESCRIPTION_TEXT_FIELD];
	}
	
	public JTextField getFrameNumberTextField() 
	{
		return textFieldsArray[FRAME_TEXT_FIELD];
	}

	public JTextField getBasePriceTextField() 
	{
		return textFieldsArray[BASE_PRICE_TEXT_FIELD];
	}

	public JButton getCreateCarButton()
	{
		return createCarButton;
	}

	public JButton getCancelButton() 
	{
		return cancelButton;
	}
	
	public String getFrameNumber() 
	{
		return getFrameNumberTextField().getText();
	}

	public String getDescription()
	{
		return getDescriptionTextField().getText();
	}
	
	public String getBasePrice()
	{
		return getBasePriceTextField().getText();
	}
	
	public String getMatriculationDate()
	{
		  String date;
		  DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	
		  if(licensePlateDateChooser.getDate() == null) 
		  {
		       date = null;

		   }
		   else 
		   {
		       date = dateformat.format(licensePlateDateChooser.getDate());
		   }
		   return date;
	}
	
	public void setFrameNumberTextFieldFontColor(Color color) {
		textFieldsArray[FRAME_TEXT_FIELD].setForeground(color);
	}

	public void setFrameNumberTextFieldColor(Color color) {
		textFieldsArray[FRAME_TEXT_FIELD].setBackground(color);
	}

	public void setDescriptionTextFieldFontColor(Color color) {
		textFieldsArray[DESCRIPTION_TEXT_FIELD].setForeground(color);
	}

	public void setDescriptionTextFieldColor(Color color) {
		textFieldsArray[DESCRIPTION_TEXT_FIELD].setBackground(color);
	}

	public void setBasePriceTextFieldFontColor(Color color) {
		textFieldsArray[BASE_PRICE_TEXT_FIELD].setForeground(color);
	}

	public void setBasePriceTextFieldColor(Color color) {
		textFieldsArray[BASE_PRICE_TEXT_FIELD].setBackground(color);
	}
}
