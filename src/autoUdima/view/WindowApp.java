package autoUdima.view;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;


/**
 * CLass WindowApp this class its the main window for the application.<br>
 * @author aitorSf
 *
 */
public class WindowApp extends JFrame{

	//Serial ID
	private static final long serialVersionUID = 1L;
	//minimum width and height
	private static final int MIN_WIDTH=800;
	private static final int MIN_HEIGHT=400;

	//Number of Menu Items and subitmes
	private static final int NUM_MENU_ITEMS = 3;
	private static final int NUM_SUBMENU_ITEMS = 5;
	// Main menu items
	private static final int AUTOUDIMA = 0;
	private static final int FILE = 1;
	private static final int HELP = 2;

	//Main menu subItems
	private static final int ADD_NEW_CAR = 0;
	private static final int SEE_STOCK = 1;
	private static final int DELETE_CAR = 2;
	private static final int EXIT = 3;
	private static final int ABOUT = 4;

	//Components
	private JPanel imagenEmpresaPanel;
	private JPanel panelComponentes;

	//Buttons
	private JButton addCarButton;
	private JButton removeCarButton;
	private JButton showStockButton;

	//image icons for the buttons
	private ImageIcon iconCar;
	private ImageIcon iconRemove;
	private ImageIcon iconStock;
	private ImageIcon iconLogo;
	// labels 
	JLabel inicialMessageLabel;

	//menu bar
	private JMenuBar menuBar;
	private JMenu [] menuItems;
	private JMenuItem []menuSubItems;

	//Tool bar
	private JToolBar toolBar;
	


	/**************
	 * CONSTRUCTOR*
	 **************/
	public WindowApp() {
		super("AutoUdima 2.0");


		initializeComponents();

		this.setIconImage(createImage("Resources/autocon2.png"));
		this.setResizable(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(MIN_WIDTH,MIN_HEIGHT));
		this.pack();//this method has to be call after the dimensions, to center the JFRAME
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
	}

	/***********************************
	 *     METHODS                     *
	 ***********************************/
/**
 * Create an ImageIcon an return it Back
 */
	private Image createImage(String path) {
		ImageIcon temp =  new ImageIcon(path);
		Image tempImage = temp.getImage();//.getScaledInstance(20,22,  java.awt.Image.SCALE_SMOOTH);
		return tempImage;
	}
	
	/**
	 * Initialize all the components on the frame
	 */
	private void initializeComponents()  {

		//Set the layout for the main Frame
		getContentPane().setLayout(new BorderLayout() );


		// configuration of the menu bar
		menuBar = new JMenuBar();

		menuItems = new JMenu[NUM_MENU_ITEMS] ;
		menuItems[AUTOUDIMA]= new JMenu("AutoUdima");
		menuItems[FILE]= new JMenu("File");
		menuItems[HELP]=new JMenu("Ayuda");

		menuSubItems = new  JMenuItem[NUM_SUBMENU_ITEMS];
		menuSubItems[ADD_NEW_CAR] = new  JMenuItem("Añadir coche");
		menuSubItems[SEE_STOCK] = new JMenuItem("Mirar stock");
		menuSubItems[DELETE_CAR] = new JMenuItem("Elminiar coche");
		menuSubItems[EXIT] = new JMenuItem("Salir");
		menuSubItems[ABOUT] = new JMenuItem("About AutoUdima");





		menuItems[FILE].add(menuSubItems[ADD_NEW_CAR]);
		menuItems[FILE].add(menuSubItems[SEE_STOCK]);
		menuItems[FILE].add(menuSubItems[DELETE_CAR]);
		menuItems[AUTOUDIMA].add(menuSubItems[ABOUT]);
		menuItems[AUTOUDIMA].add(menuSubItems[EXIT]);

		for ( JMenu m : menuItems) {  	
			menuBar.add(m);  
		}


		//Tool bar configuration

		toolBar = new JToolBar();
		toolBar.setFloatable(false);

		//Enterprise panel icon configuration
		imagenEmpresaPanel = new JPanel();
		imagenEmpresaPanel.setLayout(new FlowLayout());
		imagenEmpresaPanel.setBorder(new LineBorder(new Color(228,225,223),14));
		imagenEmpresaPanel.setBackground(new Color(228,225,223));

		//Add the image background to the enterprise icon panel
		try {
			imagenEmpresaPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("Resources/AutoUdima.png")) )));

		} catch (IOException e) {
			System.out.println("image doesn't exist");
		}

		//data base access message
		inicialMessageLabel = new JLabel();
		inicialMessageLabel.setFont(new Font("Heveltica",Font.PLAIN,18));
		imagenEmpresaPanel.add(inicialMessageLabel);

		//Components panel, where the buttons goes
		panelComponentes = new JPanel();
		panelComponentes.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		panelComponentes.setBackground(new Color(34,35,49));
		panelComponentes.setPreferredSize(new Dimension(MIN_WIDTH/4, MIN_HEIGHT));
		//Components panel, where the buttons goes
		JPanel panelComponentesDos = new JPanel();
		panelComponentesDos.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		panelComponentesDos.setBackground(new Color(34,35,49));

		panelComponentesDos.setPreferredSize(new Dimension(MIN_WIDTH/4, MIN_HEIGHT));

		//--------------------------------//
		//Add car button configuration
		//--------------------------------//
		addCarButton = new JButton();
		iconCar = new ImageIcon("Resources/addCarrButton.png");
		Image imagen = iconCar.getImage();
		Image newimg = imagen.getScaledInstance(30,32,  java.awt.Image.SCALE_SMOOTH);
		iconCar = new ImageIcon(newimg);
		addCarButton.setIcon(iconCar);



		//Add car button text font
		addCarButton.setText("add Car");
		addCarButton.setFont(new Font("Heveltica",Font.BOLD,14));
		//	//Add car button text color
		addCarButton.setForeground(new Color(34,35,49));
		//--------------------------------//
		//Remove car button configuration
		//--------------------------------//
		iconRemove = new ImageIcon("Resources/removeCar.png");
		Image img= iconRemove.getImage();
		Image nImg = img.getScaledInstance(30,32,java.awt.Image.SCALE_SMOOTH);
		iconRemove = new ImageIcon(nImg);

		// Remove car button text font
		removeCarButton = new JButton("remove Car",iconRemove);
		removeCarButton.setText("remove");
		removeCarButton.setFont(new Font("Heveltica",Font.BOLD,14));

		// Remove car button text color
		removeCarButton.setForeground(new Color(34,35,49));
		removeCarButton.setIcon(iconRemove);


		//--------------------------------//
		// Show stock button configuration
		//--------------------------------//

		showStockButton = new JButton();
		iconStock = new ImageIcon("Resources/listIcon.png");
		Image imgS= iconStock.getImage();
		Image nImgS = imgS.getScaledInstance(35,32, java.awt.Image.SCALE_SMOOTH);
		iconStock = new ImageIcon(nImgS);
		showStockButton.setIcon(iconStock);
		//Show stock button text font
		showStockButton.setText("show stock");
		showStockButton.setFont(new Font("Heveltica",Font.BOLD,14));
		//Show stock button text color
		showStockButton.setForeground(new Color(34,35,49));

		//add icons
		iconLogo = new ImageIcon("Resources/mainIcon.png");
		Image logoImg= iconLogo.getImage();
		Image logo = logoImg.getScaledInstance(35,32, java.awt.Image.SCALE_SMOOTH);
		iconLogo = new ImageIcon(logo);
		
		menuSubItems[ADD_NEW_CAR].setIcon(iconCar); 
		menuSubItems[SEE_STOCK].setIcon(iconStock);
		menuSubItems[DELETE_CAR].setIcon(iconRemove);
		menuSubItems[ABOUT].setIcon(iconLogo);

		//Add the components to the frame
		toolBar.add(addCarButton);
		toolBar.add(removeCarButton);
		toolBar.add(showStockButton);
		getContentPane().add(imagenEmpresaPanel,BorderLayout.CENTER);
		getContentPane().add(panelComponentes,BorderLayout.LINE_START);
		getContentPane().add(panelComponentesDos,BorderLayout.LINE_END);
		getContentPane().add(toolBar,BorderLayout.SOUTH);
		getContentPane().add(menuBar,BorderLayout.NORTH);

	}


	//Buttons and label use by the controller to add listeners


	/**
	 * Getter for the menu subItem add car
	 * @return JMenuItem
	 */
	public JMenuItem getAddCarMenuItem() {
		return menuSubItems[ADD_NEW_CAR];
	}

	/**
	 * Getter for the menu subItem delete car
	 * @return JMenuitem
	 */
	public JMenuItem getRemoveCarMenuItem() {
		return menuSubItems[DELETE_CAR];
	}
	/**
	 * Getter for the menu subItem show stock of cars
	 * @return JMenuItem
	 */
	public JMenuItem getShowStockMenuItem() {
		return menuSubItems[SEE_STOCK];
	}

	/**
	 * Getter for the add car button
	 * @return JButton 
	 */
	public JButton getAddCarButton() {
		return addCarButton;
	}

	/**
	 * Getter for the remove car button
	 * @return JButton
	 */
	public JButton getRemoveCarButton() {
		return removeCarButton;
	}

	/**
	 * Getter for the show stock button
	 * @return JButton
	 */
	public JButton getShowStockButton() {
		return showStockButton;
	}

	/**
	 * Getter for the Exit subMenuitem
	 * @return JMenuItem Exit
	 */
	public JMenuItem getExitSubItem() {
		return menuSubItems[EXIT];
	}

	/**
	 * Getter for the About subMenuitem
	 * @return JMenuItem about
	 */
	public JMenuItem getAboutSubItem() {
		return menuSubItems[ABOUT];
	}




	/**
	 * Get the label that will display the intial message
	 * @return JLabel incialMessage
	 */
	public JLabel getInicialMessageLabel() {
		return inicialMessageLabel;
	}

	/**
	 * Set the value of the initial message
	 * @param mensaje
	 */
	public void setInicialMessage(String mensaje) {
		this.inicialMessageLabel.setText(mensaje);
	}


	/// prueba






}
